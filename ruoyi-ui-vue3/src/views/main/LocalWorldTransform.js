import * as GeoJsonUti from "./GeoJsonUti"


/**
 * geojson转为geojson
 * @param {*} geojson 
 * @returns geojson--arcgispro--
 */
export function geoJsonTransSelf(geojson) {
    //let geojsonBack = formatGeoJSON_point(JSON.parse(JSON.stringify(geojson)));//点类
    let geojsonBack = formatGeoJSON_line(JSON.parse(JSON.stringify(geojson)));//线类
    //let geojsonBack = formatGeoJSON_polygon(JSON.parse(JSON.stringify(geojson)));//面类
    
    let features = geojson.features;
    for (let i = 0; i < features.length; i++) {
        let feature = features[i];
        let key = feature.properties.OBJECTID;
        let coordinates = findCoordinates(key, geojsonBack);
        replaceNullWithEmptyString(feature.properties)
        feature.geometry.coordinates = coordinates;
    }

    return geojson;
}

/**
 * json转为geojson
 * @param {*} json 
 * @param {*} geojson 
 * @returns json--arcgis
 */
export function geoJsonTransJson(json, geojson) {
    let features = json.features;
    for (let i = 0; i < features.length; i++) {
        let feature = features[i];
        let key = feature.attributes.OBJECTID;
        let coordinates = findCoordinates(key, geojson);
        feature.geometry.rings = coordinates;
    }

    return json;
}

//点
function formatGeoJSON_point(geojson) {
    let features = geojson.features;
    for (let i = 0; i < features.length; i++) {
        let feature = features[i];
        let geometry = feature.geometry;
        let coordinates = geometry.coordinates.slice();
        geometry.coordinates = getPositionTransCoordinatesPoint(coordinates, geometry.type);
    }
    return geojson;
}

//线
function formatGeoJSON_line(geojson) {
    let features = geojson.features;
    for (let i = 0; i < features.length; i++) {
        let feature = features[i];
        let geometry = feature.geometry;
        let coordinates = geometry.coordinates.slice();
        geometry.coordinates = getPositionTransCoordinatesLine(coordinates, geometry.type);
    }
    return geojson;
}

//面
function formatGeoJSON_polygon(geojson) {
    let features = geojson.features;
    for (let i = 0; i < features.length; i++) {
        let feature = features[i];
        let geometry = feature.geometry;
        let coordinates = geometry.coordinates.slice();
        geometry.coordinates = getPositionTransCoordinatesPolygon(coordinates, geometry.type);
    }
    return geojson;
}


//去除对象中的null
function replaceNullWithEmptyString(obj) {
    // 检查输入是否为对象，且不是 null
    if (obj !== null && typeof obj === 'object') {
        for (const key in obj) {
            if (obj.hasOwnProperty(key)) {
                // 如果属性值是 null，替换为 ""
                if (obj[key] === null) {
                    obj[key] = "";
                } else if (typeof obj[key] === 'object') {
                    // 如果是对象，递归调用
                    replaceNullWithEmptyString(obj[key]);
                }
            }
        }
    }
    return obj; // 返回修改后的对象
}

function findCoordinates(key, geojson) {

    let features = geojson.features;
    let coordinates = null;
    for (let i = 0; i < features.length; i++) {
        let feature = features[i];
        if (feature.properties.OBJECTID === key) {
            coordinates = feature.geometry.coordinates;
            break;
        }
    }

    return coordinates;

}


//let originPoint = Cesium.Cartesian3.fromDegrees(119.64865914929707, 26.4809118262545);//可以
//let originPoint = Cesium.Cartesian3.fromDegrees(119.64857775674058, 26.479818267785543);
//let originPoint = Cesium.Cartesian3.fromDegrees(119.65017713387718, 26.480158538342);
//let originPoint = Cesium.Cartesian3.fromDegrees(119.64849433623597, 26.47946041166227);
//let originPoint = Cesium.Cartesian3.fromDegrees(119.6367555920446, 26.479619987878625);//高德影像
let originPoint = Cesium.Cartesian3.fromDegrees(119.63680685139437, 26.479694853684066);//必应影像

//点
export function getPositionTransCoordinatesPoint(coordinates, type) {
    if (type == "Point") {
        return GeoJsonUti.copy_Point(coordinates);
    }
    if (type == "MultiPoint") {
        return GeoJsonUti.copy_MultiPoint(coordinates);
    }
 
    console.error("该类型不支持转换——",type)
    return null;
}


//线
export function getPositionTransCoordinatesLine(coordinates, type) {
    if (type == "LineString") {
        return GeoJsonUti.copy_LineString(coordinates);
    }
    if (type == "MultiLineString") {
        return GeoJsonUti.copy_MultiLineString(coordinates);
    }
 
    console.error("该类型不支持转换——",type)
    return null;
}

//面
export function getPositionTransCoordinatesPolygon(coordinates, type) {
    if (type == "Polygon") {
        return GeoJsonUti.copy_Polygon(coordinates);
    }
    if (type == "MultiPolygon") {
        return GeoJsonUti.copy_MultiPolygon(coordinates);
    }
 
    console.error("该类型不支持转换——",type)
    return null;
}

