declare namespace API {
  type chucunqu = {
    /** 区域名称 */
    areaName?: string;
    /** 区域类型 */
    areaType?: string;
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
    /** 绑定的污染源 */
    enterprisePollutionInfoId?: string;
    /** 主键ID */
    id?: number;
    /** 是否为危险化学品 */
    isHazardousChemical?: string;
    /** 泄漏或污染痕迹 */
    leakageOrPollutionTraces?: string;
    /** 左边位置 */
    leftPosition?: string;
    params?: Record<string, any>;
    /** 照片 */
    photos?: string;
    remark?: string;
    /** 储存量 */
    storageAmount?: string;
    /** 储存年代 */
    storageYear?: string;
    /** 储存物质名称 */
    storedSubstanceName?: string;
    /** 储罐数量 */
    tankCount?: number;
    /** 储罐类型 */
    tankType?: string;
    /** 计量单位 */
    unit?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type dekuaijizhoubianminganshoutixinxi = {
    createBy?: string;
    createTime?: string;
    /** 所属部门 */
    deptId?: string;
    /** 地块内职工人数 */
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

  type dekuaiwuranqingkuangfenxi = {
    /** 特征污染物名称 */
    characteristicPollutantName?: string;
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
    /** 绑定的污染源 */
    enterprisePollutionInfoId?: string;
    /** 主键ID */
    id?: number;
    params?: Record<string, any>;
    remark?: string;
    /** 疑似土壤污染区名称 */
    suspectedPollutionAreaName?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type deleteAdminEnterpriseIdsParams = {
    /** ids */
    ids: string;
  };

  type deleteAdminEnterprisesIdsParams = {
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

  type deleteAdminIndicatorsIdsParams = {
    /** ids */
    ids: string;
  };

  type deleteAdminIndustryIdsParams = {
    /** ids */
    ids: string;
  };

  type deleteAdminLandPollutionAnalysisIdsParams = {
    /** ids */
    ids: string;
  };

  type deleteAdminMainProductsIdsParams = {
    /** ids */
    ids: string;
  };

  type deleteAdminPipelineLeakIdsParams = {
    /** ids */
    ids: string;
  };

  type deleteAdminPollutionIdsParams = {
    /** ids */
    ids: string;
  };

  type deleteAdminPollutionIncidentIdsParams = {
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

  type deleteAdminSewageSolidWasteIdsParams = {
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

  type deleteAdminStorageAreaIdsParams = {
    /** ids */
    ids: string;
  };

  type deleteAdminSuspectedPollutionTraceIdsParams = {
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
    deptId?: string;
    /** 主键ID */
    id?: number;
    /** 最大浓度 */
    maxConcentrationMgl?: string;
    /** 关联的地下水监测记录ID */
    monitoringId?: string;
    params?: Record<string, any>;
    /** 超标污染物名称 */
    pollutantName?: string;
    remark?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type dexiashuijianceshuju = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
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

  type dexiashuiqieyitujingxinxiduixiang = {
    /** 年降雨量范围 */
    annualRainfallCategory?: string;
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
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

  type getAdminEnterprisesIdParams = {
    /** id */
    id: string;
  };

  type getAdminEnterprisesListParams = {
    /** 单位所在地详细地址 */
    address?: string;
    /** 占地面积 */
    area?: string;
    /** 地块实际使用面积 */
    areaActual?: string;
    /** 地块占地面积 */
    areaLandCert?: string;
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
    /** 企业名称 */
    enterpriseName?: string;
    /** 企业规模 */
    enterpriseScale?: string;
    /** 利用历史结束年份 */
    historyEndYear?: number;
    /** 地块利用历史 */
    historyLandUse?: string;
    /** 利用历史起始年份 */
    historyStartYear?: number;
    /** 主键 */
    id?: string;
    /** 是否位于工业园区或集聚区 */
    inIndustrialPark?: string;
    /** 行业类别 */
    industryCategory?: string;
    /** 行业代码 */
    industryCode?: string;
    /** 是否重点 */
    isKeyEnterprise?: string;
    /** 最新改扩建时间 */
    latestExpansionDate?: string;
    /** 纬度 */
    latitude?: string;
    /** 法定代表人 */
    legalRepresentative?: string;
    /** 经度 */
    longitude?: string;
    params?: Record<string, any>;
    /** 地块编码 */
    plotCode?: string;
    /** 地块名称 */
    plotName?: string;
    /** 生产年限 */
    productionYears?: string;
    /** 行政区划代码 */
    regionCode?: string;
    /** 登记注册类型 */
    registrationType?: string;
    /** 监管类型 */
    regulatoryType?: string;
    remark?: string;
    /** 报送情况 */
    reportingStatus?: string;
    searchValue?: string;
    /** 开业时间 */
    startDate?: string;
    /** 存续状态 */
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
    deptId?: string;
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
    deptId?: string;
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
    deptId?: string;
    /** 主键ID */
    id?: number;
    /** 最大浓度 */
    maxConcentrationMgl?: string;
    /** 关联的地下水监测记录ID */
    monitoringId?: string;
    params?: Record<string, any>;
    /** 超标污染物名称 */
    pollutantName?: string;
    remark?: string;
    searchValue?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type getAdminIndicatorsIdParams = {
    /** id */
    id: number;
  };

  type getAdminIndicatorsListParams = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
    /** 主键ID */
    id?: number;
    /** 是否启用 */
    isEnabled?: string;
    /** 是否国标 */
    isNationalStandard?: string;
    params?: Record<string, any>;
    remark?: string;
    searchValue?: string;
    /** 类型 */
    type?: string;
    updateBy?: string;
    updateTime?: string;
    /** 值 */
    value?: number;
  };

  type getAdminIndustryIdParams = {
    /** id */
    id: number;
  };

  type getAdminIndustryListParams = {
    PCode?: string;
    /** 行业编码 */
    code?: string;
    createBy?: string;
    createTime?: string;
    /** 主键ID */
    id?: number;
    /** 行业名称 */
    industryCategory?: string;
    params?: Record<string, any>;
    remark?: string;
    searchValue?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type getAdminLandPollutionAnalysisIdParams = {
    /** id */
    id: number;
  };

  type getAdminLandPollutionAnalysisListParams = {
    /** 特征污染物名称 */
    characteristicPollutantName?: string;
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
    /** 绑定的污染源 */
    enterprisePollutionInfoId?: string;
    /** 主键ID */
    id?: number;
    params?: Record<string, any>;
    remark?: string;
    searchValue?: string;
    /** 疑似土壤污染区名称 */
    suspectedPollutionAreaName?: string;
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
    deptId?: string;
    /** 主键ID */
    id?: number;
    params?: Record<string, any>;
    /** 产品名称 */
    productName?: string;
    /** 生产年代 */
    productionYear?: string;
    remark?: string;
    searchValue?: string;
    /** 计量单位 */
    unit?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type getAdminPipelineLeakIdParams = {
    /** id */
    id: number;
  };

  type getAdminPipelineLeakListParams = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
    /** 绑定的污染源 */
    enterprisePollutionInfoId?: string;
    /** 主键ID */
    id?: number;
    /** 泄漏点坐标位置 */
    leakCoordinates?: string;
    /** 泄漏的物质名称 */
    leakedSubstanceName?: string;
    params?: Record<string, any>;
    /** 管道类型 */
    pipelineType?: string;
    remark?: string;
    searchValue?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type getAdminPollutionIdParams = {
    /** id */
    id: number;
  };

  type getAdminPollutionIncidentIdParams = {
    /** id */
    id: number;
  };

  type getAdminPollutionIncidentListParams = {
    /** 事故发生坐标 */
    coordinates?: string;
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
    /** 绑定的污染源 */
    enterprisePollutionInfoId?: string;
    /** 主键ID */
    id?: number;
    /** 突发环境事件等级 */
    incidentLevel?: string;
    /** 发生时间 */
    incidentTime?: string;
    /** 事故发生地点 */
    location?: string;
    params?: Record<string, any>;
    remark?: string;
    searchValue?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type getAdminPollutionListParams = {
    /** 废水治理区面积 */
    areaOfWastewaterTreatmentArea?: string;
    /** 事故基本情况描述 */
    basicDescriptionOfAccident?: string;
    /** 清洁生产审核时间 */
    cleanAuditTime?: string;
    /** 清洁生产水平 */
    cleanLevel?: string;
    /** 废水治理区坐标位置 */
    coordinatesOfWastewaterTreatmentArea?: string;
    createBy?: string;
    createTime?: string;
    /** 生产区数据来源 */
    dataSourceOfProductionArea?: string;
    /** 原辅材料数据来源 */
    dataSourceOfRawAndAuxiliaryMaterials?: string;
    /** 固体废物贮存处置区数据来源 */
    dataSourceOfSolidWasteStorageAndDisposalArea?: string;
    /** 储存区数据来源 */
    dataSourceOfStorageArea?: string;
    /** 废气数据来源 */
    dataSourceOfWasteGas?: string;
    /** 废水数据来源 */
    dataSourceOfWastewater?: string;
    /** 归属部门 */
    deptId?: string;
    /** 环境污染事故发生情况 */
    environmentalPollutionAccidentOccurrence?: string;
    /** 设施区域（多选） */
    facilitiesAreas?: string;
    /** 平面布置图 */
    floorPlan?: string;
    /** 是否存在废气治理设施 */
    gasTreatmentExists?: string;
    /** 管道是否发生过泄漏 */
    hasThereBeenAPipeLeak?: string;
    /** 产品、原材料或其他有毒有害物质装卸区 */
    hazardousMaterialLoadingAndUnloadingArea?: string;
    /** 存放产品、原材料的或其他有毒有害物质的仓库等 */
    hazardousMaterialWarehouse?: string;
    /** 危险废物规范化管理考核评估结果 */
    hazardousWasteStandardizedManagementAssessmentResults?: string;
    /** 危废贮存处置区防护措施 */
    hazardousWasteStorageAndDisposalAreaProtectionMeasures?: string;
    /** 主键ID */
    id?: number;
    /** 地块内道路、地表、建（构）筑物表面、墙壁、空地污染痕迹 */
    internalPollutionTracesOfThePlot?: string;
    /** 企业是否开展过清洁生产审核 */
    isCleanProductionAudited?: string;
    /** 是否有危险废物自处置 */
    isHazardousWasteSelfDisposed?: string;
    /** 是否产生固体废物 */
    isSolidWasteGenerated?: string;
    /** 是否有固体废物贮存 */
    isSolidWasteStored?: string;
    /** 是否有废水治理设施 */
    isThereAWastewaterTreatmentFacility?: string;
    /** 是否有废气治理设施 */
    isThereAnEmissionTreatmentFacility?: string;
    /** 是否有废气在线监测装置 */
    isThereAnOnlineMonitoringDeviceForEmissions?: string;
    /** 是否有废水在线监测装置 */
    isThereAnOnlineMonitoringDeviceForWastewater?: string;
    /** 是否有废水排放 */
    isThereWastewaterDischarge?: string;
    /** 是否有废气排放 */
    isWasteGasEmission?: string;
    /** 主中间产物名称 */
    mainIntermediateProductName?: string;
    /** 废气中主要污染物名称 */
    mainPollutantsInEmissions?: string;
    /** 废水中主要污染物名称 */
    mainPollutantsInWastewater?: string;
    /** 主要生产工艺流程图 */
    mainProcessFlowChart?: string;
    /** 主要生产工艺描述 */
    mainProcessProductionDescription?: string;
    /** 废气治理设施运行情况 */
    operationStatusOfEmissionTreatmentFacility?: string;
    /** 在线监测装置运行情况 */
    operationStatusOfOnlineMonitoringDevice?: string;
    /** 废水治理设施运行情况 */
    operationStatusOfWastewaterTreatmentFacility?: string;
    params?: Record<string, any>;
    /** 污染痕迹照片 */
    photosOfPollutionTraces?: string;
    /** 污染区域处理情况 */
    pollutionAreaTreatmentSituation?: string;
    /** 废水治理区污染痕迹 */
    pollutionTracesInWastewaterTreatmentArea?: string;
    /** 各储存区域防护措施 */
    protectionMeasuresForStorageAreas?: string;
    /** 废水治理区防护措施 */
    protectionMeasuresInWastewaterTreatmentArea?: string;
    remark?: string;
    searchValue?: string;
    /** 固废贮存处置区防护措施 */
    solidWasteStorageAndDisposalAreaProtectionMeasures?: string;
    /** 储存区面积 */
    storageArea?: string;
    /** 储罐、储槽等储存设施 */
    storageFacility?: string;
    /** 废气排放量 */
    totalGasEmission?: string;
    /** 地下管道管线图 */
    undergroundPipeLineDiagram?: string;
    updateBy?: string;
    updateTime?: string;
    /** 废水排放量 */
    wastewaterDischargeVolume?: string;
    /** 车间总面积 */
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
    deptId?: string;
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
    usageYear?: string;
  };

  type getAdminReceptorIdParams = {
    /** id */
    id: number;
  };

  type getAdminReceptorListParams = {
    createBy?: string;
    createTime?: string;
    /** 所属部门 */
    deptId?: string;
    /** 地块内职工人数 */
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

  type getAdminSewageSolidWasteIdParams = {
    /** id */
    id: number;
  };

  type getAdminSewageSolidWasteListParams = {
    /** 面积 */
    area?: string;
    /** 区域名称 */
    areaName?: string;
    /** 坐标位置 */
    coordinates?: string;
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
    /** 绑定的污染源 */
    enterprisePollutionInfoId?: string;
    /** 主键ID */
    id?: number;
    /** 是否为危险废物 */
    isHazardousWaste?: string;
    /** 地点 */
    location?: string;
    params?: Record<string, any>;
    remark?: string;
    searchValue?: string;
    /** 固体废物名称 */
    solidWasteName?: string;
    /** 贮存量处置量 */
    storageAndDisposalAmount?: string;
    /** 贮存量处置照片 */
    storageAndDisposalPhotos?: string;
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
    deptId?: string;
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
    deptId?: string;
    /** 上层埋深 */
    depthTopCm?: string;
    /** 绑定的地下水迁移途径信息 */
    groundwaterPathwayId?: string;
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
    thicknessCm?: string;
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
    /** 归属部门 */
    deptId?: string;
    /** 主键ID */
    id?: number;
    /** 最大浓度 */
    maxConcentrationMgkg?: string;
    /** 关联的土壤监测记录ID */
    monitoringId?: string;
    params?: Record<string, any>;
    /** 超标污染物名称 */
    pollutantName?: string;
    remark?: string;
    searchValue?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type getAdminStorageAreaIdParams = {
    /** id */
    id: number;
  };

  type getAdminStorageAreaListParams = {
    /** 区域名称 */
    areaName?: string;
    /** 区域类型 */
    areaType?: string;
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
    /** 绑定的污染源 */
    enterprisePollutionInfoId?: string;
    /** 主键ID */
    id?: number;
    /** 是否为危险化学品 */
    isHazardousChemical?: string;
    /** 泄漏或污染痕迹 */
    leakageOrPollutionTraces?: string;
    /** 左边位置 */
    leftPosition?: string;
    params?: Record<string, any>;
    /** 照片 */
    photos?: string;
    remark?: string;
    searchValue?: string;
    /** 储存量 */
    storageAmount?: string;
    /** 储存年代 */
    storageYear?: string;
    /** 储存物质名称 */
    storedSubstanceName?: string;
    /** 储罐数量 */
    tankCount?: number;
    /** 储罐类型 */
    tankType?: string;
    /** 计量单位 */
    unit?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type getAdminSuspectedPollutionTraceIdParams = {
    /** id */
    id: number;
  };

  type getAdminSuspectedPollutionTraceListParams = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
    /** 绑定的污染源 */
    enterprisePollutionInfoId?: string;
    /** 主键ID */
    id?: number;
    params?: Record<string, any>;
    remark?: string;
    searchValue?: string;
    /** 污染痕迹出现的坐标位置 */
    traceCoordinates?: string;
    /** 污染痕迹出现的地点 */
    traceLocation?: string;
    /** 污染痕迹照片 */
    tracePhotos?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type getAdminWellsAllParams = {
    altitude?: string;
    burialCondition?: string;
    completionDate?: string;
    createBy?: string;
    createTime?: string;
    deptId?: string;
    groundwaterType?: string;
    id?: string;
    innerDiameter?: string;
    latitude?: string;
    location?: string;
    longitude?: string;
    multiScreenPipe?: string;
    ownership?: string;
    params?: Record<string, any>;
    pipeMaterial?: string;
    pointType?: string;
    realTime?: string;
    remark?: string;
    screenDepthRange?: string;
    searchValue?: string;
    suitableForLongterm?: string;
    updateBy?: string;
    updateTime?: string;
    videoUrl?: string;
    waterBuriedDepth?: string;
    waterMedium?: string;
    wellCode?: string;
    wellDepth?: string;
    wellElevation?: string;
  };

  type getAdminWellsIdParams = {
    /** id */
    id: string;
  };

  type getAdminWellsListParams = {
    altitude?: string;
    burialCondition?: string;
    completionDate?: string;
    createBy?: string;
    createTime?: string;
    deptId?: string;
    groundwaterType?: string;
    id?: string;
    innerDiameter?: string;
    latitude?: string;
    location?: string;
    longitude?: string;
    multiScreenPipe?: string;
    ownership?: string;
    params?: Record<string, any>;
    pipeMaterial?: string;
    pointType?: string;
    realTime?: string;
    remark?: string;
    screenDepthRange?: string;
    searchValue?: string;
    suitableForLongterm?: string;
    updateBy?: string;
    updateTime?: string;
    videoUrl?: string;
    waterBuriedDepth?: string;
    waterMedium?: string;
    wellCode?: string;
    wellDepth?: string;
    wellElevation?: string;
  };

  type getAdminWorkshopEnvironmentIdParams = {
    /** id */
    id: number;
  };

  type getAdminWorkshopEnvironmentListParams = {
    /** 坐标位置 */
    coordinate?: string;
    createBy?: string;
    createTime?: string;
    /** 添加时间 */
    createdAt?: string;
    /** 添加人 */
    createdBy?: string;
    /** 归属部门 */
    deptId?: string;
    /** 主键 */
    id?: number;
    /** 跑冒滴漏点照片路径 */
    leakImagePath?: string;
    /** 是否有泄漏现象 */
    leakage?: string;
    params?: Record<string, any>;
    /** 生产工艺描述 */
    processDescription?: string;
    remark?: string;
    searchValue?: string;
    /** 生产起始日期 */
    startDate?: string;
    /** 总面积 */
    totalArea?: string;
    updateBy?: string;
    updateTime?: string;
    /** 车间名称 */
    workshopName?: string;
  };

  type guandaoxielou = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
    /** 绑定的污染源 */
    enterprisePollutionInfoId?: string;
    /** 主键ID */
    id?: number;
    /** 泄漏点坐标位置 */
    leakCoordinates?: string;
    /** 泄漏的物质名称 */
    leakedSubstanceName?: string;
    params?: Record<string, any>;
    /** 管道类型 */
    pipelineType?: string;
    remark?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type huanjingwuranshigufashengqingkuang = {
    /** 事故发生坐标 */
    coordinates?: string;
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
    /** 绑定的污染源 */
    enterprisePollutionInfoId?: string;
    /** 主键ID */
    id?: number;
    /** 突发环境事件等级 */
    incidentLevel?: string;
    /** 发生时间 */
    incidentTime?: string;
    /** 事故发生地点 */
    location?: string;
    params?: Record<string, any>;
    remark?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type jiancejingduixiang = {
    altitude?: string;
    burialCondition?: string;
    completionDate?: string;
    createBy?: string;
    createTime?: string;
    deptId?: string;
    groundwaterType?: string;
    id?: string;
    innerDiameter?: string;
    latitude?: string;
    location?: string;
    longitude?: string;
    multiScreenPipe?: string;
    ownership?: string;
    params?: Record<string, any>;
    pipeMaterial?: string;
    pointType?: string;
    realTime?: string;
    remark?: string;
    screenDepthRange?: string;
    suitableForLongterm?: string;
    updateBy?: string;
    updateTime?: string;
    videoUrl?: string;
    waterBuriedDepth?: string;
    waterMedium?: string;
    wellCode?: string;
    wellDepth?: string;
    wellElevation?: string;
  };

  type keyiwuranyuanhuowuranhenji = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
    /** 绑定的污染源 */
    enterprisePollutionInfoId?: string;
    /** 主键ID */
    id?: number;
    params?: Record<string, any>;
    remark?: string;
    /** 污染痕迹出现的坐标位置 */
    traceCoordinates?: string;
    /** 污染痕迹出现的地点 */
    traceLocation?: string;
    /** 污染痕迹照片 */
    tracePhotos?: string;
    updateBy?: string;
    updateTime?: string;
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

  type postAdminEnterprisesExportParams = {
    /** 单位所在地详细地址 */
    address?: string;
    /** 占地面积 */
    area?: string;
    /** 地块实际使用面积 */
    areaActual?: string;
    /** 地块占地面积 */
    areaLandCert?: string;
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
    /** 企业名称 */
    enterpriseName?: string;
    /** 企业规模 */
    enterpriseScale?: string;
    /** 利用历史结束年份 */
    historyEndYear?: number;
    /** 地块利用历史 */
    historyLandUse?: string;
    /** 利用历史起始年份 */
    historyStartYear?: number;
    /** 主键 */
    id?: string;
    /** 是否位于工业园区或集聚区 */
    inIndustrialPark?: string;
    /** 行业类别 */
    industryCategory?: string;
    /** 行业代码 */
    industryCode?: string;
    /** 是否重点 */
    isKeyEnterprise?: string;
    /** 最新改扩建时间 */
    latestExpansionDate?: string;
    /** 纬度 */
    latitude?: string;
    /** 法定代表人 */
    legalRepresentative?: string;
    /** 经度 */
    longitude?: string;
    params?: Record<string, any>;
    /** 地块编码 */
    plotCode?: string;
    /** 地块名称 */
    plotName?: string;
    /** 生产年限 */
    productionYears?: string;
    /** 行政区划代码 */
    regionCode?: string;
    /** 登记注册类型 */
    registrationType?: string;
    /** 监管类型 */
    regulatoryType?: string;
    remark?: string;
    /** 报送情况 */
    reportingStatus?: string;
    searchValue?: string;
    /** 开业时间 */
    startDate?: string;
    /** 存续状态 */
    status?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type postAdminGroundwaterMonitoringExportParams = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
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
    deptId?: string;
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
    deptId?: string;
    /** 主键ID */
    id?: number;
    /** 最大浓度 */
    maxConcentrationMgl?: string;
    /** 关联的地下水监测记录ID */
    monitoringId?: string;
    params?: Record<string, any>;
    /** 超标污染物名称 */
    pollutantName?: string;
    remark?: string;
    searchValue?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type postAdminIndicatorsExportParams = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
    /** 主键ID */
    id?: number;
    /** 是否启用 */
    isEnabled?: string;
    /** 是否国标 */
    isNationalStandard?: string;
    params?: Record<string, any>;
    remark?: string;
    searchValue?: string;
    /** 类型 */
    type?: string;
    updateBy?: string;
    updateTime?: string;
    /** 值 */
    value?: number;
  };

  type postAdminIndustryExportParams = {
    PCode?: string;
    /** 行业编码 */
    code?: string;
    createBy?: string;
    createTime?: string;
    /** 主键ID */
    id?: number;
    /** 行业名称 */
    industryCategory?: string;
    params?: Record<string, any>;
    remark?: string;
    searchValue?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type postAdminLandPollutionAnalysisExportParams = {
    /** 特征污染物名称 */
    characteristicPollutantName?: string;
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
    /** 绑定的污染源 */
    enterprisePollutionInfoId?: string;
    /** 主键ID */
    id?: number;
    params?: Record<string, any>;
    remark?: string;
    searchValue?: string;
    /** 疑似土壤污染区名称 */
    suspectedPollutionAreaName?: string;
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
    deptId?: string;
    /** 主键ID */
    id?: number;
    params?: Record<string, any>;
    /** 产品名称 */
    productName?: string;
    /** 生产年代 */
    productionYear?: string;
    remark?: string;
    searchValue?: string;
    /** 计量单位 */
    unit?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type postAdminPipelineLeakExportParams = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
    /** 绑定的污染源 */
    enterprisePollutionInfoId?: string;
    /** 主键ID */
    id?: number;
    /** 泄漏点坐标位置 */
    leakCoordinates?: string;
    /** 泄漏的物质名称 */
    leakedSubstanceName?: string;
    params?: Record<string, any>;
    /** 管道类型 */
    pipelineType?: string;
    remark?: string;
    searchValue?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type postAdminPollutionExportParams = {
    /** 废水治理区面积 */
    areaOfWastewaterTreatmentArea?: string;
    /** 事故基本情况描述 */
    basicDescriptionOfAccident?: string;
    /** 清洁生产审核时间 */
    cleanAuditTime?: string;
    /** 清洁生产水平 */
    cleanLevel?: string;
    /** 废水治理区坐标位置 */
    coordinatesOfWastewaterTreatmentArea?: string;
    createBy?: string;
    createTime?: string;
    /** 生产区数据来源 */
    dataSourceOfProductionArea?: string;
    /** 原辅材料数据来源 */
    dataSourceOfRawAndAuxiliaryMaterials?: string;
    /** 固体废物贮存处置区数据来源 */
    dataSourceOfSolidWasteStorageAndDisposalArea?: string;
    /** 储存区数据来源 */
    dataSourceOfStorageArea?: string;
    /** 废气数据来源 */
    dataSourceOfWasteGas?: string;
    /** 废水数据来源 */
    dataSourceOfWastewater?: string;
    /** 归属部门 */
    deptId?: string;
    /** 环境污染事故发生情况 */
    environmentalPollutionAccidentOccurrence?: string;
    /** 设施区域（多选） */
    facilitiesAreas?: string;
    /** 平面布置图 */
    floorPlan?: string;
    /** 是否存在废气治理设施 */
    gasTreatmentExists?: string;
    /** 管道是否发生过泄漏 */
    hasThereBeenAPipeLeak?: string;
    /** 产品、原材料或其他有毒有害物质装卸区 */
    hazardousMaterialLoadingAndUnloadingArea?: string;
    /** 存放产品、原材料的或其他有毒有害物质的仓库等 */
    hazardousMaterialWarehouse?: string;
    /** 危险废物规范化管理考核评估结果 */
    hazardousWasteStandardizedManagementAssessmentResults?: string;
    /** 危废贮存处置区防护措施 */
    hazardousWasteStorageAndDisposalAreaProtectionMeasures?: string;
    /** 主键ID */
    id?: number;
    /** 地块内道路、地表、建（构）筑物表面、墙壁、空地污染痕迹 */
    internalPollutionTracesOfThePlot?: string;
    /** 企业是否开展过清洁生产审核 */
    isCleanProductionAudited?: string;
    /** 是否有危险废物自处置 */
    isHazardousWasteSelfDisposed?: string;
    /** 是否产生固体废物 */
    isSolidWasteGenerated?: string;
    /** 是否有固体废物贮存 */
    isSolidWasteStored?: string;
    /** 是否有废水治理设施 */
    isThereAWastewaterTreatmentFacility?: string;
    /** 是否有废气治理设施 */
    isThereAnEmissionTreatmentFacility?: string;
    /** 是否有废气在线监测装置 */
    isThereAnOnlineMonitoringDeviceForEmissions?: string;
    /** 是否有废水在线监测装置 */
    isThereAnOnlineMonitoringDeviceForWastewater?: string;
    /** 是否有废水排放 */
    isThereWastewaterDischarge?: string;
    /** 是否有废气排放 */
    isWasteGasEmission?: string;
    /** 主中间产物名称 */
    mainIntermediateProductName?: string;
    /** 废气中主要污染物名称 */
    mainPollutantsInEmissions?: string;
    /** 废水中主要污染物名称 */
    mainPollutantsInWastewater?: string;
    /** 主要生产工艺流程图 */
    mainProcessFlowChart?: string;
    /** 主要生产工艺描述 */
    mainProcessProductionDescription?: string;
    /** 废气治理设施运行情况 */
    operationStatusOfEmissionTreatmentFacility?: string;
    /** 在线监测装置运行情况 */
    operationStatusOfOnlineMonitoringDevice?: string;
    /** 废水治理设施运行情况 */
    operationStatusOfWastewaterTreatmentFacility?: string;
    params?: Record<string, any>;
    /** 污染痕迹照片 */
    photosOfPollutionTraces?: string;
    /** 污染区域处理情况 */
    pollutionAreaTreatmentSituation?: string;
    /** 废水治理区污染痕迹 */
    pollutionTracesInWastewaterTreatmentArea?: string;
    /** 各储存区域防护措施 */
    protectionMeasuresForStorageAreas?: string;
    /** 废水治理区防护措施 */
    protectionMeasuresInWastewaterTreatmentArea?: string;
    remark?: string;
    searchValue?: string;
    /** 固废贮存处置区防护措施 */
    solidWasteStorageAndDisposalAreaProtectionMeasures?: string;
    /** 储存区面积 */
    storageArea?: string;
    /** 储罐、储槽等储存设施 */
    storageFacility?: string;
    /** 废气排放量 */
    totalGasEmission?: string;
    /** 地下管道管线图 */
    undergroundPipeLineDiagram?: string;
    updateBy?: string;
    updateTime?: string;
    /** 废水排放量 */
    wastewaterDischargeVolume?: string;
    /** 车间总面积 */
    workshopArea?: string;
  };

  type postAdminPollutionIncidentExportParams = {
    /** 事故发生坐标 */
    coordinates?: string;
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
    /** 绑定的污染源 */
    enterprisePollutionInfoId?: string;
    /** 主键ID */
    id?: number;
    /** 突发环境事件等级 */
    incidentLevel?: string;
    /** 发生时间 */
    incidentTime?: string;
    /** 事故发生地点 */
    location?: string;
    params?: Record<string, any>;
    remark?: string;
    searchValue?: string;
    updateBy?: string;
    updateTime?: string;
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
    deptId?: string;
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
    usageYear?: string;
  };

  type postAdminReceptorExportParams = {
    createBy?: string;
    createTime?: string;
    /** 所属部门 */
    deptId?: string;
    /** 地块内职工人数 */
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

  type postAdminSewageSolidWasteExportParams = {
    /** 面积 */
    area?: string;
    /** 区域名称 */
    areaName?: string;
    /** 坐标位置 */
    coordinates?: string;
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
    /** 绑定的污染源 */
    enterprisePollutionInfoId?: string;
    /** 主键ID */
    id?: number;
    /** 是否为危险废物 */
    isHazardousWaste?: string;
    /** 地点 */
    location?: string;
    params?: Record<string, any>;
    remark?: string;
    searchValue?: string;
    /** 固体废物名称 */
    solidWasteName?: string;
    /** 贮存量处置量 */
    storageAndDisposalAmount?: string;
    /** 贮存量处置照片 */
    storageAndDisposalPhotos?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type postAdminSoilMonitoringExportParams = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
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
    deptId?: string;
    /** 上层埋深 */
    depthTopCm?: string;
    /** 绑定的地下水迁移途径信息 */
    groundwaterPathwayId?: string;
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
    thicknessCm?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type postAdminSoilPollutantExportParams = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
    /** 主键ID */
    id?: number;
    /** 最大浓度 */
    maxConcentrationMgkg?: string;
    /** 关联的土壤监测记录ID */
    monitoringId?: string;
    params?: Record<string, any>;
    /** 超标污染物名称 */
    pollutantName?: string;
    remark?: string;
    searchValue?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type postAdminStorageAreaExportParams = {
    /** 区域名称 */
    areaName?: string;
    /** 区域类型 */
    areaType?: string;
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
    /** 绑定的污染源 */
    enterprisePollutionInfoId?: string;
    /** 主键ID */
    id?: number;
    /** 是否为危险化学品 */
    isHazardousChemical?: string;
    /** 泄漏或污染痕迹 */
    leakageOrPollutionTraces?: string;
    /** 左边位置 */
    leftPosition?: string;
    params?: Record<string, any>;
    /** 照片 */
    photos?: string;
    remark?: string;
    searchValue?: string;
    /** 储存量 */
    storageAmount?: string;
    /** 储存年代 */
    storageYear?: string;
    /** 储存物质名称 */
    storedSubstanceName?: string;
    /** 储罐数量 */
    tankCount?: number;
    /** 储罐类型 */
    tankType?: string;
    /** 计量单位 */
    unit?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type postAdminSuspectedPollutionTraceExportParams = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
    /** 绑定的污染源 */
    enterprisePollutionInfoId?: string;
    /** 主键ID */
    id?: number;
    params?: Record<string, any>;
    remark?: string;
    searchValue?: string;
    /** 污染痕迹出现的坐标位置 */
    traceCoordinates?: string;
    /** 污染痕迹出现的地点 */
    traceLocation?: string;
    /** 污染痕迹照片 */
    tracePhotos?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type postAdminWellsExportParams = {
    altitude?: string;
    burialCondition?: string;
    completionDate?: string;
    createBy?: string;
    createTime?: string;
    deptId?: string;
    groundwaterType?: string;
    id?: string;
    innerDiameter?: string;
    latitude?: string;
    location?: string;
    longitude?: string;
    multiScreenPipe?: string;
    ownership?: string;
    params?: Record<string, any>;
    pipeMaterial?: string;
    pointType?: string;
    realTime?: string;
    remark?: string;
    screenDepthRange?: string;
    searchValue?: string;
    suitableForLongterm?: string;
    updateBy?: string;
    updateTime?: string;
    videoUrl?: string;
    waterBuriedDepth?: string;
    waterMedium?: string;
    wellCode?: string;
    wellDepth?: string;
    wellElevation?: string;
  };

  type postAdminWorkshopEnvironmentExportParams = {
    /** 坐标位置 */
    coordinate?: string;
    createBy?: string;
    createTime?: string;
    /** 添加时间 */
    createdAt?: string;
    /** 添加人 */
    createdBy?: string;
    /** 归属部门 */
    deptId?: string;
    /** 主键 */
    id?: number;
    /** 跑冒滴漏点照片路径 */
    leakImagePath?: string;
    /** 是否有泄漏现象 */
    leakage?: string;
    params?: Record<string, any>;
    /** 生产工艺描述 */
    processDescription?: string;
    remark?: string;
    searchValue?: string;
    /** 生产起始日期 */
    startDate?: string;
    /** 总面积 */
    totalArea?: string;
    updateBy?: string;
    updateTime?: string;
    /** 车间名称 */
    workshopName?: string;
  };

  type qiyeguanli = {
    /** 单位所在地详细地址 */
    address?: string;
    /** 占地面积 */
    area?: string;
    /** 地块实际使用面积 */
    areaActual?: string;
    /** 地块占地面积 */
    areaLandCert?: string;
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
    /** 企业名称 */
    enterpriseName?: string;
    /** 企业规模 */
    enterpriseScale?: string;
    /** 利用历史结束年份 */
    historyEndYear?: number;
    /** 地块利用历史 */
    historyLandUse?: string;
    /** 利用历史起始年份 */
    historyStartYear?: number;
    /** 主键 */
    id?: string;
    /** 是否位于工业园区或集聚区 */
    inIndustrialPark?: string;
    /** 行业类别 */
    industryCategory?: string;
    /** 行业代码 */
    industryCode?: string;
    /** 是否重点 */
    isKeyEnterprise?: string;
    /** 最新改扩建时间 */
    latestExpansionDate?: string;
    /** 纬度 */
    latitude?: string;
    /** 法定代表人 */
    legalRepresentative?: string;
    /** 经度 */
    longitude?: string;
    params?: Record<string, any>;
    /** 地块编码 */
    plotCode?: string;
    /** 地块名称 */
    plotName?: string;
    /** 生产年限 */
    productionYears?: string;
    /** 行政区划代码 */
    regionCode?: string;
    /** 登记注册类型 */
    registrationType?: string;
    /** 监管类型 */
    regulatoryType?: string;
    remark?: string;
    /** 报送情况 */
    reportingStatus?: string;
    /** 开业时间 */
    startDate?: string;
    /** 存续状态 */
    status?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type qiyewuranyuan = {
    /** 废水治理区面积 */
    areaOfWastewaterTreatmentArea?: string;
    /** 事故基本情况描述 */
    basicDescriptionOfAccident?: string;
    /** 清洁生产审核时间 */
    cleanAuditTime?: string;
    /** 清洁生产水平 */
    cleanLevel?: string;
    /** 废水治理区坐标位置 */
    coordinatesOfWastewaterTreatmentArea?: string;
    createBy?: string;
    createTime?: string;
    /** 生产区数据来源 */
    dataSourceOfProductionArea?: string;
    /** 原辅材料数据来源 */
    dataSourceOfRawAndAuxiliaryMaterials?: string;
    /** 固体废物贮存处置区数据来源 */
    dataSourceOfSolidWasteStorageAndDisposalArea?: string;
    /** 储存区数据来源 */
    dataSourceOfStorageArea?: string;
    /** 废气数据来源 */
    dataSourceOfWasteGas?: string;
    /** 废水数据来源 */
    dataSourceOfWastewater?: string;
    /** 归属部门 */
    deptId?: string;
    /** 环境污染事故发生情况 */
    environmentalPollutionAccidentOccurrence?: string;
    /** 设施区域（多选） */
    facilitiesAreas?: string;
    /** 平面布置图 */
    floorPlan?: string;
    /** 是否存在废气治理设施 */
    gasTreatmentExists?: string;
    /** 管道是否发生过泄漏 */
    hasThereBeenAPipeLeak?: string;
    /** 产品、原材料或其他有毒有害物质装卸区 */
    hazardousMaterialLoadingAndUnloadingArea?: string;
    /** 存放产品、原材料的或其他有毒有害物质的仓库等 */
    hazardousMaterialWarehouse?: string;
    /** 危险废物规范化管理考核评估结果 */
    hazardousWasteStandardizedManagementAssessmentResults?: string;
    /** 危废贮存处置区防护措施 */
    hazardousWasteStorageAndDisposalAreaProtectionMeasures?: string;
    /** 主键ID */
    id?: number;
    /** 地块内道路、地表、建（构）筑物表面、墙壁、空地污染痕迹 */
    internalPollutionTracesOfThePlot?: string;
    /** 企业是否开展过清洁生产审核 */
    isCleanProductionAudited?: string;
    /** 是否有危险废物自处置 */
    isHazardousWasteSelfDisposed?: string;
    /** 是否产生固体废物 */
    isSolidWasteGenerated?: string;
    /** 是否有固体废物贮存 */
    isSolidWasteStored?: string;
    /** 是否有废水治理设施 */
    isThereAWastewaterTreatmentFacility?: string;
    /** 是否有废气治理设施 */
    isThereAnEmissionTreatmentFacility?: string;
    /** 是否有废气在线监测装置 */
    isThereAnOnlineMonitoringDeviceForEmissions?: string;
    /** 是否有废水在线监测装置 */
    isThereAnOnlineMonitoringDeviceForWastewater?: string;
    /** 是否有废水排放 */
    isThereWastewaterDischarge?: string;
    /** 是否有废气排放 */
    isWasteGasEmission?: string;
    /** 主中间产物名称 */
    mainIntermediateProductName?: string;
    /** 废气中主要污染物名称 */
    mainPollutantsInEmissions?: string;
    /** 废水中主要污染物名称 */
    mainPollutantsInWastewater?: string;
    /** 主要生产工艺流程图 */
    mainProcessFlowChart?: string;
    /** 主要生产工艺描述 */
    mainProcessProductionDescription?: string;
    /** 废气治理设施运行情况 */
    operationStatusOfEmissionTreatmentFacility?: string;
    /** 在线监测装置运行情况 */
    operationStatusOfOnlineMonitoringDevice?: string;
    /** 废水治理设施运行情况 */
    operationStatusOfWastewaterTreatmentFacility?: string;
    params?: Record<string, any>;
    /** 污染痕迹照片 */
    photosOfPollutionTraces?: string;
    /** 污染区域处理情况 */
    pollutionAreaTreatmentSituation?: string;
    /** 废水治理区污染痕迹 */
    pollutionTracesInWastewaterTreatmentArea?: string;
    /** 各储存区域防护措施 */
    protectionMeasuresForStorageAreas?: string;
    /** 废水治理区防护措施 */
    protectionMeasuresInWastewaterTreatmentArea?: string;
    remark?: string;
    /** 固废贮存处置区防护措施 */
    solidWasteStorageAndDisposalAreaProtectionMeasures?: string;
    /** 储存区面积 */
    storageArea?: string;
    /** 储罐、储槽等储存设施 */
    storageFacility?: string;
    /** 废气排放量 */
    totalGasEmission?: string;
    /** 地下管道管线图 */
    undergroundPipeLineDiagram?: string;
    updateBy?: string;
    updateTime?: string;
    /** 废水排放量 */
    wastewaterDischargeVolume?: string;
    /** 车间总面积 */
    workshopArea?: string;
  };

  type shengchanchejianfenbuyuhuanjingqingkuang = {
    /** 坐标位置 */
    coordinate?: string;
    createBy?: string;
    createTime?: string;
    /** 添加时间 */
    createdAt?: string;
    /** 添加人 */
    createdBy?: string;
    /** 归属部门 */
    deptId?: string;
    /** 主键 */
    id?: number;
    /** 跑冒滴漏点照片路径 */
    leakImagePath?: string;
    /** 是否有泄漏现象 */
    leakage?: string;
    params?: Record<string, any>;
    /** 生产工艺描述 */
    processDescription?: string;
    remark?: string;
    /** 生产起始日期 */
    startDate?: string;
    /** 总面积 */
    totalArea?: string;
    updateBy?: string;
    updateTime?: string;
    /** 车间名称 */
    workshopName?: string;
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
    deptId?: string;
    /** 上层埋深 */
    depthTopCm?: string;
    /** 绑定的地下水迁移途径信息 */
    groundwaterPathwayId?: string;
    /** 主键ID */
    id?: number;
    params?: Record<string, any>;
    remark?: string;
    /** 土壤层序号 */
    seqNo?: number;
    /** 土层性质 */
    soilType?: string;
    /** 土层厚度 */
    thicknessCm?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type turangjiancefaxiandechaobiaowuranwuxinxi = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
    /** 主键ID */
    id?: number;
    /** 最大浓度 */
    maxConcentrationMgkg?: string;
    /** 关联的土壤监测记录ID */
    monitoringId?: string;
    params?: Record<string, any>;
    /** 超标污染物名称 */
    pollutantName?: string;
    remark?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type wushuichulichanggutifeiwuchanshengliangjiwuranfangzhicuoshi = {
    /** 面积 */
    area?: string;
    /** 区域名称 */
    areaName?: string;
    /** 坐标位置 */
    coordinates?: string;
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
    /** 绑定的污染源 */
    enterprisePollutionInfoId?: string;
    /** 主键ID */
    id?: number;
    /** 是否为危险废物 */
    isHazardousWaste?: string;
    /** 地点 */
    location?: string;
    params?: Record<string, any>;
    remark?: string;
    /** 固体废物名称 */
    solidWasteName?: string;
    /** 贮存量处置量 */
    storageAndDisposalAmount?: string;
    /** 贮存量处置照片 */
    storageAndDisposalPhotos?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type xingyefenlei = {
    /** 行业编码 */
    code?: string;
    createBy?: string;
    createTime?: string;
    /** 主键ID */
    id?: number;
    /** 行业名称 */
    industryCategory?: string;
    params?: Record<string, any>;
    pcode?: string;
    remark?: string;
    updateBy?: string;
    updateTime?: string;
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
    deptId?: string;
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
    usageYear?: string;
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

  type zhibiaobiaozhunbiao = {
    createBy?: string;
    createTime?: string;
    /** 归属部门 */
    deptId?: string;
    /** 主键ID */
    id?: number;
    /** 是否启用 */
    isEnabled?: string;
    /** 是否国标 */
    isNationalStandard?: string;
    params?: Record<string, any>;
    remark?: string;
    /** 类型 */
    type?: string;
    updateBy?: string;
    updateTime?: string;
    /** 值 */
    value?: number;
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
    deptId?: string;
    /** 主键ID */
    id?: number;
    params?: Record<string, any>;
    /** 产品名称 */
    productName?: string;
    /** 生产年代 */
    productionYear?: string;
    remark?: string;
    /** 计量单位 */
    unit?: string;
    updateBy?: string;
    updateTime?: string;
  };
}
