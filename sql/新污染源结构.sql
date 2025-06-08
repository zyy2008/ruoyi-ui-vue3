CREATE TABLE t_enterprise_pollution_info
(
    id                                                            BIGINT PRIMARY KEY, -- 主键ID

    facilities_areas                                              TEXT,               -- 设施区域（多选）
    floor_plan                                                    TEXT,               -- 平面布置图
    main_intermediate_product_name                                TEXT,               -- 主中间产物名称
    main_process_flow_chart                                       TEXT,               -- 主要生产工艺流程图
    main_process_production_description                           TEXT,               -- 主要生产工艺描述
    is_clean_production_audited                                   VARCHAR(10),        -- 企业是否开展过清洁生产审核
    clean_audit_time                                              VARCHAR(50),        -- 清洁生产审核时间
    clean_level                                                   VARCHAR(100),       -- 清洁生产水平

    storage_facility                                              TEXT,               -- 储罐、储槽等储存设施
    hazardous_material_warehouse                                  TEXT,               -- 存放产品、原材料的或其他有毒有害物质的仓库等
    hazardous_material_loading_and_unloading_area                 TEXT,               -- 装卸区
    storage_area                                                  VARCHAR(50),        -- 储存区面积（平方米）
    protection_measures_for_storage_areas                         TEXT,               -- 各储存区域防护措施

    has_there_been_a_pipe_leak                                    VARCHAR(10),        -- 管道是否发生过泄漏
    underground_pipe_line_diagram                                 TEXT,               -- 地下管道管线图

    gas_treatment_exists                                          BOOLEAN,            -- 是否存在废气治理设施
    total_gas_emission                                            VARCHAR(100),       -- 废气排放总量
    main_pollutants_in_emissions                                  TEXT,               -- 废气中主要污染物名称
    is_there_an_online_monitoring_device_for_emissions            VARCHAR(10),        -- 是否有废气在线监测装置
    operation_status_of_online_monitoring_device                  TEXT,               -- 在线监测装置运行情况
    is_there_an_emission_treatment_facility                       VARCHAR(10),        -- 是否有废气治理设施
    operation_status_of_emission_treatment_facility               TEXT,               -- 废气治理设施运行情况

    is_there_wastewater_discharge                                 VARCHAR(10),        -- 是否有废水排放
    wastewater_discharge_volume                                   VARCHAR(100),       -- 废水排放量
    main_pollutants_in_wastewater                                 TEXT,               -- 废水中主要污染物名称
    is_there_an_online_monitoring_device_for_wastewater           VARCHAR(10),        -- 是否有废水在线监测装置
    is_there_a_wastewater_treatment_facility                      VARCHAR(10),        -- 是否有废水治理设施
    operation_status_of_wastewater_treatment_facility             TEXT,               -- 废水治理设施运行情况
    coordinates_of_wastewater_treatment_area                      VARCHAR(100),       -- 废水治理区坐标位置
    area_of_wastewater_treatment_area                             VARCHAR(100),       -- 废水治理区面积
    pollution_traces_in_wastewater_treatment_area                 TEXT,               -- 废水治理区污染痕迹
    photos_of_pollution_traces                                    TEXT,               -- 污染痕迹照片
    protection_measures_in_wastewater_treatment_area              TEXT,               -- 废水治理区防护措施

    is_solid_waste_generated                                      VARCHAR(10),        -- 是否产生固体废物
    is_solid_waste_stored                                         VARCHAR(10),        -- 是否有固体废物贮存
    is_hazardous_waste_self_disposed                              VARCHAR(10),        -- 是否有危险废物自处置

    wastewater_treatment_area_protection_measures                 TEXT,               -- 废水治理区防护措施（重复字段，建议去掉或合并）
    solid_waste_storage_and_disposal_area_protection_measures     TEXT,               -- 固废贮存处置区防护措施
    hazardous_waste_storage_and_disposal_area_protection_measures TEXT,               -- 危废贮存处置区防护措施
    hazardous_waste_standardized_management_assessment_results    TEXT,               -- 危险废物规范化管理考核评估结果

    dept_id                                                       VARCHAR(50)         -- 归属部门
);


CREATE TABLE t_storage_area
(
    id                           SERIAL PRIMARY KEY,
    area_name                    TEXT,
    area_type                    TEXT,
    left_position                TEXT,
    tank_count                   INTEGER,
    tank_type                    TEXT,
    stored_substance_name        TEXT,
    is_hazardous_chemical        BOOLEAN,
    storage_amount               NUMERIC,
    unit                         TEXT,
    storage_year                 TEXT,
    leakage_or_pollution_traces  TEXT,
    photos                       TEXT,
    enterprise_pollution_info_id VARCHAR(50),
    dept_id                      VARCHAR(50)
);

COMMENT
ON TABLE t_storage_area IS '储存区';

