import { listLayer } from "@/api/admin/layer";
import { getBaseSelect } from "@/api/admin/base";
import { getSetTree } from "@/api/admin/set";
import { handleTree } from "@/utils/ruoyi.js"
import { FilterDict, FiledTableDict } from "./mapFilter"
import { getDicts } from '@/api/system/dict/data'
let LocalProxy;
let attribute_query = false;
//获取filter参数
export function getFilter(code) {
    if (FilterDict[code]) {
        return FilterDict[code]
    }
    return ""
}

function getParmarsObj(params) {
    const object = {}

    // 先按照逗号分隔
    const matches = params.split(",")

    // 再按照=分隔
    matches.forEach(match => {
        const [key, value] = match.split('=')
        object[key] = value
    })

    return object
}

function createWMSLayer(layer, pid) {
    let params = getParmarsObj(layer.layerParam)
    return {
        "pid": pid,
        "name": layer.name,
        "type": "wms",
        "url": layer.url,
        "layers": params.layer,
        "crs": "EPSG:4326",
        // "crs": "EPSG:3587",
        "parameters": {
            "transparent": "true",
            "format": "image/png",
            "cql_filter": getFilter(layer.code)
        },
        //"popup": getPopup,
        "highlight": {
            type: "polylineP",
            width: 10,
            clampToGround: true,
            materialType: "PolylineGlow",
            materialOptions: {
                color: "#ff0000",
                glowPower: 0.1, // 发光强度
            },
        },
        "show": layer.isShow,
        "flyTo": true,
        "code": layer.code,
        "layerId": layer.id
    }
}


function getPopup(e) {
    // if (!attribute_query) {
    //     return;
    // }
    // attribute_query = false;
    console.log(e)
    let attr = e.attr;
    // let options = e.layer.options;
    // const { attribute_type } = LocalProxy.useDict("attribute_type");
    // console.log("attribute_type", attribute_type)
    // let attNames = attribute_type.value;
    let html = '';

    // let fields = FiledTableDict[layers]
    // if (fields && fields.length > 0) {
    //     for (let i = 0; i < fields.length; i++) {

    //         const field = fields[i];
    //         let key = field.alias;
    //         let value = attr[key] || "-";
    //         value = value === null ? '-' : value;
    //         // 使用模板字符串构建 HTML 字符串
    //         html += `<tr><td>${field.name}</td><td>${value}</td></tr>`;
    //     }
    //     return "<table style='width:280px;'>" + html + "</table>"
    // }

    Object.keys(attr).forEach(key => {
        // 将属性名和属性值拼接成字符串
        let value = attr[key] === null ? '-' : attr[key];
        value = value || "-";
        // 使用模板字符串构建 HTML 字符串
        html += `<tr><td style='width:50%;'>${getFiledName(LocalProxy || [], key)}</td><td style='width:50%;'>${value}</td></tr>`;
    });

    return "<table style='width:100%;'>" + html + "</table>"
}


function getFiledName(dic, key) {
    key = key.toLowerCase();
    for (let i = 0; i < dic.length; i++) {
        let item = dic[i];
        let value = item.value.toLowerCase();
        if (value == key) {
            console.log("我找到了，", value)
            return item.label
        }
    }

    return key;
}

function createTreeDataFromSet(setList) {

    console.log(setList)

    let layersMap = []
    let clipLayersCode = []
    setList.forEach(base => {

        let layerSetVoList = base.layerSetVoList || [];
        layerSetVoList.forEach(a => {

            let group_base = { "id": a.id + "_base", "pid": -1, "name": a.name, "type": "group" }
            group_base._hasMapInit = true;
            //基地
            layersMap.push(group_base);
            let elementClassificationVoList = a.elementClassificationVoList || [];
            elementClassificationVoList.forEach(b => {
                let group_class = { "id": b.id + "_class", "pid": a.id + "_base", "name": b.name, "type": "group" }
                group_class._hasMapInit = true;
                //大类
                layersMap.push(group_class);
                let elementClassificationNormVoList = b.elementClassificationNormVoList || [];
                let layers = elementClassificationNormVoList.filter(c => !!c.layer); //去除空数据
                layers.forEach(d => {
                    let wms = createWMSLayer(d.layer, b.id + "_class")
                    wms._hasMapInit = true;
                    //图层
                    layersMap.push(wms);
                    clipLayersCode.push(d.layer.code)
                })

            });

        })
    });


    console.log("layersMap", layersMap)

    return {
        layersMap,
        clipLayersCode
    };

}

