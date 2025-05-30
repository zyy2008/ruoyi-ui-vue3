CREATE TABLE t_enterprise_pollution_info (
    id SERIAL PRIMARY KEY, -- 主键，自增ID

    product_name TEXT, -- 产品名称
    production_year_range TEXT, -- 产品生产年份范围（例如：2020-2023）
    product_unit TEXT, -- 产品单位（例如：吨、件）
    product_avg_output NUMERIC, -- 年均产量

    material_name TEXT, -- 原料名称
    material_use_year_range TEXT, -- 原料使用年份范围
    material_unit TEXT, -- 原料单位
    material_avg_usage NUMERIC, -- 原料年均用量

    process_line_name TEXT, -- 生产线名称
    longitude NUMERIC(9,6), -- 生产线经度
    latitude NUMERIC(9,6), -- 生产线纬度
    startup_date DATE, -- 投产时间
    process_description TEXT, -- 工艺描述
    leak_status TEXT, -- 是否存在泄漏（是/否/不详）
    workshop_area NUMERIC, -- 车间面积（平方米）

    storage_area_name TEXT, -- 储存区名称
    storage_longitude NUMERIC(9,6), -- 储存区经度
    storage_latitude NUMERIC(9,6), -- 储存区纬度
    tank_count INTEGER, -- 储罐数量
    stored_material TEXT, -- 储存物料名称
    is_hazardous BOOLEAN, -- 是否为危化品（true/false）
    storage_amount NUMERIC, -- 储存量
    storage_unit TEXT, -- 储存单位
    storage_start_date DATE, -- 储存开始时间
    storage_leak_status TEXT, -- 是否存在泄漏（是/否/不详）
    storage_remark TEXT, -- 备注信息
    storage_area NUMERIC, -- 储存区面积（平方米）

    gas_treatment_exists BOOLEAN, -- 是否存在废气治理设施（true/false）
    total_gas_emission NUMERIC, -- 废气排放总量
    gas_emission_unit TEXT -- 废气排放单位（例如：立方米/年）
);


-- 表1：土壤迁移途径信息 SoilPathway
CREATE TABLE t_soil_pathway (
    id SERIAL PRIMARY KEY, -- 主键
    seq_no INTEGER NOT NULL, -- 序号
    soil_type TEXT NOT NULL, -- 土层性质（例如碎石土、砂土等）
    depth_top_cm INTEGER, -- 土层（上层）埋深（单位：cm）
    thickness_cm INTEGER -- 土层厚度（单位：cm）
);
COMMENT ON TABLE t_soil_pathway IS '土壤途径：含水层以上土壤分层信息';
COMMENT ON COLUMN t_soil_pathway.seq_no IS '土壤层序号';
COMMENT ON COLUMN t_soil_pathway.soil_type IS '土层性质（如碎石土、砂土等）';
COMMENT ON COLUMN t_soil_pathway.depth_top_cm IS '上层埋深（单位cm）';
COMMENT ON COLUMN t_soil_pathway.thickness_cm IS '土层厚度（单位cm）';

-- 表2：地下水迁移途径 GroundwaterPathway
CREATE TABLE t_groundwater_pathway (
    id SERIAL PRIMARY KEY,
    water_depth_category TEXT NOT NULL, -- 地下水埋深分类
    permeability_category TEXT NOT NULL, -- 含水层渗透性分类
    is_karst_area BOOLEAN NOT NULL, -- 是否属于喀斯特地貌
    annual_rainfall_category TEXT NOT NULL, -- 年降雨量分类
    groundwater_use TEXT NOT NULL -- 地下水用途
);
COMMENT ON TABLE t_groundwater_pathway IS '地下水迁移途径信息';
COMMENT ON COLUMN t_groundwater_pathway.water_depth_category IS '地下水埋深（<3m、3-10m、>10m、不确定）';
COMMENT ON COLUMN t_groundwater_pathway.permeability_category IS '含水层渗透性分类';
COMMENT ON COLUMN t_groundwater_pathway.is_karst_area IS '是否属于喀斯特地貌';
COMMENT ON COLUMN t_groundwater_pathway.annual_rainfall_category IS '年降雨量范围';
COMMENT ON COLUMN t_groundwater_pathway.groundwater_use IS '地下水用途';

