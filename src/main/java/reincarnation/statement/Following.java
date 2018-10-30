/*
 * Copyright (C) 2018 Reincarnation Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package reincarnation.statement;

import reincarnation.Node;
import reincarnation.coder.Code;
import reincarnation.coder.Coder;

/**
 * @version 2018/10/30 23:27:11
 */
public class Following extends Statement {

    /** The code. */
    private final Code now;

    /** The following. */
    private final Node follow;

    /**
     * Statement.
     * 
     * @param now
     * @param follow
     */
    public Following(Code now, Node follow) {
        this.now = now;
        this.follow = follow;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(Coder coder) {
        if (now != null) now.write(coder);
        if (follow != null) follow.write(coder);
    }
}
