package com.ruoyi.admin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 企业管理对象 t_enterprises
 *
 * @author wy
 * @date 2025-06-01
 */
@Data
@ApiModel("企业管理")
public class TEnterprises extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 企业名称
     */
    @Excel(name = "企业名称")
    @ApiModelProperty("企业名称")
    private String enterpriseName;

    /**
     * 行业类别
     */
    @Excel(name = "行业类别")
    @ApiModelProperty("行业类别")
    private String industryCategory;

    /**
     * 生产年限
     */
    @Excel(name = "生产年限")
    @ApiModelProperty("生产年限")
    private String productionYears;

    /**
     * 存续状态
     */
    @Excel(name = "存续状态")
    @ApiModelProperty("存续状态")
    private String status;

    /**
     * 报送情况
     */
    @Excel(name = "报送情况")
    @ApiModelProperty("报送情况")
    private String reportingStatus;

    /**
     * 经度
     */
    @Excel(name = "经度")
    @ApiModelProperty("经度")
    private String longitude;

    /**
     * 纬度
     */
    @Excel(name = "纬度")
    @ApiModelProperty("纬度")
    private String latitude;

    /**
     * 占地面积
     */
    @Excel(name = "占地面积")
    @ApiModelProperty("占地面积")
    private String area;

    /**
     * 是否重点
     */
    @Excel(name = "是否重点")
    @ApiModelProperty("是否重点")
    private String isKeyEnterprise;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private String id;

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
     * 利用历史起始年份
     */
    @Excel(name = "利用历史起始年份")
    @ApiModelProperty("利用历史起始年份")
    private Long historyStartYear;

    /**
     * 利用历史结束年份
     */
    @Excel(name = "利用历史结束年份")
    @ApiModelProperty("利用历史结束年份")
    private Long historyEndYear;

}