-- 表3：敏感受体信息 SensitiveReceptor
CREATE TABLE t_sensitive_receptor (
    id SERIAL PRIMARY KEY,
    surrounding_land_east TEXT,
    surrounding_land_south TEXT,
    surrounding_land_west TEXT,
    surrounding_land_north TEXT,
    employee_count_category TEXT NOT NULL, -- 职工人数分类
    permanent_population_category TEXT NOT NULL, -- 常驻人口分类
    sensitive_targets TEXT[] -- 敏感目标数组，使用文本数组保存多选
);
COMMENT ON TABLE t_sensitive_receptor IS '地块及周边敏感受体信息';
COMMENT ON COLUMN t_sensitive_receptor.surrounding_land_east IS '东侧用地类型';
COMMENT ON COLUMN t_sensitive_receptor.surrounding_land_south IS '南侧用地类型';
COMMENT ON COLUMN t_sensitive_receptor.surrounding_land_west IS '西侧用地类型';
COMMENT ON COLUMN t_sensitive_receptor.surrounding_land_north IS '北侧用地类型';
COMMENT ON COLUMN t_sensitive_receptor.employee_count_category IS '地块内职工人数分类';
COMMENT ON COLUMN t_sensitive_receptor.permanent_population_category IS '500m范围内常驻人口数量分类';
COMMENT ON COLUMN t_sensitive_receptor.sensitive_targets IS '1km范围内敏感目标及距离分类';

-- 表4：土壤监测 SoilMonitoring
CREATE TABLE t_soil_monitoring (
    id SERIAL PRIMARY KEY,
    has_monitoring BOOLEAN NOT NULL, -- 是否开展过调查
    survey_date DATE, -- 调查时间
    pollutants_exceed BOOLEAN, -- 是否检出超标
    source TEXT -- 数据来源
);
COMMENT ON TABLE t_soil_monitoring IS '土壤环境调查监测信息';
COMMENT ON COLUMN t_soil_monitoring.has_monitoring IS '是否曾开展土壤环境调查监测';
COMMENT ON COLUMN t_soil_monitoring.survey_date IS '调查时间';
COMMENT ON COLUMN t_soil_monitoring.pollutants_exceed IS '是否发现超标污染物';
COMMENT ON COLUMN t_soil_monitoring.source IS '数据来源';

-- 表5：土壤超标污染物 SoilPollutant
CREATE TABLE t_soil_pollutant (
    id SERIAL PRIMARY KEY,
    monitoring_id INTEGER REFERENCES soil_monitoring(id), -- 外键关联
    pollutant_name TEXT NOT NULL, -- 污染物名称
    max_concentration_mgkg REAL -- 最大浓度（mg/kg）
);
COMMENT ON TABLE t_soil_pollutant IS '土壤监测发现的超标污染物信息';
COMMENT ON COLUMN t_soil_pollutant.monitoring_id IS '关联的土壤监测记录ID';
COMMENT ON COLUMN t_soil_pollutant.pollutant_name IS '超标污染物名称';
COMMENT ON COLUMN t_soil_pollutant.max_concentration_mgkg IS '最大浓度（mg/kg）';

-- 表6：地下水监测 GroundwaterMonitoring
CREATE TABLE t_groundwater_monitoring (
    id SERIAL PRIMARY KEY,
    has_monitoring BOOLEAN NOT NULL,
    survey_date DATE,
    pollutants_exceed BOOLEAN,
    source TEXT
);
COMMENT ON TABLE t_groundwater_monitoring IS '地下水环境调查监测信息';
COMMENT ON COLUMN t_groundwater_monitoring.has_monitoring IS '是否曾开展地下水环境调查监测';
COMMENT ON COLUMN t_groundwater_monitoring.survey_date IS '调查时间';
COMMENT ON COLUMN t_groundwater_monitoring.pollutants_exceed IS '是否发现超标污染物';
COMMENT ON COLUMN t_groundwater_monitoring.source IS '数据来源';

