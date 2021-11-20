package com.tcgl.common.model.constant;

/**
 * 合同类型枚举
 * @author xc
 * @date 2019年12月23日21:21:27
 */
public enum ContractTypeEnum implements StringEnum<ContractTypeEnum>{
    TYPE_SELL("sell", "销售合同"),
    TYPE_BUY("buy", "采购合同");
    private String type;
    private String description;
    private ContractTypeEnum(String type, String description) {
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
        for (ContractTypeEnum contractTypeEnum : ContractTypeEnum.values()) {
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
