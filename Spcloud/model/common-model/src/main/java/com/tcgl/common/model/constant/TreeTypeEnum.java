package com.tcgl.common.model.constant;

/**
 * @author : sunmingyao
 * @since : 2020/10/12 18:22
 * <p>说明<br>
 */
public enum TreeTypeEnum implements StringEnum<TreeTypeEnum> {
    AREA_INFO("areaInfo", "区域信息");

    private String title;
    private String desc;
    TreeTypeEnum(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }
    public String getTitle() {
        return title;
    }
    public String getDesc() {
        return desc;
    }

    public static String getDesc(String title) {
        String desc = null;
        for (TreeTypeEnum treeTypeEnum : TreeTypeEnum.values()) {
            if (title.equals(treeTypeEnum.title)) {
                desc = treeTypeEnum.desc;
            }
        }
        return desc;
    }

    @Override
    public String getStringValue() {
        return this.title;
    }
}
