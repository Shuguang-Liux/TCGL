package com.tcgl.common.model.constant;

/**
 * 商品操作类型
 * @author yyz
 * @date 2019年12月23日21:21:27
 */
public enum GoodsOperateTypeEnum implements StringEnum<GoodsOperateTypeEnum>{
    TYPE_PUT_ON("putOn", "上架"),
    TYPE_PULL_OFF("pullOff", "下架"),

    OPERATOR_PORTAL("portal", "portal操作"),//查询、上下架等
    OPERATOR_EOC("eoc", "eoc操作"),//查询、上下架等

    VERIFY_YSE("yes", "已审核"),
    VERIFY_NO("no", "未审核");

    private String type;
    private String description;
    private GoodsOperateTypeEnum(String type, String description) {
        this.type = type;
        this.description = description;
    }
    public String getType() {
        return type;
    }
    public String getDescription() {
        return description;
    }

    public static String getDescription(String type) {
        String description = null;
        for (GoodsOperateTypeEnum contractTypeEnum : GoodsOperateTypeEnum.values()) {
            if (type.equals(contractTypeEnum.type)) {
                description = contractTypeEnum.description;
            }
        }
        return description;
    }
    @Override
    public String getStringValue() {
        return this.type;
    }
}
