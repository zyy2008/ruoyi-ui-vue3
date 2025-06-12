import { allWells } from '@/api/admin/wells'
import { listEnterprises } from "@/api/admin/enterprises"


export async function loadEnterprises() {
  const res = await listEnterprises({ pageNum: 1, pageSize: 2000 })
  let rows = res.rows || [];
  //111.9-112.6
  //37.7-37.4
  let enterpriseList = rows.filter(item => !!item.longitude && !!item.latitude && Number(item.longitude) >= 111.9 && Number(item.longitude) <= 112.6 && Number(item.latitude) >= 37.4 && Number(item.latitude) <= 37.7);
  let isKeyEnterpriseList = enterpriseList.filter(item => item.isKeyEnterprise === "是");
  let isNotKeyEnterpriseList = enterpriseList.filter(item => item.isKeyEnterprise === "否");

  addEnterpriseLayer_isNotKey(isNotKeyEnterpriseList);
  addEnterpriseLayer_isKey(isKeyEnterpriseList);
}

function addEnterpriseLayer_isKey(list) {
  let graphicLayer = new mars3d.layer.GraphicLayer({
    pid: 30,
    show: false,
    _hasMapInit: true,
    name: "重点企业",
  })
  map.addLayer(graphicLayer)

  list.forEach(item => {
    const graphic = new mars3d.graphic.BillboardPrimitive({
      position: [Number(item.longitude), Number(item.latitude), 10],
      style: {
        image: "img/marker/yq_a.png",
        scale: 1,
        horizontalOrigin: Cesium.HorizontalOrigin.CENTER,
        verticalOrigin: Cesium.VerticalOrigin.BOTTOM,
        distanceDisplayCondition: true,
        distanceDisplayCondition_far: 150000,
        distanceDisplayCondition_near: 0,
        clampToGround: true,
      },
      attr: { ...item },
      popup: [
        { field: "enterpriseName", name: "企业名称" },
        { field: "industryCategory", name: "行业类别" },
        { field: "productionYears", name: "生产年限" },
        { field: "status", name: "存续状态" },
        { field: "reportingStatus", name: "报送情况" },
        { field: "longitude", name: "经度" },
        { field: "latitude", name: "纬度" },
        { field: "area", name: "占地面积" },
        { field: "isKeyEnterprise", name: "是否重点" },
        {
          type: "html",
          html: "<label>企业信息</label><div style='cursor: pointer;color: #ff0000;' id='btnDetails'>点击查看</div>"
        }
      ],
    })
    graphicLayer.addGraphic(graphic)

    graphic.on(mars3d.EventType.popupOpen, function (event) {


      const container = event.container // popup对应的DOM
      const btnDetails = container.querySelector("#btnDetails")
      if (btnDetails) {
        btnDetails.addEventListener("click", (e) => {
          console.log(132121313);
          openQYPage(event)
          graphic.closePopup();
        })
      }
    })
  })
}

function addEnterpriseLayer_isNotKey(list) {
  let graphicLayer = new mars3d.layer.GraphicLayer({
    pid: 30,
    show: false,
    _hasMapInit: true,
    name: "非重点企业",
  })
  map.addLayer(graphicLayer)

  list.forEach(item => {
    const graphic = new mars3d.graphic.BillboardPrimitive({
      position: [Number(item.longitude), Number(item.latitude), 10],
      style: {
        image: "img/marker/yq_b.png",
        scale: 1,
        horizontalOrigin: Cesium.HorizontalOrigin.CENTER,
        verticalOrigin: Cesium.VerticalOrigin.BOTTOM,
        distanceDisplayCondition: true,
        distanceDisplayCondition_far: 150000,
        distanceDisplayCondition_near: 0,
        clampToGround: true,
      },
      attr: { ...item },
      popup: [
        { field: "enterpriseName", name: "企业名称" },
        { field: "industryCategory", name: "行业类别" },
        { field: "productionYears", name: "生产年限" },
        { field: "status", name: "存续状态" },
        { field: "reportingStatus", name: "报送情况" },
        { field: "longitude", name: "经度" },
        { field: "latitude", name: "纬度" },
        { field: "area", name: "占地面积" },
        { field: "isKeyEnterprise", name: "是否重点" },
        {
          type: "html",
          html: "<label>企业信息</label><div style='cursor: pointer;color: #ff0000;' id='btnDetails'>点击查看</div>"
        }
      ],
    })
    graphicLayer.addGraphic(graphic)

    graphic.on(mars3d.EventType.popupOpen, function (event) {


      const container = event.container // popup对应的DOM
      const btnDetails = container.querySelector("#btnDetails")
      if (btnDetails) {
        btnDetails.addEventListener("click", (e) => {
          console.log(132121313);
          openQYPage(event)
          graphic.closePopup();
        })
      }
    })
  })
}

