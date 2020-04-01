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
public class RepoCloneRunner extends BaseRunner {

    public boolean doClone(GitRepository repo) {
        try {
            Assert.notNull(repo, "GitRepository must provided!");
            CommandLine cmdLine = new CommandLine("git");
            cmdLine.addArguments("clone ${repoAddr} ${dir}", false);
            Map<String, String> map = new HashMap<>();
            map.put("repoAddr", repo.getAddr());
            map.put("dir", WebGitStatsUtils.getGitDirPath() + File.separator + repo.getDirPath());
            cmdLine.setSubstitutionMap(map);
            return runCommand(cmdLine);
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
    }
}
