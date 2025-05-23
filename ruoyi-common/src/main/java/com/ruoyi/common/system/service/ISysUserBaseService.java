package com.ruoyi.common.system.service;

import com.ruoyi.common.system.domain.SysUserBase;

import java.util.List;

/**
 * 用户基地Service接口
 * 
 * @author ruoyi
 * @date 2024-11-12
 */
public interface ISysUserBaseService 
{
    /**
     * 查询用户基地
     * 
     * @param userId 用户基地主键
     * @return 用户基地
     */
    public SysUserBase selectSysUserBaseByUserId(Long userId);

    /**
     * 查询用户基地列表
     * 
     * @param sysUserBase 用户基地
     * @return 用户基地集合
     */
    public List<SysUserBase> selectSysUserBaseList(SysUserBase sysUserBase);

    /**
     * 新增用户基地
     * 
     * @param sysUserBase 用户基地
     * @return 结果
     */
    public int insertSysUserBase(SysUserBase sysUserBase);

    /**
     * 修改用户基地
     * 
     * @param sysUserBase 用户基地
     * @return 结果
     */
    public int updateSysUserBase(SysUserBase sysUserBase);

    /**
     * 批量删除用户基地
     * 
     * @param userIds 需要删除的用户基地主键集合
     * @return 结果
     */
    public int deleteSysUserBaseByUserIds(Long[] userIds);

    /**
     * 删除用户基地信息
     * 
     * @param userId 用户基地主键
     * @return 结果
     */
    public int deleteSysUserBaseByUserId(Long userId);
}