window.openQYPage = function (event) {
  window.openQYMSG(event.attr.id)
}

export async function loadWells() {
  const res = await allWells()
  let rows = res.rows || [];
  let wellList = rows.filter(item => !!item.longitude && !!item.latitude);
  console.log(wellList)
  let wellList_realTime = wellList.filter(item => item.realTime === "是");
  let wellList_NotrealTime = wellList.filter(item => item.realTime !== "否");
  addWellLayer_NotrealTime(wellList_NotrealTime);
  addWellLayer_realTime(wellList_realTime);
}

window.QY_formatFun = function (value) {
  return value || "公共区域"
}

function addWellLayer_NotrealTime(list) {
  let graphicLayer = new mars3d.layer.GraphicLayer({
    pid: 20,
    show: false,
    _hasMapInit: true,
    name: "常规监测点",
  })
  map.addLayer(graphicLayer)

  list.forEach(item => {
    const graphic = new mars3d.graphic.BillboardPrimitive({
      position: [Number(item.longitude), Number(item.latitude), 10],
      style: {
        image: "img/marker/j_b.png",
        scale: 1,
        horizontalOrigin: Cesium.HorizontalOrigin.CENTER,
        verticalOrigin: Cesium.VerticalOrigin.BOTTOM,
        distanceDisplayCondition: true,
        distanceDisplayCondition_far: 50000,
        distanceDisplayCondition_near: 0,
        clampToGround: true,
        label: {
          text: item.wellCode,
          font_size: 18,
          color: "#00ff00",
          pixelOffsetY: -40,
          distanceDisplayCondition: true,
          distanceDisplayCondition_far: 50000,
          distanceDisplayCondition_near: 0
        }
      },
      attr: { ...item },
      popup: [
        { field: "wellCode", name: "监测井编码" },
        // { field: "enterpriseName", name: "所属企业" },
        {
          type: "html",
          html: "<label>所属企业</label><div style='cursor: pointer;color: #ff0000;' id='btnDetailsQy'>" + (item.enterpriseName || "公共区域") + "</div>"
        },
        { field: "location", name: "监测井位置" },
        { field: "completionDate", name: "成井时间" },
        { field: "pipeMaterial", name: "井管材质" },
        { field: "screenDepthRange", name: "埋深范围" },
        { field: "burialCondition", name: "埋藏条件" },
        { field: "waterMedium", name: "含水介质" },
        { field: "ownership", name: "权属单位" },
        { field: "videoUrl", name: "视频资料地址" },
        { field: "pointType", name: "类型" },
        { field: "waterBuriedDepth", name: "水位埋深" },
        { field: "wellElevation", name: "井口高程" },
        { field: "groundwaterType", name: "地下水类型" },
        { field: "longitude", name: "经度" },
        { field: "latitude", name: "纬度" },
        {
          type: "html",
          html: "<label>监测数据</label><div style='cursor: pointer;color: #ff0000;' id='btnDetails'>点击查看</div>"
        },
        // {
        //   type: "html",
        //   html: "<label>企业详情</label><div style='cursor: pointer;color: #ff0000;' id='btnDetailsQy'>点击查看</div>"
        // }
      ],
    })
    graphicLayer.addGraphic(graphic)


    graphic.on(mars3d.EventType.popupOpen, function (event) {
      openLinePage(event)
      const container = event.container // popup对应的DOM
      const btnDetails = container.querySelector("#btnDetails")
      if (btnDetails) {
        btnDetails.addEventListener("click", (e) => {
          openJCPage(event)
          graphic.closePopup();
          console.log("点击查看监测数据", event)

        })
      }

      const btnDetailsQy = container.querySelector("#btnDetailsQy")
      if (btnDetailsQy) {
        btnDetailsQy.addEventListener("click", (e) => {

          if (!event.attr || !event.attr.deptId) {
            globalMsg("该监测点属于公共区域")
            return;
          }
          graphic.closePopup();
          window.openQYMSG(event.attr.deptId)
        })
      }
    })
  })
}

