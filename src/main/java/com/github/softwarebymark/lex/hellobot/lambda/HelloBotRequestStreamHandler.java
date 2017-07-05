/*
 * Copyright 2017 Mark Borner
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.github.softwarebymark.lex.hellobot.lambda;

import com.github.softwarebymark.lex.hellobot.HelloBotRequestHandler;
import com.github.softwarebymark.lex.lambda.LexRequestStreamHandler;

/**
 * An example Lex Request Stream Handler which Lambda will invoke for each
 * request from Lex
 *
 * @author Mark Borner
 */
public class HelloBotRequestStreamHandler extends LexRequestStreamHandler {

    /**
     * The bot name is the name of your bot that you configured in the Lex console
     */
    public HelloBotRequestStreamHandler() {
        super("HelloBot", new HelloBotRequestHandler());
    }

}
