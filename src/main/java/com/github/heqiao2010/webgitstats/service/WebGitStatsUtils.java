package com.github.heqiao2010.webgitstats.service;

import com.github.heqiao2010.webgitstats.entity.GitRepository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

public abstract class WebGitStatsUtils {

    public static String getGitDirPath(GitRepository repo) throws IOException {
        return ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath()
                + "gitfiles" + File.separator + repo.getDirPath();
    }

    public static String getScriptPath() throws IOException {
        return ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath()
                + "gitstats" + File.separator + "gitstats";
    }

    public static String getStatsPath(GitRepository repo) throws IOException {
        return ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath()
                + "templates" + File.separator + "stats" + File.separator + repo.getDirPath();
    }
}
