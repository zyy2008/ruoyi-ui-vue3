
import { getPositionTranslationCartographic } from "./LocalWorldTransform"


/**************************点开始*********************************/
export function copy_Point(coordinate) {
    let coord = getPositionTranslationCartographic({ x: coordinate[0], y: coordinate[1] });
    return coord;
}

export function copy_MultiPoint(coordinates) {
    let trans_coordinates = [];
    for (let i = 0; i < coordinates.length; i++) {
        let coordinate = coordinates[i];
        let coord = getPositionTranslationCartographic({ x: coordinate[0], y: coordinate[1] });
        trans_coordinates.push(coord);
    }
    return trans_coordinates;
}


/**************************线开始*********************************/
export function copy_LineString(coordinates) {

    let trans_coordinates = [];
    for (let i = 0; i < coordinates.length; i++) {
        let coordinate = coordinates[i];
        let coord = getPositionTranslationCartographic({ x: coordinate[0], y: coordinate[1] });
        trans_coordinates.push(coord);
    }

    return trans_coordinates;

}

export function copy_MultiLineString(coordinates) {

    let trans_coordinates = [];
    for (let i = 0; i < coordinates.length; i++) {
        let coordinate = coordinates[i];
        let trans_coordinate = [];
        for (let j = 0; j < coordinate.length; j++) {
            let coord = getPositionTranslationCartographic({ x: coordinate[j][0], y: coordinate[j][1] });
            trans_coordinate.push(coord);
        }
        trans_coordinates.push(trans_coordinate);
    }
    return trans_coordinates;
}


/**************************线结束*********************************/

/**************************面开始*********************************/
export function copy_Polygon(coordinates) {
    let trans_coordinates = [];
    for (let i = 0; i < coordinates.length; i++) {
        let coordinate = coordinates[i];
        let trans_coordinate = [];
        for (let j = 0; j < coordinate.length; j++) {
            let coord = getPositionTranslationCartographic({ x: coordinate[j][0], y: coordinate[j][1] });
            trans_coordinate.push(coord);
        }
        trans_coordinates.push(trans_coordinate);
    }
    return trans_coordinates;
}


export function copy_MultiPolygon(coordinates) {
    let trans_coordinates = [];
    for (let i = 0; i < coordinates.length; i++) {
        let coordinate = coordinates[i];
        let outCoords=[];
        for (let j = 0; j < coordinate.length; j++) {
            let innerCoords = coordinate[j]
            let trans_coordinate = [];
            for (let k = 0; k < innerCoords.length; k++) {
                let coord = getPositionTranslationCartographic({ x: innerCoords[k][0], y: innerCoords[k][1] });
                trans_coordinate.push(coord);
            }
            outCoords.push(innerCoords)
        }
        trans_coordinates.push(outCoords);
    }
    return trans_coordinates;
}

/**************************面结束*********************************/