export function getPositionTransCoordinatesPolygon_back(coordinates) {



    // let ori4 = getPositionTranslationCartographic(
    //     {
    //         x: 3.6,
    //         y: 4.05,
    //     }
    // );

    // // 获取经纬度
    // const longitude4 = ori4[0];
    // const latitude4 = ori4[1];
    // console.log(longitude4, "-----", latitude4);
    // return;

    let trans_coordinates = [];

    for (let i = 0; i < coordinates.length; i++) {
        let coordinate = coordinates[i];
        let trans_coordinate = [];
        for (let j = 0; j < coordinate.length; j++) {
            //let coord=getPositionTranslationCartographic({x:coordinate[j][0]-1060-90+6,y:coordinate[j][1]-484+151-14});
            //let coord = getPositionTranslationCartographic({ x: coordinate[j][0] - 1144, y: coordinate[j][1] - 347 });
            //let coord = getPositionTranslationCartographic({ x: coordinate[j][0], y: coordinate[j][1] + 3 + 2.5 + 3.6 });
            let coord = getPositionTranslationCartographic({ x: coordinate[j][0], y: coordinate[j][1] });
            trans_coordinate.push(coord);
        }
        trans_coordinates.push(trans_coordinate);
    }

    return trans_coordinates;
}

export function getPositionTranslationCartographic(offest) {
    let pointCartesian = getPositionTranslation(originPoint, offest, 9.9);
    const cartographic = Cesium.Cartographic.fromCartesian(pointCartesian);
    // 获取经纬度
    const longitude = Cesium.Math.toDegrees(cartographic.longitude);
    const latitude = Cesium.Math.toDegrees(cartographic.latitude);

    return [longitude, latitude];
}



export function getPositionTranslation(position, offest, degree, type, fixedFrameTransform) {
    fixedFrameTransform = fixedFrameTransform || Cesium.Transforms.eastNorthUpToFixedFrame;

    var rotate = Cesium.Math.toRadians(-Cesium.defaultValue(degree, 0)); //转成弧度

    type = (type || "z").toUpperCase();
    var _normal = Cesium.Cartesian3["UNIT_" + type];

    var quaternion = Cesium.Quaternion.fromAxisAngle(_normal, rotate); //quaternion为围绕这个z轴旋转d度的四元数
    var rotateMatrix3 = Cesium.Matrix3.fromQuaternion(quaternion); //rotateMatrix3为根据四元数求得的旋转矩阵

    var pointCartesian3 = new Cesium.Cartesian3(Cesium.defaultValue(offest.x, 0), Cesium.defaultValue(offest.y, 0), Cesium.defaultValue(offest.z, 0)); //point的局部坐标
    var rotateTranslationMatrix4 = Cesium.Matrix4.fromRotationTranslation(rotateMatrix3, Cesium.Cartesian3.ZERO); //rotateTranslationMatrix4为旋转加平移的4x4变换矩阵，这里平移为(0,0,0)，故填个Cesium.Cartesian3.ZERO
    Cesium.Matrix4.multiplyByTranslation(rotateTranslationMatrix4, pointCartesian3, rotateTranslationMatrix4); //rotateTranslationMatrix4 = rotateTranslationMatrix4  X  pointCartesian3
    var originPositionCartesian3 = Cesium.Ellipsoid.WGS84.cartographicToCartesian(Cesium.Cartographic.fromCartesian(position)); //得到局部坐标原点的全局坐标
    var originPositionTransform = fixedFrameTransform(originPositionCartesian3); //m1为局部坐标的z轴垂直于地表，局部坐标的y轴指向正北的4x4变换矩阵
    Cesium.Matrix4.multiplyTransformation(originPositionTransform, rotateTranslationMatrix4, rotateTranslationMatrix4); //rotateTranslationMatrix4 = rotateTranslationMatrix4 X originPositionTransform
    var pointCartesian = new Cesium.Cartesian3();
    Cesium.Matrix4.getTranslation(rotateTranslationMatrix4, pointCartesian); //根据最终变换矩阵m得到p2
    return pointCartesian;
}


export function getPositionTranslationOffest(pointCartesian) {

    return getOffest(originPoint, pointCartesian, 9.9);
}

export function getOffest(position, pointCartesian, degree, fixedFrameTransform) {
    fixedFrameTransform = fixedFrameTransform || Cesium.Transforms.eastNorthUpToFixedFrame;

    // 1. 获取局部坐标系转换矩阵
    var originPositionCartesian3 = Cesium.Ellipsoid.WGS84.cartographicToCartesian(Cesium.Cartographic.fromCartesian(position));
    var originPositionTransform = fixedFrameTransform(originPositionCartesian3);

    // 2. 计算旋转矩阵并获取其逆矩阵
    var rotate = Cesium.Math.toRadians(-degree);
    var quaternion = Cesium.Quaternion.fromAxisAngle(Cesium.Cartesian3.UNIT_Z, rotate); // 以 Z 轴为例
    var rotateMatrix3 = Cesium.Matrix3.fromQuaternion(quaternion);
    var inverseRotateMatrix3 = Cesium.Matrix3.transpose(rotateMatrix3, new Cesium.Matrix3());

    // 3. 将 pointCartesian 转换回局部坐标系
    var inverseOriginPositionTransform = Cesium.Matrix4.inverseTransformation(originPositionTransform, new Cesium.Matrix4());
    var pointCartesianInLocal = new Cesium.Cartesian3();
    Cesium.Matrix4.multiplyByPoint(inverseOriginPositionTransform, pointCartesian, pointCartesianInLocal);

    // 4. 逆旋转得到位移 offest
    var offest = new Cesium.Cartesian3();
    Cesium.Matrix3.multiplyByVector(inverseRotateMatrix3, pointCartesianInLocal, offest);

    return offest;
}
