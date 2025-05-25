declare namespace API {
  type deleteAdminEnterpriseIdParams = {
    /** id */
    id: number;
  };

  type deleteTestUserUserIdParams = {
    /** 用户ID */
    userId: number;
  };

  type getAdminEnterpriseIdParams = {
    /** id */
    id: number;
  };

  type getAdminEnterpriseListParams = {
    /** 单位所在地详细地址 */
    address?: string;
    /** 地块实际使用面积（现场勾画得出） */
    areaActual?: number;
    /** 地块占地面积（以土地证为准） */
    areaLandCert?: number;
    /** 中心纬度（如：37°33ˊ50.04"） */
    centerLatitude?: string;
    /** 中心经度（如：112°15ˊ18.68"） */
    centerLongitude?: string;
    /** 统一社会信用代码 */
    companyCreditCode?: string;
    /** 单位名称（企业名称） */
    companyName?: string;
    /** 联系人姓名 */
    contactName?: string;
    /** 联系人电话 */
    contactPhone?: string;
    createBy?: string;
    createTime?: string;
    /** 归属部门ID */
    deptId?: number;
    /** 企业规模（大型、中型、小型、微型） */
    enterpriseScale?: string;
    /** 利用历史结束年份 */
    historyEndYear?: number;
    /** 地块利用历史（如：工业用地、荒地等） */
    historyLandUse?: string;
    /** 利用历史起始年份 */
    historyStartYear?: number;
    /** 主键ID */
    id?: number;
    /** 是否位于工业园区或集聚区（true=是，false=否） */
    inIndustrialPark?: boolean;
    /** 行业类别（如：化学试剂和助剂制造） */
    industryCategory?: string;
    /** 行业代码（如：C2661） */
    industryCode?: string;
    /** 最新改扩建时间 */
    latestExpansionDate?: string;
    /** 法定代表人 */
    legalRepresentative?: string;
    params?: Record<string, any>;
    /** 地块编码（格式：6位行政区+1位地块类型+2位行业大类+4位流水） */
    plotCode?: string;
    /** 地块名称 */
    plotName?: string;
    /** 行政区划代码（如：140121） */
    regionCode?: string;
    /** 登记注册类型（如：有限责任公司） */
    registrationType?: string;
    remark?: string;
    searchValue?: string;
    /** 开业时间 */
    startDate?: string;
    updateBy?: string;
    updateTime?: string;
  };

  type getAdminLayerListParams = {
    ancestors?: string;
    baseId?: number;
    baseIds?: number[];
    baseName?: string;
    children?: Record<string, any>[];
    code?: string;
    createBy?: string;
    createTime?: string;
    dataType?: string;
    delFlag?: number;
    deptId?: number;
    id?: number;
    isShow?: boolean;
    layerParam?: string;
    name?: string;
    orderNum?: number;
    params?: Record<string, any>;
    parentId?: number;
    parentName?: string;
    pid?: number;
    remark?: string;
    searchValue?: string;
    serverType?: string;
    tableName?: string;
    updateBy?: string;
    updateTime?: string;
    url?: string;
    userId?: number;
  };

  type getTestUserUserIdParams = {
    /** 用户ID */
    userId: number;
  };

  type LoginBody = {
    code?: string;
    password?: string;
    username?: string;
    uuid?: string;
  };

  type postAdminGdalGetIntersectionExtParams = {
    /** tableName */
    tableName?: string;
    /** targetJson */
    targetJson?: string;
    /** srid */
    srid?: number;
  };

  type postTestUserSaveParams = {
    /** 用户手机 */
    mobile?: string;
    /** 用户密码 */
    password?: string;
    /** 用户id */
    userId?: number;
    /** 用户名称 */
    username?: string;
  };

  type RListUserEntity = {
    code?: number;
    data?: UserEntity[];
    msg?: string;
  };

  type Rstring = {
    code?: number;
    data?: string;
    msg?: string;
  };

  type RUserEntity = {
    code?: number;
    data?: UserEntity;
    msg?: string;
  };

  type TableDataInfo = {
    code?: number;
    msg?: string;
    rows?: Record<string, any>[];
    total?: number;
  };

  type UserEntity = {
    /** 用户手机 */
    mobile?: string;
    /** 用户密码 */
    password?: string;
    /** 用户ID */
    userId?: number;
    /** 用户名称 */
    username?: string;
  };

  type zaichanqiyedekuaixinxi = {
    /** 单位所在地详细地址 */
    address?: string;
    /** 地块实际使用面积（现场勾画得出） */
    areaActual?: number;
    /** 地块占地面积（以土地证为准） */
    areaLandCert?: number;
    /** 中心纬度（如：37°33ˊ50.04"） */
    centerLatitude?: string;
    /** 中心经度（如：112°15ˊ18.68"） */
    centerLongitude?: string;
    /** 统一社会信用代码 */
    companyCreditCode?: string;
    /** 单位名称（企业名称） */
    companyName?: string;
    /** 联系人姓名 */
    contactName?: string;
    /** 联系人电话 */
    contactPhone?: string;
    createBy?: string;
    createTime?: string;
    /** 归属部门ID */
    deptId?: number;
    /** 企业规模（大型、中型、小型、微型） */
    enterpriseScale?: string;
    /** 利用历史结束年份 */
    historyEndYear?: number;
    /** 地块利用历史（如：工业用地、荒地等） */
    historyLandUse?: string;
    /** 利用历史起始年份 */
    historyStartYear?: number;
    /** 主键ID */
    id?: number;
    /** 是否位于工业园区或集聚区（true=是，false=否） */
    inIndustrialPark?: boolean;
    /** 行业类别（如：化学试剂和助剂制造） */
    industryCategory?: string;
    /** 行业代码（如：C2661） */
    industryCode?: string;
    /** 最新改扩建时间 */
    latestExpansionDate?: string;
    /** 法定代表人 */
    legalRepresentative?: string;
    params?: Record<string, any>;
    /** 地块编码（格式：6位行政区+1位地块类型+2位行业大类+4位流水） */
    plotCode?: string;
    /** 地块名称 */
    plotName?: string;
    /** 行政区划代码（如：140121） */
    regionCode?: string;
    /** 登记注册类型（如：有限责任公司） */
    registrationType?: string;
    remark?: string;
    /** 开业时间 */
    startDate?: string;
    updateBy?: string;
    updateTime?: string;
  };
}
