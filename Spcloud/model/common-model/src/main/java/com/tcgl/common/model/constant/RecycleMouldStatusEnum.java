package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : yyz
 * @since : 2020/2/12 13:46
 * <p>模具回收状态枚举<br>
 */
public enum RecycleMouldStatusEnum implements IntEnum<RecycleMouldStatusEnum> {

    //基本状态
//    BASE_SAVE(0, "草稿"),
//    BASE_NOT_PUSH(1, "未推送"),
//    BASE_PUSHED(2, "已推送（待接单）"),
//    BASE_CONFIRM(3, "已接单"),
//    BASE_NOT_PRINT(5,"取货单未打印"),
//    BASE_PRINTED(6,"取货单已打印"),
//    BASE_PHOTO(7,"待拍照"),
//    BASE_WEIGHT(8,"待称重"),
//    BASE_SIGNING(9,"会签中"),
//    BASE_ARREAR(10,"待付款"),
//    BASE_PAID(11,"已付款");
    BASE_ZERO(0,"EOC(未推送)  客户中心（撤回）"),
    BASE_ONE(1,"EOC(未推送)  客户中心（已提交）"),
    BASE_TWO(2,"EOC（已推送）客户中心（已接单）资源方中心（取货单未打印）"),
    BASE_THREE(3,"EOC（待拍照）客户中心（待拍照）资源方中心（取货单已打印）"),
    BASE_FOUR(4,"EOC（待称重）客户中心（待称重）资源方中心（待称重）"),
    BASE_FIVE(5,"EOC（会签中）客户中心（会签中）资源方中心（会签中）"),
    BASE_SIX(6,"EOC（待收款）客户中心（会签完成） 资源方中心（待付款）"),
    BASE_SEVEN(7,"EOC(已驳回)   客户中心（已驳回）"),
    BASE_EIGHT(8,"EOC（收款待确认） 资源方中心（已付款）"),
    BASE_NINE(9,"EOC（已收款）  资源方中心（交易完成）");


    //其他待用


    private final int index;
    private final String value;


    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    RecycleMouldStatusEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public int getIntValue() {
        return this.index;
    }

    public static final List<Integer> indexList(){
        List<Integer> l = new ArrayList<>();
        for (RecycleMouldStatusEnum s : RecycleMouldStatusEnum.values()) {
            l.add(s.index);
        }
        return l;
    }
}
