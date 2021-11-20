package com.tcgl.common.model.constant;

/**
 * 合同类型枚举
 * @author xc
 * @date 2019年12月23日21:21:27
 */
public enum GoodsOrderByEnum implements StringEnum<GoodsOrderByEnum>{
    ORDER_FIELD_SALES("sales", "销量"),
    ORDER_FIELD_PRICE("price", "价格"),

    ORDER_BY_DESC("DESC", "降序"),
    ORDER_BY_ASC("ASC", "升序");

    private String type;
    private String description;
    private GoodsOrderByEnum(String type, String description) {
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
        for (GoodsOrderByEnum contractTypeEnum : GoodsOrderByEnum.values()) {
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
