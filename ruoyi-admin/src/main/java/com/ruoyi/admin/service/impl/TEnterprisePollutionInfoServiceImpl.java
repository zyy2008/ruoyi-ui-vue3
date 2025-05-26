package com.ruoyi.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.admin.mapper.TEnterprisePollutionInfoMapper;
import com.ruoyi.admin.domain.TEnterprisePollutionInfo;
import com.ruoyi.admin.service.ITEnterprisePollutionInfoService;

/**
 * 企业污染源Service业务层处理
 *
 * @author ruoyi
 * @date 2025-05-26
 */
@Service
public class TEnterprisePollutionInfoServiceImpl implements ITEnterprisePollutionInfoService {
    @Autowired
    private TEnterprisePollutionInfoMapper tEnterprisePollutionInfoMapper;

    /**
     * 查询企业污染源
     *
     * @param id 企业污染源主键
     * @return 企业污染源
     */
    @Override
    public TEnterprisePollutionInfo selectTEnterprisePollutionInfoById(Long id) {
        return tEnterprisePollutionInfoMapper.selectTEnterprisePollutionInfoById(id);
    }

    /**
     * 查询企业污染源列表
     *
     * @param tEnterprisePollutionInfo 企业污染源
     * @return 企业污染源
     */
    @Override
    public List<TEnterprisePollutionInfo> selectTEnterprisePollutionInfoList(TEnterprisePollutionInfo tEnterprisePollutionInfo) {
        return tEnterprisePollutionInfoMapper.selectTEnterprisePollutionInfoList(tEnterprisePollutionInfo);
    }

    /**
     * 新增企业污染源
     *
     * @param tEnterprisePollutionInfo 企业污染源
     * @return 结果
     */
    @Override
    public int insertTEnterprisePollutionInfo(TEnterprisePollutionInfo tEnterprisePollutionInfo) {
        return tEnterprisePollutionInfoMapper.insertTEnterprisePollutionInfo(tEnterprisePollutionInfo);
    }

    /**
     * 修改企业污染源
     *
     * @param tEnterprisePollutionInfo 企业污染源
     * @return 结果
     */
    @Override
    public int updateTEnterprisePollutionInfo(TEnterprisePollutionInfo tEnterprisePollutionInfo) {
        return tEnterprisePollutionInfoMapper.updateTEnterprisePollutionInfo(tEnterprisePollutionInfo);
    }

    /**
     * 批量删除企业污染源
     *
     * @param ids 需要删除的企业污染源主键
     * @return 结果
     */
    @Override
    public int deleteTEnterprisePollutionInfoByIds(Long[] ids) {
        return tEnterprisePollutionInfoMapper.deleteTEnterprisePollutionInfoByIds(ids);
    }

    /**
     * 删除企业污染源信息
     *
     * @param id 企业污染源主键
     * @return 结果
     */
    @Override
    public int deleteTEnterprisePollutionInfoById(Long id) {
        return tEnterprisePollutionInfoMapper.deleteTEnterprisePollutionInfoById(id);
    }
}
