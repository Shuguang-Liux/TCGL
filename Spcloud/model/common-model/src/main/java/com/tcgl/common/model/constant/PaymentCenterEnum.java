package com.tcgl.common.model.constant;

/**
 * <p><br>
 *
 * @Author hzw
 * @create 2019/12/24 14:50
 */
public enum PaymentCenterEnum implements IntEnum<MallDeliveryOrderStatusEnum> {
    PAYMENT_CENTER_PROJECT_CODE(0, "", "立项号"),
    PAYMENT_CENTER_USER_NO(1,"20009663", "申请人工号"),
    PAYMENT_CENTER_DEPARTMENT_CODE(2, "KD03040201", "部门编号"),
    PAYMENT_CENTER_PAY_ACCOUNT_CODE(3, "", "付款账号"),
    PAYMENT_CENTER_PAY_HBANK_CODE(4, "", "付款开户行"),
    PAYMENT_CENTER_COMPANY_CODE(5, "80H0", "付款公司编码"),
    PAYMENT_CENTER_BUSINESS_DESC(6, "支付供应商材料款", "业务描述"),
    PAYMENT_CENTER_CURRENCY(7, "CNY", "币种编码"),
    PAYMENT_CENTER_PAY_REASON(8, "179", "支付理由默认 179"),
    PAYMENT_CENTER_PAYMENT_TYPE(9, "01", "发票支付方式,默认01"),
    PAYMENT_CENTER_EXPENSE_ITEM(10, "7010101", "费用项目编码"),
    PAYMENT_CENTER_TAX_RATE(11, "0.13", "税率(暂时全部按照13%算)"),
    PAYMENT_CENTER_APP_KEY(12, "1000070", ""),
    PAYMENT_CENTER_KEY(13, "ce552eaf8ca7fcfb4db6e144b4ae5515", ""),
    PAYMENT_CENTER_APP_KEY_TEST(12, "1000077", ""),
    PAYMENT_CENTER_KEY_TEST(13, "7de517bbb4e77a9f10c06f58eb67e1fd", "");

//    //回收付款 -测试
//    RECYCLE_PAY_APP_KEY(21,"1000002","回收付款appkey"),
//    RECYCLE_PAY_KEY(22,"fc870398b1808a4064b5a7a2e23f61f0","回收付款密钥"),
//    //回收付款 -正式
////    RECYCLE_PAY_APP_KEY(21,"1000011","回收付款appkey"),
////    RECYCLE_PAY_KEY(22,"9cafe3857ef0810eafc309df9a0bce04","回收付款密钥"),
//
//    RECYCLE_PAY_SECRET_KEY(23,"3f57e0237e58307fe897771fc2633f2d","回收付款收银台密钥");



    private final int index;
    private final String value;
    private final String explain;

    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    PaymentCenterEnum(int index, String value, String explain) {
        this.index = index;
        this.value = value;
        this.explain = explain;
    }

    @Override
    public int getIntValue() {
        return this.index;
    }
}
