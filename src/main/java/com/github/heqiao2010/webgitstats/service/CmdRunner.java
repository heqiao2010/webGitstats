package com.github.heqiao2010.webgitstats.service;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.LogOutputStream;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class CmdRunner {

    private final LogOutputStream outputStream = new LogOutputStream() {
        @Override
        protected void processLine(String line, int logLevel) {
            if (log.isDebugEnabled()) {
                log.debug("output: {}", line);
            }
        }
    };

    private final LogOutputStream errorStream = new LogOutputStream() {
        @Override
        protected void processLine(String line, int logLevel) {
            log.error("error:{}", line);
        }
    };

    /**
     * 设置文件权限的命令: sh -c "chmod -R 755 aviraDir"
     */
    private CommandLine buildComand(String path) {
        CommandLine cmdLine = new CommandLine("sh");
        cmdLine.addArguments("-c");
        cmdLine.addArguments("\"chmod ${permissionCode} ${dir}\"", false);
        Map<String, String> map = new HashMap<>();
        map.put("dir", path);
        map.put("permissionCode", "755");
        cmdLine.setSubstitutionMap(map);
        return cmdLine;
    }
}
