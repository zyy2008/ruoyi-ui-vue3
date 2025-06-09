import { defineComponent, ExtractPropTypes, PropType, watchEffect } from "vue";
import Schema from "@/components/Form";
import { ISchemaFieldProps, FormProvider } from "@formily/vue";
import { Field, createForm, onFormInit, Form } from "@formily/core";
import { FormButtonGroup, Submit, Reset } from "@formily/element-plus";
import userStore from "@/store/modules/user";
import API from "@/services";
import { ElCard, ElButton, ElSpace, ElMessage } from "element-plus";
import { CloseBold, EditPen } from "@element-plus/icons-vue";
import { observer } from "@formily/reactive-vue";
import { observable, autorun } from "@formily/reactive";
import { useRequest } from "vue-request";
import {
  MainProducts,
  RawMaterials,
  WorkshopEnvironment,
  StorageArea,
  CheckboxCol,
  Radios,
  PipelineLeak,
  SuspectedPollutionTrace,
  PollutionIncident,
  LandPollutionAnalysis,
  SewageSolidWaste,
} from "./components";
import { useDeptId } from "@/hooks";
import { FormCollapse } from "@formily/element-plus";

const { SchemaField } = Schema;

const formCollapse = FormCollapse.createFormCollapse();

formCollapse.setActiveKeys([]);

