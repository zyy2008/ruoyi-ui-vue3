package com.ruoyi.common.system.service.impl;

import com.ruoyi.common.system.domain.SysUserBase;
import com.ruoyi.common.system.mapper.SysUserBaseMapper;
import com.ruoyi.common.system.service.ISysUserBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户基地Service业务层处理
 *
 * @author ruoyi
 * @date 2024-11-12
 */
@Service
public class SysUserBaseServiceImpl implements ISysUserBaseService {
    @Autowired
    private SysUserBaseMapper sysUserBaseMapper;

    /**
     * 查询用户基地
     *
     * @param userId 用户基地主键
     * @return 用户基地
     */
    @Override
    public SysUserBase selectSysUserBaseByUserId(Long userId) {
        return sysUserBaseMapper.selectSysUserBaseByUserId(userId);
    }

    /**
     * 查询用户基地列表
     *
     * @param sysUserBase 用户基地
     * @return 用户基地
     */
    @Override
    public List<SysUserBase> selectSysUserBaseList(SysUserBase sysUserBase) {
        return sysUserBaseMapper.selectSysUserBaseList(sysUserBase);
    }

    /**
     * 新增用户基地
     *
     * @param sysUserBase 用户基地
     * @return 结果
     */
    @Override
    public int insertSysUserBase(SysUserBase sysUserBase) {
        return sysUserBaseMapper.insertSysUserBase(sysUserBase);
    }

    /**
     * 修改用户基地
     *
     * @param sysUserBase 用户基地
     * @return 结果
     */
    @Override
    public int updateSysUserBase(SysUserBase sysUserBase) {
        return sysUserBaseMapper.updateSysUserBase(sysUserBase);
    }

    /**
     * 批量删除用户基地
     *
     * @param userIds 需要删除的用户基地主键
     * @return 结果
     */
    @Override
    public int deleteSysUserBaseByUserIds(Long[] userIds) {
        return sysUserBaseMapper.deleteSysUserBaseByUserIds(userIds);
    }

    /**
     * 删除用户基地信息
     *
     * @param userId 用户基地主键
     * @return 结果
     */
    @Override
    public int deleteSysUserBaseByUserId(Long userId) {
        return sysUserBaseMapper.deleteSysUserBaseByUserId(userId);
    }
}
