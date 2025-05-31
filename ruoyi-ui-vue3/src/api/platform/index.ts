//平台api
import request from "@/utils/request";

// ==============================
/**
 * 企业管理信息
 */
interface Enterprise {
  /**
   * 单位所在地详细地址
   */
  address?: string;
  /**
   * 地块实际使用面积
   */
  areaActual?: string;
  /**
   * 地块占地面积
   */
  areaLandCert?: string;
  /**
   * 中心纬度
   */
  centerLatitude?: string;
  /**
   * 中心经度
   */
  centerLongitude?: string;
  /**
   * 统一社会信用代码
   */
  companyCreditCode?: string;
  /**
   * 单位名称
   */
  companyName?: string;
  /**
   * 联系人姓名
   */
  contactName?: string;
  /**
   * 联系人电话
   */
  contactPhone?: string;
  createBy?: string;
  createTime?: Date;
  /**
   * 归属部门ID
   */
  deptId?: number;
  /**
   * 企业规模
   */
  enterpriseScale?: string;
  /**
   * 利用历史结束年份
   */
  historyEndYear?: number;
  /**
   * 地块利用历史
   */
  historyLandUse?: string;
  /**
   * 利用历史起始年份
   */
  historyStartYear?: number;
  /**
   * 主键ID
   */
  id?: number;
  /**
   * 行业类别
   */
  industryCategory?: string;
  /**
   * 行业代码
   */
  industryCode?: string;
  /**
   * 是否位于工业园区或集聚区
   */
  inIndustrialPark?: string;
  /**
   * 最新改扩建时间
   */
  latestExpansionDate?: Date;
  /**
   * 法定代表人
   */
  legalRepresentative?: string;
  params?: { [key: string]: any };
  /**
   * 地块编码
   */
  plotCode?: string;
  /**
   * 地块名称
   */
  plotName?: string;
  /**
   * 行政区划代码
   */
  regionCode?: string;
  /**
   * 登记注册类型
   */
  registrationType?: string;
  remark?: string;
  /**
   * 开业时间
   */
  startDate?: Date;
  updateBy?: string;
  updateTime?: Date;
  [property: string]: any;
}
// ==============================
/**
 * 企业污染源
 */

type PollutantSource = {
  createBy?: string;
  createTime?: Date;
  /**
   * 归属部门
   */
  deptId?: number;
  /**
   * 是否曾开展地下水环境调查监测
   */
  hasMonitoring?: string;
  /**
   * 主键ID
   */
  id?: number;
  params?: { [key: string]: any };
  /**
   * 是否发现超标污染物
   */
  pollutantsExceed?: string;
  remark?: string;
  /**
   * 数据来源
   */
  source?: string;
  /**
   * 调查时间
   */
  surveyDate?: Date;
  updateBy?: string;
  updateTime?: Date;
  [property: string]: any;
};

/**
 * 水源信息
 */
type Watersource = {
  createBy?: string;
  createTime?: Date;
  /**
   * 归属部门
   */
  deptId?: number;
  /**
   * 是否曾开展地下水环境调查监测
   */
  hasMonitoring?: string;
  /**
   * 主键ID
   */
  id?: number;
  params?: { [key: string]: any };
  /**
   * 是否发现超标污染物
   */
  pollutantsExceed?: string;
  remark?: string;
  /**
   * 数据来源
   */
  source?: string;
  /**
   * 调查时间
   */
  surveyDate?: Date;
  updateBy?: string;
  updateTime?: Date;
  [property: string]: any;
};

/**
 * 地下水迁移途径信息对象
 */
interface Groundwatermonitoring {
  /**
   * 年降雨量范围
   */
  annualRainfallCategory?: string;
  createBy?: string;
  createTime?: Date;
  /**
   * 归属部门
   */
  deptId?: number;
  /**
   * 地下水用途
   */
  groundwaterUse?: string;
  /**
   * 主键ID
   */
  id?: number;
  /**
   * 是否属于喀斯特地貌
   */
  isKarstArea?: string;
  params?: { [key: string]: any };
  /**
   * 含水层渗透性分类
   */
  permeabilityCategory?: string;
  remark?: string;
  updateBy?: string;
  updateTime?: Date;
  /**
   * 地下水埋深
   */
  waterDepthCategory?: string;
  [property: string]: any;
}
/**
 * 地下水监测发现的超标污染物信息
 */
interface Goundwatermonitoring {
  createBy?: string;
  createTime?: Date;
  /**
   * 归属部门
   */
  deptId?: number;
  /**
   * 主键ID
   */
  id?: number;
  /**
   * 最大浓度
   */
  maxConcentrationMgl?: string;
  /**
   * 关联的地下水监测记录ID
   */
  monitoringId?: number;
  params?: { [key: string]: any };
  /**
   * 超标污染物名称
   */
  pollutantName?: string;
  remark?: string;
  updateBy?: string;
  updateTime?: Date;
  [property: string]: any;
}
/**
 * 园区监测井信息
 */
