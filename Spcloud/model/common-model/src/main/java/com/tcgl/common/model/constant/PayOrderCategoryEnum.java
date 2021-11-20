package com.tcgl.common.model.constant;

/**
 * 支付订单类别枚举
 * @author yyz
 * @date 2020年4月2日18:21:15
 */
public enum PayOrderCategoryEnum implements StringEnum<PayOrderCategoryEnum>{
    CATEGORY_RECYCLE("recycle", "模具回收支付订单"),
    CATEGORY_DESIGN("design", "云设计支付订单");

    private String category;
    private String description;
    private PayOrderCategoryEnum(String category, String description) {
        this.category = category;
        this.description = description;
    }
    public String getCategory() {
        return category;
    }
    public String getDescription() {
        return description;
    }

    public static String getDescription(String title) {
        String description = null;
        for (PayOrderCategoryEnum contractCategoryEnum : PayOrderCategoryEnum.values()) {
            if (title.equals(contractCategoryEnum.category)) {
                description = contractCategoryEnum.description;
            }
        }
        return description;
    }
    @Override
    public String getStringValue() {
        return this.category;
    }
}
