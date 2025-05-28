declare namespace API {
  type deleteAdminEnterpriseIdsParams = {
    /** ids */
    ids: string;
  };

  type deleteAdminPollutionIdsParams = {
    /** ids */
    ids: string;
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
    deptId?: number;
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
    deptId?: number;
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

  type qiyewuranyuan = {
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

  type TableDataInfo = {
    code?: number;
    msg?: string;
    rows?: Record<string, any>[];
    total?: number;
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
    deptId?: number;
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
}
