


//生产上水

export const FilterDict = {
    J3: " smscode != '生活区建、' and smscode != '外单位建、'",
    J3: " smscode = '生活区建、' ",
    J4: " smscode = '外单位建、' ",
    SSGX_X_SSG: " classify = '上水管(S)' ",
    SSGX_X_SCSS: " classify = '生产上水' ",
    SSGX_X_SCSS_S1: " classify = '生产上水(S1)' ",
    SSGX_X_SHSS: " classify = '生活上水' ",
    SSGX_X_SHSS_S2: " classify = '生活上水(S2)' ",
    SSGX_X_XFSS: " classify = '消防上水' ",
    "MP-XH": " classify = '循环水' ",
    SSGX_X_XHSG_XH: " classify = '循环水管(XH)' ",

    XSGX_X_WSG: " classify = '污水管' ",
    XSGX_X_X: " classify = '下水管(X)' ",
    XSGX_X_YS: " classify = '雨水' ",

    TR: " classify = '天然气' ",
    RQGX_X_MQG: " classify = '煤气管(M)' ",
    M1: " classify = '高炉煤气(M1)' ",
    M2: " classify = '焦炉煤气(M2)' ",
    M3: " classify = '混合煤气(M3)' ",
    M4: " classify = '转炉煤气(M4)' ",

    RLGX_X_RLG: " classify = '热力管(R)' ",
    RLGX_X_Z: " classify = '蒸汽管(Z)' ",

    CC: " classify = '除尘管(CC)' ",
    DQ: " classify = '氮气管(DQ)' ",
    GYGX_X_FQS: " classify = '汾清水' ",
    GYGX_X_G: " classify = '工业管' ",
    GF: " classify = '鼓风管(GF)' ",
    GYGX_X_HGG: " classify = '化工管(H)' ",
    GYGX_X_JK: " classify = '架空的工业管道' ",
    GYGX_X_JYG: " classify = '碱液管(JY)' ",
    YS: " classify = '压缩空气管(YS)' ",
    YA: " classify = '氩气管(YA)' ",
    YQ: " classify = '氧气管(YQ)' ",
    GYGX_X_GG: " classify = '综合管沟(廊)中心线' ",
}


//"ne:GYGX_X"

