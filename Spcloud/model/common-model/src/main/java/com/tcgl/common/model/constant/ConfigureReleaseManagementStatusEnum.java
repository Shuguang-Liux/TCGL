package com.tcgl.common.model.constant;

/**
 * <p><br>
 *
 * @Author hzw
 * @create 2020/5/11 15:28
 */
public enum ConfigureReleaseManagementStatusEnum {
    CONFIGURE_RELEASE_MANAGEMENT_STATUS_DRAFT(0, "草稿"),
    CONFIGURE_RELEASE_MANAGEMENT_STATUS_USE(1, "使用中"),
    CONFIGURE_RELEASE_MANAGEMENT_STATUS_OVERDUE(2, "已过期");

    private Integer index;
    private String value;

    ConfigureReleaseManagementStatusEnum(Integer index, String value) {
        this.index = index;
        this.value = value;
    }

    public Integer getIndex() {
        return index;
    }
}
