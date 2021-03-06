/*
 * Copyright (C) 2020 Reincarnation Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
public class Project extends bee.api.Project {

    {
        product("com.github.teletha", "Reincarnation", "0.6");

        require("org.ow2.asm", "asm");
        require("com.github.teletha", "sinobu", "[2.0,)");
        require("com.github.teletha", "antibug").atTest();
        require("com.github.teletha", "psychopath").atTest();
    }
}