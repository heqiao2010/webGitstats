package com.github.heqiao2010.webgitstats.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Tolerate;

import javax.persistence.*;

/**
 * git仓库实体
 */
@Entity
@Getter
@Setter
@Builder
@ToString
@Table(name = "t_repository")
public class GitRepository {
    public static final String FILED_CREATE_TIME = "createTime";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 仓库名称
     */
    private String name;

    /**
     * 目录地址
     */
    @Column(name = "dir_path")
    private String dirPath;

    /**
     * 仓库地址
     */
    private String addr;

    /**
     * status
     */
    private int status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private long createTime;

    @Tolerate
    public GitRepository(){}
}
