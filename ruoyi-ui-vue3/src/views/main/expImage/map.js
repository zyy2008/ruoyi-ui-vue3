
let map; // mars3d.Map三维地图对象
import * as mars3d from "mars3d"
export const eventTarget = new mars3d.BaseClass()
import { compassBase64 } from "./imgData.js"
let tileLayer;
let graphicLayer;
/**
 * 初始化地图业务，生命周期钩子函数（必须）
 * 框架在地图初始化完成后自动调用该函数
 * @param {mars3d.Map} mapInstance 地图对象
 * @returns {void} 无
 */
export function onMounted(mapInstance, mars3dGlobe) {
    console.log("地图打印被挂载了")
    map = mapInstance // 记录map

    // // 经纬网
    // tileLayer = new mars3d.layer.GraticuleLayer({
    //     steps: [0.00025, 0.0005, 0.001, 0.0025, 0.005, 0.01, 0.025, 0.05],
    //     lineStyle: {
    //         color: "#fff",
    //         height: 1000
    //     },
    //     labelStyle: {
    //         color: "#ffff00",
    //         pixelOffset: new Cesium.Cartesian2(50, 50)
    //     }
    // })
    // map.addLayer(tileLayer)
    graphicLayer = new mars3d.layer.GraphicLayer()
    map.addLayer(graphicLayer)

   // squareGrid(1)
}

// 正方形网格
export function squareGrid(cellSide) {
    const turfOptions = { units: "kilometers" }
    const rectangleBounds = map.getExtent(); //获取屏幕范围
    const span = 0.02;
    const bbox = [rectangleBounds.xmin - span, rectangleBounds.ymin - span, rectangleBounds.xmax + span, rectangleBounds.ymax + span];
    const geojson = turf.squareGrid(bbox, cellSide, turfOptions)
    drawPolyon(geojson)
}

// 蜂窝网格、正方形网格、三角形网格
function drawPolyon(geojson) {
    graphicLayer.clear()
    const polygons = mars3d.Util.geoJsonToGraphics(geojson) // 解析geojson

    for (let i = 0; i < polygons.length; i++) {
        const item = polygons[i]
        console.log(item.positions)
        const graphic = new mars3d.graphic.PolygonPrimitive({
            positions: item.positions,
            style: {
                color: "#ffff00",
                opacity: 0.01,
                outline: true,
                outlineWidth: 2,
                outlineColor: "#ffff00",
                outlineOpacity: 0.5,
                clampToGround: false,
            }
        })


        const label = new mars3d.graphic.LabelPrimitive({
            position: item.positions[0],
            style: {
                text: `${item.positions[0][0].toFixed(4)},${item.positions[0][1].toFixed(4)}`
            }
        })
        graphicLayer.addGraphic(graphic)
        graphicLayer.addGraphic(label)
    }
}
/**
 * 释放当前地图业务的生命周期函数
 * @returns {void} 无
 */
export function onUnmounted() {

    if (tileLayer) {
        map.removeLayer(tileLayer)
    }
    graphicLayer.clear()
    console.log("地图打印被释放了")
}

// 查看场景出图
export function showMapImg(options = {}) {
    return map.expImage({ download: false, ...options }).then((result) => {
        return result.image
    })
}


const getDistance = (lat1, lon1, lat2, lon2) => {
    // 将经纬度转换为Cartesian3坐标
    const cartesian1 = Cesium.Cartesian3.fromDegrees(lon1, lat1)
    const cartesian2 = Cesium.Cartesian3.fromDegrees(lon2, lat2)
    // 计算距离
    const distance = Cesium.Cartesian3.distance(cartesian1, cartesian2)
    return distance
}


