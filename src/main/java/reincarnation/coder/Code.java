/*
 * Copyright (C) 2020 Reincarnation Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package reincarnation.coder;

import java.util.Optional;

import kiss.I;
import kiss.Signal;

/**
 * @version 2018/10/26 22:51:00
 */
public interface Code<C extends Code> {

    /** The empty code. */
    Code Empty = coder -> {
    };

    /**
     * Write source code.
     * 
     * @param coder
     */
    void write(Coder coder);

    /**
     * Comment for {@link Code}.
     * 
     * @return
     */
    default Optional<String> comment() {
        return Optional.empty();
    }

    /**
     * Check this code is empty or not.
     * 
     * @return A result.
     */
    default boolean isEmpty() {
        return false;
    }

    /**
     * Find all children {@link Code} fragments.
     * 
     * @return
     */
    default Signal<C> children() {
        return I.signal();
    }

    /**
     * Collect all descendent {@link Code} fragments.
     * 
     * @return
     */
    default Signal<C> descendent() {
        return I.signal(this).merge(children().skipNull().flatMap(c -> c.descendent()));
    }
}