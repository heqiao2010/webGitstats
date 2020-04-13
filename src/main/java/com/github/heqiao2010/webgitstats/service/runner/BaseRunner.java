package com.github.heqiao2010.webgitstats.service.runner;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.*;

import java.io.IOException;

@Slf4j
public abstract class BaseRunner {
    protected final static int COMMAND_RET_OK = 0;

    protected final static int COMMAND_TIME_OUT = 5 * 60 * 1000;

    protected final LogOutputStream outputStream = new LogOutputStream() {
        @Override
        protected void processLine(String line, int logLevel) {
            if (log.isDebugEnabled()) {
                log.debug("output: {}", line);
            }
        }
    };

    protected final LogOutputStream errorStream = new LogOutputStream() {
        @Override
        protected void processLine(String line, int logLevel) {
            log.warn("{}", line);
        }
    };

    protected boolean runCommand(CommandLine cmdLine){
        try {
            DefaultExecutor executor = new DefaultExecutor();
            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream, errorStream);
            executor.setStreamHandler(streamHandler);
            executor.setExitValue(COMMAND_RET_OK);
            ExecuteWatchdog watchdog = new ExecuteWatchdog(COMMAND_TIME_OUT);
            executor.setWatchdog(watchdog);
            return COMMAND_RET_OK == executor.execute(cmdLine);
        } catch (IOException e) {
            log.error("checkGitAvailable", e);
            return false;
        }
    }
}