-- 表7：地下水超标污染物 GroundwaterPollutant
CREATE TABLE t_groundwater_pollutant (
    id SERIAL PRIMARY KEY,
    monitoring_id INTEGER REFERENCES groundwater_monitoring(id),
    pollutant_name TEXT NOT NULL,
    max_concentration_mgl REAL -- 最大浓度（mg/l）
);
COMMENT ON TABLE t_groundwater_pollutant IS '地下水监测发现的超标污染物信息';
COMMENT ON COLUMN t_groundwater_pollutant.monitoring_id IS '关联的地下水监测记录ID';
COMMENT ON COLUMN t_groundwater_pollutant.pollutant_name IS '超标污染物名称';
COMMENT ON COLUMN t_groundwater_pollutant.max_concentration_mgl IS '最大浓度（mg/l）';


-- 创建监测井信息表
CREATE TABLE t_monitoring_wells (
    id SERIAL PRIMARY KEY,                      -- 主键
    well_code TEXT,                             -- 监测井编码
    location TEXT,                              -- 监测井位置
    point_type TEXT,                            -- 监测点类型（内部/对照）
    completion_date TEXT,                       -- 成井时间
    water_buried_depth DECIMAL(5,2),            -- 水位埋深（单位：米）
    well_elevation DECIMAL(6,2),                -- 井口高程（单位：米）
    well_depth DECIMAL(5,2),                    -- 成井深度（单位：米）
    inner_diameter DECIMAL(5,2),                -- 井口内径（单位：毫米）
    pipe_material TEXT,                         -- 井管材质（如 PVC、不锈钢）
    multi_screen_pipe BOOLEAN,                  -- 是否多段筛管
    screen_depth_range TEXT,                    -- 筛管上下部埋深范围（单位：米）
    groundwater_type TEXT,                      -- 地下水类型（潜水、承压水）
    burial_condition TEXT,                      -- 埋藏条件（如裂隙、孔隙）
    water_medium TEXT,                          -- 含水介质
    ownership TEXT,                             -- 监测井权属单位（生态环境部门/工业企业）
    suitable_for_longterm BOOLEAN,              -- 是否符合长期监测井要求

    -- 附加字段
    longitude DECIMAL(9,6),                     -- 经度
    latitude DECIMAL(9,6),                      -- 纬度
    altitude DECIMAL(6,2),                      -- 高程（单位：米）
    video_url TEXT                              -- 视频地址（URL）
);

-- 表注释
COMMENT ON TABLE t_monitoring_wells IS '园区监测井信息表，记录每个监测井的基础属性与附加信息';

-- 字段注释
COMMENT ON COLUMN t_monitoring_wells.id IS '主键，自增ID';
COMMENT ON COLUMN t_monitoring_wells.well_code IS '监测井编码';
COMMENT ON COLUMN t_monitoring_wells.location IS '监测井所在位置';
COMMENT ON COLUMN t_monitoring_wells.point_type IS '监测点类型，如内部监测点、对照监测点';
COMMENT ON COLUMN t_monitoring_wells.completion_date IS '成井时间';
COMMENT ON COLUMN t_monitoring_wells.water_buried_depth IS '水位埋深，单位：米';
COMMENT ON COLUMN t_monitoring_wells.well_elevation IS '井口高程，单位：米';
COMMENT ON COLUMN t_monitoring_wells.well_depth IS '成井深度，单位：米';
COMMENT ON COLUMN t_monitoring_wells.inner_diameter IS '井口内径，单位：毫米';
COMMENT ON COLUMN t_monitoring_wells.pipe_material IS '井管材质，如PVC、不锈钢';
COMMENT ON COLUMN t_monitoring_wells.multi_screen_pipe IS '是否为多段筛管结构';
COMMENT ON COLUMN t_monitoring_wells.screen_depth_range IS '筛管上部与下部的埋深范围，单位：米';
COMMENT ON COLUMN t_monitoring_wells.groundwater_type IS '地下水类型，如潜水、承压水';
COMMENT ON COLUMN t_monitoring_wells.burial_condition IS '埋藏条件，如孔隙水、裂隙水';
COMMENT ON COLUMN t_monitoring_wells.water_medium IS '含水介质';
COMMENT ON COLUMN t_monitoring_wells.ownership IS '监测井的权属单位';
COMMENT ON COLUMN t_monitoring_wells.suitable_for_longterm IS '是否符合长期监测井的标准';
COMMENT ON COLUMN t_monitoring_wells.longitude IS '经度，WGS84坐标系';
COMMENT ON COLUMN t_monitoring_wells.latitude IS '纬度，WGS84坐标系';
COMMENT ON COLUMN t_monitoring_wells.altitude IS '高程，单位：米';
COMMENT ON COLUMN t_monitoring_wells.video_url IS '视频资料地址（例如外观或施工过程）';


