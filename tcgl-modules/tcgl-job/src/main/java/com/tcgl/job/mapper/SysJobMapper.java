package com.tcgl.job.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tcgl.job.domain.SysJob;

/**
 * 调度任务信息 数据层
 *
 * @author tcgl
 */
public interface SysJobMapper extends BaseMapper<SysJob> {
    /**
     * 查询调度任务日志集合
     *
     * @param job 调度信息
     * @return 操作日志集合
     */
    List<SysJob> selectJobList(SysJob job);

    /**
     * 查询所有调度任务
     *
     * @return 调度任务列表
     */
    List<SysJob> selectJobAll();

    /**
     * 通过调度ID查询调度任务信息
     *
     * @param jobId 调度ID
     * @return 角色对象信息
     */
    SysJob selectJobById(Long jobId);

    /**
     * 通过调度ID删除调度任务信息
     *
     * @param jobId 调度ID
     * @return 结果
     */
    int deleteJobById(Long jobId);

    /**
     * 批量删除调度任务信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteJobByIds(Long[] ids);

    /**
     * 修改调度任务信息
     *
     * @param job 调度任务信息
     * @return 结果
     */
    int updateJob(SysJob job);

    /**
     * 新增调度任务信息
     *
     * @param job 调度任务信息
     * @return 结果
     */
    int insertJob(SysJob job);
}