//如果-他只有一个基地，且基地只含有一个图层集，则默认选中这个
const layerStore = defineStore(
    'layer',
    {
        state: () => ({
            layers: [],
            treeData: [],
            expandedKeys: [],
            checkedKeys: [],
            setTrees: [], //原始数据
            setDeps:[], //所有部门
            layerSetVoList: [],//所有图层集
            baseList: [], //所有基地
            selectedBase: [],//选中的基地
            selectedBaseObj: null, //所选中的单个基地
            selectedlayerSet: "",

            lengthStatistics: undefined, //长度
            flowDirection: [], //流向分析
            sectionLayers: [], //断面分析
            sectionLayersOne: [],
            clipLayersCode: [], //被裁剪的目标图层
            attribute_flag: false



        }),
        actions: {

            setAttribute_query(flag) {
                this.attribute_flag = flag;
                //attribute_query = flag;
            },
            setProxy(proxy) {

                if (LocalProxy) {
                    return;
                }

                getDicts("attribute_type").then(resp => {
                    LocalProxy = resp.data.map(p => ({ label: p.dictLabel, value: p.dictValue, elTagType: p.listClass, elTagClass: p.cssClass }))
                })
            },
            //获取图层数据
            getlistLayer() {
                return new Promise((resolve, reject) => {
                    listLayer({}).then(res => {
                        this.layers = res.data;
                        res.data.forEach((element, index) => {
                            element.id = element.id + ""
                            element.key = element.id;
                            // element.index = index;
                            // element.hasZIndex = index;
                            // element.hasOpacity = 1;
                            // element.opacity = 100;
                        });
                        this.treeData = handleTree(res.data, "id", "pid");
                        console.log(" this.treeData,", this.treeData)
                        resolve()
                    }).catch(error => {
                        reject(error)
                    })
                })
            },
            //查询基地ID
            getBaseSelect() {
                return new Promise((resolve, reject) => {
                    getBaseSelect().then(res => {
                        this.baseList = res.data; //所有可查询基地
                        resolve()
                    }).catch(error => {
                        reject(error)
                    })
                })
            },
            setBaseSelect(baseSelectIds) {
                this.selectedBase = baseSelectIds;
                return this.getSetlistLayer();
            },
            //计算树形菜单
            computeTree(baseTree) {

                let layersMap = []
                let clipLayersCode = []

                //递归图层节点
                function recursiveLayer(cur_list, prop_name, sum_list, pid) {

                    cur_list.forEach(node => {


                        let elementClassificationVoList = node[prop_name] || []; //

                        elementClassificationVoList = elementClassificationVoList.filter(classGroup => {
                            if (!classGroup.elementClassificationNormVoList) {
                                return false;
                            }

                            let hasArr = classGroup.elementClassificationNormVoList.filter(c => !!c.layer);
                            return hasArr.length > 0;
                        }); //去除空数据
                        elementClassificationVoList.forEach(b => {
                            let group_class = { "id": b.id + "_class" + pid, "pid": pid, "name": b.name, "type": "group" }
                            group_class._hasMapInit = true;
                            //大类
                            sum_list.push(group_class);


                            let elementClassificationNormVoList = b["elementClassificationNormVoList"] || [];
                            let layers = elementClassificationNormVoList.filter(c => !!c.layer); //去除空数据
                            layers.forEach(d => {
                                let wms = createWMSLayer(d.layer, b.id + "_class" + pid)
                                wms._hasMapInit = true;
                                //图层
                                sum_list.push(wms);
                                clipLayersCode.push(d.layer.code)
                            })

                        })

                    })

                }

                //递归组节点
                function recursiveTraversal(cur_list, prop_name, sum_list, pid) {

                    cur_list.forEach(node => {
                        //组节点
                        let id = node.deptId + "_dept";
                        let name = node.deptName;
                        let type = "group";
                        let group_common = { "id": id, "pid": pid, "name": name, "type": type }
                        group_common._hasMapInit = true;
                        sum_list.push(group_common);

                        //递归开始
                        if (node[prop_name] && node[prop_name].length > 0) {
                            recursiveTraversal(node[prop_name], "children", sum_list, id)
                        }


                    });

                    cur_list.forEach(node => {
                        //组节点
                        let id = node.deptId + "_dept";
                        if (!node[prop_name] || node[prop_name].length < 1) {
                            recursiveLayer(node["layerSetVoList"], "elementClassificationVoList", sum_list, id)
                        }

                    });

                }

                baseTree.forEach(base => {

                    let depMap = [];
                    recursiveTraversal(base.sysDeptVoList, "children", depMap, -1);
                    layersMap.push(...depMap)
                });

                return {
                    layersMap,
                    clipLayersCode
                };

            },
            getSetlistLayer() {
                this.setDeps=[];
                return new Promise((resolve, reject) => {
                    getSetTree({
                        baseId: this.selectedBase.join(",")
                    }).then(res => {
                        this.selectedlayerSet = ""
                        //this.setTrees = res.data.filter(base => base.layerSetVoList && base.layerSetVoList.length > 0)
                        this.setTrees = res.data;
                        console.log("this.setTrees,", this.setTrees)
                        let layerData = this.computeTree(this.setTrees)

                        this.layers = layerData.layersMap;
                        this.clipLayersCode = layerData.clipLayersCode;
                        console.log("this.layersMap,", layerData.layersMap)

                        //如果-他只有一个基地，且基地只含有一个图层集，则默认选中这个
                        //基地表-1
                        //部门表-2
                        //部门表-3----这个
                        //图层表-4
                        if (this.setTrees.length == 1 && this.setTrees[0].sysDeptVoList.length == 1 && this.setTrees[0].sysDeptVoList[0].children.length == 0) {
                            this.selectedBaseObj = this.setTrees[0].sysDeptVoList[0]; //赋值
                            this.selectedlayerSet = this.setTrees[0].sysDeptVoList[0].deptId;
                        }
                        if(this.setTrees.length == 1 && this.setTrees[0].sysDeptVoList.length == 1 && this.setTrees[0].sysDeptVoList[0].children.length > 1){
                            this.setDeps=this.setTrees[0].sysDeptVoList[0].children;
                        }

                        if (res.data.length == 1) {
                            let base = res.data[0];
                            if (window.map && base.view) {
                                let view = JSON.parse(base.view);

                                window.map.setCameraView(view)
                                console.log("view-Map", view)
                            }
                        }
                        resolve()
                    }).catch(error => {
                        reject(error)
                    })
                })
            },

            //设置长途统计图层
            setLengthStatistics(treeData, lengthStatistics, flowDirection, sectionLayers, sectionLayersOne) {
                this.lengthStatistics = lengthStatistics || {};
                this.flowDirection = flowDirection || [];
                this.sectionLayers = sectionLayers || [];
                this.sectionLayersOne = sectionLayersOne || [];
                this.treeData = treeData || [];
                console.log("this.lengthStatistics", this.lengthStatistics)
            }
        }
    })


layerStore.getPopup = getPopup
export default layerStore
