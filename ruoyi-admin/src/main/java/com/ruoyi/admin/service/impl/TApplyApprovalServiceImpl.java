package com.ruoyi.admin.service.impl;

import com.ruoyi.admin.domain.TApplyApproval;
import com.ruoyi.admin.domain.bo.ApplyBo;
import com.ruoyi.admin.mapper.TApplyApprovalMapper;
import com.ruoyi.admin.service.ITApplyApprovalService;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 申请审批Service业务层处理
 *
 * @author ruoyi
 * @date 2024-10-31
 */
@Service
public class TApplyApprovalServiceImpl implements ITApplyApprovalService {
    @Autowired
    private TApplyApprovalMapper tApplyApprovalMapper;

    /**
     * 查询申请审批
     *
     * @param id 申请审批主键
     * @return 申请审批
     */
    @Override
    public TApplyApproval selectTApplyApprovalById(Long id) {
        return tApplyApprovalMapper.selectTApplyApprovalById(id);
    }

    /**
     * 查询申请审批列表
     *
     * @param tApplyApproval 申请审批
     * @return 申请审批
     */
    @Override
    public List<TApplyApproval> selectTApplyApprovalList(TApplyApproval tApplyApproval) {
        return tApplyApprovalMapper.selectTApplyApprovalList(tApplyApproval);
    }

    /**
     * 新增申请审批
     *
     * @param tApplyApproval 申请审批
     * @return 结果
     */
    @Override
    public int insertTApplyApproval(TApplyApproval tApplyApproval) {
        return tApplyApprovalMapper.insertTApplyApproval(tApplyApproval);
    }

    /**
     * 修改申请审批
     *
     * @param tApplyApproval 申请审批
     * @return 结果
     */
    @Override
    public int updateTApplyApproval(TApplyApproval tApplyApproval) {
        return tApplyApprovalMapper.updateTApplyApproval(tApplyApproval);
    }

    /**
     * 批量删除申请审批
     *
     * @param ids 需要删除的申请审批主键
     * @return 结果
     */
    @Override
    public int deleteTApplyApprovalByIds(Long[] ids) {
        return tApplyApprovalMapper.deleteTApplyApprovalByIds(ids);
    }

    /**
     * 删除申请审批信息
     *
     * @param id 申请审批主键
     * @return 结果
     */
    @Override
    public int deleteTApplyApprovalById(Long id) {
        return tApplyApprovalMapper.deleteTApplyApprovalById(id);
    }

    public int updateState(ApplyBo applyBo) {
        TApplyApproval t = new TApplyApproval();
        t.setId(applyBo.getId());
        t.setState(applyBo.getState());
        t.setAuditorId(SecurityUtils.getUserId());
        return tApplyApprovalMapper.updateTApplyApproval(t);
    }
}
