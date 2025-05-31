package com.ruoyi.admin.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 【请填写功能名称】对象 t_water_quality_monitoring
 * 
 * @author ruoyi
 * @date 2025-05-30
 */
public class TWaterQualityMonitoring extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String pointId;

    /** $column.columnComment */
    private String sampleTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String color;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String odorAndTaste;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String turbidity;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String visibleMatters;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String ph;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String totalHardness;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String totalDissolvedSolids;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String sulfate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String chloride;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String iron;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String manganese;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String aluminum;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String anionicSurfactant;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String oxygenConsumption;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String sulfide;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String sodium;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String nitriteNitrogen;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String nitrateNitrogen;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String iodide;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String selenium;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String copper;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String zinc;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String volatilePhenol;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String ammoniaNitrogen;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String cyanide;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String fluoride;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String mercury;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String arsenic;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String cadmium;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String chromiumVi;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String lead;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String chloroform;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String carbonTetrachloride;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String benzene;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String toluene;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String molybdenum;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String vanadium;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String cobalt;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String nickel;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String dichloromethane;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String dichloroethane12;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String trichloroethane111;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String trichloroethane112;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String dichloropropane12;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String vinylChloride;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String dichloroethylene11;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String transDichloroethylene12;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String cisDichloroethylene12;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String trichloroethylene;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String tetrachloroethylene;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String chlorobenzene;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String dichlorobenzene12;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String dichlorobenzene14;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String ethylbenzene;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String xyleneOrtho;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String xyleneMetaPara;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String styrene;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String nitrobenzene;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String naphthalene;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String anthracene;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String fluoranthene;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String benzoBFluoranthene;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String benzoAPyrene;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String petroleumHydrocarbonsC6C9;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String petroleumHydrocarbonsC10C40;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String phenol;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String chlorophenol2;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String aniline;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String acenaphthylene;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String acenaphthene;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String fluorene;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String phenanthrene;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String pyrene;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String benzoAAnthracene;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String chrysene;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String benzoKFluoranthene;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String indeno123CdPyrene;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String dibenzoAHAnthracene;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String benzoGHIPerylene;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String chloromethane;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String dichloroethane11;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String tetrachloroethane1112;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String tetrachloroethane1122;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String trichloropropane123;

    public void setPointId(String pointId) 
    {
        this.pointId = pointId;
    }

    public String getPointId() 
    {
        return pointId;
    }
    public void setSampleTime(String sampleTime) 
    {
        this.sampleTime = sampleTime;
    }

    public String getSampleTime() 
    {
        return sampleTime;
    }
    public void setColor(String color) 
    {
        this.color = color;
    }

    public String getColor() 
    {
        return color;
    }
    public void setOdorAndTaste(String odorAndTaste) 
    {
        this.odorAndTaste = odorAndTaste;
    }

    public String getOdorAndTaste() 
    {
        return odorAndTaste;
    }
    public void setTurbidity(String turbidity) 
    {
        this.turbidity = turbidity;
    }

    public String getTurbidity() 
    {
        return turbidity;
    }
    public void setVisibleMatters(String visibleMatters) 
    {
        this.visibleMatters = visibleMatters;
    }

    public String getVisibleMatters() 
    {
        return visibleMatters;
    }
    public void setPh(String ph) 
    {
        this.ph = ph;
    }

    public String getPh() 
    {
        return ph;
    }
    public void setTotalHardness(String totalHardness) 
    {
        this.totalHardness = totalHardness;
    }

    public String getTotalHardness() 
    {
        return totalHardness;
    }
    public void setTotalDissolvedSolids(String totalDissolvedSolids) 
    {
        this.totalDissolvedSolids = totalDissolvedSolids;
    }

    public String getTotalDissolvedSolids() 
    {
        return totalDissolvedSolids;
    }
    public void setSulfate(String sulfate) 
    {
        this.sulfate = sulfate;
    }

    public String getSulfate() 
    {
        return sulfate;
    }
    public void setChloride(String chloride) 
    {
        this.chloride = chloride;
    }

    public String getChloride() 
    {
        return chloride;
    }
    public void setIron(String iron) 
    {
        this.iron = iron;
    }

    public String getIron() 
    {
        return iron;
    }
    public void setManganese(String manganese) 
    {
        this.manganese = manganese;
    }

    public String getManganese() 
    {
        return manganese;
    }
    public void setAluminum(String aluminum) 
    {
        this.aluminum = aluminum;
    }

    public String getAluminum() 
    {
        return aluminum;
    }
    public void setAnionicSurfactant(String anionicSurfactant) 
    {
        this.anionicSurfactant = anionicSurfactant;
    }

    public String getAnionicSurfactant() 
    {
        return anionicSurfactant;
    }
    public void setOxygenConsumption(String oxygenConsumption) 
    {
        this.oxygenConsumption = oxygenConsumption;
    }

    public String getOxygenConsumption() 
    {
        return oxygenConsumption;
    }
    public void setSulfide(String sulfide) 
    {
        this.sulfide = sulfide;
    }

    public String getSulfide() 
    {
        return sulfide;
    }
    public void setSodium(String sodium) 
    {
        this.sodium = sodium;
    }

    public String getSodium() 
    {
        return sodium;
    }
    public void setNitriteNitrogen(String nitriteNitrogen) 
    {
        this.nitriteNitrogen = nitriteNitrogen;
    }

    public String getNitriteNitrogen() 
    {
        return nitriteNitrogen;
    }
    public void setNitrateNitrogen(String nitrateNitrogen) 
    {
        this.nitrateNitrogen = nitrateNitrogen;
    }

    public String getNitrateNitrogen() 
    {
        return nitrateNitrogen;
    }
    public void setIodide(String iodide) 
    {
        this.iodide = iodide;
    }

    public String getIodide() 
    {
        return iodide;
    }
    public void setSelenium(String selenium) 
    {
        this.selenium = selenium;
    }

    public String getSelenium() 
    {
        return selenium;
    }
    public void setCopper(String copper) 
    {
        this.copper = copper;
    }

    public String getCopper() 
    {
        return copper;
    }
    public void setZinc(String zinc) 
    {
        this.zinc = zinc;
    }

    public String getZinc() 
    {
        return zinc;
    }
    public void setVolatilePhenol(String volatilePhenol) 
    {
        this.volatilePhenol = volatilePhenol;
    }

    public String getVolatilePhenol() 
    {
        return volatilePhenol;
    }
    public void setAmmoniaNitrogen(String ammoniaNitrogen) 
    {
        this.ammoniaNitrogen = ammoniaNitrogen;
    }

    public String getAmmoniaNitrogen() 
    {
        return ammoniaNitrogen;
    }
    public void setCyanide(String cyanide) 
    {
        this.cyanide = cyanide;
    }

    public String getCyanide() 
    {
        return cyanide;
    }
    public void setFluoride(String fluoride) 
    {
        this.fluoride = fluoride;
    }

    public String getFluoride() 
    {
        return fluoride;
    }
    public void setMercury(String mercury) 
    {
        this.mercury = mercury;
    }

    public String getMercury() 
    {
        return mercury;
    }
    public void setArsenic(String arsenic) 
    {
        this.arsenic = arsenic;
    }

    public String getArsenic() 
    {
        return arsenic;
    }
    public void setCadmium(String cadmium) 
    {
        this.cadmium = cadmium;
    }

    public String getCadmium() 
    {
        return cadmium;
    }
    public void setChromiumVi(String chromiumVi) 
    {
        this.chromiumVi = chromiumVi;
    }

    public String getChromiumVi() 
    {
        return chromiumVi;
    }
    public void setLead(String lead) 
    {
        this.lead = lead;
    }

    public String getLead() 
    {
        return lead;
    }
    public void setChloroform(String chloroform) 
    {
        this.chloroform = chloroform;
    }

    public String getChloroform() 
    {
        return chloroform;
    }
    public void setCarbonTetrachloride(String carbonTetrachloride) 
    {
        this.carbonTetrachloride = carbonTetrachloride;
    }

    public String getCarbonTetrachloride() 
    {
        return carbonTetrachloride;
    }
    public void setBenzene(String benzene) 
    {
        this.benzene = benzene;
    }

    public String getBenzene() 
    {
        return benzene;
    }
    public void setToluene(String toluene) 
    {
        this.toluene = toluene;
    }

    public String getToluene() 
    {
        return toluene;
    }
    public void setMolybdenum(String molybdenum) 
    {
        this.molybdenum = molybdenum;
    }

    public String getMolybdenum() 
    {
        return molybdenum;
    }
    public void setVanadium(String vanadium) 
    {
        this.vanadium = vanadium;
    }

    public String getVanadium() 
    {
        return vanadium;
    }
    public void setCobalt(String cobalt) 
    {
        this.cobalt = cobalt;
    }

    public String getCobalt() 
    {
        return cobalt;
    }
    public void setNickel(String nickel) 
    {
        this.nickel = nickel;
    }

    public String getNickel() 
    {
        return nickel;
    }
    public void setDichloromethane(String dichloromethane) 
    {
        this.dichloromethane = dichloromethane;
    }

    public String getDichloromethane() 
    {
        return dichloromethane;
    }
    public void setDichloroethane12(String dichloroethane12) 
    {
        this.dichloroethane12 = dichloroethane12;
    }

    public String getDichloroethane12() 
    {
        return dichloroethane12;
    }
    public void setTrichloroethane111(String trichloroethane111) 
    {
        this.trichloroethane111 = trichloroethane111;
    }

    public String getTrichloroethane111() 
    {
        return trichloroethane111;
    }
    public void setTrichloroethane112(String trichloroethane112) 
    {
        this.trichloroethane112 = trichloroethane112;
    }

    public String getTrichloroethane112() 
    {
        return trichloroethane112;
    }
    public void setDichloropropane12(String dichloropropane12) 
    {
        this.dichloropropane12 = dichloropropane12;
    }

    public String getDichloropropane12() 
    {
        return dichloropropane12;
    }
    public void setVinylChloride(String vinylChloride) 
    {
        this.vinylChloride = vinylChloride;
    }

    public String getVinylChloride() 
    {
        return vinylChloride;
    }
    public void setDichloroethylene11(String dichloroethylene11) 
    {
        this.dichloroethylene11 = dichloroethylene11;
    }

    public String getDichloroethylene11() 
    {
        return dichloroethylene11;
    }
    public void setTransDichloroethylene12(String transDichloroethylene12) 
    {
        this.transDichloroethylene12 = transDichloroethylene12;
    }

    public String getTransDichloroethylene12() 
    {
        return transDichloroethylene12;
    }
    public void setCisDichloroethylene12(String cisDichloroethylene12) 
    {
        this.cisDichloroethylene12 = cisDichloroethylene12;
    }

    public String getCisDichloroethylene12() 
    {
        return cisDichloroethylene12;
    }
    public void setTrichloroethylene(String trichloroethylene) 
    {
        this.trichloroethylene = trichloroethylene;
    }

    public String getTrichloroethylene() 
    {
        return trichloroethylene;
    }
    public void setTetrachloroethylene(String tetrachloroethylene) 
    {
        this.tetrachloroethylene = tetrachloroethylene;
    }

    public String getTetrachloroethylene() 
    {
        return tetrachloroethylene;
    }
    public void setChlorobenzene(String chlorobenzene) 
    {
        this.chlorobenzene = chlorobenzene;
    }

    public String getChlorobenzene() 
    {
        return chlorobenzene;
    }
    public void setDichlorobenzene12(String dichlorobenzene12) 
    {
        this.dichlorobenzene12 = dichlorobenzene12;
    }

    public String getDichlorobenzene12() 
    {
        return dichlorobenzene12;
    }
    public void setDichlorobenzene14(String dichlorobenzene14) 
    {
        this.dichlorobenzene14 = dichlorobenzene14;
    }

    public String getDichlorobenzene14() 
    {
        return dichlorobenzene14;
    }
    public void setEthylbenzene(String ethylbenzene) 
    {
        this.ethylbenzene = ethylbenzene;
    }

    public String getEthylbenzene() 
    {
        return ethylbenzene;
    }
    public void setXyleneOrtho(String xyleneOrtho) 
    {
        this.xyleneOrtho = xyleneOrtho;
    }

    public String getXyleneOrtho() 
    {
        return xyleneOrtho;
    }
    public void setXyleneMetaPara(String xyleneMetaPara) 
    {
        this.xyleneMetaPara = xyleneMetaPara;
    }

    public String getXyleneMetaPara() 
    {
        return xyleneMetaPara;
    }
    public void setStyrene(String styrene) 
    {
        this.styrene = styrene;
    }

    public String getStyrene() 
    {
        return styrene;
    }
    public void setNitrobenzene(String nitrobenzene) 
    {
        this.nitrobenzene = nitrobenzene;
    }

    public String getNitrobenzene() 
    {
        return nitrobenzene;
    }
    public void setNaphthalene(String naphthalene) 
    {
        this.naphthalene = naphthalene;
    }

    public String getNaphthalene() 
    {
        return naphthalene;
    }
    public void setAnthracene(String anthracene) 
    {
        this.anthracene = anthracene;
    }

    public String getAnthracene() 
    {
        return anthracene;
    }
    public void setFluoranthene(String fluoranthene) 
    {
        this.fluoranthene = fluoranthene;
    }

    public String getFluoranthene() 
    {
        return fluoranthene;
    }
    public void setBenzoBFluoranthene(String benzoBFluoranthene) 
    {
        this.benzoBFluoranthene = benzoBFluoranthene;
    }

    public String getBenzoBFluoranthene() 
    {
        return benzoBFluoranthene;
    }
    public void setBenzoAPyrene(String benzoAPyrene) 
    {
        this.benzoAPyrene = benzoAPyrene;
    }

    public String getBenzoAPyrene() 
    {
        return benzoAPyrene;
    }
    public void setPetroleumHydrocarbonsC6C9(String petroleumHydrocarbonsC6C9) 
    {
        this.petroleumHydrocarbonsC6C9 = petroleumHydrocarbonsC6C9;
    }

    public String getPetroleumHydrocarbonsC6C9() 
    {
        return petroleumHydrocarbonsC6C9;
    }
    public void setPetroleumHydrocarbonsC10C40(String petroleumHydrocarbonsC10C40) 
    {
        this.petroleumHydrocarbonsC10C40 = petroleumHydrocarbonsC10C40;
    }

    public String getPetroleumHydrocarbonsC10C40() 
    {
        return petroleumHydrocarbonsC10C40;
    }
    public void setPhenol(String phenol) 
    {
        this.phenol = phenol;
    }

    public String getPhenol() 
    {
        return phenol;
    }
    public void setChlorophenol2(String chlorophenol2) 
    {
        this.chlorophenol2 = chlorophenol2;
    }

    public String getChlorophenol2() 
    {
        return chlorophenol2;
    }
    public void setAniline(String aniline) 
    {
        this.aniline = aniline;
    }

    public String getAniline() 
    {
        return aniline;
    }
    public void setAcenaphthylene(String acenaphthylene) 
    {
        this.acenaphthylene = acenaphthylene;
    }

    public String getAcenaphthylene() 
    {
        return acenaphthylene;
    }
    public void setAcenaphthene(String acenaphthene) 
    {
        this.acenaphthene = acenaphthene;
    }

    public String getAcenaphthene() 
    {
        return acenaphthene;
    }
    public void setFluorene(String fluorene) 
    {
        this.fluorene = fluorene;
    }

    public String getFluorene() 
    {
        return fluorene;
    }
    public void setPhenanthrene(String phenanthrene) 
    {
        this.phenanthrene = phenanthrene;
    }

    public String getPhenanthrene() 
    {
        return phenanthrene;
    }
    public void setPyrene(String pyrene) 
    {
        this.pyrene = pyrene;
    }

    public String getPyrene() 
    {
        return pyrene;
    }
    public void setBenzoAAnthracene(String benzoAAnthracene) 
    {
        this.benzoAAnthracene = benzoAAnthracene;
    }

    public String getBenzoAAnthracene() 
    {
        return benzoAAnthracene;
    }
    public void setChrysene(String chrysene) 
    {
        this.chrysene = chrysene;
    }

    public String getChrysene() 
    {
        return chrysene;
    }
    public void setBenzoKFluoranthene(String benzoKFluoranthene) 
    {
        this.benzoKFluoranthene = benzoKFluoranthene;
    }

    public String getBenzoKFluoranthene() 
    {
        return benzoKFluoranthene;
    }
    public void setIndeno123CdPyrene(String indeno123CdPyrene) 
    {
        this.indeno123CdPyrene = indeno123CdPyrene;
    }

    public String getIndeno123CdPyrene() 
    {
        return indeno123CdPyrene;
    }
    public void setDibenzoAHAnthracene(String dibenzoAHAnthracene) 
    {
        this.dibenzoAHAnthracene = dibenzoAHAnthracene;
    }

    public String getDibenzoAHAnthracene() 
    {
        return dibenzoAHAnthracene;
    }
    public void setBenzoGHIPerylene(String benzoGHIPerylene) 
    {
        this.benzoGHIPerylene = benzoGHIPerylene;
    }

    public String getBenzoGHIPerylene() 
    {
        return benzoGHIPerylene;
    }
    public void setChloromethane(String chloromethane) 
    {
        this.chloromethane = chloromethane;
    }

    public String getChloromethane() 
    {
        return chloromethane;
    }
    public void setDichloroethane11(String dichloroethane11) 
    {
        this.dichloroethane11 = dichloroethane11;
    }

    public String getDichloroethane11() 
    {
        return dichloroethane11;
    }
    public void setTetrachloroethane1112(String tetrachloroethane1112) 
    {
        this.tetrachloroethane1112 = tetrachloroethane1112;
    }

    public String getTetrachloroethane1112() 
    {
        return tetrachloroethane1112;
    }
    public void setTetrachloroethane1122(String tetrachloroethane1122) 
    {
        this.tetrachloroethane1122 = tetrachloroethane1122;
    }

    public String getTetrachloroethane1122() 
    {
        return tetrachloroethane1122;
    }
    public void setTrichloropropane123(String trichloropropane123) 
    {
        this.trichloropropane123 = trichloropropane123;
    }

    public String getTrichloropropane123() 
    {
        return trichloropropane123;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("pointId", getPointId())
            .append("sampleTime", getSampleTime())
            .append("color", getColor())
            .append("odorAndTaste", getOdorAndTaste())
            .append("turbidity", getTurbidity())
            .append("visibleMatters", getVisibleMatters())
            .append("ph", getPh())
            .append("totalHardness", getTotalHardness())
            .append("totalDissolvedSolids", getTotalDissolvedSolids())
            .append("sulfate", getSulfate())
            .append("chloride", getChloride())
            .append("iron", getIron())
            .append("manganese", getManganese())
            .append("aluminum", getAluminum())
            .append("anionicSurfactant", getAnionicSurfactant())
            .append("oxygenConsumption", getOxygenConsumption())
            .append("sulfide", getSulfide())
            .append("sodium", getSodium())
            .append("nitriteNitrogen", getNitriteNitrogen())
            .append("nitrateNitrogen", getNitrateNitrogen())
            .append("iodide", getIodide())
            .append("selenium", getSelenium())
            .append("copper", getCopper())
            .append("zinc", getZinc())
            .append("volatilePhenol", getVolatilePhenol())
            .append("ammoniaNitrogen", getAmmoniaNitrogen())
            .append("cyanide", getCyanide())
            .append("fluoride", getFluoride())
            .append("mercury", getMercury())
            .append("arsenic", getArsenic())
            .append("cadmium", getCadmium())
            .append("chromiumVi", getChromiumVi())
            .append("lead", getLead())
            .append("chloroform", getChloroform())
            .append("carbonTetrachloride", getCarbonTetrachloride())
            .append("benzene", getBenzene())
            .append("toluene", getToluene())
            .append("molybdenum", getMolybdenum())
            .append("vanadium", getVanadium())
            .append("cobalt", getCobalt())
            .append("nickel", getNickel())
            .append("dichloromethane", getDichloromethane())
            .append("dichloroethane12", getDichloroethane12())
            .append("trichloroethane111", getTrichloroethane111())
            .append("trichloroethane112", getTrichloroethane112())
            .append("dichloropropane12", getDichloropropane12())
            .append("vinylChloride", getVinylChloride())
            .append("dichloroethylene11", getDichloroethylene11())
            .append("transDichloroethylene12", getTransDichloroethylene12())
            .append("cisDichloroethylene12", getCisDichloroethylene12())
            .append("trichloroethylene", getTrichloroethylene())
            .append("tetrachloroethylene", getTetrachloroethylene())
            .append("chlorobenzene", getChlorobenzene())
            .append("dichlorobenzene12", getDichlorobenzene12())
            .append("dichlorobenzene14", getDichlorobenzene14())
            .append("ethylbenzene", getEthylbenzene())
            .append("xyleneOrtho", getXyleneOrtho())
            .append("xyleneMetaPara", getXyleneMetaPara())
            .append("styrene", getStyrene())
            .append("nitrobenzene", getNitrobenzene())
            .append("naphthalene", getNaphthalene())
            .append("anthracene", getAnthracene())
            .append("fluoranthene", getFluoranthene())
            .append("benzoBFluoranthene", getBenzoBFluoranthene())
            .append("benzoAPyrene", getBenzoAPyrene())
            .append("petroleumHydrocarbonsC6C9", getPetroleumHydrocarbonsC6C9())
            .append("petroleumHydrocarbonsC10C40", getPetroleumHydrocarbonsC10C40())
            .append("phenol", getPhenol())
            .append("chlorophenol2", getChlorophenol2())
            .append("aniline", getAniline())
            .append("acenaphthylene", getAcenaphthylene())
            .append("acenaphthene", getAcenaphthene())
            .append("fluorene", getFluorene())
            .append("phenanthrene", getPhenanthrene())
            .append("pyrene", getPyrene())
            .append("benzoAAnthracene", getBenzoAAnthracene())
            .append("chrysene", getChrysene())
            .append("benzoKFluoranthene", getBenzoKFluoranthene())
            .append("indeno123CdPyrene", getIndeno123CdPyrene())
            .append("dibenzoAHAnthracene", getDibenzoAHAnthracene())
            .append("benzoGHIPerylene", getBenzoGHIPerylene())
            .append("chloromethane", getChloromethane())
            .append("dichloroethane11", getDichloroethane11())
            .append("tetrachloroethane1112", getTetrachloroethane1112())
            .append("tetrachloroethane1122", getTetrachloroethane1122())
            .append("trichloropropane123", getTrichloropropane123())
            .toString();
    }
}
