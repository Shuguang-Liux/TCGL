package com.tcgl.common.model.constant;

/**
 * 短信模板枚举
 * @author wp,xc
 * @date 2020年3月11日
 */
public enum TemplateFieldEnum {
    //模板
    TEMP_ID1("20786470",
            "【海模智云】尊敬的${userName}，您好：您有${num}条订单已逾期未付款,合计金额${total}元,，逾期将产生滞纳金，请及时登录海模智云平台进行付款。",
            "userName,num,total"),

    TEMP_ID2("18990194",
            "【海模智云】，您好，你在海模智云申请的手机验证码为：${code}，请注意保密，有效期为3分钟。",
            "code"),

    TEMP_ID3("20787436",
            "【海模智云】尊敬的${userName}，您好：您的订单PO创建失败，订单号${orderCode},PO创建失败原因${reason},请知悉。",
            "userName,orderCode,reason"),

    TEMP_ID4("20786971",
            "【海模智云】尊敬的${userName}，您好：您有${num}条订单付款时间已不足${numDay}天,合计金额${total}元,请及时登录海模智云平台进行付款。",
            "userName,num,numDay,total"),

    TEMP_ID5("20786096",
            "【海模智云】尊敬的${userName}，您好：平台有${num}条订单已逾期未付款,合计金额${total}元，请及时提醒客户进行付款。",
            "userName,num,total"),

    TEMP_ID6("20785829",
            "【海模智云】尊敬的${userName}，您好：平台有${num}条订单付款期限低于5天，请及时提醒客户进行付款。",
            "userName,num"),

    TEMP_ID7("20785368",
            "【海模智云】尊敬的${userName}，您好：您有${number}个订单待审核，请及时登录平台进行审核。",
            "userName,number"),

    TEMP_ID8("20785128",
            "【海模智云】尊敬的${userName}，您好：您有一个订单卖家已经发货，订单号：${orderCode}，物流号：${logisticsNumber}，请查收。",
            "userName,orderCode,logisticsNumber"),

    TEMP_ID9("20784775",
            "【海模智云】尊敬的${userName}，您好：您有新的订单,订单号：${orderCode},请及时发货。",
            "userName,orderCode"),

    TEMP_ID10("20784596",
            "【海模智云】尊敬的${userName}，您好：您的订单已确认，订单号：${orderCode},请及时登录海模智云平台进行操作。",
            "userName,orderCode"),

    TEMP_ID11("22487441",
            "【海模智云】尊敬的${userName}，您好：您有询价单需要重新议价，询价单号：${orderCode},请及时登录海模智云平台进行议价。",
            "userName,orderCode"),

    TEMP_ID12("20783792",
            "【海模智云】尊敬的${userName}，您好：您有询价订单，询价单号：${orderCode},请及时登录海模智云平台进行报价。",
            "userName,orderCode"),

    TEMP_ID13("23684981",
            "【海模智云】尊敬的${userName}，您好：${industrialLine}产业线-${factoryName}互联工厂，${nowDate}发起模具回收申请，请您登陆海模智云平台查看处理，如有疑问，请及时与模具资产管理取得联系。平台网址：hmzy.cosmoplat.com",
            "userName,industrialLine,factoryName,nowDate"),

    TEMP_ID14("23685021",
            "【海模智云】尊敬的${userName}，您好：${industrialLine}产业线-${factoryName}互联工厂已发起编号为${code}的模具回收计划，请您登陆海模智云平台查看，并与发起本次回收的模具资产管理沟通安排回收执行事宜，如有疑问，请及时与模具资产管理取得联系。平台网址：hmzy.cosmoplat.com",
            "userName,industrialLine,factoryName,code"),

    TEMP_ID15("23685052",
            "【海模智云】尊敬的${userName}，您好：${industrialLine}产业线-${factoryName}互联工厂-编号为${code}的模具回收计划已执行完成，等待您进行公议，请您登陆海模智云平台APP查看，如有疑问，请及时与模具资产管理取得联系。平台网址：hmzy.cosmoplat.com",
            "userName,industrialLine,factoryName,code"),

    TEMP_ID16("23685093",
            "【海模智云】尊敬的${userName}，您好：${industrialLine}产业线-${factoryName}互联工厂-编号为${orderCode}的模具回收订单已生成，等待您进行付款，请您登陆海模智云平台查看，如有疑问，请及时与平台运维取得联系。平台网址：hmzy.cosmoplat.com。运维电话：18561912708",
            "userName,industrialLine,factoryName,orderCode"),

    TEMP_ID17("23685115",
            "【海模智云】尊敬的${userName}，您好：${industrialLine}产业线-${factoryName}互联工厂-编号为${code}的模具回收计划已公议完成，回收车辆等待您的放行，如有疑问，请及时与模具资产管理取得联系。平台网址：hmzy.cosmoplat.com。",
            "userName,industrialLine,factoryName,code"),

    TEMP_ID18("25375786",
            "尊敬的用户，现有【供应商账期管理】业务待您维护：资源方名称：${supplierName}，请您登录海模智云平台查看，网站地址：海模智云EOC管理端",
            "supplierName"),

    TEMP_ID19("25375872",
            "尊敬的用户，现有【供应商利润率管理】业务待您维护：资源方名称：${supplierName}，请您登录海模智云平台查看，网站地址：海模智云EOC管理端",
            "supplierName");

    private final String tempId;//短信模板ID
    private final String content;//模板内容
    private final String params;//参数列表

    public final String getTempId() {
        return tempId;
    }

    public final String getContent() {
        return content;
    }

    public final String getParams() {
        return params;
    }

    TemplateFieldEnum(String tempId, String content, String params) {
        this.tempId = tempId;
        this.content = content;
        this.params = params;
    }
}