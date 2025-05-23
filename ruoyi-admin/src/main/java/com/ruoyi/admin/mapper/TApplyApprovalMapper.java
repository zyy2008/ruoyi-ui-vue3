package com.ruoyi.admin.mapper;

import com.ruoyi.admin.domain.TApplyApproval;
import com.ruoyi.common.annotation.DataScope;

import java.util.List;

/**
 * 申请审批Mapper接口
 *
 * @author ruoyi
 * @date 2024-10-31
 */
public interface TApplyApprovalMapper {
    /**
     * 查询申请审批
     *
     * @param id 申请审批主键
     * @return 申请审批
     */
    public TApplyApproval selectTApplyApprovalById(Long id);

    /**
     * 查询申请审批列表
     *
     * @param tApplyApproval 申请审批
     * @return 申请审批集合
     */
    @DataScope(deptAlias = "aa", userAlias = "aa")
    public List<TApplyApproval> selectTApplyApprovalList(TApplyApproval tApplyApproval);

    /**
     * 新增申请审批
     *
     * @param tApplyApproval 申请审批
     * @return 结果
     */
    public int insertTApplyApproval(TApplyApproval tApplyApproval);

    /**
     * 修改申请审批
     *
     * @param tApplyApproval 申请审批
     * @return 结果
     */
    public int updateTApplyApproval(TApplyApproval tApplyApproval);

    /**
     * 删除申请审批
     *
     * @param id 申请审批主键
     * @return 结果
     */
    public int deleteTApplyApprovalById(Long id);

    /**
     * 批量删除申请审批
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTApplyApprovalByIds(Long[] ids);
}
