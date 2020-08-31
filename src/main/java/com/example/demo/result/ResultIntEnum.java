package com.example.demo.result;


/**
 * @author Shuguang_Liux
 * @package com.example.demo.result
 * @Description ToDo
 * @Date 2020/8/28 21:51
 **/
public enum ResultIntEnum implements IntEnum<ResultIntEnum>{
    /**
     * 成功
     */
    SUCCESS("success",200),
    /**
     * 错误
     */
    ERROR("error",400);

    private final String index;
    private final int intValue;

    ResultIntEnum(String index, int intValue) {
        this.index=index;
        this.intValue = intValue;
    }

    @Override
    public int getIntValue() {
        return intValue;
    }
}
