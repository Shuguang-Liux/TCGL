package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : yyz
 * @since : 2020/1/14 16:46
 * <p>项目管理状态枚举<br>
 */
public enum ProjectManageStatusEnum implements IntEnum<ProjectManageStatusEnum> {

    //项目基本状态
    PROJECT_START_SAVE(0, "开工待提交"),//开工待提交
    PROJECT_START_SUBMIT(1, "开工已提交"),//开工已提交
    PROJECT_START_PASS(2, "主计划制定中"),//开工审核通过
//    PROJECT_PLAN_SAVE(3, "计划-草稿"),//计划-草稿   此状态意义同2，故暂时不用
    PROJECT_PLAN_SUBMIT(4, "计划已提交"),//计划已提交-待计划评审
    PROJECT_PLAN_PASS(5, "方案设计中"),//计划评审通过-设计方案中
    PROJECT_DESIGN_SUBMIT(6,"设计已提交"),//设计提交-待设计评审
    PROJECT_DESIGN_PASS(7,"设计评审通过"),//设计评审通过-采购中
    PROJECT_PURCHASE_FINISH(8,"采购完成"),//采购完成-加工中
    PROJECT_PROCESS_FINISH(9,"加工完成"),//加工完成-装配试模中
    PROJECT_ASSEMBLE_FINISH(10,"装配试模完成"),//装配试模第一个材料上传完成-首件中-上传首件材料
    PROJECT_FIRST_SUBMIT(11,"首件已提交"),//首件材料上传完成-首件确认中
    PROJECT_FIRST_PLATFORM_PASS(12,"首件平台确认通过"),//首件平台确认完成 ----平台和客户不区分先后顺序，都通过则进入-样件调试中
    PROJECT_FIRST_CUSTOMER_PASS(13,"首件客户确认通过"),//首件客户确认完成 ----平台和客户不区分先后顺序，都通过则进入-样件调试中
    PROJECT_SAMPLE_TEST(14,"样件调试中"),//首件确认通过 -样件调试中
    PROJECT_SAMPLE_SUBMIT(15,"样件已提交"),//调试材料上传完-调试完成-待样件确认
    PROJECT_SAMPLE_PLATFORM_PASS(16,"样件平台确认通过"),//样件平台确认完成-待客户确认
    PROJECT_SAMPLE_CUSTOMER_PASS(17,"样件客户确认通过"),//样件客户确认完成-四方验收中-可上传材料
    //预留样件不分先后审核通过节点 -18
    PROJECT_FOUR_LAUNCH(19,"验收已发起"),
    PROJECT_FOUR_SUBMIT(20,"验收已提交"),//材料上传完成-待验收评审
    PROJECT_FOUR_PLATFORM_PASS(21,"验收平台评审通过"),//四方验收平台评审完成
    PROJECT_FOUR_CUSTOMER_PASS(22,"验收客户评审通过"),//四方验收客户评审完成
    PROJECT_FOUR_PASS(23,"验收评审通过"),//四方验收评审完成-可发起调拨
    PROJECT_ALLOT_LAUNCH(24,"调拨已发起"),//调拨已发起-执行中-上传发运单
    PROJECT_ALLOT_SUBMIT(25,"调拨已提交"),//调拨已提交-上传发运单完成-待确认收货
    PROJECT_FINISH(26,"确认收货");

    private final int index;
    private final String value;


    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    ProjectManageStatusEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public int getIntValue() {
        return this.index;
    }

    public static final List<Integer> indexList(){
        List<Integer> l = new ArrayList<>();
        for (ProjectManageStatusEnum s : ProjectManageStatusEnum.values()) {
            l.add(s.index);
        }
        return l;
    }
}
