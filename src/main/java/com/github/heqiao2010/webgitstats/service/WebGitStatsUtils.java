package com.github.heqiao2010.webgitstats.service;

import java.io.File;

public abstract class WebGitStatsUtils {

    private static final String BASE_PATH = System.getProperty("user.dir");

    public static String getBasePath(){
        return BASE_PATH + File.separator + ".webgitstats";
    }

    public static String getGitDirPath() {
        return getBasePath() + File.separator + "gitfiles";
    }

    public static String getScriptDirPath() {
        return getBasePath() + File.separator + "gitstats";
    }

    public static String getStatsPath() {
        return getBasePath() + File.separator + "templates" + File.separator + "stats";
    }
}
