package com.github.heqiao2010.webgitstats.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum StatusEnum {
    INIT(0, "初始状态"),
    CLONING(1, "克隆中"),
    IN_STATS(2, "统计中"),
    COMPLETE(3, "已完成"),
    ERROR(-1, "出错"),
    ;

    private int status;
    private String desc;

    StatusEnum(int status, String desc){
        this.status = status;
        this.desc = desc;
    }

    public static StatusEnum fromStatus(int status){
        return Arrays.stream(values()).filter(s -> s.status == status).findFirst().orElse(ERROR);
    }

    public StatusEnum nextStatus() {
        int newOrdinal = ordinal() + 1;
        // 用null兜底
        return Arrays.stream(values()).filter(s -> s.ordinal() == newOrdinal).findAny().orElse(null);
    }
}
