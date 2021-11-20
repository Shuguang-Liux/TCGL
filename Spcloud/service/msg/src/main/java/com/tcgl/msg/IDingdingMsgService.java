package com.tcgl.msg;

import com.tcgl.common.model.R;

/**
 * 钉钉消息发送接口
 *
 * @author Shuguang_Liux
 * @date 2021/11/18 17:53
 */
public interface IDingdingMsgService {
    /**
     * 发送手机通知
     *
     * @param mobile 移动
     * @return {@link R}<{@link ?}>
     */
    R<?> sendMobileNotification(String mobile);

    /**
     * 得到所有部门信息
     *
     * @param deptId 部门id
     * @return {@link R}<{@link ?}>
     */
    R<?> getAllDeptInfo(Long deptId);
    R<?> getListId(Long deptId);
}