export const FiledTableDict = {

    "ne:GYGX_X": [
        { id: 1, name: '管线名称', field: 'GXMC', alias: 'smscode', type: 'Char', length: 18 },
        { id: 2, name: '管线类型', field: 'GXLX', alias: 'pipelinety', type: 'Char', length: 18 },
        { id: 3, name: '代号', field: 'DH', alias: 'snodeid', type: 'Char', length: 8 },
        { id: 4, name: '压力', field: 'YL', alias: 'pressure', type: 'Char', length: 60 },
        { id: 5, name: '管线分级', field: 'GXFJ', alias: '管线分级', type: 'Char', length: 60 },
        { id: 6, name: '管线材质', field: 'GXCZ', alias: 'matter', type: 'Char', length: 60 },
        { id: 7, name: '公称直径', field: 'ZJ', alias: 'standard', type: 'INT', length: 10 },
        { id: 8, name: '壁厚', field: 'BH', alias: '壁厚', type: 'Float', length: 15, precision: 2 },
        { id: 9, name: '外径', field: 'WJ', alias: '外径', type: 'Float', length: 15, precision: 2 },
        { id: 10, name: '备注', field: 'BZ', alias: '备注', type: 'Char', length: 255 }
    ],
    "ne:DLGX_X_JSONToFeatures": [
        { id: 1, name: '管线名称', field: 'GXMC', type: 'Char', length: 18, alias: 'pipeName' },
        { id: 2, name: '管线类型', field: 'GXLX', type: 'Char', length: 18, alias: 'pipeType' },
        { id: 3, name: '代号', field: 'DH', type: 'Char', length: 8, alias: 'code' },
        { id: 4, name: '电压', field: 'DY', type: 'Char', length: 60, alias: 'voltage' },
        { id: 5, name: '电力类型', field: 'DLLX', type: 'Char', length: 60, alias: 'powerType' },
        { id: 6, name: '管线材质', field: 'GXCZ', type: 'Char', length: 60, alias: 'pipeMaterial' },
        { id: 7, name: '公称直径', field: 'ZJ', type: 'INT', length: 10, alias: 'nominalDiameter' },
        { id: 8, name: '壁厚', field: 'BH', type: 'Float', length: 15, precision: 2, alias: 'wallThickness' },
        { id: 9, name: '外径', field: 'WJ', type: 'Float', length: 15, precision: 2, alias: 'outerDiameter' },
        { id: 10, name: '备注', field: 'BZ', type: 'Char', length: 255, alias: 'remarks' }
    ],
    "ne:RLGX_D_JSONToFeatures":[
        { id: 1, name: '管线名称', field: 'GXMC', type: 'Char', length: 18, alias: 'gxmc' },
        { id: 2, name: '管线类型', field: 'GXLX', type: 'Char', length: 18, alias: 'gxlx' },
        { id: 3, name: '代号', field: 'DH', type: 'Char', length: 8, alias: 'dh' },
        { id: 4, name: '压力', field: 'YL', type: 'Char', length: 60, alias: 'yl' },
        { id: 5, name: '管线分级', field: 'GXFJ', type: 'Char', length: 60, alias: 'gxfj' },
        { id: 6, name: '管线材质', field: 'GXCZ', type: 'Char', length: 60, alias: 'gxcx' },
        { id: 7, name: '公称直径', field: 'ZJ', type: 'INT', length: 10, alias: 'zj' },
        { id: 8, name: '壁厚', field: 'BH', type: 'Float', length: 15, precision: 2, alias: 'bh' },
        { id: 9, name: '外径', field: 'WJ', type: 'Float', length: 15, precision: 2, alias: 'wj' },
        { id: 10, name: '备注', field: 'BZ', type: 'Char', length: 255, alias: 'bz' }
      ],
      "ne:RQGX_X_JSONToFeatures":[
        { id: 1, name: '管线名称', field: 'GXMC', type: 'Char', length: 18, alias: 'pipeName' },
        { id: 2, name: '管线类型', field: 'GXLX', type: 'Char', length: 18, alias: 'pipeType' },
        { id: 3, name: '代号', field: 'DH', type: 'Char', length: 8, alias: 'code' },
        { id: 4, name: '压力', field: 'YL', type: 'Char', length: 60, alias: 'pressure' },
        { id: 5, name: '管线分级', field: 'GXFJ', type: 'Char', length: 60, alias: 'pipeGrade' },
        { id: 6, name: '管线材质', field: 'GXCZ', type: 'Char', length: 60, alias: 'pipeMaterial' },
        { id: 7, name: '公称直径', field: 'ZJ', type: 'INT', length: 10, alias: 'nominalDiameter' },
        { id: 8, name: '壁厚', field: 'BH', type: 'Float', length: 15, precision: 2, alias: 'wallThickness' },
        { id: 9, name: '外径', field: 'WJ', type: 'Float', length: 15, precision: 2, alias: 'outerDiameter' },
        { id: 10, name: '备注', field: 'BZ', type: 'Char', length: 255, alias: 'remarks' }
      ],
      "ne:XSGX_X_JSONToFeatures":[
        { id: 1, name: '管线名称', field: 'GXMC', type: 'Char', length: 18, alias: 'gxmc' },
        { id: 2, name: '管线类型', field: 'GXLX', type: 'Char', length: 18, alias: 'gxlx' },
        { id: 3, name: '代号', field: 'DH', type: 'Char', length: 8, alias: 'dh' },
        { id: 4, name: '管线材质', field: 'GXCZ', type: 'Char', length: 60, alias: 'gxcx' },
        { id: 5, name: '公称直径', field: 'ZJ', type: 'INT', length: 10, alias: 'zj' },
        { id: 6, name: '壁厚', field: 'BH', type: 'Float', length: 15, precision: 2, alias: 'bh' },
        { id: 7, name: '外径', field: 'WJ', type: 'Float', length: 15, precision: 2, alias: 'wj' },
        { id: 8, name: '备注', field: 'BZ', type: 'Char', length: 255, alias: 'bz' }
      ],
      "ne:SSGX_X_JSONToFeatures":[
        { id: 1, name: '管线名称', field: 'GXMC', type: 'Char', length: 18, alias: 'pipeName' },
        { id: 2, name: '管线类型', field: 'GXLX', type: 'Char', length: 18, alias: 'pipeType' },
        { id: 3, name: '代号', field: 'DH', type: 'Char', length: 8, alias: 'designation' },
        { id: 4, name: '管线材质', field: 'GXCZ', type: 'Char', length: 60, alias: 'pipeMaterial' },
        { id: 5, name: '公称直径', field: 'ZJ', type: 'INT', length: 10, alias: 'nominalDiameter' },
        { id: 6, name: '壁厚', field: 'BH', type: 'Float', length: 15, precision: 2, alias: 'wallThickness' },
        { id: 7, name: '外径', field: 'WJ', type: 'Float', length: 15, precision: 2, alias: 'outerDiameter' },
        { id: 8, name: '备注', field: 'BZ', type: 'Char', length: 255, alias: 'remarks' }
      ],
      "ne:TXGX_X_JSONToFeatures":[
        { id: 1, name: '管线名称', field: 'GXMC', type: 'Char', length: 18, alias: 'pipeName' },
        { id: 2, name: '管线类型', field: 'GXLX', type: 'Char', length: 18, alias: 'pipeType' },
        { id: 3, name: '代号', field: 'DH', type: 'Char', length: 8, alias: 'code' },
        { id: 4, name: '管孔数量', field: 'GKSL', type: 'INT', length: 60, alias: 'holeCount' },
        { id: 5, name: '管线材质', field: 'GXCZ', type: 'Char', length: 60, alias: 'pipeMaterial' },
        { id: 6, name: '公称直径', field: 'ZJ', type: 'INT', length: 10, alias: 'nominalDiameter' },
        { id: 7, name: '备注', field: 'BZ', type: 'Char', length: 255, alias: 'remarks' }
      ],
      "ne:DM_X_JSONToFeatures":[
        { id: 1, name: '地貌名称', field: 'DMMC', type: 'Char', length: 18, alias: 'landformName' },
        { id: 2, name: '地貌类型', field: 'DMLX', type: 'Char', length: 18, alias: 'landformType' },
        { id: 3, name: '面积', field: 'MJ', type: 'Float', length: 15, precision: 2, alias: 'area' },
        { id: 4, name: '备注', field: 'BZ', type: 'Char', length: 255, alias: 'remarks' }
      ],
      "ne:QYmap_JSONToFeatures":[
        { id: 1, name: '境界名称', field: 'JJMC', type: 'Char', length: 18, alias: 'boundaryName' },
        { id: 2, name: '境界类型', field: 'JJLX', type: 'Char', length: 18, alias: 'boundaryType' },
        { id: 3, name: '面积', field: 'MJ', type: 'Float', length: 15, precision: 2, alias: 'area' },
        { id: 4, name: '备注', field: 'BZ', type: 'Char', length: 255, alias: 'remarks' }
      ],
      "ne:SX_X_JSONToFeatures":[
        { id: 1, name: '水系名称', field: 'SXMC', type: 'Char', length: 18, alias: 'waterSystemName' },
        { id: 2, name: '水系类型', field: 'SXLX', type: 'Char', length: 18, alias: 'waterSystemType' },
        { id: 3, name: '面积', field: 'MJ', type: 'Float', length: 15, precision: 2, alias: 'area' },
        { id: 4, name: '备注', field: 'BZ', type: 'Char', length: 255, alias: 'remarks' }
      ],
      "ne:JTDL":[
        { id: 1, name: '道路名称', field: 'DLMC', type: 'Char', length: 18, alias: 'roadName' },
        { id: 2, name: '宽度', field: 'DLKD', type: 'Float', length: 8, precision: 2, alias: 'roadWidth' },
        { id: 3, name: '长度', field: 'DLCD', type: 'Float', length: 8, precision: 2, alias: 'roadLength' },
        { id: 4, name: '备注', field: 'BZ', type: 'Char', length: 255, alias: 'remarks' }
      ],
      "ne:JTTL":[
        { id: 1, name: '名称', field: 'MC', type: 'Char', length: 10, alias: 'name' },
        { id: 2, name: '长度', field: 'DD', type: 'Float', length: 8, precision: null, alias: 'length' },
        { id: 3, name: '备注', field: 'BZ', type: 'Char', length: 255, alias: 'remarks' }
      ],
      "ne:GZW_M_JSONToFeatures2":[
        { id: 1, name: '构筑物编号', field: 'BH', type: 'Char', length: 18, alias: 'structureCode' },
        { id: 2, name: '名称', field: 'MC', type: 'Char', length: 10, alias: 'name' },
        { id: 3, name: '起始层', field: 'QSC', type: 'INT', length: 8, alias: 'startFloor' },
        { id: 4, name: '中止层', field: 'ZZC', type: 'INT', length: 8, alias: 'endFloor' },
        { id: 5, name: '层数', field: 'CS', type: 'INT', length: 8, alias: 'floorCount' },
        { id: 6, name: '总高度', field: 'ZGD', type: 'Float', length: 10, precision: 2, alias: 'totalHeight' },
        { id: 7, name: '结构', field: 'JG', type: 'Char', length: 8, alias: 'structureType' },
        { id: 8, name: '占地面积', field: 'ZDMJ', type: 'Float', length: 15, precision: 2, alias: 'landArea' },
        { id: 9, name: '建筑面积', field: 'JZBJ', type: 'Float', length: 15, precision: 2, alias: 'buildingArea' },
        { id: 10, name: '备注', field: 'BZ', type: 'Char', length: 255, alias: 'remarks' }
      ],
      "ne:t_jzw_m_84_gbk2312":[
        { id: 1, name: '建筑编号', field: 'BH', type: 'Char', length: 18, alias: 'buildingCode' },
        { id: 2, name: '名称', field: 'MC', type: 'Char', length: 10, alias: 'name' },
        { id: 3, name: '性质', field: 'XZ', type: 'Char', length: 12, alias: 'nature' },
        { id: 4, name: '火灾危险性', field: 'HZWXX', type: 'Char', length: 12, alias: 'fireRisk' },
        { id: 5, name: '耐火等级', field: 'NHDJ', type: 'Char', length: 12, alias: 'fireResistance' },
        { id: 6, name: '起始层', field: 'QSC', type: 'INT', length: 8, alias: 'startFloor' },
        { id: 7, name: '中止层', field: 'ZZC', type: 'INT', length: 8, alias: 'endFloor' },
        { id: 8, name: '层数', field: 'CS', type: 'INT', length: 8, alias: 'floorCount' },
        { id: 9, name: '总高度', field: 'ZGD', type: 'Float', length: 10, precision: 2, alias: 'totalHeight' },
        { id: 10, name: '建设年代', field: 'JSND', type: 'INT', length: 8, alias: 'constructionYear' },
        { id: 11, name: '占地面积', field: 'ZDMJ', type: 'Float', length: 15, precision: 2, alias: 'siteArea' },
        { id: 12, name: '面积系数', field: 'MJSX', type: 'Float', length: 10, precision: 2, alias: 'areaCoefficient' },
        { id: 13, name: '建筑面积', field: 'JZBJ', type: 'Float', length: 15, precision: 2, alias: 'buildingArea' },
        { id: 14, name: '备注', field: 'BZ', type: 'Char', length: 255, alias: 'remarks' }
      ]


}



