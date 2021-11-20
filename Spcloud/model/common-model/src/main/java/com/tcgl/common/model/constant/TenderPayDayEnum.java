package com.tcgl.common.model.constant;

/**
 * 合同类型枚举
 * @author xc
 * @date 2019年12月23日21:21:27
 */
public enum TenderPayDayEnum implements StringEnum<TenderPayDayEnum>{
    DELIVERDAY_FIRST("firstDeliverDay", "第一次交付日期"),
    DELIVERDAY_SECOND("secondDeliverDay", "第二次交付日期"),
    PAY_DAY_FIRST("firstPayDay", "第一次付款日期"),
    PAY_DAY_SECOND("secondPayDay", "第二次付款日期"),
    PAY_DAY_THIRD("thirdPayDay", "第三次付款日期"),
    PAY_DAY_FOURTH("fourthPayDay", "第四次付款日期");
    private String type;
    private String description;
    private TenderPayDayEnum(String type, String description) {
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
        for (TenderPayDayEnum contractTypeEnum : TenderPayDayEnum.values()) {
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
