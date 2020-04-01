package com.github.heqiao2010.webgitstats.service.runner;

import com.github.heqiao2010.webgitstats.entity.GitRepository;
import com.github.heqiao2010.webgitstats.service.WebGitStatsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class GitStatsRunner extends BaseRunner {
    private static final String GITSTATS_ADDR = "https://github.com/hoxu/gitstats.git";

    public boolean doStats(GitRepository repo) {
        try {
            Assert.notNull(repo, "GitRepository must provided!");
            CommandLine cmdLine = new CommandLine("python");
            cmdLine.addArguments("${script} ${git-dir} ${stats-dir}");
            Map<String, String> map = new HashMap<>();
            map.put("script", WebGitStatsUtils.getScriptDirPath() + File.separator + "gitstats");
            map.put("git-dir", WebGitStatsUtils.getGitDirPath() + File.separator + repo.getDirPath());
            map.put("stats-dir", WebGitStatsUtils.getStatsPath() + File.separator + repo.getDirPath());
            cmdLine.setSubstitutionMap(map);
            cloneScriptIfNeed();
            return runCommand(cmdLine);
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
    }

    private void cloneScriptIfNeed() {
        File statsDir = new File(WebGitStatsUtils.getScriptDirPath());
        if (!statsDir.exists()) {
            log.info("{} is empty, clone gitStats...", WebGitStatsUtils.getScriptDirPath());
            CommandLine cmdLine = new CommandLine("git");
            cmdLine.addArguments("clone ${gitstats-addr} ${gitstats-dir}");
            Map<String, String> map = new HashMap<>();
            map.put("gitstats-addr", GITSTATS_ADDR);
            map.put("gitstats-dir", WebGitStatsUtils.getScriptDirPath());
            cmdLine.setSubstitutionMap(map);
            runCommand(cmdLine);
        }
    }
}