interface Wells {
  /**
   * 高程
   */
  altitude?: string;
  /**
   * 埋藏条件
   */
  burialCondition?: string;
  /**
   * 成井时间
   */
  completionDate?: string;
  createBy?: string;
  createTime?: Date;
  /**
   * 归属部门
   */
  deptId?: number;
  /**
   * 地下水类型
   */
  groundwaterType?: string;
  /**
   * 主键ID
   */
  id?: number;
  /**
   * 井口内径
   */
  innerDiameter?: string;
  /**
   * 纬度
   */
  latitude?: string;
  /**
   * 监测井所在位置
   */
  location?: string;
  /**
   * 经度
   */
  longitude?: string;
  /**
   * 是否为多段筛管结构
   */
  multiScreenPipe?: string;
  /**
   * 监测井的权属单位
   */
  ownership?: string;
  params?: { [key: string]: any };
  /**
   * 井管材质
   */
  pipeMaterial?: string;
  /**
   * 监测点类型
   */
  pointType?: string;
  remark?: string;
  /**
   * 筛管上部与下部的埋深范围
   */
  screenDepthRange?: string;
  /**
   * 是否符合长期监测井的标准
   */
  suitableForLongterm?: string;
  updateBy?: string;
  updateTime?: Date;
  /**
   * 视频资料地址
   */
  videoUrl?: string;
  /**
   * 水位埋深
   */
  waterBuriedDepth?: string;
  /**
   * 含水介质
   */
  waterMedium?: string;
  /**
   * 监测井编码
   */
  wellCode?: string;
  /**
   * 成井深度
   */
  wellDepth?: string;
  /**
   * 井口高程
   */
  wellElevation?: string;
  [property: string]: any;
}

/**
 * 地块及周边敏感受体信息
 */
interface Receptor {
  createBy?: string;
  createTime?: Date;
  /**
   * 所属部门
   */
  deptId?: number;
  /**
   * 地块内职工人数分类
   */
  employeeCountCategory?: string;
  /**
   * 主键ID
   */
  id?: number;
  params?: { [key: string]: any };
  /**
   * 500m范围内常驻人口数量分类
   */
  permanentPopulationCategory?: string;
  remark?: string;
  /**
   * 1km范围内敏感目标及距离分类
   */
  sensitiveTargets?: string;
  /**
   * 东侧用地类型
   */
  surroundingLandEast?: string;
  /**
   * 北侧用地类型
   */
  surroundingLandNorth?: string;
  /**
   * 南侧用地类型
   */
  surroundingLandSouth?: string;
  /**
   * 西侧用地类型
   */
  surroundingLandWest?: string;
  updateBy?: string;
  updateTime?: Date;
  [property: string]: any;
}
/**
 * 主要产品信息
 */
interface MainProducts {
  /**
   * 年平均产量
   */
  annualOutput?: string;
  createBy?: string;
  /**
   * 添加时间
   */
  createdAt?: string;
  /**
   * 添加人
   */
  createdBy?: string;
  createTime?: Date;
  /**
   * 产品数据来源
   */
  dataSource?: string;
  /**
   * 归属部门
   */
  deptId?: number;
  /**
   * 主键ID
   */
  id?: number;
  params?: { [key: string]: any };
  /**
   * 生产年代
   */
  productionYear?: number;
  /**
   * 产品名称
   */
  productName?: string;
  remark?: string;
  /**
   * 计量单位
   */
  unit?: string;
  updateBy?: string;
  updateTime?: Date;
  [property: string]: any;
}
/**
 * 原辅材料信息
 */
interface RawMaterials {
  /**
   * 年平均用量
   */
  annualUsage?: string;
  createBy?: string;
  /**
   * 添加时间
   */
  createdAt?: string;
  /**
   * 添加人
   */
  createdBy?: string;
  createTime?: Date;
  /**
   * 数据来源
   */
  dataSource?: string;
  /**
   * 归属部门
   */
  deptId?: number;
  /**
   * 主键ID
   */
  id?: number;
  /**
   * 原辅材料名称
   */
  materialName?: string;
  params?: { [key: string]: any };
  remark?: string;
  /**
   * 计量单位
   */
  unit?: string;
  updateBy?: string;
  updateTime?: Date;
  /**
   * 使用年代
   */
  usageYear?: number;
  [property: string]: any;
}
/**
 * 土壤环境调查监测信息
 */
interface SoilMonitoring {
  createBy?: string;
  createTime?: Date;
  /**
   * 上层埋深
   */
  depthTopCm?: number;
  /**
   * 归属部门
   */
  deptId?: number;
  /**
   * 主键ID
   */
  id?: number;
  params?: { [key: string]: any };
  remark?: string;
  /**
   * 土壤层序号
   */
  seqNo?: number;
  /**
   * 土层性质
   */
  soilType?: string;
  /**
   * 土层厚度
   */
  thicknessCm?: number;
  updateBy?: string;
  updateTime?: Date;
  [property: string]: any;
}

/**
 * 土壤分层信息
 */
