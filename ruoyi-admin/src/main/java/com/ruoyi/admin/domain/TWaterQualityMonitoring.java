package com.ruoyi.admin.domain;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 监测数据管理对象 t_water_quality_monitoring
 *
 * @author ruoyi
 * @date 2025-06-14
 */
public class TWaterQualityMonitoring extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 字段说明：监测点位编号，唯一标识监测位置，长度不超过10位字符
     */
    @ApiModelProperty( "点位编号")
    private String pointId;

    /**
     * 字段说明：样品说明，一天监测两次的情况，如上、下
     */
    @ApiModelProperty( "样品说明")
    private String tag;

    /**
     * 字段说明：采样时间，格式为YYYYMMDD（8位字符），如20250606
     */
    @ApiModelProperty( "采样时间")
    private String sampleTime;

    /**
     * 单位：度，字段说明：水的色度，表征水的颜色深浅
     */
    @ApiModelProperty( "色度")
    private String color;

    /**
     * 字段说明：水的臭和味描述，记录感官检测结果（如无异味、轻微腥臭等）
     */
    @ApiModelProperty( "臭和味")
    private String odorAndTaste;

    /**
     * 单位：NTU，字段说明：水的浊度，反映水中悬浮物和胶体物质的含量
     */
    @ApiModelProperty( "浑浊度") // J01-J31
    private String turbidity;

    /**
     * 字段说明：肉眼可见物描述，如泥沙、絮状物等
     */
    @ApiModelProperty( "肉眼可见物")
    private String visibleMatters;

    /**
     * 字段说明：pH值，表征水的酸碱性，无量纲
     */
    @ApiModelProperty( "pH")
    private String ph;

    /**
     * 单位：mg/L（以CaCO3计），字段说明：水的总硬度，钙镁离子折算为碳酸钙的总量
     */
    @ApiModelProperty( "总硬度 (以 CaCO3 计)")
    private String totalHardness;

    /**
     * 单位：mg/L，字段说明：溶解性总固体，水中溶解的无机盐和有机物总和
     */
    @ApiModelProperty( "溶解性总固体")
    private String totalDissolvedSolids;

    /**
     * 单位：mg/L，字段说明：硫酸根离子浓度
     */
    @ApiModelProperty( "硫酸盐")
    private String sulfate;

    /**
     * 单位：mg/L，字段说明：氯离子浓度
     */
    @ApiModelProperty( "氯化物")
    private String chloride;

    /**
     * 单位：mg/L，字段说明：铁离子浓度，以Fe计
     */
    @ApiModelProperty( "铁")
    private String iron;

    /**
     * 单位：mg/L，字段说明：锰离子浓度，以Mn计
     */
    @ApiModelProperty( "锰")
    private String manganese;

    /**
     * 单位：mg/L，字段说明：铝离子浓度，以Al计
     */
    @ApiModelProperty( "铝")
    private String aluminum;

    /**
     * 单位：mg/L，字段说明：阴离子表面活性剂浓度，如LAS（直链烷基苯磺酸盐）
     */
    @ApiModelProperty( "阴离子表面活性剂")
    private String anionicSurfactant;

    /**
     * 单位：mg/L，字段说明：耗氧量（COD），水中还原性物质消耗氧气的量
     */
    @ApiModelProperty( "耗氧量")
    private String oxygenConsumption;

    /**
     * 单位：mg/L，字段说明：硫化物浓度，以S²⁻计
     */
    @ApiModelProperty( "硫化物")
    private String sulfide;

    /**
     * 单位：mg/L，字段说明：钠离子浓度
     */
    @ApiModelProperty( "钠")
    private String sodium;

    /**
     * 单位：mg/L，字段说明：亚硝酸盐氮浓度，以N计
     */
    @ApiModelProperty( "亚硝酸盐") // J01-J31
    private String nitriteNitrogen;

    /**
     * 单位：mg/L，字段说明：硝酸盐氮浓度，以N计
     */
    @ApiModelProperty( "硝酸盐") // J01-J31
    private String nitrateNitrogen;

    /**
     * 单位：mg/L，字段说明：碘化物浓度，以I⁻计
     */
    @ApiModelProperty( "碘化物")
    private String iodide;

    /**
     * 单位：mg/L，字段说明：硒离子浓度，以Se计
     */
    @ApiModelProperty( "硒")
    private String selenium;

    /**
     * 单位：mg/L，字段说明：铜离子浓度，以Cu计
     */
    @ApiModelProperty( "铜")
    private String copper;

    /**
     * 单位：mg/L，字段说明：锌离子浓度，以Zn计
     */
    @ApiModelProperty( "锌")
    private String zinc;

    /**
     * 单位：mg/L，字段说明：挥发酚浓度，以苯酚计
     */
    @ApiModelProperty( "挥发酚")
    private String volatilePhenol;

    /**
     * 单位：mg/L，字段说明：氨氮浓度，以N计
     */
    @ApiModelProperty( "氨氮")
    private String ammoniaNitrogen;

    /**
     * 单位：mg/L，字段说明：氰化物浓度，以CN⁻计
     */
    @ApiModelProperty( "氰化物")
    private String cyanide;

    /**
     * 单位：mg/L，字段说明：氟化物浓度，以F⁻计
     */
    @ApiModelProperty( "氟化物")
    private String fluoride;

    /**
     * 单位：mg/L，字段说明：汞离子浓度，以Hg计
     */
    @ApiModelProperty( "汞")
    private String mercury;

    /**
     * 单位：mg/L，字段说明：砷离子浓度，以As计
     */
    @ApiModelProperty( "砷")
    private String arsenic;

    /**
     * 单位：mg/L，字段说明：镉离子浓度，以Cd计
     */
    @ApiModelProperty( "镉")
    private String cadmium;

    /**
     * 单位：mg/L，字段说明：六价铬浓度，以Cr⁶⁺计
     */
    @ApiModelProperty( "六价铬")
    private String chromiumVi;

    /**
     * 单位：mg/L，字段说明：铅离子浓度，以Pb计
     */
    @ApiModelProperty( "铅")
    private String lead;

    /**
     * 单位：µg/L，字段说明：氯仿（三氯甲烷）浓度，饮用水消毒副产物
     */
    @ApiModelProperty( "氯仿")
    private String chloroform;

    /**
     * 单位：µg/L，字段说明：四氯化碳浓度，有机溶剂，具有毒性
     */
    @ApiModelProperty( "四氯化碳")
    private String carbonTetrachloride;

    /**
     * 单位：µg/L，字段说明：苯浓度，致癌性挥发性有机物
     */
    @ApiModelProperty( "苯")
    private String benzene;

    /**
     * 单位：µg/L，字段说明：甲苯浓度，常见有机溶剂
     */
    @ApiModelProperty( "甲苯")
    private String toluene;

    /**
     * 单位: mg/L
     */
    @ApiModelProperty( "钼")
    private String molybdenum;

    /**
     * 单位: mg/L
     */
    @ApiModelProperty( "钒")
    private String vanadium;

    /**
     * 单位: mg/L
     */
    @ApiModelProperty( "钴")
    private String cobalt;

    /**
     * 单位: mg/L
     */
    @ApiModelProperty( "镍")
    private String nickel;

    /**
     * 单位：µg/L，字段说明：二氯甲烷浓度，用于涂料和胶黏剂
     */
    @ApiModelProperty( "二氯甲烷")
    private String dichloromethane;

    /**
     * 单位：µg/L，字段说明：1,2-二氯乙烷浓度，工业溶剂
     */
    @ApiModelProperty( "1,2 - 二氯乙烷")
    private String dichloroethane12;

    /**
     * 单位：µg/L，字段说明：1,1,1-三氯乙烷浓度，曾用作风幕机清洗剂
     */
    @ApiModelProperty( "1,1,1 - 三氯乙烷")
    private String trichloroethane111;

    /**
     * 单位：µg/L，字段说明：1,1,2-三氯乙烷浓度，与1,1,1-三氯乙烷互为同分异构体
     */
    @ApiModelProperty( "1,1,2 - 三氯乙烷")
    private String trichloroethane112;

    /**
     * 单位：µg/L，字段说明：1,2-二氯丙烷浓度，用于有机合成
     */
    @ApiModelProperty( "1,2 - 二氯丙烷")
    private String dichloropropane12;

    /**
     * 单位：µg/L，字段说明：氯乙烯浓度，用于生产聚氯乙烯（PVC）
     */
    @ApiModelProperty( "氯乙烯")
    private String vinylChloride;

    /**
     * 单位：µg/L，字段说明：1,1-二氯乙烯浓度，几何异构体
     */
    @ApiModelProperty( "1,1 - 二氯乙烯")
    private String dichloroethylene11;

    /**
     * 单位：µg/L，字段说明：反式-1,2-二氯乙烯浓度，氯乙烯异构体
     */
    @ApiModelProperty( "反式 - 1,2 - 二氯乙烯")
    private String transDichloroethylene12;

    /**
     * 单位：µg/L，字段说明：顺式-1,2-二氯乙烯浓度，与反式结构互为同分异构体
     */
    @ApiModelProperty( "顺式 - 1,2 - 二氯乙烯")
    private String cisDichloroethylene12;

    /**
     * 单位：µg/L，字段说明：三氯乙烯浓度，金属脱脂剂，具有肝毒性
     */
    @ApiModelProperty( "三氯乙烯")
    private String trichloroethylene;

    /**
     * 单位：µg/L，字段说明：四氯乙烯浓度，干洗剂主要成分
     */
    @ApiModelProperty( "四氯乙烯")
    private String tetrachloroethylene;

    /**
     * 单位：µg/L，字段说明：氯苯浓度，用于生产农药和染料
     */
    @ApiModelProperty( "氯苯")
    private String chlorobenzene;

    /**
     * 单位：µg/L，字段说明：1,2-二氯苯浓度，邻二氯苯，用于杀虫剂
     */
    @ApiModelProperty( "1,2 - 二氯苯")
    private String dichlorobenzene12;

    /**
     * 单位：µg/L，字段说明：1,4-二氯苯浓度，对二氯苯，防虫剂
     */
    @ApiModelProperty( "1,4 - 二氯苯")
    private String dichlorobenzene14;

    /**
     * 单位：µg/L，字段说明：乙苯浓度，用于生产苯乙烯
     */
    @ApiModelProperty( "乙苯")
    private String ethylbenzene;

    /**
     * 单位：µg/L，字段说明：邻二甲苯浓度，二甲苯异构体之一
     */
    @ApiModelProperty( "邻 - 二甲苯") // J01-J31
    private String xyleneOrtho;

    /**
     * 单位：µg/L，字段说明：间/对-二甲苯浓度，二甲苯异构体混合物
     */
    @ApiModelProperty( "间，对 - 二甲苯") // J01-J31
    private String xyleneMetaPara;

    /**
     * 单位：µg/L，字段说明：苯乙烯浓度，用于生产塑料和橡胶
     */
    @ApiModelProperty( "苯乙烯")
    private String styrene;

    /**
     * 单位：µg/L，字段说明：硝基苯浓度，工业污染物，具有毒性
     */
    @ApiModelProperty( "硝基苯")
    private String nitrobenzene;

    /**
     * 单位：µg/L，字段说明：萘浓度，多环芳烃类化合物，常见于煤焦油
     */
    @ApiModelProperty( "萘")
    private String naphthalene;

    /**
     * 单位：µg/L，字段说明：蒽浓度，多环芳烃类，用于生产染料
     */
    @ApiModelProperty( "蒽")
    private String anthracene;

    /**
     * 单位：µg/L，字段说明：荧蒽浓度，多环芳烃类，环境中常见污染物
     */
    @ApiModelProperty( "荧蒽")
    private String fluoranthene;

    /**
     * 单位：µg/L，字段说明：苯并[b]荧蒽浓度，致癌性多环芳烃
     */
    @ApiModelProperty( "苯并 [b] 荧蒽")
    private String benzoBFluoranthene;

    /**
     * 单位：µg/L，字段说明：苯并[a]芘浓度，强致癌性多环芳烃
     */
    @ApiModelProperty( "苯并 [a] 芘")
    private String benzoAPyrene;

    /**
     * 单位：mg/L，字段说明：石油烃（C6-C9）浓度，挥发性石油烃组分
     */
    @ApiModelProperty( "石油烃 (C6-C9)")
    private String petroleumHydrocarbonsC6C9;

    /**
     * 单位：mg/L，字段说明：石油烃（C10-C40）浓度，半挥发性石油烃组分
     */
    @ApiModelProperty( "石油烃（C10-C40）")
    private String petroleumHydrocarbonsC10C40;

    /**
     * 单位：mg/L，字段说明：苯酚浓度，具有腐蚀性和毒性
     */
    @ApiModelProperty( "苯酚")
    private String phenol;

    /**
     * 单位：mg/L，字段说明：2-氯酚浓度，苯酚的氯代衍生物
     */
    @ApiModelProperty( "2 - 氯酚")
    private String chlorophenol2;

    /**
     * 单位：µg/L，字段说明：苯胺浓度，用于生产染料和医药，有毒
     */
    @ApiModelProperty( "苯胺")
    private String aniline;

    /**
     * 单位：µg/L，字段说明：苊烯浓度，多环芳烃类，存在于煤烟中
     */
    @ApiModelProperty( "苊烯")
    private String acenaphthylene;

    /**
     * 单位：µg/L，字段说明：苊浓度，多环芳烃类，用于生产树脂
     */
    @ApiModelProperty( "苊")
    private String acenaphthene;

    /**
     * 单位：µg/L，字段说明：芴浓度，多环芳烃类，用于塑料工业
     */
    @ApiModelProperty( "芴")
    private String fluorene;

    /**
     * 单位：µg/L，字段说明：菲浓度，多环芳烃类，存在于烟草烟雾中
     */
    @ApiModelProperty( "菲")
    private String phenanthrene;

    /**
     * 单位：µg/L，字段说明：芘浓度，多环芳烃类，环境污染物
     */
    @ApiModelProperty( "芘")
    private String pyrene;

    /**
     * 单位：µg/L，字段说明：苯并[a]蒽浓度，致癌性多环芳烃
     */
    @ApiModelProperty( "苯并 [a] 蒽")
    private String benzoAAnthracene;

    /**
     * 单位：µg/L，字段说明：䓛浓度，多环芳烃类，具有潜在致癌性
     */
    @ApiModelProperty( "䓛")
    private String chrysene;

    /**
     * 单位：µg/L，字段说明：苯并[k]荧蒽浓度，致癌性多环芳烃
     */
    @ApiModelProperty( "苯并 [k] 荧蒽")
    private String benzoKFluoranthene;

    /**
     * 单位：µg/L，字段说明：茚并[1,2,3-c,d]芘浓度，强致癌性多环芳烃
     */
    @ApiModelProperty( "茚并 [1,2,3-c,d] 芘")
    private String indeno123CdPyrene;

    /**
     * 单位：µg/L，字段说明：二苯并[a,h]蒽浓度，高毒性多环芳烃
     */
    @ApiModelProperty( "二苯并 [a,h] 蒽")
    private String dibenzoAHAnthracene;

    /**
     * 单位：µg/L，字段说明：苯并[g,h,i]苝浓度，多环芳烃类，环境持久性污染物
     */
    @ApiModelProperty( "苯并 [g,h,i] 苝")
    private String benzoGHIPerylene;

    /**
     * 单位：µg/L，字段说明：氯甲烷浓度，用于生产甲基氯硅烷
     */
    @ApiModelProperty( "氯甲烷")
    private String chloromethane;

    /**
     * 单位：µg/L，字段说明：1,1-二氯乙烷浓度，与1,2-二氯乙烷互为同分异构体
     */
    @ApiModelProperty( "1,1 - 二氯乙烷") // 注意空格
    private String dichloroethane11;

    /**
     * 单位：µg/L，字段说明：1,1,1,2-四氯乙烷浓度，四氯乙烷异构体
     */
    @ApiModelProperty( "1,1,1,2 - 四氯乙烷")
    private String tetrachloroethane1112;

    /**
     * 单位：µg/L，字段说明：1,1,2,2-四氯乙烷浓度，与1,1,1,2-四氯乙烷互为同分异构体
     */
    @ApiModelProperty( "1,1,2,2 - 四氯乙烷") // 注意空格
    private String tetrachloroethane1122;

    /**
     * 单位：µg/L，字段说明：1,2,3-三氯丙烷浓度，工业污染物，具有生殖毒性
     */
    @ApiModelProperty( "1,2,3 - 三氯丙烷")
    private String trichloropropane123;

    /**
     * $column.columnComment
     */
    private String id;

    /**
     * 字段说明：分析编号，记录样品分析的唯一标识
     */
    @ApiModelProperty( "分析编号")
    private String analysisNumber;

    /**
     * 字段说明：原编号，样品的原始编号
     */
    @ApiModelProperty( "原编号")
    private String originalNumber;

    /**
     * 单位：mg/L，字段说明：碳酸根离子浓度
     */
    @ApiModelProperty( "碳酸根")
    private String carbonate;

    /**
     * 单位：mg/L，字段说明：碳酸氢根离子浓度
     */
    @ApiModelProperty( "碳酸氢根")
    private String bicarbonate;

    /**
     * 单位：mg/L，字段说明：亚铁离子浓度
     */
    @ApiModelProperty( "亚铁离子")
    private String ferrousIon;

    /**
     * 单位：mg/L，字段说明：钾离子浓度
     */
    @ApiModelProperty( "钾")
    private String potassium;

    /**
     * 单位：mg/L，字段说明：钙离子浓度
     */
    @ApiModelProperty( "钙")
    private String calcium;

    /**
     * 单位：mg/L，字段说明：镁离子浓度
     */
    @ApiModelProperty( "镁")
    private String magnesium;

    /**
     * 单位：µg/L，字段说明：氯丁二烯（2-氯-1,3-丁二烯）浓度，常用于合成橡胶，具有挥发性
     */
    @ApiModelProperty( "氯丁二烯")
    private String chloroprene;

    /**
     * 单位：µg/L，字段说明：溴氯甲烷（氯溴甲烷）浓度，混合卤代烃，用作溶剂
     */
    @ApiModelProperty( "溴氯甲烷")
    private String bromochloromethane;

    /**
     * 单位：µg/L，字段说明：一溴二氯甲烷（二氯溴甲烷）浓度，饮用水中消毒副产物
     */
    @ApiModelProperty( "一溴二氯甲烷")
    private String bromodichloromethane;

    /**
     * 单位：µg/L，字段说明：二溴甲烷浓度，用于有机合成和阻燃剂
     */
    @ApiModelProperty( "二溴甲烷")
    private String dibromomethane;

    /**
     * 单位：µg/L，字段说明：二溴氯甲烷浓度，三卤甲烷类消毒副产物
     */
    @ApiModelProperty( "二溴氯甲烷")
    private String dibromochloromethane;

    /**
     * 单位：µg/L，字段说明：溴仿（三溴甲烷）浓度，曾用作麻醉剂，饮用水污染物
     */
    @ApiModelProperty( "溴仿")
    private String bromoform;

    /**
     * 单位：µg/L，字段说明：异丙苯（枯烯）浓度，用于生产苯酚和丙酮
     */
    @ApiModelProperty( "异丙苯")
    private String cumene;

    /**
     * 单位：µg/L，字段说明：正丙苯浓度，丙基苯的直链异构体，工业溶剂
     */
    @ApiModelProperty( "正丙苯")
    private String nPropylbenzene;

    /**
     * 单位：µg/L，字段说明：溴苯浓度，用于医药、农药中间体
     */
    @ApiModelProperty( "溴苯")
    private String bromobenzene;

    /**
     * 单位：µg/L，字段说明：1,3,5-三甲基苯（均三甲苯）浓度，用于制备合成树脂
     */
    @ApiModelProperty( "1,3,5 - 三甲基苯")
    private String trimethylbenzene135;

    /**
     * 单位：µg/L，字段说明：邻氯甲苯（2-氯甲苯）浓度，甲苯的邻位氯代物，有机合成原料
     */
    @ApiModelProperty( "邻氯甲苯")
    private String oChlorotoluene;

    /**
     * 单位：µg/L，字段说明：对氯甲苯（4-氯甲苯）浓度，用于生产医药、染料
     */
    @ApiModelProperty( "对氯甲苯")
    private String pChlorotoluene;

    /**
     * 单位：µg/L，字段说明：叔丁基苯浓度，用于有机合成和香料工业
     */
    @ApiModelProperty( "叔丁基苯")
    private String tertButylbenzene;

    /**
     * 单位：µg/L，字段说明：1,2,4-三甲基苯（偏三甲苯）浓度，用于制备均苯三甲酸
     */
    @ApiModelProperty( "1,2,4 - 三甲基苯")
    private String trimethylbenzene124;

    /**
     * 单位：µg/L，字段说明：仲丁基苯浓度，丁基苯的支链异构体，溶剂
     */
    @ApiModelProperty( "仲丁基苯")
    private String secButylbenzene;

    /**
     * 单位：µg/L，字段说明：4-异丙基甲苯浓度，对异丙基甲苯，用于生产麝香香料
     */
    @ApiModelProperty( "4 - 异丙基甲苯")
    private String isopropyltoluene4;

    /**
     * 单位：µg/L，字段说明：正丁基苯浓度，丁基苯的直链异构体，工业溶剂
     */
    @ApiModelProperty( "正丁基苯")
    private String nButylbenzene;

    /**
     * 单位：µg/L，字段说明：环氧氯丙烷（3-氯-1,2-环氧丙烷）浓度，用于生产环氧树脂
     */
    @ApiModelProperty( "环氧氯丙烷")
    private String epichlorohydrin;

    /**
     * 单位：µg/L，字段说明：1,2,4-三甲基苯（偏三甲苯）浓度，用于制备均苯三甲酸
     */
    @ApiModelProperty( "1,2,4 - 三甲基苯")
    private String trimethylbenzene14; // 注意此处为 trimethylbenzene124，而非 trimethylbenzene14

    /**
     * 单位：µg/L，字段说明：1,2-二溴-3-氯丙烷浓度，曾用作土壤熏蒸剂，剧毒
     */
    @ApiModelProperty( "1,2 - 二溴 - 3 - 氯丙烷")
    private String dibromo3Chloropropane12;

    /**
     * 单位：µg/L，字段说明：1,2,4-三氯苯浓度，用于生产农药和染料
     */
    @ApiModelProperty( "1,2,4 - 三氯苯")
    private String trichlorobenzene124;

    /**
     * 单位：µg/L，字段说明：六氯丁二烯浓度，全氯丁二烯，用作溶剂和热载体
     */
    @ApiModelProperty( "六氯丁二烯")
    private String hexachlorobutadiene;

    /**
     * 单位：µg/L，字段说明：2-甲基萘浓度，属于多环芳烃
     */
    @ApiModelProperty( "2 - 甲基萘")
    private String methylnaphthalene2;

    /**
     * 单位：µg/L，字段说明：1-甲基萘浓度，属于多环芳烃
     */
    @ApiModelProperty( "1 - 甲基萘")
    private String methylnaphthalene1;

    /**
     * 单位：µg/L，字段说明：1,1-二氯丙烯（1,1-二氯-1-丙烯）浓度，属于不饱和卤代烃
     */
    @ApiModelProperty( "1,1 - 二氯丙烯")
    private String dichloropropene11;

    /**
     * 单位：µg/L，字段说明：反式-1,3-二氯丙烯浓度，几何异构体，用于有机合成
     */
    @ApiModelProperty( "反式 - 1,3 - 二氯丙烯")
    private String trans13Dichloropropene;

    /**
     * 单位：µg/L，字段说明：顺式-1,3-二氯丙烯浓度，与反式结构互为同分异构体
     */
    @ApiModelProperty( "顺式 - 1,3 - 二氯丙烯")
    private String cis13Dichloropropene;

    /**
     * 单位：µg/L，字段说明：2,2-二氯丙烷（对称二氯丙烷）浓度，用于溶剂和化工原料
     */
    @ApiModelProperty( "2,2 - 二氯丙烷")
    private String dichloropropane22;

    /**
     * 单位：µg/L，字段说明：1,2-二溴乙烷浓度，曾用作汽油抗震剂，有毒性
     */
    @ApiModelProperty( "1,2 - 二溴乙烷")
    private String dibromoethane12;

    /**
     * 单位：µg/L，字段说明：1,3-二氯丙烷浓度，用于生产农药和医药中间体
     */
    @ApiModelProperty( "1,3 - 二氯丙烷")
    private String dichloropropane13;

    /**
     * 2-氯甲苯
     */
    @ApiModelProperty( "2 - 氯甲苯")
    private String chlorotoluene2;

    /**
     * 4-氯甲苯
     */
    @ApiModelProperty( "4 - 氯甲苯")
    private String chlorotoluene4;

    /**
     * 1,3-二氯苯
     */
    @ApiModelProperty( "1,3 - 二氯苯")
    private String dichlorobenzene13;

    /**
     * 1,2,3-三氯苯
     */
    @ApiModelProperty( "1,2,3 - 三氯苯")
    private String trichlorobenzene123;

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public String getPointId() {
        return pointId;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setSampleTime(String sampleTime) {
        this.sampleTime = sampleTime;
    }

    public String getSampleTime() {
        return sampleTime;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setOdorAndTaste(String odorAndTaste) {
        this.odorAndTaste = odorAndTaste;
    }

    public String getOdorAndTaste() {
        return odorAndTaste;
    }

    public void setTurbidity(String turbidity) {
        this.turbidity = turbidity;
    }

    public String getTurbidity() {
        return turbidity;
    }

    public void setVisibleMatters(String visibleMatters) {
        this.visibleMatters = visibleMatters;
    }

    public String getVisibleMatters() {
        return visibleMatters;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getPh() {
        return ph;
    }

    public void setTotalHardness(String totalHardness) {
        this.totalHardness = totalHardness;
    }

    public String getTotalHardness() {
        return totalHardness;
    }

    public void setTotalDissolvedSolids(String totalDissolvedSolids) {
        this.totalDissolvedSolids = totalDissolvedSolids;
    }

    public String getTotalDissolvedSolids() {
        return totalDissolvedSolids;
    }

    public void setSulfate(String sulfate) {
        this.sulfate = sulfate;
    }

    public String getSulfate() {
        return sulfate;
    }

    public void setChloride(String chloride) {
        this.chloride = chloride;
    }

    public String getChloride() {
        return chloride;
    }

    public void setIron(String iron) {
        this.iron = iron;
    }

    public String getIron() {
        return iron;
    }

    public void setManganese(String manganese) {
        this.manganese = manganese;
    }

    public String getManganese() {
        return manganese;
    }

    public void setAluminum(String aluminum) {
        this.aluminum = aluminum;
    }

    public String getAluminum() {
        return aluminum;
    }

    public void setAnionicSurfactant(String anionicSurfactant) {
        this.anionicSurfactant = anionicSurfactant;
    }

    public String getAnionicSurfactant() {
        return anionicSurfactant;
    }

    public void setOxygenConsumption(String oxygenConsumption) {
        this.oxygenConsumption = oxygenConsumption;
    }

    public String getOxygenConsumption() {
        return oxygenConsumption;
    }

    public void setSulfide(String sulfide) {
        this.sulfide = sulfide;
    }

    public String getSulfide() {
        return sulfide;
    }

    public void setSodium(String sodium) {
        this.sodium = sodium;
    }

    public String getSodium() {
        return sodium;
    }

    public void setNitriteNitrogen(String nitriteNitrogen) {
        this.nitriteNitrogen = nitriteNitrogen;
    }

    public String getNitriteNitrogen() {
        return nitriteNitrogen;
    }

    public void setNitrateNitrogen(String nitrateNitrogen) {
        this.nitrateNitrogen = nitrateNitrogen;
    }

    public String getNitrateNitrogen() {
        return nitrateNitrogen;
    }

    public void setIodide(String iodide) {
        this.iodide = iodide;
    }

    public String getIodide() {
        return iodide;
    }

    public void setSelenium(String selenium) {
        this.selenium = selenium;
    }

    public String getSelenium() {
        return selenium;
    }

    public void setCopper(String copper) {
        this.copper = copper;
    }

    public String getCopper() {
        return copper;
    }

    public void setZinc(String zinc) {
        this.zinc = zinc;
    }

    public String getZinc() {
        return zinc;
    }

    public void setVolatilePhenol(String volatilePhenol) {
        this.volatilePhenol = volatilePhenol;
    }

    public String getVolatilePhenol() {
        return volatilePhenol;
    }

    public void setAmmoniaNitrogen(String ammoniaNitrogen) {
        this.ammoniaNitrogen = ammoniaNitrogen;
    }

    public String getAmmoniaNitrogen() {
        return ammoniaNitrogen;
    }

    public void setCyanide(String cyanide) {
        this.cyanide = cyanide;
    }

    public String getCyanide() {
        return cyanide;
    }

    public void setFluoride(String fluoride) {
        this.fluoride = fluoride;
    }

    public String getFluoride() {
        return fluoride;
    }

    public void setMercury(String mercury) {
        this.mercury = mercury;
    }

    public String getMercury() {
        return mercury;
    }

    public void setArsenic(String arsenic) {
        this.arsenic = arsenic;
    }

    public String getArsenic() {
        return arsenic;
    }

    public void setCadmium(String cadmium) {
        this.cadmium = cadmium;
    }

    public String getCadmium() {
        return cadmium;
    }

    public void setChromiumVi(String chromiumVi) {
        this.chromiumVi = chromiumVi;
    }

    public String getChromiumVi() {
        return chromiumVi;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String getLead() {
        return lead;
    }

    public void setChloroform(String chloroform) {
        this.chloroform = chloroform;
    }

    public String getChloroform() {
        return chloroform;
    }

    public void setCarbonTetrachloride(String carbonTetrachloride) {
        this.carbonTetrachloride = carbonTetrachloride;
    }

    public String getCarbonTetrachloride() {
        return carbonTetrachloride;
    }

    public void setBenzene(String benzene) {
        this.benzene = benzene;
    }

    public String getBenzene() {
        return benzene;
    }

    public void setToluene(String toluene) {
        this.toluene = toluene;
    }

    public String getToluene() {
        return toluene;
    }

    public void setMolybdenum(String molybdenum) {
        this.molybdenum = molybdenum;
    }

    public String getMolybdenum() {
        return molybdenum;
    }

    public void setVanadium(String vanadium) {
        this.vanadium = vanadium;
    }

    public String getVanadium() {
        return vanadium;
    }

    public void setCobalt(String cobalt) {
        this.cobalt = cobalt;
    }

    public String getCobalt() {
        return cobalt;
    }

    public void setNickel(String nickel) {
        this.nickel = nickel;
    }

    public String getNickel() {
        return nickel;
    }

    public void setDichloromethane(String dichloromethane) {
        this.dichloromethane = dichloromethane;
    }

    public String getDichloromethane() {
        return dichloromethane;
    }

    public void setDichloroethane12(String dichloroethane12) {
        this.dichloroethane12 = dichloroethane12;
    }

    public String getDichloroethane12() {
        return dichloroethane12;
    }

    public void setTrichloroethane111(String trichloroethane111) {
        this.trichloroethane111 = trichloroethane111;
    }

    public String getTrichloroethane111() {
        return trichloroethane111;
    }

    public void setTrichloroethane112(String trichloroethane112) {
        this.trichloroethane112 = trichloroethane112;
    }

    public String getTrichloroethane112() {
        return trichloroethane112;
    }

    public void setDichloropropane12(String dichloropropane12) {
        this.dichloropropane12 = dichloropropane12;
    }

    public String getDichloropropane12() {
        return dichloropropane12;
    }

    public void setVinylChloride(String vinylChloride) {
        this.vinylChloride = vinylChloride;
    }

    public String getVinylChloride() {
        return vinylChloride;
    }

    public void setDichloroethylene11(String dichloroethylene11) {
        this.dichloroethylene11 = dichloroethylene11;
    }

    public String getDichloroethylene11() {
        return dichloroethylene11;
    }

    public void setTransDichloroethylene12(String transDichloroethylene12) {
        this.transDichloroethylene12 = transDichloroethylene12;
    }

    public String getTransDichloroethylene12() {
        return transDichloroethylene12;
    }

    public void setCisDichloroethylene12(String cisDichloroethylene12) {
        this.cisDichloroethylene12 = cisDichloroethylene12;
    }

    public String getCisDichloroethylene12() {
        return cisDichloroethylene12;
    }

    public void setTrichloroethylene(String trichloroethylene) {
        this.trichloroethylene = trichloroethylene;
    }

    public String getTrichloroethylene() {
        return trichloroethylene;
    }

    public void setTetrachloroethylene(String tetrachloroethylene) {
        this.tetrachloroethylene = tetrachloroethylene;
    }

    public String getTetrachloroethylene() {
        return tetrachloroethylene;
    }

    public void setChlorobenzene(String chlorobenzene) {
        this.chlorobenzene = chlorobenzene;
    }

    public String getChlorobenzene() {
        return chlorobenzene;
    }

    public void setDichlorobenzene12(String dichlorobenzene12) {
        this.dichlorobenzene12 = dichlorobenzene12;
    }

    public String getDichlorobenzene12() {
        return dichlorobenzene12;
    }

    public void setDichlorobenzene14(String dichlorobenzene14) {
        this.dichlorobenzene14 = dichlorobenzene14;
    }

    public String getDichlorobenzene14() {
        return dichlorobenzene14;
    }

    public void setEthylbenzene(String ethylbenzene) {
        this.ethylbenzene = ethylbenzene;
    }

    public String getEthylbenzene() {
        return ethylbenzene;
    }

    public void setXyleneOrtho(String xyleneOrtho) {
        this.xyleneOrtho = xyleneOrtho;
    }

    public String getXyleneOrtho() {
        return xyleneOrtho;
    }

    public void setXyleneMetaPara(String xyleneMetaPara) {
        this.xyleneMetaPara = xyleneMetaPara;
    }

    public String getXyleneMetaPara() {
        return xyleneMetaPara;
    }

    public void setStyrene(String styrene) {
        this.styrene = styrene;
    }

    public String getStyrene() {
        return styrene;
    }

    public void setNitrobenzene(String nitrobenzene) {
        this.nitrobenzene = nitrobenzene;
    }

    public String getNitrobenzene() {
        return nitrobenzene;
    }

    public void setNaphthalene(String naphthalene) {
        this.naphthalene = naphthalene;
    }

    public String getNaphthalene() {
        return naphthalene;
    }

    public void setAnthracene(String anthracene) {
        this.anthracene = anthracene;
    }

    public String getAnthracene() {
        return anthracene;
    }

    public void setFluoranthene(String fluoranthene) {
        this.fluoranthene = fluoranthene;
    }

    public String getFluoranthene() {
        return fluoranthene;
    }

    public void setBenzoBFluoranthene(String benzoBFluoranthene) {
        this.benzoBFluoranthene = benzoBFluoranthene;
    }

    public String getBenzoBFluoranthene() {
        return benzoBFluoranthene;
    }

    public void setBenzoAPyrene(String benzoAPyrene) {
        this.benzoAPyrene = benzoAPyrene;
    }

    public String getBenzoAPyrene() {
        return benzoAPyrene;
    }

    public void setPetroleumHydrocarbonsC6C9(String petroleumHydrocarbonsC6C9) {
        this.petroleumHydrocarbonsC6C9 = petroleumHydrocarbonsC6C9;
    }

    public String getPetroleumHydrocarbonsC6C9() {
        return petroleumHydrocarbonsC6C9;
    }

    public void setPetroleumHydrocarbonsC10C40(String petroleumHydrocarbonsC10C40) {
        this.petroleumHydrocarbonsC10C40 = petroleumHydrocarbonsC10C40;
    }

    public String getPetroleumHydrocarbonsC10C40() {
        return petroleumHydrocarbonsC10C40;
    }

    public void setPhenol(String phenol) {
        this.phenol = phenol;
    }

    public String getPhenol() {
        return phenol;
    }

    public void setChlorophenol2(String chlorophenol2) {
        this.chlorophenol2 = chlorophenol2;
    }

    public String getChlorophenol2() {
        return chlorophenol2;
    }

    public void setAniline(String aniline) {
        this.aniline = aniline;
    }

    public String getAniline() {
        return aniline;
    }

    public void setAcenaphthylene(String acenaphthylene) {
        this.acenaphthylene = acenaphthylene;
    }

    public String getAcenaphthylene() {
        return acenaphthylene;
    }

    public void setAcenaphthene(String acenaphthene) {
        this.acenaphthene = acenaphthene;
    }

    public String getAcenaphthene() {
        return acenaphthene;
    }

    public void setFluorene(String fluorene) {
        this.fluorene = fluorene;
    }

    public String getFluorene() {
        return fluorene;
    }

    public void setPhenanthrene(String phenanthrene) {
        this.phenanthrene = phenanthrene;
    }

    public String getPhenanthrene() {
        return phenanthrene;
    }

    public void setPyrene(String pyrene) {
        this.pyrene = pyrene;
    }

    public String getPyrene() {
        return pyrene;
    }

    public void setBenzoAAnthracene(String benzoAAnthracene) {
        this.benzoAAnthracene = benzoAAnthracene;
    }

    public String getBenzoAAnthracene() {
        return benzoAAnthracene;
    }

    public void setChrysene(String chrysene) {
        this.chrysene = chrysene;
    }

    public String getChrysene() {
        return chrysene;
    }

    public void setBenzoKFluoranthene(String benzoKFluoranthene) {
        this.benzoKFluoranthene = benzoKFluoranthene;
    }

    public String getBenzoKFluoranthene() {
        return benzoKFluoranthene;
    }

    public void setIndeno123CdPyrene(String indeno123CdPyrene) {
        this.indeno123CdPyrene = indeno123CdPyrene;
    }

    public String getIndeno123CdPyrene() {
        return indeno123CdPyrene;
    }

    public void setDibenzoAHAnthracene(String dibenzoAHAnthracene) {
        this.dibenzoAHAnthracene = dibenzoAHAnthracene;
    }

    public String getDibenzoAHAnthracene() {
        return dibenzoAHAnthracene;
    }

    public void setBenzoGHIPerylene(String benzoGHIPerylene) {
        this.benzoGHIPerylene = benzoGHIPerylene;
    }

    public String getBenzoGHIPerylene() {
        return benzoGHIPerylene;
    }

    public void setChloromethane(String chloromethane) {
        this.chloromethane = chloromethane;
    }

    public String getChloromethane() {
        return chloromethane;
    }

    public void setDichloroethane11(String dichloroethane11) {
        this.dichloroethane11 = dichloroethane11;
    }

    public String getDichloroethane11() {
        return dichloroethane11;
    }

    public void setTetrachloroethane1112(String tetrachloroethane1112) {
        this.tetrachloroethane1112 = tetrachloroethane1112;
    }

    public String getTetrachloroethane1112() {
        return tetrachloroethane1112;
    }

    public void setTetrachloroethane1122(String tetrachloroethane1122) {
        this.tetrachloroethane1122 = tetrachloroethane1122;
    }

    public String getTetrachloroethane1122() {
        return tetrachloroethane1122;
    }

    public void setTrichloropropane123(String trichloropropane123) {
        this.trichloropropane123 = trichloropropane123;
    }

    public String getTrichloropropane123() {
        return trichloropropane123;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setAnalysisNumber(String analysisNumber) {
        this.analysisNumber = analysisNumber;
    }

    public String getAnalysisNumber() {
        return analysisNumber;
    }

    public void setOriginalNumber(String originalNumber) {
        this.originalNumber = originalNumber;
    }

    public String getOriginalNumber() {
        return originalNumber;
    }

    public void setCarbonate(String carbonate) {
        this.carbonate = carbonate;
    }

    public String getCarbonate() {
        return carbonate;
    }

    public void setBicarbonate(String bicarbonate) {
        this.bicarbonate = bicarbonate;
    }

    public String getBicarbonate() {
        return bicarbonate;
    }

    public void setFerrousIon(String ferrousIon) {
        this.ferrousIon = ferrousIon;
    }

    public String getFerrousIon() {
        return ferrousIon;
    }

    public void setPotassium(String potassium) {
        this.potassium = potassium;
    }

    public String getPotassium() {
        return potassium;
    }

    public void setCalcium(String calcium) {
        this.calcium = calcium;
    }

    public String getCalcium() {
        return calcium;
    }

    public void setMagnesium(String magnesium) {
        this.magnesium = magnesium;
    }

    public String getMagnesium() {
        return magnesium;
    }

    public void setChloroprene(String chloroprene) {
        this.chloroprene = chloroprene;
    }

    public String getChloroprene() {
        return chloroprene;
    }

    public void setBromochloromethane(String bromochloromethane) {
        this.bromochloromethane = bromochloromethane;
    }

    public String getBromochloromethane() {
        return bromochloromethane;
    }

    public void setBromodichloromethane(String bromodichloromethane) {
        this.bromodichloromethane = bromodichloromethane;
    }

    public String getBromodichloromethane() {
        return bromodichloromethane;
    }

    public void setDibromomethane(String dibromomethane) {
        this.dibromomethane = dibromomethane;
    }

    public String getDibromomethane() {
        return dibromomethane;
    }

    public void setDibromochloromethane(String dibromochloromethane) {
        this.dibromochloromethane = dibromochloromethane;
    }

    public String getDibromochloromethane() {
        return dibromochloromethane;
    }

    public void setBromoform(String bromoform) {
        this.bromoform = bromoform;
    }

    public String getBromoform() {
        return bromoform;
    }

    public void setCumene(String cumene) {
        this.cumene = cumene;
    }

    public String getCumene() {
        return cumene;
    }

    public void setnPropylbenzene(String nPropylbenzene) {
        this.nPropylbenzene = nPropylbenzene;
    }

    public String getnPropylbenzene() {
        return nPropylbenzene;
    }

    public void setBromobenzene(String bromobenzene) {
        this.bromobenzene = bromobenzene;
    }

    public String getBromobenzene() {
        return bromobenzene;
    }

    public void setTrimethylbenzene135(String trimethylbenzene135) {
        this.trimethylbenzene135 = trimethylbenzene135;
    }

    public String getTrimethylbenzene135() {
        return trimethylbenzene135;
    }

    public void setoChlorotoluene(String oChlorotoluene) {
        this.oChlorotoluene = oChlorotoluene;
    }

    public String getoChlorotoluene() {
        return oChlorotoluene;
    }

    public void setpChlorotoluene(String pChlorotoluene) {
        this.pChlorotoluene = pChlorotoluene;
    }

    public String getpChlorotoluene() {
        return pChlorotoluene;
    }

    public void setTertButylbenzene(String tertButylbenzene) {
        this.tertButylbenzene = tertButylbenzene;
    }

    public String getTertButylbenzene() {
        return tertButylbenzene;
    }

    public void setTrimethylbenzene14(String trimethylbenzene14) {
        this.trimethylbenzene14 = trimethylbenzene14;
    }

    public String getTrimethylbenzene14() {
        return trimethylbenzene14;
    }

    public void setSecButylbenzene(String secButylbenzene) {
        this.secButylbenzene = secButylbenzene;
    }

    public String getSecButylbenzene() {
        return secButylbenzene;
    }

    public void setIsopropyltoluene4(String isopropyltoluene4) {
        this.isopropyltoluene4 = isopropyltoluene4;
    }

    public String getIsopropyltoluene4() {
        return isopropyltoluene4;
    }

    public void setnButylbenzene(String nButylbenzene) {
        this.nButylbenzene = nButylbenzene;
    }

    public String getnButylbenzene() {
        return nButylbenzene;
    }

    public void setEpichlorohydrin(String epichlorohydrin) {
        this.epichlorohydrin = epichlorohydrin;
    }

    public String getEpichlorohydrin() {
        return epichlorohydrin;
    }

    public void setDibromo3Chloropropane12(String dibromo3Chloropropane12) {
        this.dibromo3Chloropropane12 = dibromo3Chloropropane12;
    }

    public String getDibromo3Chloropropane12() {
        return dibromo3Chloropropane12;
    }

    public void setTrichlorobenzene124(String trichlorobenzene124) {
        this.trichlorobenzene124 = trichlorobenzene124;
    }

    public String getTrichlorobenzene124() {
        return trichlorobenzene124;
    }

    public void setHexachlorobutadiene(String hexachlorobutadiene) {
        this.hexachlorobutadiene = hexachlorobutadiene;
    }

    public String getHexachlorobutadiene() {
        return hexachlorobutadiene;
    }

    public void setMethylnaphthalene2(String methylnaphthalene2) {
        this.methylnaphthalene2 = methylnaphthalene2;
    }

    public String getMethylnaphthalene2() {
        return methylnaphthalene2;
    }

    public void setMethylnaphthalene1(String methylnaphthalene1) {
        this.methylnaphthalene1 = methylnaphthalene1;
    }

    public String getMethylnaphthalene1() {
        return methylnaphthalene1;
    }

    public void setDichloropropene11(String dichloropropene11) {
        this.dichloropropene11 = dichloropropene11;
    }

    public String getDichloropropene11() {
        return dichloropropene11;
    }

    public void setTrans13Dichloropropene(String trans13Dichloropropene) {
        this.trans13Dichloropropene = trans13Dichloropropene;
    }

    public String getTrans13Dichloropropene() {
        return trans13Dichloropropene;
    }

    public void setCis13Dichloropropene(String cis13Dichloropropene) {
        this.cis13Dichloropropene = cis13Dichloropropene;
    }

    public String getCis13Dichloropropene() {
        return cis13Dichloropropene;
    }

    public void setDichloropropane22(String dichloropropane22) {
        this.dichloropropane22 = dichloropropane22;
    }

    public String getDichloropropane22() {
        return dichloropropane22;
    }

    public void setDibromoethane12(String dibromoethane12) {
        this.dibromoethane12 = dibromoethane12;
    }

    public String getDibromoethane12() {
        return dibromoethane12;
    }

    public void setDichloropropane13(String dichloropropane13) {
        this.dichloropropane13 = dichloropropane13;
    }

    public String getDichloropropane13() {
        return dichloropropane13;
    }

    public void setChlorotoluene2(String chlorotoluene2) {
        this.chlorotoluene2 = chlorotoluene2;
    }

    public String getChlorotoluene2() {
        return chlorotoluene2;
    }

    public void setChlorotoluene4(String chlorotoluene4) {
        this.chlorotoluene4 = chlorotoluene4;
    }

    public String getChlorotoluene4() {
        return chlorotoluene4;
    }

    public void setDichlorobenzene13(String dichlorobenzene13) {
        this.dichlorobenzene13 = dichlorobenzene13;
    }

    public String getDichlorobenzene13() {
        return dichlorobenzene13;
    }

    public void setTrichlorobenzene123(String trichlorobenzene123) {
        this.trichlorobenzene123 = trichlorobenzene123;
    }

    public String getTrichlorobenzene123() {
        return trichlorobenzene123;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("pointId", getPointId())
                .append("tag", getTag())
                .append("sampleTime", getSampleTime())
                .append("color", getColor())
                .append("odorAndTaste", getOdorAndTaste())
                .append("turbidity", getTurbidity())
                .append("visibleMatters", getVisibleMatters())
                .append("ph", getPh())
                .append("totalHardness", getTotalHardness())
                .append("totalDissolvedSolids", getTotalDissolvedSolids())
                .append("sulfate", getSulfate())
                .append("chloride", getChloride())
                .append("iron", getIron())
                .append("manganese", getManganese())
                .append("aluminum", getAluminum())
                .append("anionicSurfactant", getAnionicSurfactant())
                .append("oxygenConsumption", getOxygenConsumption())
                .append("sulfide", getSulfide())
                .append("sodium", getSodium())
                .append("nitriteNitrogen", getNitriteNitrogen())
                .append("nitrateNitrogen", getNitrateNitrogen())
                .append("iodide", getIodide())
                .append("selenium", getSelenium())
                .append("copper", getCopper())
                .append("zinc", getZinc())
                .append("volatilePhenol", getVolatilePhenol())
                .append("ammoniaNitrogen", getAmmoniaNitrogen())
                .append("cyanide", getCyanide())
                .append("fluoride", getFluoride())
                .append("mercury", getMercury())
                .append("arsenic", getArsenic())
                .append("cadmium", getCadmium())
                .append("chromiumVi", getChromiumVi())
                .append("lead", getLead())
                .append("chloroform", getChloroform())
                .append("carbonTetrachloride", getCarbonTetrachloride())
                .append("benzene", getBenzene())
                .append("toluene", getToluene())
                .append("molybdenum", getMolybdenum())
                .append("vanadium", getVanadium())
                .append("cobalt", getCobalt())
                .append("nickel", getNickel())
                .append("dichloromethane", getDichloromethane())
                .append("dichloroethane12", getDichloroethane12())
                .append("trichloroethane111", getTrichloroethane111())
                .append("trichloroethane112", getTrichloroethane112())
                .append("dichloropropane12", getDichloropropane12())
                .append("vinylChloride", getVinylChloride())
                .append("dichloroethylene11", getDichloroethylene11())
                .append("transDichloroethylene12", getTransDichloroethylene12())
                .append("cisDichloroethylene12", getCisDichloroethylene12())
                .append("trichloroethylene", getTrichloroethylene())
                .append("tetrachloroethylene", getTetrachloroethylene())
                .append("chlorobenzene", getChlorobenzene())
                .append("dichlorobenzene12", getDichlorobenzene12())
                .append("dichlorobenzene14", getDichlorobenzene14())
                .append("ethylbenzene", getEthylbenzene())
                .append("xyleneOrtho", getXyleneOrtho())
                .append("xyleneMetaPara", getXyleneMetaPara())
                .append("styrene", getStyrene())
                .append("nitrobenzene", getNitrobenzene())
                .append("naphthalene", getNaphthalene())
                .append("anthracene", getAnthracene())
                .append("fluoranthene", getFluoranthene())
                .append("benzoBFluoranthene", getBenzoBFluoranthene())
                .append("benzoAPyrene", getBenzoAPyrene())
                .append("petroleumHydrocarbonsC6C9", getPetroleumHydrocarbonsC6C9())
                .append("petroleumHydrocarbonsC10C40", getPetroleumHydrocarbonsC10C40())
                .append("phenol", getPhenol())
                .append("chlorophenol2", getChlorophenol2())
                .append("aniline", getAniline())
                .append("acenaphthylene", getAcenaphthylene())
                .append("acenaphthene", getAcenaphthene())
                .append("fluorene", getFluorene())
                .append("phenanthrene", getPhenanthrene())
                .append("pyrene", getPyrene())
                .append("benzoAAnthracene", getBenzoAAnthracene())
                .append("chrysene", getChrysene())
                .append("benzoKFluoranthene", getBenzoKFluoranthene())
                .append("indeno123CdPyrene", getIndeno123CdPyrene())
                .append("dibenzoAHAnthracene", getDibenzoAHAnthracene())
                .append("benzoGHIPerylene", getBenzoGHIPerylene())
                .append("chloromethane", getChloromethane())
                .append("dichloroethane11", getDichloroethane11())
                .append("tetrachloroethane1112", getTetrachloroethane1112())
                .append("tetrachloroethane1122", getTetrachloroethane1122())
                .append("trichloropropane123", getTrichloropropane123())
                .append("id", getId())
                .append("analysisNumber", getAnalysisNumber())
                .append("originalNumber", getOriginalNumber())
                .append("carbonate", getCarbonate())
                .append("bicarbonate", getBicarbonate())
                .append("ferrousIon", getFerrousIon())
                .append("potassium", getPotassium())
                .append("calcium", getCalcium())
                .append("magnesium", getMagnesium())
                .append("chloroprene", getChloroprene())
                .append("bromochloromethane", getBromochloromethane())
                .append("bromodichloromethane", getBromodichloromethane())
                .append("dibromomethane", getDibromomethane())
                .append("dibromochloromethane", getDibromochloromethane())
                .append("bromoform", getBromoform())
                .append("cumene", getCumene())
                .append("nPropylbenzene", getnPropylbenzene())
                .append("bromobenzene", getBromobenzene())
                .append("trimethylbenzene135", getTrimethylbenzene135())
                .append("oChlorotoluene", getoChlorotoluene())
                .append("pChlorotoluene", getpChlorotoluene())
                .append("tertButylbenzene", getTertButylbenzene())
                .append("trimethylbenzene14", getTrimethylbenzene14())
                .append("secButylbenzene", getSecButylbenzene())
                .append("isopropyltoluene4", getIsopropyltoluene4())
                .append("nButylbenzene", getnButylbenzene())
                .append("epichlorohydrin", getEpichlorohydrin())
                .append("dibromo3Chloropropane12", getDibromo3Chloropropane12())
                .append("trichlorobenzene124", getTrichlorobenzene124())
                .append("hexachlorobutadiene", getHexachlorobutadiene())
                .append("methylnaphthalene2", getMethylnaphthalene2())
                .append("methylnaphthalene1", getMethylnaphthalene1())
                .append("dichloropropene11", getDichloropropene11())
                .append("trans13Dichloropropene", getTrans13Dichloropropene())
                .append("cis13Dichloropropene", getCis13Dichloropropene())
                .append("dichloropropane22", getDichloropropane22())
                .append("dibromoethane12", getDibromoethane12())
                .append("dichloropropane13", getDichloropropane13())
                .append("chlorotoluene2", getChlorotoluene2())
                .append("chlorotoluene4", getChlorotoluene4())
                .append("dichlorobenzene13", getDichlorobenzene13())
                .append("trichlorobenzene123", getTrichlorobenzene123())
                .toString();
    }
}
