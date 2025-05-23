package com.ruoyi.admin.service.impl;

import com.ruoyi.admin.domain.TBase;
import com.ruoyi.admin.mapper.TBaseMapper;
import com.ruoyi.admin.mapper.TMapLabelMapper;
import com.ruoyi.admin.service.ITBaseService;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 基地Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-11-06
 */
@Service
public class TBaseServiceImpl implements ITBaseService 
{
    @Autowired
    private TBaseMapper tBaseMapper;
    @Autowired
    private TMapLabelMapper mapLabelMapper;

    /**
     * 查询基地
     * 
     * @param id 基地主键
     * @return 基地
     */
    @Override
    public TBase selectTBaseById(Long id)
    {
        return tBaseMapper.selectTBaseById(id);
    }

    /**
     * 查询基地列表
     * 
     * @param tBase 基地
     * @return 基地
     */
    @Override
    public List<TBase> selectTBaseList(TBase tBase)
    {
        return tBaseMapper.selectTBaseList(tBase);
    }

    /**
     * 新增基地
     * 
     * @param tBase 基地
     * @return 结果
     */
    @Override
    public int insertTBase(TBase tBase)
    {
        return tBaseMapper.insertTBase(tBase);
    }

    /**
     * 修改基地
     * 
     * @param tBase 基地
     * @return 结果
     */
    @Override
    public int updateTBase(TBase tBase)
    {
        return tBaseMapper.updateTBase(tBase);
    }

    /**
     * 批量删除基地
     * 
     * @param ids 需要删除的基地主键
     * @return 结果
     */
    @Override
    public int deleteTBaseByIds(Long[] ids)
    {
        return tBaseMapper.deleteTBaseByIds(ids);
    }

    /**
     * 删除基地信息
     * 
     * @param id 基地主键
     * @return 结果
     */
    @Override
    public int deleteTBaseById(Long id)
    {
        return tBaseMapper.deleteTBaseById(id);
    }

    @Override
    public List<TBase> getBase(){
        List<TBase> data=new ArrayList<>();
        if(SecurityUtils.isAdmin()){
            data=tBaseMapper.selectTBaseList(new TBase());
        }else{
            List<Long> baseIdList=mapLabelMapper.selectBaseIdByUserId(SecurityUtils.getUserId());
            Long[] baseIds=SecurityUtils.getBaseId();
            if(null!=baseIds&&0<baseIds.length) {
                for (Long baseId : baseIds) {
                    baseIdList.add(baseId);
                }
            }
            if(!baseIdList.isEmpty())
                data=tBaseMapper.selectTBaseListByBaseId(baseIdList);
        }
        return data;
    }
}