function addWellLayer_realTime(list) {
  let graphicLayer = new mars3d.layer.GraphicLayer({
    pid: 20,
    _hasMapInit: true,
    name: "在线监测点",
  })
  map.addLayer(graphicLayer)

  list.forEach(item => {
    const graphic = new mars3d.graphic.BillboardPrimitive({
      position: [Number(item.longitude), Number(item.latitude), 10],
      style: {
        image: "img/marker/j_a.png",
        scale: 1,
        horizontalOrigin: Cesium.HorizontalOrigin.CENTER,
        verticalOrigin: Cesium.VerticalOrigin.BOTTOM,
        distanceDisplayCondition: true,
        distanceDisplayCondition_far: 50000,
        distanceDisplayCondition_near: 0,
        clampToGround: true,
        label: {
          text: item.wellCode,
          font_size: 18,
          color: "#00ff00",
          pixelOffsetY: -40,
          distanceDisplayCondition: true,
          distanceDisplayCondition_far: 50000,
          distanceDisplayCondition_near: 0
        }
      },
      attr: { ...item },
      popup: [
        { field: "wellCode", name: "监测井编码" },
        // { field: "enterpriseName", name: "所属企业" },
        {
          type: "html",
          html: "<label>所属企业</label><div style='cursor: pointer;color: #ff0000;' id='btnDetailsQy'>" + (item.enterpriseName || "公共区域") + "</div>"
        },
        { field: "location", name: "监测井位置" },
        { field: "completionDate", name: "成井时间" },
        { field: "pipeMaterial", name: "井管材质" },
        { field: "screenDepthRange", name: "埋深范围" },
        { field: "burialCondition", name: "埋藏条件" },
        { field: "waterMedium", name: "含水介质" },
        { field: "ownership", name: "权属单位" },
        { field: "videoUrl", name: "视频资料地址" },
        { field: "pointType", name: "类型" },
        { field: "waterBuriedDepth", name: "水位埋深" },
        { field: "wellElevation", name: "井口高程" },
        { field: "groundwaterType", name: "地下水类型" },
        { field: "longitude", name: "经度" },
        { field: "latitude", name: "纬度" },
        {
          type: "html",
          html: "<label>监测数据</label><div style='cursor: pointer;color: #ff0000;' id='btnDetails'>点击查看</div>"
        },
        // {
        //   type: "html",
        //   html: "<label>企业详情</label><div style='cursor: pointer;color: #ff0000;' id='btnDetailsQy'>点击查看</div>"
        // }
      ],
    })
    graphicLayer.addGraphic(graphic)


    graphic.on(mars3d.EventType.popupOpen, function (event) {
      openLinePage(event)
      const container = event.container // popup对应的DOM
      const btnDetails = container.querySelector("#btnDetails")
      if (btnDetails) {
        btnDetails.addEventListener("click", (e) => {
          graphic.closePopup();
          openJCPage(event)
          //
          console.log("点击查看监测数据", event)

        })
      }


      const btnDetailsQy = container.querySelector("#btnDetailsQy")
      if (btnDetailsQy) {
        btnDetailsQy.addEventListener("click", (e) => {

          if (!event.attr || !event.attr.deptId) {
            globalMsg("该监测点属于公共区域")
            return;
          }
          graphic.closePopup();
          window.openQYMSG(event.attr.deptId)
        })
      }
    })
  })
}


window.openJCPage = function (event) {
  window.openJCPageVue(event)
}

window.openLinePage = function (event) {
  window.openLineChartPage(event)
}
