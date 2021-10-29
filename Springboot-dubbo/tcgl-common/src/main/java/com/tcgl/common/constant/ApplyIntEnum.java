package com.tcgl.common.constant;

/**
 * @author Shuguang_Liux
 * @package com.example.demo.result
 * @Description ToDo
 * @Date 2020/8/30 21:28
 **/
public enum ApplyIntEnum implements IntEnum<ApplyIntEnum>{
    //价格
    PRICE(1,4),
    /**
     *  月租金
     */
    MONTHLYRENT(2,200);

    private final int index;
    private final int intEnumValue;

    ApplyIntEnum(int index, int intEnumValue) {
        this.index=index;
        this.intEnumValue=intEnumValue;
    }

    public int intEnumValue(){
        return index;
    }
    public int getIntEnumValue(){
        return intEnumValue;
    }

    private static int getIntEnumValue(int index){
        int intEnumValue = 0;
        for (ApplyIntEnum applyIntEnum : ApplyIntEnum.values()) {
            if (index == applyIntEnum.index){
                intEnumValue = applyIntEnum.intEnumValue;
            }
        }
        return intEnumValue;

    }

    @Override
    public int getIntValue() {
        return this.index;
    }
}
