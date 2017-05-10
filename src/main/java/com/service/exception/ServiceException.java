package com.service.exception;

import com.exception.ApplicationException;

/**
 * Created by vlad on 09.04.17.
 */
public class ServiceException extends ApplicationException {

    public ServiceException(){

    }

    /**
     * Message to be logged
     * @param logMessage
     * @return
     */
    @Override
    public ServiceException addLogMessage(String logMessage) {
        super.addLogMessage(logMessage);
        return this;
    }

    /**
     * Message key from ResourceBundle
     * @param messageKey
     * @return
     */
    @Override
    public ServiceException addMessageKey(String messageKey) {
        super.addMessageKey(messageKey);
        return this;
    }

    /**
     * Additional message to be logged
     * @param additionalMessage
     * @return
     */
    @Override
    public ServiceException addAdditionalMessage(String additionalMessage) {
        super.addAdditionalMessage(additionalMessage);
        return this;
    }
}
