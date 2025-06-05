
import * as mars3d from "mars3d";

export const loadCadLayer = async (mapRef:any) => {

    // const PolygonUrl = "geojson/fanweixian.geojson";
    // const Polygonlayer = new mars3d.layer.GeoJsonLayer({
    //     url: PolygonUrl, name: "cadlayer", symbol: {
    //         styleOptions: {
    //             fill: true,
    //             randomColor: true, // 随机色
    //             opacity: 0,
    //             outline: true,
    //             outlineStyle: {
    //                 color: "#f34142",
    //                 width: 3,
    //                 opacity: 1,
    //                 clampToGround: true
    //             },
    //         },
    //     },
    // });
    // mapRef.addLayer(Polygonlayer);
    // console.log("🚀 ~ loadCadLayer ~ Polygonlayer:", Polygonlayer)
};





export const removeCadLayer = (mapRef:any) => {
    let layer = mapRef.value.getLayers().find((item:any) => item.name === "cadlayer")
    mapRef.removeLayer(layer)
}