COMMENT
ON COLUMN t_storage_area.area_name IS '区域名称';
COMMENT
ON COLUMN t_storage_area.area_type IS '区域类型';
COMMENT
ON COLUMN t_storage_area.left_position IS '左边位置';
COMMENT
ON COLUMN t_storage_area.tank_count IS '储罐数量';
COMMENT
ON COLUMN t_storage_area.tank_type IS '储罐类型';
COMMENT
ON COLUMN t_storage_area.stored_substance_name IS '储存物质名称';
COMMENT
ON COLUMN t_storage_area.is_hazardous_chemical IS '是否为危险化学品';
COMMENT
ON COLUMN t_storage_area.storage_amount IS '储存量';
COMMENT
ON COLUMN t_storage_area.unit IS '计量单位';
COMMENT
ON COLUMN t_storage_area.storage_year IS '储存年代';
COMMENT
ON COLUMN t_storage_area.leakage_or_pollution_traces IS '泄漏或污染痕迹';
COMMENT
ON COLUMN t_storage_area.photos IS '照片';
COMMENT
ON COLUMN t_storage_area.enterprise_pollution_info_id IS '绑定的污染源';
COMMENT
ON COLUMN t_storage_area.dept_id IS '归属部门';

CREATE TABLE t_pipeline_leak
(
    id                           SERIAL PRIMARY KEY,
    pipeline_type                TEXT,
    leak_coordinates             TEXT,
    leaked_substance_name        TEXT,
    enterprise_pollution_info_id VARCHAR(50),
    dept_id                      VARCHAR(50)
);

COMMENT
ON TABLE t_pipeline_leak IS '管道泄漏';

COMMENT
ON COLUMN t_pipeline_leak.pipeline_type IS '管道类型';
COMMENT
ON COLUMN t_pipeline_leak.leak_coordinates IS '泄漏点坐标位置';
COMMENT
ON COLUMN t_pipeline_leak.leaked_substance_name IS '泄漏的物质名称';
COMMENT
ON COLUMN t_pipeline_leak.enterprise_pollution_info_id IS '绑定的污染源';
COMMENT
ON COLUMN t_pipeline_leak.dept_id IS '归属部门';

CREATE TABLE t_sewage_solid_waste
(
    id                           SERIAL PRIMARY KEY,
    area_name                    TEXT,
    location                     TEXT,
    coordinates                  TEXT,
    area                         TEXT,
    solid_waste_name             TEXT,
    is_hazardous_waste           BOOLEAN,
    storage_and_disposal_amount  TEXT,
    storage_and_disposal_photos  TEXT,
    enterprise_pollution_info_id VARCHAR(50),
    dept_id                      VARCHAR(50)
);

COMMENT
ON TABLE t_sewage_solid_waste IS '污水处理厂固体废物产生量及污染防治措施';

COMMENT
ON COLUMN t_sewage_solid_waste.area_name IS '区域名称';
COMMENT
ON COLUMN t_sewage_solid_waste.location IS '地点';
COMMENT
ON COLUMN t_sewage_solid_waste.coordinates IS '坐标位置';
COMMENT
ON COLUMN t_sewage_solid_waste.area IS '面积';
COMMENT
ON COLUMN t_sewage_solid_waste.solid_waste_name IS '固体废物名称';
COMMENT
ON COLUMN t_sewage_solid_waste.is_hazardous_waste IS '是否为危险废物';
COMMENT
ON COLUMN t_sewage_solid_waste.storage_and_disposal_amount IS '贮存量处置量';
COMMENT
ON COLUMN t_sewage_solid_waste.storage_and_disposal_photos IS '贮存量处置照片';
COMMENT
ON COLUMN t_sewage_solid_waste.enterprise_pollution_info_id IS '绑定的污染源';
COMMENT
ON COLUMN t_sewage_solid_waste.dept_id IS '归属部门';

CREATE TABLE t_pollution_incident
(
    id                           SERIAL PRIMARY KEY,
    incident_time                TIMESTAMP,
    location                     TEXT,
    coordinates                  TEXT,
    incident_level               TEXT,
    enterprise_pollution_info_id VARCHAR(50),
    dept_id                      VARCHAR(50)
);

COMMENT
ON TABLE t_pollution_incident IS '环境污染事故发生情况';

COMMENT
ON COLUMN t_pollution_incident.incident_time IS '发生时间';
COMMENT
ON COLUMN t_pollution_incident.location IS '事故发生地点';
COMMENT
ON COLUMN t_pollution_incident.coordinates IS '事故发生坐标';
COMMENT
ON COLUMN t_pollution_incident.incident_level IS '突发环境事件等级';
COMMENT
ON COLUMN t_pollution_incident.enterprise_pollution_info_id IS '绑定的污染源';
COMMENT
ON COLUMN t_pollution_incident.dept_id IS '归属部门';

CREATE TABLE t_land_pollution_analysis
(
    id                            SERIAL PRIMARY KEY,
    suspected_pollution_area_name TEXT,
    characteristic_pollutant_name TEXT,
    enterprise_pollution_info_id  VARCHAR(50),
    dept_id                       VARCHAR(50)
);

COMMENT
ON TABLE t_land_pollution_analysis IS '地块污染情况分析';

COMMENT
ON COLUMN t_land_pollution_analysis.suspected_pollution_area_name IS '疑似土壤污染区名称';
COMMENT
ON COLUMN t_land_pollution_analysis.characteristic_pollutant_name IS '特征污染物名称';
COMMENT
ON COLUMN t_land_pollution_analysis.enterprise_pollution_info_id IS '绑定的污染源';
COMMENT
ON COLUMN t_land_pollution_analysis.dept_id IS '归属部门';
