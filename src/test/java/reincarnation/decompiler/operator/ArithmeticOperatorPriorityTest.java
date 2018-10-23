/*
 * Copyright (C) 2016 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package reincarnation.decompiler.operator;

import org.junit.jupiter.api.Test;

import reincarnation.Code;
import reincarnation.CodeVerifier;

/**
 * @version 2018/10/22 17:01:57
 */
class ArithmeticOperatorPriorityTest extends CodeVerifier {

    @Test
    void shiftWithAdd() {
        verify(new Code.Run() {

            @Override
            public void run() {
                int value = 128;
                int base = 2;
                assert value >> base + 1 == 16;
                assert (value >> base) + 1 == 33;
            }
        });
    }
}