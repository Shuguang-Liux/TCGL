package com.tcgl.common.model.constant;

/**
 * 合同类别枚举
 * @author xc
 * @date 2019年12月23日21:21:15
 */
public enum ContractCategoryEnum implements StringEnum<ContractCategoryEnum>{
    CATEGORY_TENDER("tender", "招标合同"),
    CATEGORY_MALL("mall", "资源超市合同"),
    CATEGORY_MALL_DELIVERY("mall_delivery", "资源超市发货单"),
    CATEGORY_MALL_TAKE_DELIVERY("mall_take_delivery", "资源超市收货单"),
    CATEGORY_RECYCLE("recycle", "模具回收合同"),
    CATEGORY_PROJECT("project", "项目管理调拨单"),
    CATEGORY_DESIGN("design", "云设计合同"),
    CATEGORY_PRODUCE("produce", "云制造合同");

    private String category;
    private String description;
    private ContractCategoryEnum(String category, String description) {
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
        for (ContractCategoryEnum contractCategoryEnum : ContractCategoryEnum.values()) {
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
