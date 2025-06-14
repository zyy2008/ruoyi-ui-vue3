<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="点位编号" prop="pointId">
        <el-input v-model="queryParams.pointId" placeholder="请输入点位编号" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="采样时间" prop="sampleTime">
        <el-input v-model="queryParams.sampleTime" placeholder="请选择采样时间" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd"
          v-hasPermi="['admin:monitoring:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['admin:monitoring:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['admin:monitoring:export']">导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="monitoringList" @selection-change="handleSelectionChange" style="width: 100%">
      <el-table-column type="selection" width="45" align="center" />
      <el-table-column label="点位编号" align="center" prop="pointId" min-width="130" show-overflow-tooltip />
      <el-table-column label="采样时间" align="center" prop="sampleTime" min-width="130" show-overflow-tooltip>
        <template #default="scope">
          <span>{{ scope.row.sampleTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="色度(度)" align="center" prop="color" min-width="110" show-overflow-tooltip />
      <el-table-column label="浊度(NTU)" align="center" prop="turbidity" min-width="110" show-overflow-tooltip />
      <el-table-column label="pH" align="center" prop="ph" min-width="90" show-overflow-tooltip />
      <el-table-column label="总硬度(mg/L)" align="center" prop="totalHardness" min-width="130" show-overflow-tooltip />
      <el-table-column label="溶解性总固体(mg/L)" align="center" prop="totalDissolvedSolids" min-width="150"
        show-overflow-tooltip />
      <el-table-column label="硫酸盐(mg/L)" align="center" prop="sulfate" min-width="130" show-overflow-tooltip />
      <el-table-column label="氯化物(mg/L)" align="center" prop="chloride" min-width="130" show-overflow-tooltip />
      <el-table-column label="铁(mg/L)" align="center" prop="iron" min-width="110" show-overflow-tooltip />
      <el-table-column label="锰(mg/L)" align="center" prop="manganese" min-width="110" show-overflow-tooltip />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" min-width="240">
        <template #default="scope">
          <el-button link type="primary" icon="View" @click="handleView(scope.row)"
            v-hasPermi="['admin:monitoring:query']">查看</el-button>
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['admin:monitoring:edit']">修改</el-button>
          <el-button link type="danger" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['admin:monitoring:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改水质监测信息对话框 -->
    <el-dialog :title="title" v-model="open" width="98vw" append-to-body>
      <el-form ref="monitoringRef" :model="form" :rules="rules">
        <el-descriptions :column="6" border>
          <el-descriptions-item label="点位编号">
            <el-form-item prop="pointId">
              <el-input v-model="form.pointId" placeholder="请输入点位编号" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="采样时间">
            <el-form-item prop="sampleTime">
              <el-input v-model="form.sampleTime" placeholder="请输入采样时间" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="色度(度)">
            <el-form-item prop="color">
              <el-input-number v-model="form.color" :precision="2" :step="0.1" placeholder="请输入色度" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="臭和味">
            <el-form-item prop="odorAndTaste">
              <el-input v-model="form.odorAndTaste" placeholder="请输入臭和味" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="浊度(NTU)">
            <el-form-item prop="turbidity">
              <el-input-number v-model="form.turbidity" :precision="2" :step="0.1" placeholder="请输入浊度" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="肉眼可见物">
            <el-form-item prop="visibleMatters">
              <el-input v-model="form.visibleMatters" placeholder="请输入肉眼可见物" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="pH">
            <el-form-item prop="ph">
              <el-input-number v-model="form.ph" :precision="2" :step="0.1" placeholder="请输入pH值" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="总硬度(mg/L)">
            <el-form-item prop="totalHardness">
              <el-input-number v-model="form.totalHardness" :precision="2" :step="0.1" placeholder="请输入总硬度" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="溶解性总固体(mg/L)">
            <el-form-item prop="totalDissolvedSolids">
              <el-input-number v-model="form.totalDissolvedSolids" :precision="2" :step="0.1" placeholder="请输入溶解性总固体" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="硫酸盐(mg/L)">
            <el-form-item prop="sulfate">
              <el-input-number v-model="form.sulfate" :precision="2" :step="0.1" placeholder="请输入硫酸盐" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="氯化物(mg/L)">
            <el-form-item prop="chloride">
              <el-input-number v-model="form.chloride" :precision="2" :step="0.1" placeholder="请输入氯化物" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="铁(mg/L)">
            <el-form-item prop="iron">
              <el-input-number v-model="form.iron" :precision="2" :step="0.1" placeholder="请输入铁含量" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="锰(mg/L)">
            <el-form-item prop="manganese">
              <el-input-number v-model="form.manganese" :precision="2" :step="0.1" placeholder="请输入锰含量" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="铝(mg/L)">
            <el-form-item prop="aluminum">
              <el-input-number v-model="form.aluminum" :precision="2" :step="0.1" placeholder="请输入铝含量" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="阴离子表面活性剂(mg/L)">
            <el-form-item prop="anionicSurfactant">
              <el-input-number v-model="form.anionicSurfactant" :precision="2" :step="0.1" placeholder="请输入阴离子表面活性剂" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="耗氧量(mg/L)">
            <el-form-item prop="oxygenConsumption">
              <el-input-number v-model="form.oxygenConsumption" :precision="2" :step="0.1" placeholder="请输入耗氧量" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="硫化物(mg/L)">
            <el-form-item prop="sulfide">
              <el-input-number v-model="form.sulfide" :precision="2" :step="0.1" placeholder="请输入硫化物" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="钠(mg/L)">
            <el-form-item prop="sodium">
              <el-input-number v-model="form.sodium" :precision="2" :step="0.1" placeholder="请输入钠含量" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="亚硝酸盐氮(mg/L)">
            <el-form-item prop="nitriteNitrogen">
              <el-input-number v-model="form.nitriteNitrogen" :precision="2" :step="0.1" placeholder="请输入亚硝酸盐氮" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="硝酸盐氮(mg/L)">
            <el-form-item prop="nitrateNitrogen">
              <el-input-number v-model="form.nitrateNitrogen" :precision="2" :step="0.1" placeholder="请输入硝酸盐氮" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="碘化物(mg/L)">
            <el-form-item prop="iodide">
              <el-input-number v-model="form.iodide" :precision="2" :step="0.1" placeholder="请输入碘化物" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="硒(mg/L)">
            <el-form-item prop="selenium">
              <el-input-number v-model="form.selenium" :precision="2" :step="0.1" placeholder="请输入硒含量" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="铜(mg/L)">
            <el-form-item prop="copper">
              <el-input-number v-model="form.copper" :precision="2" :step="0.1" placeholder="请输入铜含量" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="锌(mg/L)">
            <el-form-item prop="zinc">
              <el-input-number v-model="form.zinc" :precision="2" :step="0.1" placeholder="请输入锌含量" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="挥发酚(mg/L)">
            <el-form-item prop="volatilePhenol">
              <el-input-number v-model="form.volatilePhenol" :precision="2" :step="0.1" placeholder="请输入挥发酚" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="氨氮(mg/L)">
            <el-form-item prop="ammoniaNitrogen">
              <el-input-number v-model="form.ammoniaNitrogen" :precision="2" :step="0.1" placeholder="请输入氨氮" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="氰化物(mg/L)">
            <el-form-item prop="cyanide">
              <el-input-number v-model="form.cyanide" :precision="2" :step="0.1" placeholder="请输入氰化物" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="氟化物(mg/L)">
            <el-form-item prop="fluoride">
              <el-input-number v-model="form.fluoride" :precision="2" :step="0.1" placeholder="请输入氟化物" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="汞(mg/L)">
            <el-form-item prop="mercury">
              <el-input-number v-model="form.mercury" :precision="2" :step="0.1" placeholder="请输入汞含量" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="砷(mg/L)">
            <el-form-item prop="arsenic">
              <el-input-number v-model="form.arsenic" :precision="2" :step="0.1" placeholder="请输入砷含量" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="镉(mg/L)">
            <el-form-item prop="cadmium">
              <el-input-number v-model="form.cadmium" :precision="2" :step="0.1" placeholder="请输入镉含量" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="六价铬(mg/L)">
            <el-form-item prop="chromiumVi">
              <el-input-number v-model="form.chromiumVi" :precision="2" :step="0.1" placeholder="请输入六价铬" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="铅(mg/L)">
            <el-form-item prop="lead">
              <el-input-number v-model="form.lead" :precision="2" :step="0.1" placeholder="请输入铅含量" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="氯仿(μg/L)">
            <el-form-item prop="chloroform">
              <el-input-number v-model="form.chloroform" :precision="2" :step="0.1" placeholder="请输入氯仿" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="四氯化碳(μg/L)">
            <el-form-item prop="carbonTetrachloride">
              <el-input-number v-model="form.carbonTetrachloride" :precision="2" :step="0.1" placeholder="请输入四氯化碳" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="苯(μg/L)">
            <el-form-item prop="benzene">
              <el-input-number v-model="form.benzene" :precision="2" :step="0.1" placeholder="请输入苯含量" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="甲苯(μg/L)">
            <el-form-item prop="toluene">
              <el-input-number v-model="form.toluene" :precision="2" :step="0.1" placeholder="请输入甲苯含量" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="钼(mg/L)">
            <el-form-item prop="molybdenum">
              <el-input-number v-model="form.molybdenum" :precision="2" :step="0.1" placeholder="请输入钼含量" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="钒(mg/L)">
            <el-form-item prop="vanadium">
              <el-input-number v-model="form.vanadium" :precision="2" :step="0.1" placeholder="请输入钒含量" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="钴(mg/L)">
            <el-form-item prop="cobalt">
              <el-input-number v-model="form.cobalt" :precision="2" :step="0.1" placeholder="请输入钴含量" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="镍(mg/L)">
            <el-form-item prop="nickel">
              <el-input-number v-model="form.nickel" :precision="2" :step="0.1" placeholder="请输入镍含量" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="二氯甲烷(μg/L)">
            <el-form-item prop="dichloromethane">
              <el-input-number v-model="form.dichloromethane" :precision="2" :step="0.1" placeholder="请输入二氯甲烷" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="1,2-二氯乙烷(μg/L)">
            <el-form-item prop="dichloroethane12">
              <el-input-number v-model="form.dichloroethane12" :precision="2" :step="0.1" placeholder="请输入1,2-二氯乙烷" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="1,1,1-三氯乙烷(μg/L)">
            <el-form-item prop="trichloroethane111">
              <el-input-number v-model="form.trichloroethane111" :precision="2" :step="0.1"
                placeholder="请输入1,1,1-三氯乙烷" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="1,1,2-三氯乙烷(μg/L)">
            <el-form-item prop="trichloroethane112">
              <el-input-number v-model="form.trichloroethane112" :precision="2" :step="0.1"
                placeholder="请输入1,1,2-三氯乙烷" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="1,2-二氯丙烷(μg/L)">
            <el-form-item prop="dichloropropane12">
              <el-input-number v-model="form.dichloropropane12" :precision="2" :step="0.1" placeholder="请输入1,2-二氯丙烷" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="氯乙烯(μg/L)">
            <el-form-item prop="vinylChloride">
              <el-input-number v-model="form.vinylChloride" :precision="2" :step="0.1" placeholder="请输入氯乙烯" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="1,1-二氯乙烯(μg/L)">
            <el-form-item prop="dichloroethylene11">
              <el-input-number v-model="form.dichloroethylene11" :precision="2" :step="0.1" placeholder="请输入1,1-二氯乙烯" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="反式-1,2-二氯乙烯(μg/L)">
            <el-form-item prop="transDichloroethylene12">
              <el-input-number v-model="form.transDichloroethylene12" :precision="2" :step="0.1"
                placeholder="请输入反式-1,2-二氯乙烯" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="顺式-1,2-二氯乙烯(μg/L)">
            <el-form-item prop="cisDichloroethylene12">
              <el-input-number v-model="form.cisDichloroethylene12" :precision="2" :step="0.1"
                placeholder="请输入顺式-1,2-二氯乙烯" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="三氯乙烯(μg/L)">
            <el-form-item prop="trichloroethylene">
              <el-input-number v-model="form.trichloroethylene" :precision="2" :step="0.1" placeholder="请输入三氯乙烯" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="四氯乙烯(μg/L)">
            <el-form-item prop="tetrachloroethylene">
              <el-input-number v-model="form.tetrachloroethylene" :precision="2" :step="0.1" placeholder="请输入四氯乙烯" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="氯苯(μg/L)">
            <el-form-item prop="chlorobenzene">
              <el-input-number v-model="form.chlorobenzene" :precision="2" :step="0.1" placeholder="请输入氯苯" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="1,2-二氯苯(μg/L)">
            <el-form-item prop="dichlorobenzene12">
              <el-input-number v-model="form.dichlorobenzene12" :precision="2" :step="0.1" placeholder="请输入1,2-二氯苯" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="1,4-二氯苯(μg/L)">
            <el-form-item prop="dichlorobenzene14">
              <el-input-number v-model="form.dichlorobenzene14" :precision="2" :step="0.1" placeholder="请输入1,4-二氯苯" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="乙苯(μg/L)">
            <el-form-item prop="ethylbenzene">
              <el-input-number v-model="form.ethylbenzene" :precision="2" :step="0.1" placeholder="请输入乙苯" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="邻二甲苯(μg/L)">
            <el-form-item prop="xyleneOrtho">
              <el-input-number v-model="form.xyleneOrtho" :precision="2" :step="0.1" placeholder="请输入邻二甲苯" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="间/对-二甲苯(μg/L)">
            <el-form-item prop="xyleneMetaPara">
              <el-input-number v-model="form.xyleneMetaPara" :precision="2" :step="0.1" placeholder="请输入间/对-二甲苯" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="苯乙烯(μg/L)">
            <el-form-item prop="styrene">
              <el-input-number v-model="form.styrene" :precision="2" :step="0.1" placeholder="请输入苯乙烯" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="硝基苯(μg/L)">
            <el-form-item prop="nitrobenzene">
              <el-input-number v-model="form.nitrobenzene" :precision="2" :step="0.1" placeholder="请输入硝基苯" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="萘(μg/L)">
            <el-form-item prop="naphthalene">
              <el-input-number v-model="form.naphthalene" :precision="2" :step="0.1" placeholder="请输入萘" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="蒽(μg/L)">
            <el-form-item prop="anthracene">
              <el-input-number v-model="form.anthracene" :precision="2" :step="0.1" placeholder="请输入蒽" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="荧蒽(μg/L)">
            <el-form-item prop="fluoranthene">
              <el-input-number v-model="form.fluoranthene" :precision="2" :step="0.1" placeholder="请输入荧蒽" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="苯并[b]荧蒽(μg/L)">
            <el-form-item prop="benzoBFluoranthene">
              <el-input-number v-model="form.benzoBFluoranthene" :precision="2" :step="0.1" placeholder="请输入苯并[b]荧蒽" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="苯并[a]芘(μg/L)">
            <el-form-item prop="benzoAPyrene">
              <el-input-number v-model="form.benzoAPyrene" :precision="2" :step="0.1" placeholder="请输入苯并[a]芘" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="石油烃(C6-C9)(mg/L)">
            <el-form-item prop="petroleumHydrocarbonsC6C9">
              <el-input-number v-model="form.petroleumHydrocarbonsC6C9" :precision="2" :step="0.1"
                placeholder="请输入石油烃(C6-C9)" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="石油烃(C10-C40)(mg/L)">
            <el-form-item prop="petroleumHydrocarbonsC10C40">
              <el-input-number v-model="form.petroleumHydrocarbonsC10C40" :precision="2" :step="0.1"
                placeholder="请输入石油烃(C10-C40)" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="苯酚(mg/L)">
            <el-form-item prop="phenol">
              <el-input-number v-model="form.phenol" :precision="2" :step="0.1" placeholder="请输入苯酚" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="2-氯酚(mg/L)">
            <el-form-item prop="chlorophenol2">
              <el-input-number v-model="form.chlorophenol2" :precision="2" :step="0.1" placeholder="请输入2-氯酚" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="苯胺(μg/L)">
            <el-form-item prop="aniline">
              <el-input-number v-model="form.aniline" :precision="2" :step="0.1" placeholder="请输入苯胺" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="苊烯(μg/L)">
            <el-form-item prop="acenaphthylene">
              <el-input-number v-model="form.acenaphthylene" :precision="2" :step="0.1" placeholder="请输入苊烯" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="苊(μg/L)">
            <el-form-item prop="acenaphthene">
              <el-input-number v-model="form.acenaphthene" :precision="2" :step="0.1" placeholder="请输入苊" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="芴(μg/L)">
            <el-form-item prop="fluorene">
              <el-input-number v-model="form.fluorene" :precision="2" :step="0.1" placeholder="请输入芴" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="菲(μg/L)">
            <el-form-item prop="phenanthrene">
              <el-input-number v-model="form.phenanthrene" :precision="2" :step="0.1" placeholder="请输入菲" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="芘(μg/L)">
            <el-form-item prop="pyrene">
              <el-input-number v-model="form.pyrene" :precision="2" :step="0.1" placeholder="请输入芘" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="苯并[a]蒽(μg/L)">
            <el-form-item prop="benzoAAnthracene">
              <el-input-number v-model="form.benzoAAnthracene" :precision="2" :step="0.1" placeholder="请输入苯并[a]蒽" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="䓛(μg/L)">
            <el-form-item prop="chrysene">
              <el-input-number v-model="form.chrysene" :precision="2" :step="0.1" placeholder="请输入䓛" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="苯并[k]荧蒽(μg/L)">
            <el-form-item prop="benzoKFluoranthene">
              <el-input-number v-model="form.benzoKFluoranthene" :precision="2" :step="0.1" placeholder="请输入苯并[k]荧蒽" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="茚并[1,2,3-c,d]芘(μg/L)">
            <el-form-item prop="indeno123cdPyrene">
              <el-input-number v-model="form.indeno123cdPyrene" :precision="2" :step="0.1"
                placeholder="请输入茚并[1,2,3-c,d]芘" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="二苯并[a,h]蒽(μg/L)">
            <el-form-item prop="dibenzoAhAnthracene">
              <el-input-number v-model="form.dibenzoAhAnthracene" :precision="2" :step="0.1"
                placeholder="请输入二苯并[a,h]蒽" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="苯并[g,h,i]苝(μg/L)">
            <el-form-item prop="benzoGhiPerylene">
              <el-input-number v-model="form.benzoGhiPerylene" :precision="2" :step="0.1" placeholder="请输入苯并[g,h,i]苝" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="氯甲烷(μg/L)">
            <el-form-item prop="chloromethane">
              <el-input-number v-model="form.chloromethane" :precision="2" :step="0.1" placeholder="请输入氯甲烷" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="1,1-二氯乙烷(μg/L)">
            <el-form-item prop="dichloroethane11">
              <el-input-number v-model="form.dichloroethane11" :precision="2" :step="0.1" placeholder="请输入1,1-二氯乙烷" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="1,1,1,2-四氯乙烷(μg/L)">
            <el-form-item prop="tetrachloroethane1112">
              <el-input-number v-model="form.tetrachloroethane1112" :precision="2" :step="0.1"
                placeholder="请输入1,1,1,2-四氯乙烷" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="1,1,2,2-四氯乙烷(μg/L)">
            <el-form-item prop="tetrachloroethane1122">
              <el-input-number v-model="form.tetrachloroethane1122" :precision="2" :step="0.1"
                placeholder="请输入1,1,2,2-四氯乙烷" />
            </el-form-item>
          </el-descriptions-item>
          <el-descriptions-item label="1,2,3-三氯丙烷(μg/L)">
            <el-form-item prop="trichloropropane123">
              <el-input-number v-model="form.trichloropropane123" :precision="2" :step="0.1"
                placeholder="请输入1,2,3-三氯丙烷" />
            </el-form-item>
          </el-descriptions-item>
        </el-descriptions>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 查看详情对话框 -->
    <el-dialog title="水质监测数据详情" v-model="viewOpen" width="90vw" append-to-body>
      <el-descriptions :column="5" border>
        <el-descriptions-item label="点位编号">{{
          form.pointId
          }}</el-descriptions-item>
        <el-descriptions-item label="采样时间">{{
          form.sampleTime
          }}</el-descriptions-item>
        <el-descriptions-item label="色度(度)">{{
          form.color
          }}</el-descriptions-item>
        <el-descriptions-item label="臭和味">{{
          form.odorAndTaste
          }}</el-descriptions-item>
        <el-descriptions-item label="浊度(NTU)">{{
          form.turbidity
          }}</el-descriptions-item>
        <el-descriptions-item label="肉眼可见物">{{
          form.visibleMatters
          }}</el-descriptions-item>
        <el-descriptions-item label="pH">{{ form.ph }}</el-descriptions-item>
        <el-descriptions-item label="总硬度(mg/L)">{{
          form.totalHardness
          }}</el-descriptions-item>
        <el-descriptions-item label="溶解性总固体(mg/L)">{{
          form.totalDissolvedSolids
          }}</el-descriptions-item>
        <el-descriptions-item label="硫酸盐(mg/L)">{{
          form.sulfate
          }}</el-descriptions-item>
        <el-descriptions-item label="氯化物(mg/L)">{{
          form.chloride
          }}</el-descriptions-item>
        <el-descriptions-item label="铁(mg/L)">{{
          form.iron
          }}</el-descriptions-item>
        <el-descriptions-item label="锰(mg/L)">{{
          form.manganese
          }}</el-descriptions-item>
        <el-descriptions-item label="铝(mg/L)">{{
          form.aluminum
          }}</el-descriptions-item>
        <el-descriptions-item label="阴离子表面活性剂(mg/L)">{{
          form.anionicSurfactant
          }}</el-descriptions-item>
        <el-descriptions-item label="耗氧量(mg/L)">{{
          form.oxygenConsumption
          }}</el-descriptions-item>
        <el-descriptions-item label="硫化物(mg/L)">{{
          form.sulfide
          }}</el-descriptions-item>
        <el-descriptions-item label="钠(mg/L)">{{
          form.sodium
          }}</el-descriptions-item>
        <el-descriptions-item label="亚硝酸盐氮(mg/L)">{{
          form.nitriteNitrogen
          }}</el-descriptions-item>
        <el-descriptions-item label="硝酸盐氮(mg/L)">{{
          form.nitrateNitrogen
          }}</el-descriptions-item>
        <el-descriptions-item label="碘化物(mg/L)">{{
          form.iodide
          }}</el-descriptions-item>
        <el-descriptions-item label="硒(mg/L)">{{
          form.selenium
          }}</el-descriptions-item>
        <el-descriptions-item label="铜(mg/L)">{{
          form.copper
          }}</el-descriptions-item>
        <el-descriptions-item label="锌(mg/L)">{{
          form.zinc
          }}</el-descriptions-item>
        <el-descriptions-item label="挥发酚(mg/L)">{{
          form.volatilePhenol
          }}</el-descriptions-item>
        <el-descriptions-item label="氨氮(mg/L)">{{
          form.ammoniaNitrogen
          }}</el-descriptions-item>
        <el-descriptions-item label="氰化物(mg/L)">{{
          form.cyanide
          }}</el-descriptions-item>
        <el-descriptions-item label="氟化物(mg/L)">{{
          form.fluoride
          }}</el-descriptions-item>
        <el-descriptions-item label="汞(mg/L)">{{
          form.mercury
          }}</el-descriptions-item>
        <el-descriptions-item label="砷(mg/L)">{{
          form.arsenic
          }}</el-descriptions-item>
        <el-descriptions-item label="镉(mg/L)">{{
          form.cadmium
          }}</el-descriptions-item>
        <el-descriptions-item label="六价铬(mg/L)">{{
          form.chromiumVi
          }}</el-descriptions-item>
        <el-descriptions-item label="铅(mg/L)">{{
          form.lead
          }}</el-descriptions-item>
        <el-descriptions-item label="氯仿(μg/L)">{{
          form.chloroform
          }}</el-descriptions-item>
        <el-descriptions-item label="四氯化碳(μg/L)">{{
          form.carbonTetrachloride
          }}</el-descriptions-item>
        <el-descriptions-item label="苯(μg/L)">{{
          form.benzene
          }}</el-descriptions-item>
        <el-descriptions-item label="甲苯(μg/L)">{{
          form.toluene
          }}</el-descriptions-item>
        <el-descriptions-item label="钼(mg/L)">{{
          form.molybdenum
          }}</el-descriptions-item>
        <el-descriptions-item label="钒(mg/L)">{{
          form.vanadium
          }}</el-descriptions-item>
        <el-descriptions-item label="钴(mg/L)">{{
          form.cobalt
          }}</el-descriptions-item>
        <el-descriptions-item label="镍(mg/L)">{{
          form.nickel
          }}</el-descriptions-item>
        <el-descriptions-item label="二氯甲烷(μg/L)">{{
          form.dichloromethane
          }}</el-descriptions-item>
        <el-descriptions-item label="1,2-二氯乙烷(μg/L)">{{
          form.dichloroethane12
          }}</el-descriptions-item>
        <el-descriptions-item label="1,1,1-三氯乙烷(μg/L)">{{
          form.trichloroethane111
          }}</el-descriptions-item>
        <el-descriptions-item label="1,1,2-三氯乙烷(μg/L)">{{
          form.trichloroethane112
          }}</el-descriptions-item>
        <el-descriptions-item label="1,2-二氯丙烷(μg/L)">{{
          form.dichloropropane12
          }}</el-descriptions-item>
        <el-descriptions-item label="氯乙烯(μg/L)">{{
          form.vinylChloride
          }}</el-descriptions-item>
        <el-descriptions-item label="1,1-二氯乙烯(μg/L)">{{
          form.dichloroethylene11
          }}</el-descriptions-item>
        <el-descriptions-item label="反式-1,2-二氯乙烯(μg/L)">{{
          form.transDichloroethylene12
          }}</el-descriptions-item>
        <el-descriptions-item label="顺式-1,2-二氯乙烯(μg/L)">{{
          form.cisDichloroethylene12
          }}</el-descriptions-item>
        <el-descriptions-item label="三氯乙烯(μg/L)">{{
          form.trichloroethylene
          }}</el-descriptions-item>
        <el-descriptions-item label="四氯乙烯(μg/L)">{{
          form.tetrachloroethylene
          }}</el-descriptions-item>
        <el-descriptions-item label="氯苯(μg/L)">{{
          form.chlorobenzene
          }}</el-descriptions-item>
        <el-descriptions-item label="1,2-二氯苯(μg/L)">{{
          form.dichlorobenzene12
          }}</el-descriptions-item>
        <el-descriptions-item label="1,4-二氯苯(μg/L)">{{
          form.dichlorobenzene14
          }}</el-descriptions-item>
        <el-descriptions-item label="乙苯(μg/L)">{{
          form.ethylbenzene
          }}</el-descriptions-item>
        <el-descriptions-item label="邻二甲苯(μg/L)">{{
          form.xyleneOrtho
          }}</el-descriptions-item>
        <el-descriptions-item label="间/对-二甲苯(μg/L)">{{
          form.xyleneMetaPara
          }}</el-descriptions-item>
        <el-descriptions-item label="苯乙烯(μg/L)">{{
          form.styrene
          }}</el-descriptions-item>
        <el-descriptions-item label="硝基苯(μg/L)">{{
          form.nitrobenzene
          }}</el-descriptions-item>
        <el-descriptions-item label="萘(μg/L)">{{
          form.naphthalene
          }}</el-descriptions-item>
        <el-descriptions-item label="蒽(μg/L)">{{
          form.anthracene
          }}</el-descriptions-item>
        <el-descriptions-item label="荧蒽(μg/L)">{{
          form.fluoranthene
          }}</el-descriptions-item>
        <el-descriptions-item label="苯并[b]荧蒽(μg/L)">{{
          form.benzoBFluoranthene
          }}</el-descriptions-item>
        <el-descriptions-item label="苯并[a]芘(μg/L)">{{
          form.benzoAPyrene
          }}</el-descriptions-item>
        <el-descriptions-item label="石油烃(C6-C9)(mg/L)">{{
          form.petroleumHydrocarbonsC6C9
          }}</el-descriptions-item>
        <el-descriptions-item label="石油烃(C10-C40)(mg/L)">{{
          form.petroleumHydrocarbonsC10C40
          }}</el-descriptions-item>
        <el-descriptions-item label="苯酚(mg/L)">{{
          form.phenol
          }}</el-descriptions-item>
        <el-descriptions-item label="2-氯酚(mg/L)">{{
          form.chlorophenol2
          }}</el-descriptions-item>
        <el-descriptions-item label="苯胺(μg/L)">{{
          form.aniline
          }}</el-descriptions-item>
        <el-descriptions-item label="苊烯(μg/L)">{{
          form.acenaphthylene
          }}</el-descriptions-item>
        <el-descriptions-item label="苊(μg/L)">{{
          form.acenaphthene
          }}</el-descriptions-item>
        <el-descriptions-item label="芴(μg/L)">{{
          form.fluorene
          }}</el-descriptions-item>
        <el-descriptions-item label="菲(μg/L)">{{
          form.phenanthrene
          }}</el-descriptions-item>
        <el-descriptions-item label="芘(μg/L)">{{
          form.pyrene
          }}</el-descriptions-item>
        <el-descriptions-item label="苯并[a]蒽(μg/L)">{{
          form.benzoAAnthracene
          }}</el-descriptions-item>
        <el-descriptions-item label="䓛(μg/L)">{{
          form.chrysene
          }}</el-descriptions-item>
        <el-descriptions-item label="苯并[k]荧蒽(μg/L)">{{
          form.benzoKFluoranthene
          }}</el-descriptions-item>
        <el-descriptions-item label="茚并[1,2,3-c,d]芘(μg/L)">{{
          form.indeno123cdPyrene
          }}</el-descriptions-item>
        <el-descriptions-item label="二苯并[a,h]蒽(μg/L)">{{
          form.dibenzoAhAnthracene
          }}</el-descriptions-item>
        <el-descriptions-item label="苯并[g,h,i]苝(μg/L)">{{
          form.benzoGhiPerylene
          }}</el-descriptions-item>
        <el-descriptions-item label="氯甲烷(μg/L)">{{
          form.chloromethane
          }}</el-descriptions-item>
        <el-descriptions-item label="1,1-二氯乙烷(μg/L)">{{
          form.dichloroethane11
          }}</el-descriptions-item>
        <el-descriptions-item label="1,1,1,2-四氯乙烷(μg/L)">{{
          form.tetrachloroethane1112
          }}</el-descriptions-item>
        <el-descriptions-item label="1,1,2,2-四氯乙烷(μg/L)">{{
          form.tetrachloroethane1122
          }}</el-descriptions-item>
        <el-descriptions-item label="1,2,3-三氯丙烷(μg/L)">{{
          form.trichloropropane123
          }}</el-descriptions-item>
      </el-descriptions>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="viewOpen = false">关 闭</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Monitoring">
  import {
    listMonitoring,
    getMonitoring,
    delMonitoring,
    addMonitoring,
    updateMonitoring,
  } from "@/api/admin/monitoring";
  import { generateUUID } from "@/utils/index";
  const { proxy } = getCurrentInstance();

  const monitoringList = ref([]);
  const open = ref(false);
  const viewOpen = ref(false);
  const loading = ref(true);
  const showSearch = ref(true);
  const ids = ref([]);
  const single = ref(true);
  const multiple = ref(true);
  const total = ref(0);
  const title = ref("");

  const data = reactive({
    form: {},
    queryParams: {
      pageNum: 1,
      pageSize: 10,
      pointId: null,
      sampleTime: null,
    },
    // rules: {
    //   pointId: [
    //     { required: true, message: "点位编号不能为空", trigger: "blur" }
    //   ],
    //   sampleTime: [
    //     { required: true, message: "采样时间不能为空", trigger: "blur" }
    //   ],
    //   color: [
    //     { type: 'number', message: "色度必须为数字", trigger: "blur" }
    //   ],
    //   turbidity: [
    //     { type: 'number', message: "浊度必须为数字", trigger: "blur" }
    //   ],
    //   ph: [
    //     { type: 'number', message: "pH必须为数字", trigger: "blur" }
    //   ],
    //   totalHardness: [
    //     { type: 'number', message: "总硬度必须为数字", trigger: "blur" }
    //   ],
    //   totalDissolvedSolids: [
    //     { type: 'number', message: "溶解性总固体必须为数字", trigger: "blur" }
    //   ],
    //   sulfate: [
    //     { type: 'number', message: "硫酸盐必须为数字", trigger: "blur" }
    //   ],
    //   chloride: [
    //     { type: 'number', message: "氯化物必须为数字", trigger: "blur" }
    //   ],
    //   iron: [
    //     { type: 'number', message: "铁必须为数字", trigger: "blur" }
    //   ],
    //   manganese: [
    //     { type: 'number', message: "锰必须为数字", trigger: "blur" }
    //   ]
    // }
  });

  const { queryParams, form, rules } = toRefs(data);

  /** 查询水质监测数据列表 */
  function getList() {
    loading.value = true;
    listMonitoring(queryParams.value).then((response) => {
      monitoringList.value = response.rows;
      total.value = response.total;
      loading.value = false;
    });
  }

  // 取消按钮
  function cancel() {
    open.value = false;
    reset();
  }

  // 表单重置
  function reset() {
    form.value = {
      pointId: null,
      sampleTime: null,
      color: null,
      odorAndTaste: null,
      turbidity: null,
      visibleMatters: null,
      ph: null,
      totalHardness: null,
      totalDissolvedSolids: null,
      sulfate: null,
      chloride: null,
      iron: null,
      manganese: null,
      aluminum: null,
      anionicSurfactant: null,
      oxygenConsumption: null,
      sulfide: null,
      sodium: null,
      nitriteNitrogen: null,
      nitrateNitrogen: null,
      iodide: null,
      selenium: null,
      copper: null,
      zinc: null,
      volatilePhenol: null,
      ammoniaNitrogen: null,
      cyanide: null,
      fluoride: null,
      mercury: null,
      arsenic: null,
      cadmium: null,
      chromiumVi: null,
      lead: null,
      chloroform: null,
      carbonTetrachloride: null,
      benzene: null,
      toluene: null,
      molybdenum: null,
      vanadium: null,
      cobalt: null,
      nickel: null,
      dichloromethane: null,
      dichloroethane12: null,
      trichloroethane111: null,
      trichloroethane112: null,
      dichloropropane12: null,
      vinylChloride: null,
      dichloroethylene11: null,
      transDichloroethylene12: null,
      cisDichloroethylene12: null,
      trichloroethylene: null,
      tetrachloroethylene: null,
      chlorobenzene: null,
      dichlorobenzene12: null,
      dichlorobenzene14: null,
      ethylbenzene: null,
      xyleneOrtho: null,
      xyleneMetaPara: null,
      styrene: null,
      nitrobenzene: null,
      naphthalene: null,
      anthracene: null,
      fluoranthene: null,
      benzoBFluoranthene: null,
      benzoAPyrene: null,
      petroleumHydrocarbonsC6C9: null,
      petroleumHydrocarbonsC10C40: null,
      phenol: null,
      chlorophenol2: null,
      aniline: null,
      acenaphthylene: null,
      acenaphthene: null,
      fluorene: null,
      phenanthrene: null,
      pyrene: null,
      benzoAAnthracene: null,
      chrysene: null,
      benzoKFluoranthene: null,
      indeno123cdPyrene: null,
      dibenzoAhAnthracene: null,
      benzoGhiPerylene: null,
      chloromethane: null,
      dichloroethane11: null,
      tetrachloroethane1112: null,
      tetrachloroethane1122: null,
      trichloropropane123: null,
      id: null,
    };
    proxy.resetForm("monitoringRef");
  }

  /** 搜索按钮操作 */
  function handleQuery() {
    queryParams.value.pageNum = 1;
    getList();
  }

  /** 重置按钮操作 */
  function resetQuery() {
    proxy.resetForm("queryRef");
    handleQuery();
  }

  // 多选框选中数据
  function handleSelectionChange(selection) {
    ids.value = selection.map((item) => item.id);
    single.value = selection.length != 1;
    multiple.value = !selection.length;
  }
  let isEdit = true;

  /** 新增按钮操作 */
  function handleAdd() {
    isEdit = false;
    reset();
    open.value = true;
    title.value = "添加水质监测数据";
  }

  /** 修改按钮操作 */
  function handleUpdate(row) {
    isEdit = false;
    reset();
    const _id = row.id || ids.value;
    getMonitoring(_id).then((response) => {
      form.value = response.data;
      open.value = true;
      title.value = "修改水质监测数据";
    });
  }

  /** 查看详情按钮操作 */
  function handleView(row) {
    reset();
    const _id = row.id || ids.value;
    getMonitoring(_id).then((response) => {
      form.value = response.data;
      viewOpen.value = true;
    });
  }

  /** 提交按钮 */
  function submitForm() {
    proxy.$refs["monitoringRef"].validate((valid) => {
      if (valid) {
        // Convert data types before submission
        const submitData = {
          ...form.value,
          pointId: String(form.value.pointId), // VARCHAR(10)
          sampleTime: String(form.value.sampleTime), // VARCHAR(8)
          color: Number(form.value.color) || null, // NUMERIC
          odorAndTaste: String(form.value.odorAndTaste || ""), // VARCHAR(50)
          turbidity: Number(form.value.turbidity) || null, // NUMERIC
          visibleMatters: String(form.value.visibleMatters || ""), // VARCHAR(50)
          ph: Number(form.value.ph) || null, // NUMERIC
          totalHardness: Number(form.value.totalHardness) || null, // NUMERIC
          totalDissolvedSolids: Number(form.value.totalDissolvedSolids) || null, // NUMERIC
          sulfate: Number(form.value.sulfate) || null, // NUMERIC
          chloride: Number(form.value.chloride) || null, // NUMERIC
          iron: Number(form.value.iron) || null, // NUMERIC
          manganese: Number(form.value.manganese) || null, // NUMERIC
          aluminum: Number(form.value.aluminum) || null, // NUMERIC
          anionicSurfactant: Number(form.value.anionicSurfactant) || null, // NUMERIC
          oxygenConsumption: Number(form.value.oxygenConsumption) || null, // NUMERIC
          sulfide: Number(form.value.sulfide) || null, // NUMERIC
          sodium: Number(form.value.sodium) || null, // NUMERIC
          nitriteNitrogen: Number(form.value.nitriteNitrogen) || null, // NUMERIC
          nitrateNitrogen: Number(form.value.nitrateNitrogen) || null, // NUMERIC
          iodide: Number(form.value.iodide) || null, // NUMERIC
          selenium: Number(form.value.selenium) || null, // NUMERIC
          copper: Number(form.value.copper) || null, // NUMERIC
          zinc: Number(form.value.zinc) || null, // NUMERIC
          volatilePhenol: Number(form.value.volatilePhenol) || null, // NUMERIC
          ammoniaNitrogen: Number(form.value.ammoniaNitrogen) || null, // NUMERIC
          cyanide: Number(form.value.cyanide) || null, // NUMERIC
          fluoride: Number(form.value.fluoride) || null, // NUMERIC
          mercury: Number(form.value.mercury) || null, // NUMERIC
          arsenic: Number(form.value.arsenic) || null, // NUMERIC
          cadmium: Number(form.value.cadmium) || null, // NUMERIC
          chromiumVi: Number(form.value.chromiumVi) || null, // NUMERIC
          lead: Number(form.value.lead) || null, // NUMERIC
          chloroform: Number(form.value.chloroform) || null, // NUMERIC
          carbonTetrachloride: Number(form.value.carbonTetrachloride) || null, // NUMERIC
          benzene: Number(form.value.benzene) || null, // NUMERIC
          toluene: Number(form.value.toluene) || null, // NUMERIC
          molybdenum: Number(form.value.molybdenum) || null, // NUMERIC
          vanadium: Number(form.value.vanadium) || null, // NUMERIC
          cobalt: Number(form.value.cobalt) || null, // NUMERIC
          nickel: Number(form.value.nickel) || null, // NUMERIC
          dichloromethane: Number(form.value.dichloromethane) || null, // NUMERIC
          dichloroethane12: Number(form.value.dichloroethane12) || null, // NUMERIC
          trichloroethane111: Number(form.value.trichloroethane111) || null, // NUMERIC
          trichloroethane112: Number(form.value.trichloroethane112) || null, // NUMERIC
          dichloropropane12: Number(form.value.dichloropropane12) || null, // NUMERIC
          vinylChloride: Number(form.value.vinylChloride) || null, // NUMERIC
          dichloroethylene11: Number(form.value.dichloroethylene11) || null, // NUMERIC
          transDichloroethylene12:
            Number(form.value.transDichloroethylene12) || null, // NUMERIC
          cisDichloroethylene12: Number(form.value.cisDichloroethylene12) || null, // NUMERIC
          trichloroethylene: Number(form.value.trichloroethylene) || null, // NUMERIC
          tetrachloroethylene: Number(form.value.tetrachloroethylene) || null, // NUMERIC
          chlorobenzene: Number(form.value.chlorobenzene) || null, // NUMERIC
          dichlorobenzene12: Number(form.value.dichlorobenzene12) || null, // NUMERIC
          dichlorobenzene14: Number(form.value.dichlorobenzene14) || null, // NUMERIC
          ethylbenzene: Number(form.value.ethylbenzene) || null, // NUMERIC
          xyleneOrtho: Number(form.value.xyleneOrtho) || null, // NUMERIC
          xyleneMetaPara: Number(form.value.xyleneMetaPara) || null, // NUMERIC
          styrene: Number(form.value.styrene) || null, // NUMERIC
          nitrobenzene: Number(form.value.nitrobenzene) || null, // NUMERIC
          naphthalene: Number(form.value.naphthalene) || null, // NUMERIC
          anthracene: Number(form.value.anthracene) || null, // NUMERIC
          fluoranthene: Number(form.value.fluoranthene) || null, // NUMERIC
          benzoBFluoranthene: Number(form.value.benzoBFluoranthene) || null, // NUMERIC
          benzoAPyrene: Number(form.value.benzoAPyrene) || null, // NUMERIC
          petroleumHydrocarbonsC6C9:
            Number(form.value.petroleumHydrocarbonsC6C9) || null, // NUMERIC
          petroleumHydrocarbonsC10C40:
            Number(form.value.petroleumHydrocarbonsC10C40) || null, // NUMERIC
          phenol: Number(form.value.phenol) || null, // NUMERIC
          chlorophenol2: Number(form.value.chlorophenol2) || null, // NUMERIC
          aniline: Number(form.value.aniline) || null, // NUMERIC
          acenaphthylene: Number(form.value.acenaphthylene) || null, // NUMERIC
          acenaphthene: Number(form.value.acenaphthene) || null, // NUMERIC
          fluorene: Number(form.value.fluorene) || null, // NUMERIC
          phenanthrene: Number(form.value.phenanthrene) || null, // NUMERIC
          pyrene: Number(form.value.pyrene) || null, // NUMERIC
          benzoAAnthracene: Number(form.value.benzoAAnthracene) || null, // NUMERIC
          chrysene: Number(form.value.chrysene) || null, // NUMERIC
          benzoKFluoranthene: Number(form.value.benzoKFluoranthene) || null, // NUMERIC
          indeno123cdPyrene: Number(form.value.indeno123cdPyrene) || null, // NUMERIC
          dibenzoAhAnthracene: Number(form.value.dibenzoAhAnthracene) || null, // NUMERIC
          benzoGhiPerylene: Number(form.value.benzoGhiPerylene) || null, // NUMERIC
          chloromethane: Number(form.value.chloromethane) || null, // NUMERIC
          dichloroethane11: Number(form.value.dichloroethane11) || null, // NUMERIC
          tetrachloroethane1112: Number(form.value.tetrachloroethane1112) || null, // NUMERIC
          tetrachloroethane1122: Number(form.value.tetrachloroethane1122) || null, // NUMERIC
          trichloropropane123: Number(form.value.trichloropropane123) || null, // NUMERIC
        };

        if (submitData.id != null) {
          updateMonitoring(submitData).then((response) => {
            proxy.$modal.msgSuccess("修改成功");
            open.value = false;
            getList();
          });
        } else {
          submitData.id = generateUUID();
          addMonitoring(submitData).then((response) => {
            proxy.$modal.msgSuccess("新增成功");
            open.value = false;
            getList();
          });
        }
      }
    });
  }

  /** 删除按钮操作 */
  function handleDelete(row) {
    const _ids = row.id || ids.value;
    proxy.$modal
      .confirm('是否确认删除水质监测数据编号为"' + _ids + '"的数据项？')
      .then(function () {
        return delMonitoring(_ids);
      })
      .then(() => {
        getList();
        proxy.$modal.msgSuccess("删除成功");
      })
      .catch(() => { });
  }

  /** 导出按钮操作 */
  function handleExport() {
    proxy.download(
      "system/monitoring/export",
      {
        ...queryParams.value,
      },
      `monitoring_${new Date().getTime()}.xlsx`
    );
  }

  getList();
</script>

<style scoped>
  .form-row {
    display: flex;
    margin-bottom: 10px;
  }

  .form-col {
    flex: 1;
    min-width: 0;
    padding: 0 5px;
  }

  .form-col :deep(.el-form-item) {
    margin-bottom: 0;
  }

  .form-col :deep(.el-form-item__label) {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .form-col :deep(.el-input),
  .form-col :deep(.el-input-number),
  .form-col :deep(.el-date-editor) {
    width: 100%;
  }
</style>