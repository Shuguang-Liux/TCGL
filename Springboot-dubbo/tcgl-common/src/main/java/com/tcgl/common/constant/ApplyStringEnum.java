package com.tcgl.common.constant;

/**
 * @author Shuguang_Liux
 * @package com.example.demo.result
 * @Description ToDo
 * @Date 2020/8/30 0:44
 **/
public enum ApplyStringEnum implements StringEnum<ApplyStringEnum>{
    //是否出门默认N
    ISOUT(1,"N");

    private final int index;
    private final String stringValue;

//    public final int getIndex(){
//        return index;
//    }


     ApplyStringEnum(int index, String stringValue) {
        this.index = index;
        this.stringValue = stringValue;
    }

    @Override
    public String getStringValue() {
        return this.stringValue;
    }
}
