package com.tcgl.common.model.constant;

/**
 * 项目管理见证性材料附件节点枚举
 * @author xc
 * @date 2019年12月23日21:21:15
 */
public enum ProjectManageAttachNodeEnum implements StringEnum<ProjectManageAttachNodeEnum>{

    //大节点
    PROJECT_PARENT_NODE_DESIGN("design", "设计"),
    PROJECT_PARENT_NODE_PURCHASE("purchase", "采购"),
    PROJECT_PARENT_NODE_PROCESS("process", "加工"),
    PROJECT_PARENT_NODE_ASSEMBLE("assemble", "装配试模"),
    PROJECT_PARENT_NODE_ACCEPT("accept", "验收"),
    PROJECT_PARENT_NODE_SEND("send", "交付"),
    PROJECT_PARENT_NODE_SERVICE("service", "售后"),

    //装配试模下的小节点
    PROJECT_NODE_ASSEMBLE_ASSEMBLE("assemble", "装配试模"),
    PROJECT_NODE_ASSEMBLE_FIRSTPHOTO("firstPhoto", "首件照片"),
    PROJECT_NODE_ASSEMBLE_FIRSTSEND("firstSend", "首件发运单"),
    PROJECT_NODE_ASSEMBLE_SAMPLEPHOTO("samplePhoto", "样件照片"),
    PROJECT_NODE_ASSEMBLE_SAMPLEREPORT("sampleReport", "样件检验报告"),
    PROJECT_NODE_ASSEMBLE_SAMPLESEND("sampleSend", "样件发运单");

    private String category;
    private String description;
    private ProjectManageAttachNodeEnum(String category, String description) {
        this.category = category;
        this.description = description;
    }
    public String getCategory() {
        return category;
    }
    public String getDescription() {
        return description;
    }

    public static String getDescription(String title) {
        String description = null;
        for (ProjectManageAttachNodeEnum contractCategoryEnum : ProjectManageAttachNodeEnum.values()) {
            if (title.equals(contractCategoryEnum.category)) {
                description = contractCategoryEnum.description;
            }
        }
        return description;
    }
    @Override
    public String getStringValue() {
        return this.category;
    }
}
