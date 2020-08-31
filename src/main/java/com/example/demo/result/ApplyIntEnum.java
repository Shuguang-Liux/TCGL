package com.example.demo.result;

/**
 * @author Shuguang_Liux
 * @package com.example.demo.result
 * @Description ToDo
 * @Date 2020/8/30 21:28
 **/
public enum ApplyIntEnum implements IntEnum<ApplyIntEnum>{
    //价格
    PRICE(1,4);

    private final int index;
    private final int intValue;

//    public final int getIndex(){
//        return index;
//    }
//    public final int getIntValue(){
//        return intValue;
//    }
    ApplyIntEnum(int index, int intValue) {
        this.index=index;
        this.intValue=intValue;
    }

    @Override
    public int getIntValue() {
        return this.intValue;
    }
}