-- 主要产品表
CREATE TABLE t_main_products (
           id SERIAL PRIMARY KEY, -- 主键
           product_name TEXT NOT NULL, -- 产品名称
           production_year INTEGER, -- 生产年代
           unit TEXT, -- 计量单位
           annual_output NUMERIC, -- 年平均产量
           data_source TEXT, -- 产品数据来源
           created_by TEXT, -- 添加人
           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 添加时间
);
COMMENT ON TABLE t_main_products IS '主要产品信息';
COMMENT ON COLUMN t_main_products.product_name IS '产品名称';
COMMENT ON COLUMN t_main_products.production_year IS '生产年代';
COMMENT ON COLUMN t_main_products.unit IS '计量单位';
COMMENT ON COLUMN t_main_products.annual_output IS '年平均产量';
COMMENT ON COLUMN t_main_products.data_source IS '产品数据来源';
COMMENT ON COLUMN t_main_products.created_by IS '添加人';
COMMENT ON COLUMN t_main_products.created_at IS '添加时间';

-- 原辅材料表
CREATE TABLE t_raw_materials (
       id SERIAL PRIMARY KEY, -- 主键
       material_name TEXT NOT NULL, -- 原辅材料名称
       usage_year INTEGER, -- 使用年代
       unit TEXT, -- 计量单位
       annual_usage NUMERIC, -- 年平均用量
       data_source TEXT, -- 数据来源
       created_by TEXT, -- 添加人
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 添加时间
);
COMMENT ON TABLE t_raw_materials IS '原辅材料信息';
COMMENT ON COLUMN t_raw_materials.material_name IS '原辅材料名称';
COMMENT ON COLUMN t_raw_materials.usage_year IS '使用年代';
COMMENT ON COLUMN t_raw_materials.unit IS '计量单位';
COMMENT ON COLUMN t_raw_materials.annual_usage IS '年平均用量';
COMMENT ON COLUMN t_raw_materials.data_source IS '数据来源';
COMMENT ON COLUMN t_raw_materials.created_by IS '添加人';
COMMENT ON COLUMN t_raw_materials.created_at IS '添加时间';

-- 车间分布与环境情况表
CREATE TABLE t_workshop_environment (
      id SERIAL PRIMARY KEY, -- 主键
      workshop_name TEXT NOT NULL, -- 车间名称
      coordinate TEXT, -- 坐标位置（如“中东部”或坐标编号）
      start_date DATE, -- 生产起始日期
      process_description TEXT, -- 生产工艺描述
      leakage BOOLEAN, -- 是否有泄漏现象（true/false）
      leak_image_path TEXT, -- 漏点照片路径
      created_by TEXT, -- 添加人
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 添加时间
);
COMMENT ON TABLE t_workshop_environment IS '生产车间分布与环境情况';
COMMENT ON COLUMN t_workshop_environment.workshop_name IS '车间名称';
COMMENT ON COLUMN t_workshop_environment.coordinate IS '坐标位置';
COMMENT ON COLUMN t_workshop_environment.start_date IS '生产起始日期';
COMMENT ON COLUMN t_workshop_environment.process_description IS '生产工艺描述';
COMMENT ON COLUMN t_workshop_environment.leakage IS '是否有泄漏现象';
COMMENT ON COLUMN t_workshop_environment.leak_image_path IS '跑冒滴漏点照片路径';
COMMENT ON COLUMN t_workshop_environment.created_by IS '添加人';
COMMENT ON COLUMN t_workshop_environment.created_at IS '添加时间';





















