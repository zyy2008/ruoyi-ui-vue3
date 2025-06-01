declare namespace API {
  type dekuaijizhoubianminganshoutixinxi = {
    createBy?: string;
    createTime?: string;
    /** 所属部门 */
    deptId?: number;
    /** 地块内职工人数分类 */
    employeeCountCategory?: string;
    /** 主键ID */
    id?: number;
    params?: Record<string, any>;
    /** 500m范围内常驻人口数量分类 */
    permanentPopulationCategory?: string;
    remark?: string;
    /** 1km范围内敏感目标及距离分类 */
    sensitiveTargets?: string;
    /** 东侧用地类型 */
    surroundingLandEast?: string;
    /** 北侧用地类型 */
    surroundingLandNorth?: string;
    /** 南侧用地类型 */
    surroundingLandSouth?: string;
    /** 西侧用地类型 */
    surroundingLandWest?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type deleteAdminEnterpriseIdsParams = {
    /** ids */
    ids: string;
  };

  type deleteAdminGroundwaterMonitoringIdsParams = {
    /** ids */
    ids: string;
  };

  type deleteAdminGroundwaterPathwayIdsParams = {
    /** ids */
    ids: string;
  };

  type deleteAdminGroundwaterPollutantIdsParams = {
    /** ids */
    ids: string;
  };

  type deleteAdminMainProductsIdsParams = {
    /** ids */
    ids: string;
  };

  type deleteAdminPollutionIdsParams = {
    /** ids */
    ids: string;
  };

  type deleteAdminRawMaterialsIdsParams = {
    /** ids */
    ids: string;
  };

  type deleteAdminReceptorIdsParams = {
    /** ids */
    ids: string;
  };

  type deleteAdminSoilMonitoringIdsParams = {
    /** ids */
    ids: string;
  };

  type deleteAdminSoilPathwayIdsParams = {
    /** ids */
    ids: string;
  };

  type deleteAdminSoilPollutantIdsParams = {
    /** ids */
    ids: string;
  };

  type deleteAdminWellsIdsParams = {
    /** ids */
    ids: string;
  };

  type deleteAdminWorkshopEnvironmentIdsParams = {
    /** ids */
    ids: string;
  };

  type dexiashuijiancefaxiandechaobiaowuranwuxinxi = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: number;
    /** 主键ID */
    id?: number;
    /** 最大浓度 */
    maxConcentrationMgl?: string;
    /** 关联的地下水监测记录ID */
    monitoringId?: number;
    params?: Record<string, any>;
    /** 超标污染物名称 */
    pollutantName?: string;
    remark?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type dexiashuiqieyitujingxinxiduixiang = {
    /** 年降雨量范围 */
    annualRainfallCategory?: string;
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: number;
    /** 地下水用途 */
    groundwaterUse?: string;
    /** 主键ID */
    id?: number;
    /** 是否属于喀斯特地貌 */
    isKarstArea?: string;
    params?: Record<string, any>;
    /** 含水层渗透性分类 */
    permeabilityCategory?: string;
    remark?: string;
    updateBy?: string;
    updateTime?: string;
    /** 地下水埋深 */
    waterDepthCategory?: string;
  };

  type getAdminEnterpriseIdParams = {
    /** id */
    id: number;
  };

  type getAdminEnterpriseListParams = {
    /** 单位所在地详细地址 */
    address?: string;
    /** 地块实际使用面积 */
    areaActual?: string;
    /** 地块占地面积 */
    areaLandCert?: string;
    /** 中心纬度 */
    centerLatitude?: string;
    /** 中心经度 */
    centerLongitude?: string;
    /** 统一社会信用代码 */
    companyCreditCode?: string;
    /** 单位名称 */
    companyName?: string;
    /** 联系人姓名 */
    contactName?: string;
    /** 联系人电话 */
    contactPhone?: string;
    createBy?: string;
    createTime?: string;
    /** 归属部门ID */
    deptId?: string;
    /** 企业规模 */
    enterpriseScale?: string;
    /** 利用历史结束年份 */
    historyEndYear?: number;
    /** 地块利用历史 */
    historyLandUse?: string;
    /** 利用历史起始年份 */
    historyStartYear?: number;
    /** 主键ID */
    id?: number;
    /** 是否位于工业园区或集聚区 */
    inIndustrialPark?: string;
    /** 行业类别 */
    industryCategory?: string;
    /** 行业代码 */
    industryCode?: string;
    /** 最新改扩建时间 */
    latestExpansionDate?: string;
    /** 法定代表人 */
    legalRepresentative?: string;
    params?: Record<string, any>;
    /** 地块编码 */
    plotCode?: string;
    /** 地块名称 */
    plotName?: string;
    /** 行政区划代码 */
    regionCode?: string;
    /** 登记注册类型 */
    registrationType?: string;
    remark?: string;
    searchValue?: string;
    /** 开业时间 */
    startDate?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type getAdminEnterprisesListParams = {
    area?: string;
    createBy?: string;
    createTime?: string;
    enterpriseName?: string;
    industryCategory?: string;
    isKeyEnterprise?: string;
    latitude?: string;
    longitude?: string;
    params?: Record<string, any>;
    productionYears?: string;
    remark?: string;
    reportingStatus?: string;
    searchValue?: string;
    status?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type getAdminGroundwaterMonitoringIdParams = {
    /** id */
    id: number;
  };

  type getAdminGroundwaterMonitoringListParams = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: number;
    /** 是否曾开展地下水环境调查监测 */
    hasMonitoring?: string;
    /** 主键ID */
    id?: number;
    params?: Record<string, any>;
    /** 是否发现超标污染物 */
    pollutantsExceed?: string;
    remark?: string;
    searchValue?: string;
    /** 数据来源 */
    source?: string;
    /** 调查时间 */
    surveyDate?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type getAdminGroundwaterPathwayIdParams = {
    /** id */
    id: number;
  };

  type getAdminGroundwaterPathwayListParams = {
    /** 年降雨量范围 */
    annualRainfallCategory?: string;
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: number;
    /** 地下水用途 */
    groundwaterUse?: string;
    /** 主键ID */
    id?: number;
    /** 是否属于喀斯特地貌 */
    isKarstArea?: string;
    params?: Record<string, any>;
    /** 含水层渗透性分类 */
    permeabilityCategory?: string;
    remark?: string;
    searchValue?: string;
    updateBy?: string;
    updateTime?: string;
    /** 地下水埋深 */
    waterDepthCategory?: string;
  };

  type getAdminGroundwaterPollutantIdParams = {
    /** id */
    id: number;
  };

  type getAdminGroundwaterPollutantListParams = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: number;
    /** 主键ID */
    id?: number;
    /** 最大浓度 */
    maxConcentrationMgl?: string;
    /** 关联的地下水监测记录ID */
    monitoringId?: number;
    params?: Record<string, any>;
    /** 超标污染物名称 */
    pollutantName?: string;
    remark?: string;
    searchValue?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type getAdminMainProductsIdParams = {
    /** id */
    id: number;
  };

  type getAdminMainProductsListParams = {
    /** 年平均产量 */
    annualOutput?: string;
    createBy?: string;
    createTime?: string;
    /** 添加时间 */
    createdAt?: string;
    /** 添加人 */
    createdBy?: string;
    /** 产品数据来源 */
    dataSource?: string;
    /** 归属部门 */
    deptId?: number;
    /** 主键ID */
    id?: number;
    params?: Record<string, any>;
    /** 产品名称 */
    productName?: string;
    /** 生产年代 */
    productionYear?: number;
    remark?: string;
    searchValue?: string;
    /** 计量单位 */
    unit?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type getAdminPollutionIdParams = {
    /** id */
    id: number;
  };

  type getAdminPollutionListParams = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: number;
    /** 废气排放单位 */
    gasEmissionUnit?: string;
    /** 是否存在废气治理设施 */
    gasTreatmentExists?: string;
    /** 主键ID */
    id?: number;
    /** 是否为危化品 */
    isHazardous?: string;
    /** 生产线纬度 */
    latitude?: string;
    /** 是否存在泄漏 */
    leakStatus?: string;
    /** 生产线经度 */
    longitude?: string;
    /** 原料年均用量 */
    materialAvgUsage?: string;
    /** 原料名称 */
    materialName?: string;
    /** 原料单位 */
    materialUnit?: string;
    /** 原料使用年份范围 */
    materialUseYearRange?: string;
    params?: Record<string, any>;
    /** 工艺描述 */
    processDescription?: string;
    /** 生产线名称 */
    processLineName?: string;
    /** 年均产量 */
    productAvgOutput?: string;
    /** 产品名称 */
    productName?: string;
    /** 产品单位 */
    productUnit?: string;
    /** 产品生产年份范围 */
    productionYearRange?: string;
    remark?: string;
    searchValue?: string;
    /** 投产时间 */
    startupDate?: string;
    /** 储存量 */
    storageAmount?: string;
    /** 储存区面积 */
    storageArea?: string;
    /** 储存区名称 */
    storageAreaName?: string;
    /** 储存区纬度 */
    storageLatitude?: string;
    /** 是否存在泄漏 */
    storageLeakStatus?: string;
    /** 储存区经度 */
    storageLongitude?: string;
    /** 备注信息 */
    storageRemark?: string;
    /** 储存开始时间 */
    storageStartDate?: string;
    /** 储存单位 */
    storageUnit?: string;
    /** 储存物料名称 */
    storedMaterial?: string;
    /** 储罐数量 */
    tankCount?: number;
    /** 废气排放总量 */
    totalGasEmission?: string;
    updateBy?: string;
    updateTime?: string;
    /** 车间面积 */
    workshopArea?: string;
  };

  type getAdminRawMaterialsIdParams = {
    /** id */
    id: number;
  };

  type getAdminRawMaterialsListParams = {
    /** 年平均用量 */
    annualUsage?: string;
    createBy?: string;
    createTime?: string;
    /** 添加时间 */
    createdAt?: string;
    /** 添加人 */
    createdBy?: string;
    /** 数据来源 */
    dataSource?: string;
    /** 归属部门 */
    deptId?: number;
    /** 主键ID */
    id?: number;
    /** 原辅材料名称 */
    materialName?: string;
    params?: Record<string, any>;
    remark?: string;
    searchValue?: string;
    /** 计量单位 */
    unit?: string;
    updateBy?: string;
    updateTime?: string;
    /** 使用年代 */
    usageYear?: number;
  };

  type getAdminReceptorIdParams = {
    /** id */
    id: number;
  };

  type getAdminReceptorListParams = {
    createBy?: string;
    createTime?: string;
    /** 所属部门 */
    deptId?: number;
    /** 地块内职工人数分类 */
    employeeCountCategory?: string;
    /** 主键ID */
    id?: number;
    params?: Record<string, any>;
    /** 500m范围内常驻人口数量分类 */
    permanentPopulationCategory?: string;
    remark?: string;
    searchValue?: string;
    /** 1km范围内敏感目标及距离分类 */
    sensitiveTargets?: string;
    /** 东侧用地类型 */
    surroundingLandEast?: string;
    /** 北侧用地类型 */
    surroundingLandNorth?: string;
    /** 南侧用地类型 */
    surroundingLandSouth?: string;
    /** 西侧用地类型 */
    surroundingLandWest?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type getAdminSoilMonitoringIdParams = {
    /** id */
    id: number;
  };

  type getAdminSoilMonitoringListParams = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: number;
    /** 是否曾开展土壤环境调查监测 */
    hasMonitoring?: string;
    /** 主键ID */
    id?: number;
    params?: Record<string, any>;
    /** 是否发现超标污染物 */
    pollutantsExceed?: string;
    remark?: string;
    searchValue?: string;
    /** 数据来源 */
    source?: string;
    /** 调查时间 */
    surveyDate?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type getAdminSoilPathwayIdParams = {
    /** id */
    id: number;
  };

  type getAdminSoilPathwayListParams = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: number;
    /** 上层埋深 */
    depthTopCm?: number;
    /** 主键ID */
    id?: number;
    params?: Record<string, any>;
    remark?: string;
    searchValue?: string;
    /** 土壤层序号 */
    seqNo?: number;
    /** 土层性质 */
    soilType?: string;
    /** 土层厚度 */
    thicknessCm?: number;
    updateBy?: string;
    updateTime?: string;
  };

  type getAdminSoilPollutantIdParams = {
    /** id */
    id: number;
  };

  type getAdminSoilPollutantListParams = {
    createBy?: string;
    createTime?: string;
    /** 主键ID */
    deptId?: number;
    /** 主键ID */
    id?: number;
    /** 主键ID */
    maxConcentrationMgkg?: string;
    /** 主键ID */
    monitoringId?: number;
    params?: Record<string, any>;
    /** 主键ID */
    pollutantName?: string;
    remark?: string;
    searchValue?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type getAdminWellsIdParams = {
    /** id */
    id: number;
  };

  type getAdminWellsListParams = {
    /** 高程 */
    altitude?: string;
    /** 埋藏条件 */
    burialCondition?: string;
    /** 成井时间 */
    completionDate?: string;
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: number;
    /** 地下水类型 */
    groundwaterType?: string;
    /** 主键ID */
    id?: number;
    /** 井口内径 */
    innerDiameter?: string;
    /** 纬度 */
    latitude?: string;
    /** 监测井所在位置 */
    location?: string;
    /** 经度 */
    longitude?: string;
    /** 是否为多段筛管结构 */
    multiScreenPipe?: string;
    /** 监测井的权属单位 */
    ownership?: string;
    params?: Record<string, any>;
    /** 井管材质 */
    pipeMaterial?: string;
    /** 监测点类型 */
    pointType?: string;
    remark?: string;
    /** 筛管上部与下部的埋深范围 */
    screenDepthRange?: string;
    searchValue?: string;
    /** 是否符合长期监测井的标准 */
    suitableForLongterm?: string;
    updateBy?: string;
    updateTime?: string;
    /** 视频资料地址 */
    videoUrl?: string;
    /** 水位埋深 */
    waterBuriedDepth?: string;
    /** 含水介质 */
    waterMedium?: string;
    /** 监测井编码 */
    wellCode?: string;
    /** 成井深度 */
    wellDepth?: string;
    /** 井口高程 */
    wellElevation?: string;
  };

  type getAdminWorkshopEnvironmentIdParams = {
    /** id */
    id: number;
  };

  type getAdminWorkshopEnvironmentListParams = {
    coordinate?: string;
    createBy?: string;
    createTime?: string;
    createdAt?: string;
    createdBy?: string;
    deptId?: number;
    id?: number;
    leakImagePath?: string;
    leakage?: string;
    params?: Record<string, any>;
    processDescription?: string;
    remark?: string;
    searchValue?: string;
    startDate?: string;
    updateBy?: string;
    updateTime?: string;
    workshopName?: string;
  };

  type postAdminEnterpriseExportParams = {
    /** 单位所在地详细地址 */
    address?: string;
    /** 地块实际使用面积 */
    areaActual?: string;
    /** 地块占地面积 */
    areaLandCert?: string;
    /** 中心纬度 */
    centerLatitude?: string;
    /** 中心经度 */
    centerLongitude?: string;
    /** 统一社会信用代码 */
    companyCreditCode?: string;
    /** 单位名称 */
    companyName?: string;
    /** 联系人姓名 */
    contactName?: string;
    /** 联系人电话 */
    contactPhone?: string;
    createBy?: string;
    createTime?: string;
    /** 归属部门ID */
    deptId?: string;
    /** 企业规模 */
    enterpriseScale?: string;
    /** 利用历史结束年份 */
    historyEndYear?: number;
    /** 地块利用历史 */
    historyLandUse?: string;
    /** 利用历史起始年份 */
    historyStartYear?: number;
    /** 主键ID */
    id?: number;
    /** 是否位于工业园区或集聚区 */
    inIndustrialPark?: string;
    /** 行业类别 */
    industryCategory?: string;
    /** 行业代码 */
    industryCode?: string;
    /** 最新改扩建时间 */
    latestExpansionDate?: string;
    /** 法定代表人 */
    legalRepresentative?: string;
    params?: Record<string, any>;
    /** 地块编码 */
    plotCode?: string;
    /** 地块名称 */
    plotName?: string;
    /** 行政区划代码 */
    regionCode?: string;
    /** 登记注册类型 */
    registrationType?: string;
    remark?: string;
    searchValue?: string;
    /** 开业时间 */
    startDate?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type postAdminGroundwaterMonitoringExportParams = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: number;
    /** 是否曾开展地下水环境调查监测 */
    hasMonitoring?: string;
    /** 主键ID */
    id?: number;
    params?: Record<string, any>;
    /** 是否发现超标污染物 */
    pollutantsExceed?: string;
    remark?: string;
    searchValue?: string;
    /** 数据来源 */
    source?: string;
    /** 调查时间 */
    surveyDate?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type postAdminGroundwaterPathwayExportParams = {
    /** 年降雨量范围 */
    annualRainfallCategory?: string;
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: number;
    /** 地下水用途 */
    groundwaterUse?: string;
    /** 主键ID */
    id?: number;
    /** 是否属于喀斯特地貌 */
    isKarstArea?: string;
    params?: Record<string, any>;
    /** 含水层渗透性分类 */
    permeabilityCategory?: string;
    remark?: string;
    searchValue?: string;
    updateBy?: string;
    updateTime?: string;
    /** 地下水埋深 */
    waterDepthCategory?: string;
  };

  type postAdminGroundwaterPollutantExportParams = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: number;
    /** 主键ID */
    id?: number;
    /** 最大浓度 */
    maxConcentrationMgl?: string;
    /** 关联的地下水监测记录ID */
    monitoringId?: number;
    params?: Record<string, any>;
    /** 超标污染物名称 */
    pollutantName?: string;
    remark?: string;
    searchValue?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type postAdminMainProductsExportParams = {
    /** 年平均产量 */
    annualOutput?: string;
    createBy?: string;
    createTime?: string;
    /** 添加时间 */
    createdAt?: string;
    /** 添加人 */
    createdBy?: string;
    /** 产品数据来源 */
    dataSource?: string;
    /** 归属部门 */
    deptId?: number;
    /** 主键ID */
    id?: number;
    params?: Record<string, any>;
    /** 产品名称 */
    productName?: string;
    /** 生产年代 */
    productionYear?: number;
    remark?: string;
    searchValue?: string;
    /** 计量单位 */
    unit?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type postAdminPollutionExportParams = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: number;
    /** 废气排放单位 */
    gasEmissionUnit?: string;
    /** 是否存在废气治理设施 */
    gasTreatmentExists?: string;
    /** 主键ID */
    id?: number;
    /** 是否为危化品 */
    isHazardous?: string;
    /** 生产线纬度 */
    latitude?: string;
    /** 是否存在泄漏 */
    leakStatus?: string;
    /** 生产线经度 */
    longitude?: string;
    /** 原料年均用量 */
    materialAvgUsage?: string;
    /** 原料名称 */
    materialName?: string;
    /** 原料单位 */
    materialUnit?: string;
    /** 原料使用年份范围 */
    materialUseYearRange?: string;
    params?: Record<string, any>;
    /** 工艺描述 */
    processDescription?: string;
    /** 生产线名称 */
    processLineName?: string;
    /** 年均产量 */
    productAvgOutput?: string;
    /** 产品名称 */
    productName?: string;
    /** 产品单位 */
    productUnit?: string;
    /** 产品生产年份范围 */
    productionYearRange?: string;
    remark?: string;
    searchValue?: string;
    /** 投产时间 */
    startupDate?: string;
    /** 储存量 */
    storageAmount?: string;
    /** 储存区面积 */
    storageArea?: string;
    /** 储存区名称 */
    storageAreaName?: string;
    /** 储存区纬度 */
    storageLatitude?: string;
    /** 是否存在泄漏 */
    storageLeakStatus?: string;
    /** 储存区经度 */
    storageLongitude?: string;
    /** 备注信息 */
    storageRemark?: string;
    /** 储存开始时间 */
    storageStartDate?: string;
    /** 储存单位 */
    storageUnit?: string;
    /** 储存物料名称 */
    storedMaterial?: string;
    /** 储罐数量 */
    tankCount?: number;
    /** 废气排放总量 */
    totalGasEmission?: string;
    updateBy?: string;
    updateTime?: string;
    /** 车间面积 */
    workshopArea?: string;
  };

  type postAdminRawMaterialsExportParams = {
    /** 年平均用量 */
    annualUsage?: string;
    createBy?: string;
    createTime?: string;
    /** 添加时间 */
    createdAt?: string;
    /** 添加人 */
    createdBy?: string;
    /** 数据来源 */
    dataSource?: string;
    /** 归属部门 */
    deptId?: number;
    /** 主键ID */
    id?: number;
    /** 原辅材料名称 */
    materialName?: string;
    params?: Record<string, any>;
    remark?: string;
    searchValue?: string;
    /** 计量单位 */
    unit?: string;
    updateBy?: string;
    updateTime?: string;
    /** 使用年代 */
    usageYear?: number;
  };

  type postAdminReceptorExportParams = {
    createBy?: string;
    createTime?: string;
    /** 所属部门 */
    deptId?: number;
    /** 地块内职工人数分类 */
    employeeCountCategory?: string;
    /** 主键ID */
    id?: number;
    params?: Record<string, any>;
    /** 500m范围内常驻人口数量分类 */
    permanentPopulationCategory?: string;
    remark?: string;
    searchValue?: string;
    /** 1km范围内敏感目标及距离分类 */
    sensitiveTargets?: string;
    /** 东侧用地类型 */
    surroundingLandEast?: string;
    /** 北侧用地类型 */
    surroundingLandNorth?: string;
    /** 南侧用地类型 */
    surroundingLandSouth?: string;
    /** 西侧用地类型 */
    surroundingLandWest?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type postAdminSoilMonitoringExportParams = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: number;
    /** 是否曾开展土壤环境调查监测 */
    hasMonitoring?: string;
    /** 主键ID */
    id?: number;
    params?: Record<string, any>;
    /** 是否发现超标污染物 */
    pollutantsExceed?: string;
    remark?: string;
    searchValue?: string;
    /** 数据来源 */
    source?: string;
    /** 调查时间 */
    surveyDate?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type postAdminSoilPathwayExportParams = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: number;
    /** 上层埋深 */
    depthTopCm?: number;
    /** 主键ID */
    id?: number;
    params?: Record<string, any>;
    remark?: string;
    searchValue?: string;
    /** 土壤层序号 */
    seqNo?: number;
    /** 土层性质 */
    soilType?: string;
    /** 土层厚度 */
    thicknessCm?: number;
    updateBy?: string;
    updateTime?: string;
  };

  type postAdminSoilPollutantExportParams = {
    createBy?: string;
    createTime?: string;
    /** 主键ID */
    deptId?: number;
    /** 主键ID */
    id?: number;
    /** 主键ID */
    maxConcentrationMgkg?: string;
    /** 主键ID */
    monitoringId?: number;
    params?: Record<string, any>;
    /** 主键ID */
    pollutantName?: string;
    remark?: string;
    searchValue?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type postAdminWellsExportParams = {
    /** 高程 */
    altitude?: string;
    /** 埋藏条件 */
    burialCondition?: string;
    /** 成井时间 */
    completionDate?: string;
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: number;
    /** 地下水类型 */
    groundwaterType?: string;
    /** 主键ID */
    id?: number;
    /** 井口内径 */
    innerDiameter?: string;
    /** 纬度 */
    latitude?: string;
    /** 监测井所在位置 */
    location?: string;
    /** 经度 */
    longitude?: string;
    /** 是否为多段筛管结构 */
    multiScreenPipe?: string;
    /** 监测井的权属单位 */
    ownership?: string;
    params?: Record<string, any>;
    /** 井管材质 */
    pipeMaterial?: string;
    /** 监测点类型 */
    pointType?: string;
    remark?: string;
    /** 筛管上部与下部的埋深范围 */
    screenDepthRange?: string;
    searchValue?: string;
    /** 是否符合长期监测井的标准 */
    suitableForLongterm?: string;
    updateBy?: string;
    updateTime?: string;
    /** 视频资料地址 */
    videoUrl?: string;
    /** 水位埋深 */
    waterBuriedDepth?: string;
    /** 含水介质 */
    waterMedium?: string;
    /** 监测井编码 */
    wellCode?: string;
    /** 成井深度 */
    wellDepth?: string;
    /** 井口高程 */
    wellElevation?: string;
  };

  type postAdminWorkshopEnvironmentExportParams = {
    coordinate?: string;
    createBy?: string;
    createTime?: string;
    createdAt?: string;
    createdBy?: string;
    deptId?: number;
    id?: number;
    leakImagePath?: string;
    leakage?: string;
    params?: Record<string, any>;
    processDescription?: string;
    remark?: string;
    searchValue?: string;
    startDate?: string;
    updateBy?: string;
    updateTime?: string;
    workshopName?: string;
  };

  type qiyewuranyuan = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: number;
    /** 是否曾开展地下水环境调查监测 */
    hasMonitoring?: string;
    /** 主键ID */
    id?: number;
    params?: Record<string, any>;
    /** 是否发现超标污染物 */
    pollutantsExceed?: string;
    remark?: string;
    /** 数据来源 */
    source?: string;
    /** 调查时间 */
    surveyDate?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type TableDataInfo = {
    code?: number;
    msg?: string;
    rows?: Record<string, any>[];
    total?: number;
  };

  type turanghuanjingdiaochajiancexinxi = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: number;
    /** 上层埋深 */
    depthTopCm?: number;
    /** 主键ID */
    id?: number;
    params?: Record<string, any>;
    remark?: string;
    /** 土壤层序号 */
    seqNo?: number;
    /** 土层性质 */
    soilType?: string;
    /** 土层厚度 */
    thicknessCm?: number;
    updateBy?: string;
    updateTime?: string;
  };

  type turangjiancefaxiandechaobiaowuranwuxinxi = {
    createBy?: string;
    createTime?: string;
    /** 主键ID */
    deptId?: number;
    /** 主键ID */
    id?: number;
    /** 主键ID */
    maxConcentrationMgkg?: string;
    /** 主键ID */
    monitoringId?: number;
    params?: Record<string, any>;
    /** 主键ID */
    pollutantName?: string;
    remark?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type TWorkshopEnvironment = {
    coordinate?: string;
    createBy?: string;
    createTime?: string;
    createdAt?: string;
    createdBy?: string;
    deptId?: number;
    id?: number;
    leakImagePath?: string;
    leakage?: string;
    params?: Record<string, any>;
    processDescription?: string;
    remark?: string;
    startDate?: string;
    updateBy?: string;
    updateTime?: string;
    workshopName?: string;
  };

  type yuanfucailiaoxinxi = {
    /** 年平均用量 */
    annualUsage?: string;
    createBy?: string;
    createTime?: string;
    /** 添加时间 */
    createdAt?: string;
    /** 添加人 */
    createdBy?: string;
    /** 数据来源 */
    dataSource?: string;
    /** 归属部门 */
    deptId?: number;
    /** 主键ID */
    id?: number;
    /** 原辅材料名称 */
    materialName?: string;
    params?: Record<string, any>;
    remark?: string;
    /** 计量单位 */
    unit?: string;
    updateBy?: string;
    updateTime?: string;
    /** 使用年代 */
    usageYear?: number;
  };

  type yuanqujiancejingxinxi = {
    /** 高程 */
    altitude?: string;
    /** 埋藏条件 */
    burialCondition?: string;
    /** 成井时间 */
    completionDate?: string;
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: number;
    /** 地下水类型 */
    groundwaterType?: string;
    /** 主键ID */
    id?: number;
    /** 井口内径 */
    innerDiameter?: string;
    /** 纬度 */
    latitude?: string;
    /** 监测井所在位置 */
    location?: string;
    /** 经度 */
    longitude?: string;
    /** 是否为多段筛管结构 */
    multiScreenPipe?: string;
    /** 监测井的权属单位 */
    ownership?: string;
    params?: Record<string, any>;
    /** 井管材质 */
    pipeMaterial?: string;
    /** 监测点类型 */
    pointType?: string;
    remark?: string;
    /** 筛管上部与下部的埋深范围 */
    screenDepthRange?: string;
    /** 是否符合长期监测井的标准 */
    suitableForLongterm?: string;
    updateBy?: string;
    updateTime?: string;
    /** 视频资料地址 */
    videoUrl?: string;
    /** 水位埋深 */
    waterBuriedDepth?: string;
    /** 含水介质 */
    waterMedium?: string;
    /** 监测井编码 */
    wellCode?: string;
    /** 成井深度 */
    wellDepth?: string;
    /** 井口高程 */
    wellElevation?: string;
  };

  type zaichanqiyedekuaixinxi = {
    /** 单位所在地详细地址 */
    address?: string;
    /** 地块实际使用面积 */
    areaActual?: string;
    /** 地块占地面积 */
    areaLandCert?: string;
    /** 中心纬度 */
    centerLatitude?: string;
    /** 中心经度 */
    centerLongitude?: string;
    /** 统一社会信用代码 */
    companyCreditCode?: string;
    /** 单位名称 */
    companyName?: string;
    /** 联系人姓名 */
    contactName?: string;
    /** 联系人电话 */
    contactPhone?: string;
    createBy?: string;
    createTime?: string;
    /** 归属部门ID */
    deptId?: string;
    /** 企业规模 */
    enterpriseScale?: string;
    /** 利用历史结束年份 */
    historyEndYear?: number;
    /** 地块利用历史 */
    historyLandUse?: string;
    /** 利用历史起始年份 */
    historyStartYear?: number;
    /** 主键ID */
    id?: number;
    /** 是否位于工业园区或集聚区 */
    inIndustrialPark?: string;
    /** 行业类别 */
    industryCategory?: string;
    /** 行业代码 */
    industryCode?: string;
    /** 最新改扩建时间 */
    latestExpansionDate?: string;
    /** 法定代表人 */
    legalRepresentative?: string;
    params?: Record<string, any>;
    /** 地块编码 */
    plotCode?: string;
    /** 地块名称 */
    plotName?: string;
    /** 行政区划代码 */
    regionCode?: string;
    /** 登记注册类型 */
    registrationType?: string;
    remark?: string;
    /** 开业时间 */
    startDate?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type zhuyaochanpinxinxi = {
    /** 年平均产量 */
    annualOutput?: string;
    createBy?: string;
    createTime?: string;
    /** 添加时间 */
    createdAt?: string;
    /** 添加人 */
    createdBy?: string;
    /** 产品数据来源 */
    dataSource?: string;
    /** 归属部门 */
    deptId?: number;
    /** 主键ID */
    id?: number;
    params?: Record<string, any>;
    /** 产品名称 */
    productName?: string;
    /** 生产年代 */
    productionYear?: number;
    remark?: string;
    /** 计量单位 */
    unit?: string;
    updateBy?: string;
    updateTime?: string;
  };
}