export function exportImg(base64Image) {
    // 获取 Canvas 元素
    // 创建 Canvas 元素
    const canvas = document.createElement('canvas');
    document.body.appendChild(canvas); // 将 Canvas 添加到页面中
    const ctx = canvas.getContext('2d');

    // 创建 Image 对象并加载 Base64 图像
    const img = new Image();
    img.src = base64Image;

    img.onload = function () {
        // 设置 canvas 尺寸为图像的宽高
        // 设置 canvas 尺寸，比图像的宽高更大，以便在外部绘制边框线
        const borderMargin = 50; // 边框线与图像之间的边距
        canvas.width = img.width + 2 * borderMargin;
        canvas.height = img.height + 2 * borderMargin; // 加上标题和图例的高度

        // 绘制底图
        ctx.drawImage(img, borderMargin, borderMargin, img.width, img.height); // 将底图绘制在Canvas的内部，留出标题和图例的空间

        // 绘制边框线
        ctx.strokeStyle = '#000000'; // 边框线颜色
        ctx.lineWidth = 2; // 边框线宽度
        ctx.strokeRect(0, 0, canvas.width, canvas.height, 2); // 绘制边框线

        // 添加标题
        ctx.font = '30px Arial';
        ctx.fillStyle = '#000000';
        ctx.textAlign = 'center';
        ctx.textBaseline = 'top'; // 设置文本基线为顶部对齐
        ctx.fillText('清徐地下水在线监测软件平台', canvas.width / 2, 15); // 位置可以调整

        // // 添加比例尺
        // const firstLegendLabel = document.querySelector('.legend-label');
        // ctx.font = '20px Arial';
        // ctx.fillText('比例尺：1/' + firstLegendLabel.textContent + "(1像素/实际长度)", canvas.width / 2, canvas.height - 40); // 位置可以调整

        const rectangleBounds = map.getExtent(); //获取屏幕范围
        let dis_DX = getDistance((rectangleBounds.ymin + rectangleBounds.ymax) / 2, rectangleBounds.xmin, (rectangleBounds.ymin + rectangleBounds.ymax) / 2, rectangleBounds.xmax)
        console.log("dis_DX", dis_DX);
        console.log("img", img.width)
        let x_scal = dis_DX / img.width; //每像素代表的实际距离
        let px_len = 200 / x_scal;
        console.log("px_len", px_len)

        // 添加比例尺
        const x = borderMargin; // 起始x坐标
        const y = canvas.height - borderMargin / 2; // 起始y坐标
        const lengths = [px_len, px_len, px_len]; // 比例尺的长度
        const texts = ['200m', '400m', '600m']; // 比例尺的文本
        addScaleBar(ctx, x, y, lengths, texts);

        const view = map.getCameraView();
        console.log("view", view)

        // 添加指北针
        const compassImage = new Image();
        compassImage.src = compassBase64;

        compassImage.onload = () => {

            const compassWidth = 80; // 指北针宽度
            const compassHeight = 80; // 指北针高度
            const compassX = canvas.width - compassWidth - 20 - borderMargin; // 指北针在右上角，距离右边和顶部20像素
            const compassY = 20 + borderMargin;
            const padding = 10; // 背景的内边距
            const bgWidth = compassWidth + padding * 2; // 背景宽度
            const bgHeight = compassHeight + padding * 2; // 背景高度
            const bgX = compassX - padding; // 背景左上角X坐标
            const bgY = compassY - padding; // 背景左上角Y坐标

            // 绘制带圆角的白色背景矩形
            ctx.fillStyle = '#ffffff';
            ctx.strokeStyle = '#000000'; // 可选：给背景添加黑色边框
            ctx.lineWidth = 2; // 可选：边框宽度
            drawRoundedRect(ctx, bgX, bgY, bgWidth, bgHeight, 10); // 圆角半径为10像素
            ctx.fill();
            ctx.stroke(); // 可选：绘制边框

            // 绘制指北针
            // ctx.drawImage(compassImage, compassX, compassY, compassWidth, compassHeight);

            const rotationAngle = view.heading; // 指北针旋转角度（单位：度）
            // 平移到指北针中心进行旋转
            const centerX = compassX + compassWidth / 2;
            const centerY = compassY + compassHeight / 2;
            ctx.translate(centerX, centerY);
            ctx.rotate((rotationAngle * Math.PI) / 180); // 将角度转换为弧度

            // 绘制指北针
            ctx.drawImage(
                compassImage,
                -compassWidth / 2, // 绘制时回到中心
                -compassHeight / 2,
                compassWidth,
                compassHeight
            );

            // 恢复状态
            ctx.restore();

            // 使用 jsPDF 将 Canvas 转换为 PDF
            const { jsPDF } = window.jspdf;
            const pdf = new jsPDF();

            // 获取 PDF 页面的宽度
            const pdfWidth = pdf.internal.pageSize.getWidth(); // 获取 PDF 页面的宽度

            // 计算图片的目标宽度：PDF 页面的宽度减去左右 10 像素的空白
            const margin = 10; // 左右边距
            const targetWidth = pdfWidth - 2 * margin;

            // 计算图片缩放比例
            const scaleFactor = targetWidth / canvas.width;

            // 根据缩放比例计算图片的高度
            const targetHeight = canvas.height * scaleFactor;

            // 将 Canvas 图像添加到 PDF
            pdf.addImage(canvas, 'PNG', margin, 10, targetWidth, targetHeight); // 参数：图像、格式、X坐标、Y坐标、宽度、高度
            // 下载 PDF
            pdf.save('场景出图_' + Date.now() + ".pdf");
        };

        // // 导出为新的 Base64 图片
        // const newBase64Image = canvas.toDataURL('image/png');
        // console.log('新的Base64图片：', newBase64Image);

        // // 将 Base64 数据转换为 Blob 对象
        // const byteString = atob(newBase64Image.split(',')[1]); // 解码Base64字符串
        // const arrayBuffer = new ArrayBuffer(byteString.length);
        // const uint8Array = new Uint8Array(arrayBuffer);

        // // 将解码后的数据填充到 Uint8Array 中
        // for (let i = 0; i < byteString.length; i++) {
        //     uint8Array[i] = byteString.charCodeAt(i);
        // }

        // // 创建 Blob 对象
        // const blob = new Blob([uint8Array], { type: 'image/png' });

        // // 创建 URL 对象
        // const imageUrl = URL.createObjectURL(blob);
        // console.log('Image URL:', imageUrl);

        // handleExport(imageUrl, "场景出图.png")
    }
}


