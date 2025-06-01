import { allWells } from '@/api/admin/wells'
import { listEnterprises } from "@/api/admin/enterprises"

export async function loadEnterprises() {
  const res = await listEnterprises({ pageNum: 1, pageSize: 2000 })
  let rows = res.rows || [];
  console.log(rows)

  let enterpriseList = rows.filter(item => !!item.longitude && !!item.latitude);
  addEnterpriseLayer(enterpriseList);
}

function addEnterpriseLayer(list) {
  let graphicLayer = new mars3d.layer.GraphicLayer({
    name: "企业信息",
  })
  map.addLayer(graphicLayer)

  list.forEach(item => {
    const graphic = new mars3d.graphic.BillboardPrimitive({
      position: [Number(item.longitude), Number(item.latitude), 10],
      style: {
        image: "img/marker/mark-red.png",
        scale: 1,
        horizontalOrigin: Cesium.HorizontalOrigin.CENTER,
        verticalOrigin: Cesium.VerticalOrigin.BOTTOM,
        distanceDisplayCondition: true,
        distanceDisplayCondition_far: 50000,
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
  window.openQYMSG(event)
}

export async function loadWells() {
  const res = await allWells()
  let rows = res.rows || [];
  let wellList = rows.filter(item => !!item.longitude && !!item.latitude);
  console.log(wellList)

  addWellLayer(wellList);
}

function addWellLayer(list) {
  let graphicLayer = new mars3d.layer.GraphicLayer({
    name: "监测井信息",
  })
  map.addLayer(graphicLayer)

  list.forEach(item => {
    const graphic = new mars3d.graphic.BillboardPrimitive({
      position: [Number(item.longitude), Number(item.latitude), 10],
      style: {
        image: "img/marker/well.png",
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
        { field: "location", name: "监测井位置" },
        { field: "completionDate", name: "成井时间" },
        { field: "pipeMaterial", name: "井管材质" },
        { field: "screenDepthRange", name: "埋深范围" },
        { field: "burialCondition", name: "埋藏条件" },
        { field: "waterMedium", name: "含水介质" },
        { field: "ownership", name: "权属单位" },
        { field: "videoUrl", name: "视频资料地址" },
        { field: "deptId", name: "归属部门" },
        { field: "pointType", name: "类型" },
        { field: "waterBuriedDepth", name: "水位埋深" },
        { field: "wellElevation", name: "井口高程" },
        { field: "groundwaterType", name: "地下水类型" },
        { field: "longitude", name: "经度" },
        { field: "latitude", name: "纬度" },
        {
          type: "html",
          html: "<label>监测数据</label><div style='cursor: pointer;color: #ff0000;' id='btnDetails'>点击查看</div>"
        }
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
