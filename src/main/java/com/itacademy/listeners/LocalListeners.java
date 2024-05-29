package com.itacademy.listeners;

import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LocalListeners implements LogEventListener {

    private static final Logger LOGGER = LogManager.getLogger(LocalListeners.class);
    @Override
    public void afterEvent(LogEvent logEvent) {
    LOGGER.error(logEvent);
    }

    @Override
    public void beforeEvent(LogEvent logEvent) {

    }
}
