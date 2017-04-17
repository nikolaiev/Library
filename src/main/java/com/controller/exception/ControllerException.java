package com.controller.exception;

import com.exception.ApplicationException;

/**
 * Created by vlad on 16.04.17.
 */
public class ControllerException extends ApplicationException {

    public ControllerException() {
    }

    @Override
    public ControllerException addLogMessage(String logMessage) {
        super.addLogMessage(logMessage);
        return this;
    }

    @Override
    public ControllerException addMessageKey(String messageKey) {
        super.addMessageKey(messageKey);
        return this;
    }

    @Override
    public ControllerException addAdditionalMessage(String additionalMessage) {
        super.addAdditionalMessage(additionalMessage);
        return this;
    }

}
