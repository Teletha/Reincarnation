/*
 * Copyright (C) 2018 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package reincarnation.operator;

/**
 * @version 2018/10/22 19:14:23
 */
public enum AssignOperator {
    /** = */
    ASSIGN("="),

    /** &= */
    AND("&="),

    /** |= */
    OR("|="),

    /** ^= */
    XOR("^="),

    /** += */
    PLUS("+="),

    /** -= */
    MINUS("-="),

    /** *= */
    MULTIPLY("*="),

    /** \/= */
    DIVIDE("/="),

    /** %= */
    REMAINDER("%="),

    /** <<= */
    LEFT_SHIFT("<<="),

    /** >>= */
    RIGHT_SHIFT(">>="),

    /** >>>= */
    UNSIGNED_RIGHT_SHIFT(">>>=");

    /** The operator value. */
    private final String operator;

    /**
     * Hide constructor.
     * 
     * @param operator An operator word.
     */
    private AssignOperator(String operator) {
        this.operator = operator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return operator;
    }
}