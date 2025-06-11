package com.ruoyi.admin.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 企业信息对象 t_enterprise_plots
 *
 * @author ruoyi
 * @date 2025-05-26
 */
@Data
@ApiModel("在产企业地块信息")
public class TEnterprisePlots extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 地块编码（格式：6位行政区+1位地块类型+2位行业大类+4位流水）
     */
    @ApiModelProperty("地块编码")
    private String plotCode;

    /**
     * 地块名称
     */
    @Excel(name = "地块名称")
    @ApiModelProperty("地块名称")
    private String plotName;

    /**
     * 单位名称（企业名称）
     */
    @Excel(name = "单位名称", readConverterExp = "企业名称")
    @ApiModelProperty("单位名称")
    private String companyName;

    /**
     * 统一社会信用代码
     */
    @Excel(name = "统一社会信用代码")
    @ApiModelProperty("统一社会信用代码")
    private String companyCreditCode;

    /**
     * 法定代表人
     */
    @Excel(name = "法定代表人")
    @ApiModelProperty("法定代表人")
    private String legalRepresentative;

    /**
     * 单位所在地详细地址
     */
    @Excel(name = "单位所在地详细地址")
    @ApiModelProperty("单位所在地详细地址")
    private String address;

    /**
     * 行政区划代码（如：140121）
     */
    @Excel(name = "行政区划代码", readConverterExp = "如=：140121")
    @ApiModelProperty("行政区划代码")
    private String regionCode;

    /**
     * 中心经度（如：112°15ˊ18.68）
     */
    @Excel(name = "中心经度", readConverterExp = "如=：112°15ˊ18.68")
    @ApiModelProperty("中心经度")
    private String centerLongitude;

    /**
     * 中心纬度（如：37°33ˊ50.04）
     */
    @Excel(name = "中心纬度", readConverterExp = "如=：37°33ˊ50.04")
    @ApiModelProperty("中心纬度")
    private String centerLatitude;

    /**
     * 地块占地面积（以土地证为准）
     */
    @Excel(name = "地块占地面积", readConverterExp = "以=土地证为准")
    @ApiModelProperty("地块占地面积")
    private String areaLandCert;

    /**
     * 地块实际使用面积（现场勾画得出）
     */
    @Excel(name = "地块实际使用面积", readConverterExp = "现=场勾画得出")
    @ApiModelProperty("地块实际使用面积")
    private String areaActual;

    /**
     * 联系人姓名
     */
    @Excel(name = "联系人姓名")
    @ApiModelProperty("联系人姓名")
    private String contactName;

    /**
     * 联系人电话
     */
    @Excel(name = "联系人电话")
    @ApiModelProperty("联系人电话")
    private String contactPhone;

    /**
     * 行业类别（如：化学试剂和助剂制造）
     */
    @Excel(name = "行业类别", readConverterExp = "如=：化学试剂和助剂制造")
    @ApiModelProperty("行业类别")
    private String industryCategory;

    /**
     * 行业代码（如：C2661）
     */
    @Excel(name = "行业代码", readConverterExp = "如=：C2661")
    @ApiModelProperty("行业代码")
    private String industryCode;

    /**
     * 登记注册类型（如：有限责任公司）
     */
    @Excel(name = "登记注册类型", readConverterExp = "如=：有限责任公司")
    @ApiModelProperty("登记注册类型")
    private String registrationType;

    /**
     * 企业规模（大型、中型、小型、微型）
     */
    @Excel(name = "企业规模", readConverterExp = "大型、中型、小型、微型")
    @ApiModelProperty("企业规模")
    private String enterpriseScale;

    /**
     * 开业时间
     */
    @JsonFormat(pattern = "yyyy-MM")
    @Excel(name = "开业时间", width = 30, dateFormat = "yyyy-MM")
    @ApiModelProperty("开业时间")
    private Date startDate;

    /**
     * 最新改扩建时间
     */
    @JsonFormat(pattern = "yyyy-MM")
    @Excel(name = "最新改扩建时间", width = 30, dateFormat = "yyyy-MM")
    @ApiModelProperty("最新改扩建时间")
    private Date latestExpansionDate;

    /**
     * 是否位于工业园区或集聚区（true=是，false=否）
     */
    @Excel(name = "是否位于工业园区或集聚区", readConverterExp = "t=rue=是，false=否")
    @ApiModelProperty("是否位于工业园区或集聚区")
    private String inIndustrialPark;

    /**
     * 地块利用历史（如：工业用地、荒地等）
     */
    @Excel(name = "地块利用历史", readConverterExp = "如=：工业用地、荒地等")
    @ApiModelProperty("地块利用历史")
    private String historyLandUse;

    /**
     * 归属部门ID
     */
    @Excel(name = "归属部门ID")
    @ApiModelProperty("归属部门ID")
    private String deptId;
}