interface SoilPathway {
  createBy?: string;
  createTime?: Date;
  /**
   * 上层埋深
   */
  depthTopCm?: number;
  /**
   * 归属部门
   */
  deptId?: number;
  /**
   * 主键ID
   */
  id?: number;
  params?: { [key: string]: any };
  remark?: string;
  /**
   * 土壤层序号
   */
  seqNo?: number;
  /**
   * 土层性质
   */
  soilType?: string;
  /**
   * 土层厚度
   */
  thicknessCm?: number;
  updateBy?: string;
  updateTime?: Date;
  [property: string]: any;
}
/**
 * 土壤监测发现的超标污染物信息
 */
interface SoilPollutant {
  createBy?: string;
  createTime?: Date;
  /**
   * 主键ID
   */
  deptId?: number;
  /**
   * 主键ID
   */
  id?: number;
  /**
   * 主键ID
   */
  maxConcentrationMgkg?: string;
  /**
   * 主键ID
   */
  monitoringId?: number;
  params?: { [key: string]: any };
  /**
   * 主键ID
   */
  pollutantName?: string;
  remark?: string;
  updateBy?: string;
  updateTime?: Date;
  [property: string]: any;
}
/**
 * 生产车间分布与环境情况
 */
interface WorkshopEnvironment {
  coordinate?: string;
  createBy?: string;
  createdAt?: string;
  createdBy?: string;
  createTime?: Date;
  deptId?: number;
  id?: number;
  leakage?: string;
  leakImagePath?: string;
  params?: { [key: string]: any };
  processDescription?: string;
  remark?: string;
  startDate?: Date;
  updateBy?: string;
  updateTime?: Date;
  workshopName?: string;
  [property: string]: any;
}
type DataParams =
  | Enterprise
  | PollutantSource
  | Watersource
  | Groundwatermonitoring
  | Wells
  | Receptor
  | MainProducts
  | RawMaterials
  | SoilMonitoring
  | SoilPathway
  | SoilPollutant
  | WorkshopEnvironment;
enum DataType {
  /**企业管理信息 */
  enterprise = "enterprise",
  /**企业污染源 */
  pollutantSource = "pollutantSource",
  /**水源信息 */
  watersource = "watersource",
  /**地下水迁移途径信息 */
  groundwaterPathway = "groundwaterPathway",
  /**地下水环境调查监测信息*/
  groundwatermonitoring = "groundwatermonitoring",
  /**园区监测井信息*/
  wells = "wells",
  /**地块及周边敏感受体*/
  receptor = "receptor",
  /**主要产品信息*/
  mainProducts = "mainProducts",
  /**原辅材料信息*/
  rawMaterials = "rawMaterials",
  /**土壤环境调查监测信息*/
  soilMonitoring = "soilMonitoring",
  /**土壤分层信息*/
  soilPathway = "soilPathway",
  /**土壤污染物信息*/
  soilPollutant = "soilPollutant",
  /**生产车间分布与环境情况*/
  workshopEnvironment = "workshopEnvironment",
}

enum Authority {
  "admin",
  "user",
  "guest",
}
type DataMethod = "change" | "add" | "export" | "query" | "delete" | "get"; // 确保有这一行

type ApiData = {
  dataType?: DataType;
  authority?: Authority;
  params?: DataParams;
  dataMethod?: DataMethod;
};
/**项目数据接口
 * @param object :{  
数据类型 dataType： @enum {DataType} @default "enterprise"
 权限  authority: @enum {Authority} @default "guest"
 参数：params  @param object @default {}
 数据方法 dataMethod @enum {DataMethod}  @default "query"
 */
function projectApi({
  apiData: ApiData = {
    dataType: "enterprise",
    authority: "admin",
    params: {},
    dataMethod: "query",
  },
}) {
  let { dataType, authority, params, dataMethod } = ApiData;
  console.log("🚀 ~ ApiData:", ApiData);
  let url: string = `/${authority}/${dataType}`;
  let method = dataMethod;
  let options: object;
  if (!params) {
    console.error("projectApi:param:传参");
  }
  switch (dataMethod) {
    case "change":
    case "add":
      method = "post";
      options = {
        body: params,
        headers: {
          "Content-Type": "application/json",
        },
      };
      break;
    case "export":
      method = "post";
      url = `${url}/export`;
      options = {
        query: params,
      };
      break;
    case "query":
      method = "get";
      url = `${url}/list`;
      options = {
        query: params,
      };
      break;
    case "delete":
      method = "delete";
      let ids = {};
      if (Array.isArray(params) && params.length > 0) {
        for (let index in params) {
          ids[index] = params[index];
        }
        ids = JSON.stringify(ids);
      }
      url = `${url}/${ids}`;
      break;
    case "get":
      let id = Number(params?.id);
      method = "get";
      url = `${url}/${id}`;
      break;
  }
  options = {
    ...options,
    method,
    url,
    baseURL: "project",
  };
  console.log("🚀 ~ options:", options);
  return request(options);
}
export { DataType, Authority, projectApi };
export type { DataParams, DataMethod, ApiData };