// 绘制比例尺的函数
// 绘制比例尺的函数
function addScaleBar(ctx, x, y, lengths, texts) {
    ctx.save();
    ctx.strokeStyle = '#000000'; // 比例尺线段颜色
    ctx.lineWidth = 4; // 线段宽度
    ctx.font = '12px Arial';
    ctx.textAlign = 'center';
    ctx.textBaseline = 'middle';

    ctx.font = '20px Arial';
    ctx.fillStyle = '#000000'; // 文本颜色
    ctx.fillText("0", x, y - 10); // 比例尺文本

    // 绘制比例尺横线和竖线
    for (let i = 0; i < lengths.length; i++) {
        const length = lengths[i];
        const text = texts[i];
        ctx.beginPath();
        ctx.moveTo(x, y);
        ctx.lineTo(x, y + 15); // 左端竖线
        ctx.moveTo(x + length, y);
        ctx.lineTo(x + length, y + 15); // 右端竖线
        ctx.stroke();

        // 绘制比例尺横线
        ctx.beginPath();
        ctx.moveTo(x, y + 15);
        ctx.lineTo(x + length, y + 15);
        ctx.stroke();

        // 更新x坐标，为下一个比例尺做准备
        x += length + 0; // 增加10像素的间隔

        // 绘制比例尺文本
        ctx.font = '20px Arial';
        ctx.fillStyle = '#000000'; // 文本颜色
        ctx.fillText(text, x, y - 10); // 比例尺文本
    }
    ctx.restore();
}
// 绘制圆角矩形的函数
function drawRoundedRect(ctx, x, y, width, height, radius) {
    ctx.beginPath();
    ctx.moveTo(x + radius, y);
    ctx.lineTo(x + width - radius, y);
    ctx.arcTo(x + width, y, x + width, y + radius, radius);
    ctx.lineTo(x + width, y + height - radius);
    ctx.arcTo(x + width, y + height, x + width - radius, y + height, radius);
    ctx.lineTo(x + radius, y + height);
    ctx.arcTo(x, y + height, x, y + height - radius, radius);
    ctx.lineTo(x, y + radius);
    ctx.arcTo(x, y, x + radius, y, radius);
    ctx.closePath();
}

export function getScale() {
    let Cesium = mars3d.Cesium;
    let viewer = map.viewer;
    // 获取屏幕中心坐标
    const center = new Cesium.Cartesian2(
        viewer.canvas.clientWidth / 2,
        viewer.canvas.clientHeight / 2
    );

    // 获取屏幕中心点对应的地理位置
    const centerPosition = viewer.scene.camera.pickEllipsoid(center, viewer.scene.globe.ellipsoid);
    const centerCartographic = Cesium.Ellipsoid.WGS84.cartesianToCartographic(centerPosition);

    // 向右移动一定像素
    const offsetX = 100; // 100 像素偏移
    const right = new Cesium.Cartesian2(center.x + offsetX, center.y);
    const rightPosition = viewer.scene.camera.pickEllipsoid(right, viewer.scene.globe.ellipsoid);
    const rightCartographic = Cesium.Ellipsoid.WGS84.cartesianToCartographic(rightPosition);

    // 计算两点之间的地理距离（米）
    const geodesic = new Cesium.EllipsoidGeodesic(centerCartographic, rightCartographic);
    const distanceInMeters = geodesic.surfaceDistance;

    console.log("100 pixels represent:", distanceInMeters, "meters");
}

/** 导出按钮操作 */
function handleExport(url, filename) {
    //proxy.download(url, {}, `${name}_${new Date().getTime()}.xlsx`);

    // 创建一个临时的 <a> 元素
    const a = document.createElement("a");

    // 创建一个 URL 对象
    a.href = url;

    // 设置 <a> 元素的下载文件名
    a.download = filename;

    // 触发点击事件
    document.body.appendChild(a);
    a.click();

    // 移除临时的 <a> 元素
    document.body.removeChild(a);

    // 释放 URL 对象
    URL.revokeObjectURL(url);
}



