package com.github.heqiao2010.webgitstats.service.runner;

import com.github.heqiao2010.webgitstats.entity.GitRepository;
import com.github.heqiao2010.webgitstats.service.WebGitStatsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.*;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class GitStatsRunner extends BaseRunner {

    public boolean doStats(GitRepository repo) {
        try {
            Assert.notNull(repo, "GitRepository must provided!");
            CommandLine cmdLine = new CommandLine("python");
            cmdLine.addArguments("${script} ${git-dir} ${stats-dir}");
            Map<String, String> map = new HashMap<>();
            map.put("script", WebGitStatsUtils.getScriptPath());
            map.put("git-dir", WebGitStatsUtils.getGitDirPath(repo));
            map.put("stats-dir", WebGitStatsUtils.getStatsPath(repo));
            cmdLine.setSubstitutionMap(map);
            createDirectory(repo);
            return runCommand(cmdLine);
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
    }

    private void createDirectory(GitRepository repo) throws IOException {
        File statsDir = new File(WebGitStatsUtils.getStatsPath(repo));
        if (!statsDir.exists()) {
            if(statsDir.mkdirs()){
                log.warn("mkdirs failed!");
            } else {
                log.info("mkdirs ok! {}", statsDir.getAbsolutePath());
            }
        }
    }
}
