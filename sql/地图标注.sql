CREATE TABLE t_map_label
(
    id          SERIAL PRIMARY KEY,    -- 自增主键ID
    name        VARCHAR(255) NOT NULL, -- 名称
    data        TEXT,          -- 标注对象的JSON字符串
    data_type   VARCHAR(100),          -- 标注类型
    remark      VARCHAR(255)           -- 备注
);

COMMENT
ON TABLE t_map_label IS '地图标注表';