const props: ISchemaFieldProps = {
  schema: {
    type: "object",
    properties: {
      layout: {
        type: "void",
        "x-decorator": "FormLayout",
        "x-decorator-props": {
          labelCol: 8,
          wrapperCol: 16,
        },
        "x-component": "FormGrid",
        "x-component-props": {
          minColumns: 2,
          maxColumns: 2,
        },
        properties: {
          facilitiesAreas: {
            type: "string",
            title: "企业存在以下设施或区域(多选)",
            required: true,
            "x-decorator": "FormItem",
            enum: [
              {
                label: "生产区",
                value: "1",
              },
              {
                label: "储存区",
                value: "2",
              },
              {
                label: "管道",
                value: "3",
              },
              {
                label: "废气治理设施",
                value: "4",
              },
              {
                label: "废水治理区域",
                value: "5",
              },
              {
                label: "固体废物贮存或处置区",
                value: "6",
              },
              {
                label: "空地",
                value: "7",
              },
            ],
            "x-component": "CheckboxGroupString",
          },
          floorPlan: {
            type: "string",
            title: "平面布置图",
            "x-decorator": "FormItem",
            "x-component": "UploadAjax",
            "x-component-props": {
              listType: "picture-card",
              accept: ".jpg,.png",
            },
          },
        },
      },
      collapse: {
        type: "void",
        "x-component": "FormCollapse",
        "x-component-props": {
          formCollapse: formCollapse,
        },
        properties: {
          tab1: {
            type: "void",
            "x-component": "FormCollapse.Item",
            "x-component-props": {
              title: "一、生产区",
            },
            properties: {
              card: {
                type: "void",
                "x-component": <MainProducts />,
              },
              card1: {
                type: "void",
                "x-component": <RawMaterials />,
              },
              card2: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "三、主要中间产物",
                },
                properties: {
                  mainIntermediateProductName: {
                    type: "string",
                    title: "主要中间产物名称",
                    "x-decorator": "FormItem",
                    "x-component": "Input",
                  },
                },
              },
              card3: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "四、生产工艺",
                },
                properties: {
                  mainProcessFlowChart: {
                    type: "string",
                    title: "主要生产工艺流程图",
                    "x-decorator": "FormItem",
                    "x-component": "UploadAjax",
                    "x-component-props": {
                      listType: "picture-card",
                      accept: ".jpg,.png",
                    },
                  },
                  mainProcessProductionDescription: {
                    type: "string",
                    title: "主要生产工艺描述",
                    "x-decorator": "FormItem",
                    "x-component": "Input.TextArea",
                  },
                },
              },
              card4: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "五、生产车间分布与环境情况",
                },
                properties: {
                  card: {
                    type: "void",
                    "x-component": <WorkshopEnvironment />,
                  },
                  workshopArea: {
                    type: "string",
                    title: "生产车间总面积",
                    "x-decorator": "FormItem",
                    "x-component": "Input",
                  },
                },
              },
              card5: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "六、清洁生产审核",
                },
                properties: {
                  isCleanProductionAudited: {
                    type: "string",
                    title: "企业是否开展过清洁生产审核",
                    "x-decorator": "FormItem",
                    enum: [
                      {
                        label: "是",
                        value: "0",
                      },
                      {
                        label: "否",
                        value: "1",
                      },
                    ],
                    "x-component": "Radio.Group",
                  },
                  cleanAuditTime: {
                    type: "string",
                    title: "洁洁生产审校时间",
                    "x-decorator": "FormItem",
                    "x-component": "DatePicker",
                    "x-component-props": {
                      type: "month",
                    },
                  },
                  cleanLevel: {
                    type: "string",
                    title: "清洁生产水平",
                    "x-decorator": "FormItem",
                    enum: [
                      {
                        label: "一级",
                        value: "0",
                      },
                      {
                        label: "二级",
                        value: "1",
                      },
                      {
                        label: "三级",
                        value: "2",
                      },
                      {
                        label: "未知",
                        value: "3",
                      },
                    ],
                    "x-component": "Radio.Group",
                  },
                },
              },
            },
          },
          tab2: {
            type: "void",
            "x-component": "FormCollapse.Item",
            "x-component-props": {
              title: "二、存储区",
            },
            properties: {
              card1: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "储罐、储槽等储存设施*",
                },
                properties: {
                  storageFacility: {
                    type: "string",
                    "x-decorator": "FormItem",
                    required: true,
                    enum: [
                      {
                        label: "有",
                        value: "0",
                      },
                      {
                        label: "无",
                        value: "1",
                      },
                    ],
                    "x-component": "Radio.Group",
                  },
                },
              },
              card2: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "存放产品、原材料的或其他有毒物质的仓库等*",
                },
                properties: {
                  hazardousMaterialWarehouse: {
                    type: "string",
                    "x-decorator": "FormItem",
                    required: true,
                    enum: [
                      {
                        label: "有",
                        value: "0",
                      },
                      {
                        label: "无",
                        value: "1",
                      },
                    ],
                    "x-component": "Radio.Group",
                  },
                },
              },
              card3: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "产品，原材料或其他有毒有害物质的仓库等*",
                },
                properties: {
                  hazardousMaterialLoadingAndUnloadingArea: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    enum: [
                      {
                        label: "有",
                        value: "0",
                      },
                      {
                        label: "无",
                        value: "1",
                      },
                    ],
                    "x-component": "Radio.Group",
                  },
                },
              },
              gt4: {
                type: "void",
                "x-component": () => (
                  <div
                    style={{
                      margin: "10px 0",
                    }}
                  >
                    以上三项中任何一项选择是，则须填写以下各项内容，否则不填
                  </div>
                ),
              },
              card4: {
                type: "void",
                "x-component": <StorageArea />,
              },
              card5: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "各储存区城防护措施*(多选)",
                },
                properties: {
                  protectionMeasuresForStorageAreas: {
                    type: "string",
                    "x-decorator": "FormItem",
                    required: true,
                    "x-component": (
                      <CheckboxCol
                        datas={[
                          [
                            "地面无任何处理",
                            "铺设防渗材料",
                            "有顶棚或表面覆盖",
                            "四周有围堰或围墙",
                            "有雨水收集池或导排管道",
                          ],
                          [
                            "地面硬化且完整",
                            "防渗材料完整无破损",
                            "顶棚或表面覆盖完整",
                            "围堰或围墙完整",
                            "雨水收集池或导排管道有渗漏",
                          ],
                          [
                            "硬化地面有裂缝",
                            "防渗材料有破损",
                            "顶棚或表面覆盖有破损",
                            "围堰或围墙不完整，可随意进入",
                          ],
                        ]}
                      />
                    ),
                  },
                },
              },
              card7: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "存储区总面积*",
                },
                properties: {
                  storageArea: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    "x-component": "Input",
                  },
                },
              },
              card8: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "数据来源",
                },
                properties: {
                  dataSourceOfStorageArea: {
                    type: "string",
                    "x-decorator": "FormItem",
                    "x-component": "Input",
                  },
                },
              },
            },
          },
          tab3: {
            type: "void",
            "x-component": "FormCollapse.Item",
            "x-component-props": {
              title: "三、管道",
            },
            properties: {
              card: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "管道是否发生泄漏*",
                },
                properties: {
                  hasThereBeenAPipeLeak: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    "x-component": <Radios.Input />,
                  },
                },
              },
              card1: {
                type: "void",
                "x-component": <PipelineLeak />,
              },
              card2: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "地下管道管线图*",
                },
                properties: {
                  undergroundPipeLineDiagram: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    "x-component": "UploadAjax",
                    "x-component-props": {
                      listType: "picture-card",
                      accept: ".jpg,.png",
                    },
                  },
                },
              },
            },
          },
          tab4: {
            type: "void",
            "x-component": "FormCollapse.Item",
            "x-component-props": {
              title: "四、废气治理设施",
            },
            properties: {
              card: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "是否有废气排放*(若选择有，则需填写以下各项内容)",
                },
                properties: {
                  isWasteGasEmission: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    enum: [
                      {
                        label: "有",
                        value: "0",
                      },
                      {
                        label: "无",
                        value: "1",
                      },
                    ],
                    "x-component": "Radio.Group",
                  },
                },
              },
              card1: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "废气排放量*(万立方米)",
                },
                properties: {
                  totalGasEmission: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    "x-component": "Input",
                  },
                },
              },
              card2: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "废气中主要污染物名称*",
                },
                properties: {
                  mainPollutantsInEmissions: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    "x-component": "Input",
                  },
                },
              },
              card3: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "是否有废气在线监测装置*",
                },
                properties: {
                  isThereAnOnlineMonitoringDeviceForEmissions: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    enum: [
                      {
                        label: "有",
                        value: "0",
                      },
                      {
                        label: "无",
                        value: "1",
                      },
                    ],
                    "x-component": "Radio.Group",
                  },
                },
              },
              card4: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "在线监测装置运行情况*",
                },
                properties: {
                  在线监测装置运行情况: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    enum: [
                      {
                        label: "运行良好",
                        value: "0",
                      },
                      {
                        label: "未运行",
                        value: "1",
                      },
                    ],
                    "x-component": "Radio.Group",
                  },
                },
              },
              card5: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "是否有废气治理设施*",
                },
                properties: {
                  isThereAnEmissionTreatmentFacility: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    enum: [
                      {
                        label: "有",
                        value: "0",
                      },
                      {
                        label: "无",
                        value: "1",
                      },
                    ],
                    "x-component": "Radio.Group",
                  },
                },
              },
              card6: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "废气治理设施运行情况*",
                },
                properties: {
                  operationStatusOfEmissionTreatmentFacility: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    enum: [
                      {
                        label: "运行良好",
                        value: "0",
                      },
                      {
                        label: "未运行",
                        value: "1",
                      },
                    ],
                    "x-component": "Radio.Group",
                  },
                },
              },
              card7: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "数据来源",
                },
                properties: {
                  dataSourceOfWasteGas: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    "x-component": "Input",
                  },
                },
              },
            },
          },
          tab5: {
            type: "void",
            "x-component": "FormCollapse.Item",
            "x-component-props": {
              title: "五、废水治理设施",
            },
            properties: {
              card: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "是否有废水排放*(若选择有，则需填写以下各项内容)",
                },
                properties: {
                  isThereWastewaterDischarge: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    enum: [
                      {
                        label: "有",
                        value: "0",
                      },
                      {
                        label: "无",
                        value: "1",
                      },
                    ],
                    "x-component": "Radio.Group",
                  },
                },
              },
              card1: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "废水排放量*(吨)",
                },
                properties: {
                  wastewaterDischargeVolume: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    "x-component": "Input",
                  },
                },
              },
              card2: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "废水中主要污染物名称*",
                },
                properties: {
                  mainPollutantsInWastewater: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    "x-component": "Input",
                  },
                },
              },
              card3: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "是否有废水在线监测装置*",
                },
                properties: {
                  isThereAnOnlineMonitoringDeviceForWastewater: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    enum: [
                      {
                        label: "有",
                        value: "0",
                      },
                      {
                        label: "无",
                        value: "1",
                      },
                    ],
                    "x-component": "Radio.Group",
                  },
                },
              },
              card4: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "在线监测装置运行情况*",
                },
                properties: {
                  在线监测装置运行情况: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    enum: [
                      {
                        label: "运行良好",
                        value: "0",
                      },
                      {
                        label: "未运行",
                        value: "1",
                      },
                    ],
                    "x-component": "Radio.Group",
                  },
                },
              },
              card5: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "是否有废气治理设施*",
                },
                properties: {
                  isThereAWastewaterTreatmentFacility: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    enum: [
                      {
                        label: "有",
                        value: "0",
                      },
                      {
                        label: "无",
                        value: "1",
                      },
                    ],
                    "x-component": "Radio.Group",
                  },
                },
              },
              card6: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "废气治理设施运行情况*",
                },
                properties: {
                  operationStatusOfWastewaterTreatmentFacility: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    enum: [
                      {
                        label: "运行良好",
                        value: "0",
                      },
                      {
                        label: "未运行",
                        value: "1",
                      },
                    ],
                    "x-component": "Radio.Group",
                  },
                },
              },
              card7: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "废水治理区坐标位置",
                },
                properties: {
                  coordinatesOfWastewaterTreatmentArea: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    "x-component": "Input",
                  },
                },
              },
              card8: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "废水治理区面积*",
                },
                properties: {
                  areaOfWastewaterTreatmentArea: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    "x-component": "Input",
                  },
                },
              },
              card9: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "废水治理区污染痕迹",
                },
                properties: {
                  pollutionTracesInWastewaterTreatmentArea: {
                    type: "string",
                    "x-decorator": "FormItem",
                    enum: [
                      {
                        label: "有",
                        value: "0",
                      },
                      {
                        label: "无",
                        value: "1",
                      },
                    ],
                    "x-component": "Radio.Group",
                  },
                },
              },
              card10: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "污染痕迹照片",
                },
                properties: {
                  photosOfPollutionTraces: {
                    type: "string",
                    "x-decorator": "FormItem",
                    "x-component": "UploadAjax",
                    "x-component-props": {
                      listType: "picture-card",
                      accept: ".jpg,.png",
                    },
                  },
                },
              },
              card11: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "废水治理区防护措施",
                },
                properties: {
                  protectionMeasuresInWastewaterTreatmentArea: {
                    type: "string",
                    "x-decorator": "FormItem",
                    required: true,
                    "x-component": (
                      <CheckboxCol
                        datas={[
                          [
                            "地面无任何处理",
                            "铺设防渗材料",
                            "四周有围堰或围墙",
                            "有事故水应急池",
                          ],
                          [
                            "地面硬化且完整",
                            "防渗材料完整无破损",
                            "围堰或围墙完整",
                            "事故水应急池有渗漏",
                          ],
                          [
                            "硬化地面有裂缝",
                            "防渗材料有破损",
                            "围堰或围墙不完整，可随意进入",
                          ],
                        ]}
                      />
                    ),
                  },
                },
              },
              card12: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "数据来源",
                },
                properties: {
                  dataSourceOfWastewater: {
                    type: "string",
                    "x-decorator": "FormItem",
                    "x-component": "Input",
                  },
                },
              },
            },
          },
          tab6: {
            type: "void",
            "x-component": "FormCollapse.Item",
            "x-component-props": {
              title: "六、固体废物贮存或处置区",
            },
            properties: {
              card: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header:
                    "是否产生固体废物*(若选择有，则需填写以下第2-16项内容)",
                },
                properties: {
                  isSolidWasteGenerated: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    enum: [
                      {
                        label: "是",
                        value: "0",
                      },
                      {
                        label: "否",
                        value: "1",
                      },
                    ],
                    "x-component": "Radio.Group",
                  },
                },
              },
              card1: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "是否有固体废物贮存*",
                },
                properties: {
                  isSolidWasteStored: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    enum: [
                      {
                        label: "是",
                        value: "0",
                      },
                      {
                        label: "否",
                        value: "1",
                      },
                    ],
                    "x-component": "Radio.Group",
                  },
                },
              },
              card2: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "是否有危险废物自处置*",
                },
                properties: {
                  isHazardousWasteSelfDisposed: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    enum: [
                      {
                        label: "是",
                        value: "0",
                      },
                      {
                        label: "否",
                        value: "1",
                      },
                    ],
                    "x-component": "Radio.Group",
                  },
                },
              },
              card3: {
                type: "void",
                "x-component": <SewageSolidWaste />,
              },
              card4: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "固废贮存或处置区防护措施*(多选)",
                },
                properties: {
                  solidWasteStorageAndDisposalAreaProtectionMeasures: {
                    type: "string",
                    "x-decorator": "FormItem",
                    required: true,
                    "x-component": (
                      <CheckboxCol
                        datas={[
                          [
                            "地面无任何处理",
                            "防渗材料完整无破损",
                            "表面覆盖有破损",
                            "有雨水收集池或导排管道",
                          ],
                          [
                            "地面硬化且完整",
                            "防渗材料有破损",
                            "四周有围堰或围墙",
                            "雨水收集池或导排管道有渗漏",
                          ],
                          [
                            "硬化地面有裂缝",
                            "有表面覆盖",
                            "围堰或围墙完整",
                            "有渗滤液收集或处理设施",
                          ],
                          [
                            "铺设防渗材料",
                            "表面覆盖完整",
                            "围堰或围墙不完整，可随意进入",
                            "渗滤液收集或处理设施有渗漏",
                          ],
                        ]}
                      />
                    ),
                  },
                },
              },
              card5: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "危废贮存或处置区防护措施*(多选)",
                },
                properties: {
                  hazardousWasteStorageAndDisposalAreaProtectionMeasures: {
                    type: "string",
                    "x-decorator": "FormItem",
                    required: true,
                    "x-component": (
                      <CheckboxCol
                        datas={[
                          [
                            "地面无任何处理",
                            "防渗材料完整无破损",
                            "表面覆盖有破损",
                            "有雨水收集池或导排管道",
                          ],
                          [
                            "地面硬化且完整",
                            "防渗材料有破损",
                            "四周有围堰或围墙",
                            "雨水收集池或导排管道有渗漏",
                          ],
                          [
                            "硬化地面有裂缝",
                            "有表面覆盖",
                            "围堰或围墙完整",
                            "有渗滤液收集或处理设施",
                          ],
                          [
                            "铺设防渗材料",
                            "表面覆盖完整",
                            "围堰或围墙不完整，可随意进入",
                            "渗滤液收集或处理设施有渗漏",
                          ],
                        ]}
                      />
                    ),
                  },
                },
              },
              card6: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "危险废物规范化管理考核评估结果",
                },
                properties: {
                  hazardousWasteStandardizedManagementAssessmentResults: {
                    type: "string",
                    "x-decorator": "FormItem",
                    enum: [
                      {
                        label: "达标",
                        value: "0",
                      },
                      {
                        label: "基本达标",
                        value: "1",
                      },
                      {
                        label: "不达标",
                        value: "2",
                      },
                      {
                        label: "未知",
                        value: "3",
                      },
                    ],
                    "x-component": "Radio.Group",
                  },
                },
              },
              card7: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "数据来源",
                },
                properties: {
                  dataSourceOfSolidWasteStorageAndDisposalArea: {
                    type: "string",
                    "x-decorator": "FormItem",
                    "x-component": "Input",
                  },
                },
              },
            },
          },
          tab7: {
            type: "void",
            "x-component": "FormCollapse.Item",
            "x-component-props": {
              title: "七、其他可疑污染源或污染痕迹",
            },
            properties: {
              card: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header:
                    "地块内道路、地表、建（构）筑物表面、墙壁、空地污染痕迹（若选择有，则需填写以下第2-6项内容，否则不填）",
                },
                properties: {
                  internalPollutionTracesOfThePlot: {
                    type: "string",
                    "x-decorator": "FormItem",
                    enum: [
                      {
                        label: "有",
                        value: "0",
                      },
                      {
                        label: "无",
                        value: "1",
                      },
                    ],
                    "x-component": "Radio.Group",
                  },
                },
              },
              card1: {
                type: "void",
                "x-component": <SuspectedPollutionTrace />,
              },
              card2: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "空气异味状况*",
                },
                properties: {
                  isHazardousWasteSelfDisposed: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    "x-component": <Radios.Radio />,
                  },
                },
              },
            },
          },
          tab8: {
            type: "void",
            "x-component": "FormCollapse.Item",
            "x-component-props": {
              title: "八、环境污染事故发生情况",
            },
            properties: {
              card: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "环境污染事故发生情况*",
                },
                properties: {
                  environmentalPollutionAccidentOccurrence: {
                    type: "string",
                    required: true,
                    "x-decorator": "FormItem",
                    "x-component": <Radios.Input1 />,
                  },
                },
              },
              card1: {
                type: "void",
                "x-component": <PollutionIncident />,
              },
              card2: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "事故基本情况描述",
                },
                properties: {
                  basicDescriptionOfAccident: {
                    type: "string",
                    "x-decorator": "FormItem",
                    "x-component": "Input.TextArea",
                  },
                },
              },
              card3: {
                type: "void",
                "x-component": "Card",
                "x-component-props": {
                  header: "污染区域处理情况",
                },
                properties: {
                  pollutionAreaTreatmentSituation: {
                    type: "string",
                    "x-decorator": "FormItem",
                    "x-component": "Input.TextArea",
                  },
                },
              },
            },
          },
          tab9: {
            type: "void",
            "x-component": "FormCollapse.Item",
            "x-component-props": {
              title: "九、地块污染情况分析",
            },
            properties: {
              card: {
                type: "void",
                "x-component": <LandPollutionAnalysis />,
              },
            },
          },
        },
      },
    },
  },
};

export default defineComponent({
  setup() {
    const { deptId } = useDeptId();

    const form = createForm();

    const { run, loading, data } = useRequest<any>(() =>
      API.getAdminPollutionList({ deptId })
    );
    watchEffect(() => {
      if (data.value?.code === 200) {
        const item = data.value.data;
        form.reset().then(() => {
          form.setValues(item);
        });
      }
    });

    return () => (
      <div class="app-container">
        <ElCard>
          <FormProvider form={form}>
            <SchemaField {...props} />
            <FormButtonGroup
              align="center"
              style={{
                margin: "10px",
              }}
            >
              <ElButton
                type="primary"
                loading={loading.value}
                onClick={() => {
                  form.submit().then((val: any) => {
                    const { id } = val;
                    const api = id
                      ? API.putAdminPollution
                      : API.postAdminPollution;
                    api({
                      ...val,
                      deptId,
                    }).then((res) => {
                      if (res.code === 200) {
                        ElMessage.success("保存成功");
                        run();
                      } else {
                        ElMessage.success("保存失败");
                      }
                    });
                  });
                }}
              >
                保存
              </ElButton>
            </FormButtonGroup>
          </FormProvider>
        </ElCard>
      </div>
    );
  },
});
