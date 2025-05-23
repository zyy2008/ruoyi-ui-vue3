CREATE TABLE t_layer (
                         id SERIAL PRIMARY KEY,        -- 自增主键ID
                         name VARCHAR(255) NOT NULL,   -- 图层名称
                         url VARCHAR(255),             -- 图层地址
                         server_type VARCHAR(100),     -- 服务类型
                         pid VARCHAR(255),             -- 父节点ID（字符串类型）
                         is_show BOOLEAN DEFAULT FALSE,-- 是否默认显示
                         data_type VARCHAR(50)         -- 数据类型
);

-- 为表 t_layer 添加备注
COMMENT ON TABLE t_layer IS '图层表';
ALTER TABLE t_layer ADD COLUMN "table_name" VARCHAR;
