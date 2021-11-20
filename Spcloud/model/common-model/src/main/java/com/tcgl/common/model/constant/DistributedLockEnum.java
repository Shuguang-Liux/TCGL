package com.tcgl.common.model.constant;

/**
 * @author : sunmingyao
 * @since : 2019/11/18 17:22
 * <p>分布式锁枚举类<br>
 */
public enum DistributedLockEnum implements StringEnum<DistributedLockEnum>{
    DISTRIBUTED_GOODS_CODE_LOCK("distributed_goods_code_lockr", "商品编号锁"),  //生成商品编码时的锁
    DISTRIBUTED_ORDER_CODE_LOCK("distributed_order_code_lockr", "订单编号锁"),  //生成订单编号时的锁
    DISTRIBUTED_INQUIRY_SHEET_CODE_LOCK("distributed_inquiry_sheet_code_lockr", "询价单编号锁"),  //生成询价单编号时的锁
    DISTRIBUTED_QUOTATION_SHEET_CODE_LOCK("distributed_quotation_sheet_code_lockr", "报价单编号锁");  //生成报价单编号时的锁
    private String title;
    private String desc;
    private DistributedLockEnum(String title, String desc) {
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
        for (DistributedLockEnum distributedLockEnum : DistributedLockEnum.values()) {
            if (title.equals(distributedLockEnum.title)) {
                desc = distributedLockEnum.desc;
            }
        }
        return desc;
    }
    @Override
    public String getStringValue() {
        return this.title;
    }
}
