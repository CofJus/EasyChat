DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS user
(
    id          BIGINT(20)        NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    user_id     BIGINT(20) UNIQUE NOT NULL COMMENT '用户ID',
    user_name   VARCHAR(30)       NOT NULL COMMENT '用户名',
    password    VARCHAR(100)      NOT NULL COMMENT '密码',
    create_time DATETIME          NOT NULL COMMENT '创建时间',
    update_time DATETIME          NOT NULL COMMENT '更新时间',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;