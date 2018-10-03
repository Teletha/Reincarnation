/*
 * Copyright (C) 2018 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package reincarnation;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import org.junit.platform.commons.util.ReflectionUtils;

import bee.UserInterface;
import bee.util.JavaCompiler;
import kiss.I;
import marionette.browser.Browser;

/**
 * @version 2018/10/03 18:17:03
 */
public class CodeVerifier {

    /** Javascript runtime. */
    protected static final Browser browser = Browser.build(pref -> {
        pref.headless = true;
    });

    /**
     * Verify AST of the specified {@link CodeInt}.
     * 
     * @param code
     */
    protected final void verify(Code code) {
        try {
            String decompiled = Reincarnation.exhume(code.getClass()).toString();

            JavaCompiler compiler = new JavaCompiler(UserInterface.CLI);
            compiler.addSource(code.getSimpleName(), decompiled);
            compiler.addCurrentClassPath();

            ClassLoader loader = compiler.compile();
            Class<?> loadedClass = loader.loadClass(code.getName());
            assert loadedClass != code.getClass(); // load from different class loaders

            // instantiate
            Code loadedInstance = (Code) ReflectionUtils.newInstance(loadedClass);

            for (Class type : Code.class.getClasses()) {
                if (validate(type, code, loadedInstance)) {
                    return;
                }
            }
            throw new AssertionError("Unknown Type");
        } catch (Exception e) {
            throw I.quiet(e);
        }
    }

    private boolean validate(Class type, Code original, Code decompiled) {
        if (type.isInstance(original)) {
            assert type.isInstance(decompiled);

            return true;
        }
        return false;
    }

    private Executions executeJavaCode(Code code) {
        Executions executions = new Executions();

        ReflectionUtils.findMethod(code.getClass(), "run").ifPresent(method -> {
            // runner needs return value
            Class<?> returnType = method.getReturnType();

            if (returnType == void.class || returnType == Void.class) {
                throw new AssertionError(CodeVerifier.class.getSimpleName() + " requires the method which returns non-void type.");
            }

            Parameter[] parameters = method.getParameters();

            if (parameters.length == 0) {
                // no parameter, execute simply
                executions.output.add(ReflectionUtils.invokeMethod(method, code, executions.params()));
            }
        });
        return executions;
    }

    /**
     * @version 2018/04/04 17:11:58
     */
    private static class Executions {

        /** The input container. */
        private final List<Object[]> input = new ArrayList();

        /** The output container. */
        private final List<Object> output = new ArrayList();

        /**
         * Record inputs.
         * 
         * @param params
         * @return
         */
        private Object[] params(Object... params) {
            this.input.add(params);

            return params;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return output.toString();
        }
    }
}
