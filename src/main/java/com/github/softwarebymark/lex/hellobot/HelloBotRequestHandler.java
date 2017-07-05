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

package com.github.softwarebymark.lex.hellobot;

import com.github.softwarebymark.lex.AbstractLexRequestHandler;
import com.github.softwarebymark.lex.domain.FulfillmentState;
import com.github.softwarebymark.lex.domain.InvocationSource;
import com.github.softwarebymark.lex.domain.LexRequest;
import com.github.softwarebymark.lex.domain.LexResponse;

import java.util.Map;

/**
 * An example Lex Request Handler for the Hello Chatbot
 *
 * @author Mark Borner
 */
public class HelloBotRequestHandler extends AbstractLexRequestHandler {

    private static final String NAME = "Name";

    @Override
    public LexResponse handleRequest(LexRequest lexRequest, Map<String, String> sessionAttributes) {
        try {
            if ("HelpIntent".equals(lexRequest.getIntent().getName())) {
                return processHelpIntent();
            } else if ("HelloIntent".equals(lexRequest.getIntent().getName())) {
                return processHelloIntent(lexRequest, sessionAttributes);
            } else if ("GoodbyeIntent".equals(lexRequest.getIntent().getName())) {
                return processGoodbyeIntent(sessionAttributes);
            } else {
                return createElicitIntentDialogActionResponse();
            }
        } catch (Exception e) {
            return createCloseDialogActionResponse(FulfillmentState.Failed, "Sorry, I'm having a problem fulfilling your request.  Please try again later.");
        }
    }

    private LexResponse processHelpIntent() {
        return createCloseDialogActionResponse(FulfillmentState.Fulfilled, "I'm a friendly bot.  Just say: hello I'm <insert your name here>.");
    }

    private LexResponse processHelloIntent(LexRequest lexRequest, Map<String,String> sessionAttributes) {
        String name = lexRequest.getIntent().getSlots().get(NAME);
        if (InvocationSource.DialogCodeHook.equals(lexRequest.getInvocationSource())) {
            if (name == null) {
                return createDelegateDialogActionResponse(lexRequest);
            } else {
                return sayHelloTo(name, sessionAttributes);
            }
        } else if (InvocationSource.FulfillmentCodeHook.equals(lexRequest.getInvocationSource())) {
            // The slot should not be empty if the InvocationSource is Fulfillment Code Hook
            return sayHelloTo(name, sessionAttributes);
        } else {
            throw new RuntimeException("Unknown Invocation Source: " + lexRequest.getInvocationSource());
        }
    }

    private LexResponse sayHelloTo(String name, Map<String,String> sessionAttributes) {
        sessionAttributes.put(NAME, name);
        return createCloseDialogActionResponse(FulfillmentState.Fulfilled, "Hello " + name);
    }

    private LexResponse processGoodbyeIntent(Map<String,String> sessionAttributes) {
        String name = sessionAttributes.get(NAME);
        if (name == null) {
            // If the session has timed out, we will not know the person's name
            name = "";
        }
        return createCloseDialogActionResponse(FulfillmentState.Fulfilled, "Goodbye " + name);
    }
}
