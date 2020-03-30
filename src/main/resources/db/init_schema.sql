CREATE TABLE IF NOT EXISTS t_repository (
id bigint(20) NOT NULL PRIMARY KEY,
name char(16) NOT NULL COMMENT 'name of a project',
dir_path char(128) NOT NULL COMMENT 'relative directory path',
addr char(256) NOT NULL COMMENT 'git address',
status tinyint(4) NOT NULL DEFAULT '0' COMMENT '0: init, 1: cloning, -1:error',
create_time bigint(20) NOT NULL
);
