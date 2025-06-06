/*
 Navicat Premium Data Transfer

 Source Server         : 地下水
 Source Server Type    : PostgreSQL
 Source Server Version : 140003
 Source Host           : 39.100.43.147:5432
 Source Catalog        : RuoYi
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 140003
 File Encoding         : 65001

 Date: 06/06/2025 14:30:03
*/


-- ----------------------------
-- Table structure for t_water_quality_monitoring
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_water_quality_monitoring";
CREATE TABLE "public"."t_water_quality_monitoring" (
  "point_id" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
  "sample_time" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "color" varchar(50) COLLATE "pg_catalog"."default",
  "odor_and_taste" varchar(50) COLLATE "pg_catalog"."default",
  "turbidity" varchar(50) COLLATE "pg_catalog"."default",
  "visible_matters" varchar(50) COLLATE "pg_catalog"."default",
  "ph" varchar(50) COLLATE "pg_catalog"."default",
  "total_hardness" varchar(50) COLLATE "pg_catalog"."default",
  "total_dissolved_solids" varchar(50) COLLATE "pg_catalog"."default",
  "sulfate" varchar(50) COLLATE "pg_catalog"."default",
  "chloride" varchar(50) COLLATE "pg_catalog"."default",
  "iron" varchar(50) COLLATE "pg_catalog"."default",
  "manganese" varchar(50) COLLATE "pg_catalog"."default",
  "aluminum" varchar(50) COLLATE "pg_catalog"."default",
  "anionic_surfactant" varchar(50) COLLATE "pg_catalog"."default",
  "oxygen_consumption" varchar(50) COLLATE "pg_catalog"."default",
  "sulfide" varchar(50) COLLATE "pg_catalog"."default",
  "sodium" varchar(50) COLLATE "pg_catalog"."default",
  "nitrite_nitrogen" varchar(50) COLLATE "pg_catalog"."default",
  "nitrate_nitrogen" varchar(50) COLLATE "pg_catalog"."default",
  "iodide" varchar(50) COLLATE "pg_catalog"."default",
  "selenium" varchar(50) COLLATE "pg_catalog"."default",
  "copper" varchar(50) COLLATE "pg_catalog"."default",
  "zinc" varchar(50) COLLATE "pg_catalog"."default",
  "volatile_phenol" varchar(50) COLLATE "pg_catalog"."default",
  "ammonia_nitrogen" varchar(50) COLLATE "pg_catalog"."default",
  "cyanide" varchar(50) COLLATE "pg_catalog"."default",
  "fluoride" varchar(50) COLLATE "pg_catalog"."default",
  "mercury" varchar(50) COLLATE "pg_catalog"."default",
  "arsenic" varchar(50) COLLATE "pg_catalog"."default",
  "cadmium" varchar(50) COLLATE "pg_catalog"."default",
  "chromium_vi" varchar(50) COLLATE "pg_catalog"."default",
  "lead" varchar(50) COLLATE "pg_catalog"."default",
  "chloroform" varchar(50) COLLATE "pg_catalog"."default",
  "carbon_tetrachloride" varchar(50) COLLATE "pg_catalog"."default",
  "benzene" varchar(50) COLLATE "pg_catalog"."default",
  "toluene" varchar(50) COLLATE "pg_catalog"."default",
  "molybdenum" varchar(50) COLLATE "pg_catalog"."default",
  "vanadium" varchar(50) COLLATE "pg_catalog"."default",
  "cobalt" varchar(50) COLLATE "pg_catalog"."default",
  "nickel" varchar(50) COLLATE "pg_catalog"."default",
  "dichloromethane" varchar(50) COLLATE "pg_catalog"."default",
  "dichloroethane_1_2" varchar(50) COLLATE "pg_catalog"."default",
  "trichloroethane_1_1_1" varchar(50) COLLATE "pg_catalog"."default",
  "trichloroethane_1_1_2" varchar(50) COLLATE "pg_catalog"."default",
  "dichloropropane_1_2" varchar(50) COLLATE "pg_catalog"."default",
  "vinyl_chloride" varchar(50) COLLATE "pg_catalog"."default",
  "dichloroethylene_1_1" varchar(50) COLLATE "pg_catalog"."default",
  "trans_dichloroethylene_1_2" varchar(50) COLLATE "pg_catalog"."default",
  "cis_dichloroethylene_1_2" varchar(50) COLLATE "pg_catalog"."default",
  "trichloroethylene" varchar(50) COLLATE "pg_catalog"."default",
  "tetrachloroethylene" varchar(50) COLLATE "pg_catalog"."default",
  "chlorobenzene" varchar(50) COLLATE "pg_catalog"."default",
  "dichlorobenzene_1_2" varchar(50) COLLATE "pg_catalog"."default",
  "dichlorobenzene_1_4" varchar(50) COLLATE "pg_catalog"."default",
  "ethylbenzene" varchar(50) COLLATE "pg_catalog"."default",
  "xylene_ortho" varchar(50) COLLATE "pg_catalog"."default",
  "xylene_meta_para" varchar(50) COLLATE "pg_catalog"."default",
  "styrene" varchar(50) COLLATE "pg_catalog"."default",
  "nitrobenzene" varchar(50) COLLATE "pg_catalog"."default",
  "naphthalene" varchar(50) COLLATE "pg_catalog"."default",
  "anthracene" varchar(50) COLLATE "pg_catalog"."default",
  "fluoranthene" varchar(50) COLLATE "pg_catalog"."default",
  "benzo_b_fluoranthene" varchar(50) COLLATE "pg_catalog"."default",
  "benzo_a_pyrene" varchar(50) COLLATE "pg_catalog"."default",
  "petroleum_hydrocarbons_c6_c9" varchar(50) COLLATE "pg_catalog"."default",
  "petroleum_hydrocarbons_c10_c40" varchar(50) COLLATE "pg_catalog"."default",
  "phenol" varchar(50) COLLATE "pg_catalog"."default",
  "chlorophenol_2" varchar(50) COLLATE "pg_catalog"."default",
  "aniline" varchar(50) COLLATE "pg_catalog"."default",
  "acenaphthylene" varchar(50) COLLATE "pg_catalog"."default",
  "acenaphthene" varchar(50) COLLATE "pg_catalog"."default",
  "fluorene" varchar(50) COLLATE "pg_catalog"."default",
  "phenanthrene" varchar(50) COLLATE "pg_catalog"."default",
  "pyrene" varchar(50) COLLATE "pg_catalog"."default",
  "benzo_a_anthracene" varchar(50) COLLATE "pg_catalog"."default",
  "chrysene" varchar(50) COLLATE "pg_catalog"."default",
  "benzo_k_fluoranthene" varchar(50) COLLATE "pg_catalog"."default",
  "indeno_1_2_3_cd_pyrene" varchar(50) COLLATE "pg_catalog"."default",
  "dibenzo_a_h_anthracene" varchar(50) COLLATE "pg_catalog"."default",
  "benzo_g_h_i_perylene" varchar(50) COLLATE "pg_catalog"."default",
  "chloromethane" varchar(50) COLLATE "pg_catalog"."default",
  "dichloroethane_1_1" varchar(50) COLLATE "pg_catalog"."default",
  "tetrachloroethane_1_1_1_2" varchar(50) COLLATE "pg_catalog"."default",
  "tetrachloroethane_1_1_2_2" varchar(50) COLLATE "pg_catalog"."default",
  "trichloropropane_1_2_3" varchar(50) COLLATE "pg_catalog"."default",
  "id" varchar(36) COLLATE "pg_catalog"."default" NOT NULL,
  "analysis_number" varchar(20) COLLATE "pg_catalog"."default",
  "original_number" varchar(20) COLLATE "pg_catalog"."default",
  "carbonate" numeric,
  "bicarbonate" numeric,
  "ferrous_ion" numeric,
  "potassium" numeric,
  "calcium" numeric,
  "magnesium" numeric,
  "chloroprene" numeric,
  "1,1-dichloropropene" numeric,
  "trans-1,3-dichloropropene" numeric,
  "cis-1,3-dichloropropene" numeric,
  "2,2-dichloropropane" numeric,
  "bromochloromethane" numeric,
  "bromodichloromethane" numeric,
  "dibromomethane" numeric,
  "1,1-dichloroethane" numeric,
  "1,2-dibromoethane" numeric,
  "1,3-dichloropropane" numeric,
  "dibromochloromethane" numeric,
  "bromoform" numeric,
  "1,1,1,2-tetrachloroethane" numeric,
  "1,1,2,2-tetrachloroethane" numeric,
  "1,2,3-trichloropropane" numeric,
  "cumene" numeric,
  "n_propylbenzene" numeric,
  "bromobenzene" numeric,
  "1,3,5-trimethylbenzene" numeric,
  "o_chlorotoluene" numeric,
  "p_chlorotoluene" numeric,
  "tert_butylbenzene" numeric,
  "1,2,4-trimethylbenzene" numeric,
  "sec_butylbenzene" numeric,
  "4-isopropyltoluene" numeric,
  "n_butylbenzene" numeric,
  "epichlorohydrin" numeric,
  "1,2-dibromo-3-chloropropane" numeric,
  "1,2,4-trichlorobenzene" numeric,
  "hexachlorobutadiene" numeric,
  "1,2,3-trichlorobenzene" numeric,
  "2-methylnaphthalene" numeric,
  "1-methylnaphthalene" numeric
)
;
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."point_id" IS '字段说明：监测点位编号，唯一标识监测位置，长度不超过10位字符';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."sample_time" IS '字段说明：采样时间，格式为YYYYMMDD（8位字符），如20250606';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."color" IS '单位：度，字段说明：水的色度，表征水的颜色深浅';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."odor_and_taste" IS '字段说明：水的臭和味描述，记录感官检测结果（如无异味、轻微腥臭等）';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."turbidity" IS '单位：NTU，字段说明：水的浊度，反映水中悬浮物和胶体物质的含量';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."visible_matters" IS '字段说明：肉眼可见物描述，如泥沙、絮状物等';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."ph" IS '字段说明：pH值，表征水的酸碱性，无量纲';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."total_hardness" IS '单位：mg/L（以CaCO3计），字段说明：水的总硬度，钙镁离子折算为碳酸钙的总量';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."total_dissolved_solids" IS '单位：mg/L，字段说明：溶解性总固体，水中溶解的无机盐和有机物总和';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."sulfate" IS '单位：mg/L，字段说明：硫酸根离子浓度';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."chloride" IS '单位：mg/L，字段说明：氯离子浓度';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."iron" IS '单位：mg/L，字段说明：铁离子浓度，以Fe计';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."manganese" IS '单位：mg/L，字段说明：锰离子浓度，以Mn计';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."aluminum" IS '单位：mg/L，字段说明：铝离子浓度，以Al计';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."anionic_surfactant" IS '单位：mg/L，字段说明：阴离子表面活性剂浓度，如LAS（直链烷基苯磺酸盐）';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."oxygen_consumption" IS '单位：mg/L，字段说明：耗氧量（COD），水中还原性物质消耗氧气的量';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."sulfide" IS '单位：mg/L，字段说明：硫化物浓度，以S²⁻计';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."sodium" IS '单位：mg/L，字段说明：钠离子浓度';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."nitrite_nitrogen" IS '单位：mg/L，字段说明：亚硝酸盐氮浓度，以N计';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."nitrate_nitrogen" IS '单位：mg/L，字段说明：硝酸盐氮浓度，以N计';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."iodide" IS '单位：mg/L，字段说明：碘化物浓度，以I⁻计';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."selenium" IS '单位：mg/L，字段说明：硒离子浓度，以Se计';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."copper" IS '单位：mg/L，字段说明：铜离子浓度，以Cu计';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."zinc" IS '单位：mg/L，字段说明：锌离子浓度，以Zn计';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."volatile_phenol" IS '单位：mg/L，字段说明：挥发酚浓度，以苯酚计';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."ammonia_nitrogen" IS '单位：mg/L，字段说明：氨氮浓度，以N计';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."cyanide" IS '单位：mg/L，字段说明：氰化物浓度，以CN⁻计';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."fluoride" IS '单位：mg/L，字段说明：氟化物浓度，以F⁻计';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."mercury" IS '单位：mg/L，字段说明：汞离子浓度，以Hg计';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."arsenic" IS '单位：mg/L，字段说明：砷离子浓度，以As计';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."cadmium" IS '单位：mg/L，字段说明：镉离子浓度，以Cd计';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."chromium_vi" IS '单位：mg/L，字段说明：六价铬浓度，以Cr⁶⁺计';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."lead" IS '单位：mg/L，字段说明：铅离子浓度，以Pb计';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."chloroform" IS '单位：µg/L，字段说明：氯仿（三氯甲烷）浓度，饮用水消毒副产物';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."carbon_tetrachloride" IS '单位：µg/L，字段说明：四氯化碳浓度，有机溶剂，具有毒性';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."benzene" IS '单位：µg/L，字段说明：苯浓度，致癌性挥发性有机物';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."toluene" IS '单位：µg/L，字段说明：甲苯浓度，常见有机溶剂';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."molybdenum" IS '单位: mg/L';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."vanadium" IS '单位: mg/L';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."cobalt" IS '单位: mg/L';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."nickel" IS '单位: mg/L';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."dichloromethane" IS '单位：µg/L，字段说明：二氯甲烷浓度，用于涂料和胶黏剂';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."dichloroethane_1_2" IS '单位：µg/L，字段说明：1,2-二氯乙烷浓度，工业溶剂';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."trichloroethane_1_1_1" IS '单位：µg/L，字段说明：1,1,1-三氯乙烷浓度，曾用作风幕机清洗剂';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."trichloroethane_1_1_2" IS '单位：µg/L，字段说明：1,1,2-三氯乙烷浓度，与1,1,1-三氯乙烷互为同分异构体';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."dichloropropane_1_2" IS '单位：µg/L，字段说明：1,2-二氯丙烷浓度，用于有机合成';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."vinyl_chloride" IS '单位：µg/L，字段说明：氯乙烯浓度，用于生产聚氯乙烯（PVC）';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."dichloroethylene_1_1" IS '单位：µg/L，字段说明：1,1-二氯乙烯浓度，几何异构体';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."trans_dichloroethylene_1_2" IS '单位：µg/L，字段说明：反式-1,2-二氯乙烯浓度，氯乙烯异构体';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."cis_dichloroethylene_1_2" IS '单位：µg/L，字段说明：顺式-1,2-二氯乙烯浓度，与反式结构互为同分异构体';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."trichloroethylene" IS '单位：µg/L，字段说明：三氯乙烯浓度，金属脱脂剂，具有肝毒性';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."tetrachloroethylene" IS '单位：µg/L，字段说明：四氯乙烯浓度，干洗剂主要成分';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."chlorobenzene" IS '单位：µg/L，字段说明：氯苯浓度，用于生产农药和染料';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."dichlorobenzene_1_2" IS '单位：µg/L，字段说明：1,2-二氯苯浓度，邻二氯苯，用于杀虫剂';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."dichlorobenzene_1_4" IS '单位：µg/L，字段说明：1,4-二氯苯浓度，对二氯苯，防虫剂';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."ethylbenzene" IS '单位：µg/L，字段说明：乙苯浓度，用于生产苯乙烯';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."xylene_ortho" IS '单位：µg/L，字段说明：邻二甲苯浓度，二甲苯异构体之一';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."xylene_meta_para" IS '单位：µg/L，字段说明：间/对-二甲苯浓度，二甲苯异构体混合物';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."styrene" IS '单位：µg/L，字段说明：苯乙烯浓度，用于生产塑料和橡胶';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."nitrobenzene" IS '单位：µg/L，字段说明：硝基苯浓度，工业污染物，具有毒性';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."naphthalene" IS '单位：µg/L，字段说明：萘浓度，多环芳烃类化合物，常见于煤焦油';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."anthracene" IS '单位：µg/L，字段说明：蒽浓度，多环芳烃类，用于生产染料';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."fluoranthene" IS '单位：µg/L，字段说明：荧蒽浓度，多环芳烃类，环境中常见污染物';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."benzo_b_fluoranthene" IS '单位：µg/L，字段说明：苯并[b]荧蒽浓度，致癌性多环芳烃';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."benzo_a_pyrene" IS '单位：µg/L，字段说明：苯并[a]芘浓度，强致癌性多环芳烃';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."petroleum_hydrocarbons_c6_c9" IS '单位：mg/L，字段说明：石油烃（C6-C9）浓度，挥发性石油烃组分';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."petroleum_hydrocarbons_c10_c40" IS '单位：mg/L，字段说明：石油烃（C10-C40）浓度，半挥发性石油烃组分';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."phenol" IS '单位：mg/L，字段说明：苯酚浓度，具有腐蚀性和毒性';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."chlorophenol_2" IS '单位：mg/L，字段说明：2-氯酚浓度，苯酚的氯代衍生物';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."aniline" IS '单位：µg/L，字段说明：苯胺浓度，用于生产染料和医药，有毒';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."acenaphthylene" IS '单位：µg/L，字段说明：苊烯浓度，多环芳烃类，存在于煤烟中';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."acenaphthene" IS '单位：µg/L，字段说明：苊浓度，多环芳烃类，用于生产树脂';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."fluorene" IS '单位：µg/L，字段说明：芴浓度，多环芳烃类，用于塑料工业';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."phenanthrene" IS '单位：µg/L，字段说明：菲浓度，多环芳烃类，存在于烟草烟雾中';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."pyrene" IS '单位：µg/L，字段说明：芘浓度，多环芳烃类，环境污染物';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."benzo_a_anthracene" IS '单位：µg/L，字段说明：苯并[a]蒽浓度，致癌性多环芳烃';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."chrysene" IS '单位：µg/L，字段说明：䓛浓度，多环芳烃类，具有潜在致癌性';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."benzo_k_fluoranthene" IS '单位：µg/L，字段说明：苯并[k]荧蒽浓度，致癌性多环芳烃';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."indeno_1_2_3_cd_pyrene" IS '单位：µg/L，字段说明：茚并[1,2,3-c,d]芘浓度，强致癌性多环芳烃';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."dibenzo_a_h_anthracene" IS '单位：µg/L，字段说明：二苯并[a,h]蒽浓度，高毒性多环芳烃';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."benzo_g_h_i_perylene" IS '单位：µg/L，字段说明：苯并[g,h,i]苝浓度，多环芳烃类，环境持久性污染物';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."chloromethane" IS '单位：µg/L，字段说明：氯甲烷浓度，用于生产甲基氯硅烷';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."dichloroethane_1_1" IS '单位：µg/L，字段说明：1,1-二氯乙烷浓度，与1,2-二氯乙烷互为同分异构体';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."tetrachloroethane_1_1_1_2" IS '单位：µg/L，字段说明：1,1,1,2-四氯乙烷浓度，四氯乙烷异构体';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."tetrachloroethane_1_1_2_2" IS '单位：µg/L，字段说明：1,1,2,2-四氯乙烷浓度，与1,1,1,2-四氯乙烷互为同分异构体';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."trichloropropane_1_2_3" IS '单位：µg/L，字段说明：1,2,3-三氯丙烷浓度，工业污染物，具有生殖毒性';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."analysis_number" IS '字段说明：分析编号，记录样品分析的唯一标识';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."original_number" IS '字段说明：原编号，样品的原始编号';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."carbonate" IS '单位：mg/L，字段说明：碳酸根离子浓度';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."bicarbonate" IS '单位：mg/L，字段说明：碳酸氢根离子浓度';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."ferrous_ion" IS '单位：mg/L，字段说明：亚铁离子浓度';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."potassium" IS '单位：mg/L，字段说明：钾离子浓度';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."calcium" IS '单位：mg/L，字段说明：钙离子浓度';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."magnesium" IS '单位：mg/L，字段说明：镁离子浓度';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."chloroprene" IS '单位：µg/L，字段说明：氯丁二烯（2-氯-1,3-丁二烯）浓度，常用于合成橡胶，具有挥发性';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."1,1-dichloropropene" IS '单位：µg/L，字段说明：1,1-二氯丙烯（1,1-二氯-1-丙烯）浓度，属于不饱和卤代烃';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."trans-1,3-dichloropropene" IS '单位：µg/L，字段说明：反式-1,3-二氯丙烯浓度，几何异构体，用于有机合成';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."cis-1,3-dichloropropene" IS '单位：µg/L，字段说明：顺式-1,3-二氯丙烯浓度，与反式结构互为同分异构体';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."2,2-dichloropropane" IS '单位：µg/L，字段说明：2,2-二氯丙烷（对称二氯丙烷）浓度，用于溶剂和化工原料';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."bromochloromethane" IS '单位：µg/L，字段说明：溴氯甲烷（氯溴甲烷）浓度，混合卤代烃，用作溶剂';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."bromodichloromethane" IS '单位：µg/L，字段说明：一溴二氯甲烷（二氯溴甲烷）浓度，饮用水中消毒副产物';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."dibromomethane" IS '单位：µg/L，字段说明：二溴甲烷浓度，用于有机合成和阻燃剂';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."1,1-dichloroethane" IS '单位：µg/L，字段说明：1,1-二氯乙烷浓度，与1,2-二氯乙烷互为同分异构体（原表已有，若重复需调整）';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."1,2-dibromoethane" IS '单位：µg/L，字段说明：1,2-二溴乙烷浓度，曾用作汽油抗震剂，有毒性';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."1,3-dichloropropane" IS '单位：µg/L，字段说明：1,3-二氯丙烷浓度，用于生产农药和医药中间体';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."dibromochloromethane" IS '单位：µg/L，字段说明：二溴氯甲烷浓度，三卤甲烷类消毒副产物';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."bromoform" IS '单位：µg/L，字段说明：溴仿（三溴甲烷）浓度，曾用作麻醉剂，饮用水污染物';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."1,1,1,2-tetrachloroethane" IS '单位：µg/L，字段说明：1,1,1,2-四氯乙烷浓度，与1,1,2,2-四氯乙烷互为同分异构体';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."1,1,2,2-tetrachloroethane" IS '单位：µg/L，字段说明：1,1,2,2-四氯乙烷浓度，用于溶剂和化工原料';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."1,2,3-trichloropropane" IS '单位：µg/L，字段说明：1,2,3-三氯丙烷浓度，原表已有此字段，需确认是否重复添加';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."cumene" IS '单位：µg/L，字段说明：异丙苯（枯烯）浓度，用于生产苯酚和丙酮';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."n_propylbenzene" IS '单位：µg/L，字段说明：正丙苯浓度，丙基苯的直链异构体，工业溶剂';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."bromobenzene" IS '单位：µg/L，字段说明：溴苯浓度，用于医药、农药中间体';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."1,3,5-trimethylbenzene" IS '单位：µg/L，字段说明：1,3,5-三甲基苯（均三甲苯）浓度，用于制备合成树脂';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."o_chlorotoluene" IS '单位：µg/L，字段说明：邻氯甲苯（2-氯甲苯）浓度，甲苯的邻位氯代物，有机合成原料';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."p_chlorotoluene" IS '单位：µg/L，字段说明：对氯甲苯（4-氯甲苯）浓度，用于生产医药、染料';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."tert_butylbenzene" IS '单位：µg/L，字段说明：叔丁基苯浓度，用于有机合成和香料工业';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."1,2,4-trimethylbenzene" IS '单位：µg/L，字段说明：1,2,4-三甲基苯（偏三甲苯）浓度，用于制备均苯三甲酸';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."sec_butylbenzene" IS '单位：µg/L，字段说明：仲丁基苯浓度，丁基苯的支链异构体，溶剂';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."4-isopropyltoluene" IS '单位：µg/L，字段说明：4-异丙基甲苯浓度，对异丙基甲苯，用于生产麝香香料';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."n_butylbenzene" IS '单位：µg/L，字段说明：正丁基苯浓度，丁基苯的直链异构体，工业溶剂';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."epichlorohydrin" IS '单位：µg/L，字段说明：环氧氯丙烷（3-氯-1,2-环氧丙烷）浓度，用于生产环氧树脂';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."1,2-dibromo-3-chloropropane" IS '单位：µg/L，字段说明：1,2-二溴-3-氯丙烷浓度，曾用作土壤熏蒸剂，剧毒';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."1,2,4-trichlorobenzene" IS '单位：µg/L，字段说明：1,2,4-三氯苯浓度，用于生产农药和染料';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."hexachlorobutadiene" IS '单位：µg/L，字段说明：六氯丁二烯浓度，全氯丁二烯，用作溶剂和热载体';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."1,2,3-trichlorobenzene" IS '单位：µg/L，字段说明：1,2,3-三氯苯浓度，三氯苯的三种异构体之一，工业污染物';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."2-methylnaphthalene" IS '单位：µg/L，字段说明：2-甲基萘浓度，属于多环芳烃';
COMMENT ON COLUMN "public"."t_water_quality_monitoring"."1-methylnaphthalene" IS '单位：µg/L，字段说明：1-甲基萘浓度，属于多环芳烃';

-- ----------------------------
-- Primary Key structure for table t_water_quality_monitoring
-- ----------------------------
ALTER TABLE "public"."t_water_quality_monitoring" ADD CONSTRAINT "t_water_quality_monitoring_pkey" PRIMARY KEY ("id");
