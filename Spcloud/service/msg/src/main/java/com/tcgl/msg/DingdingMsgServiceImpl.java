package com.tcgl.msg;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.taobao.api.ApiException;
import com.tcgl.common.model.R;
import com.tcgl.common.model.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * dingding消息发送实现类
 *
 * @author Shuguang_Liux
 * @date 2021/11/18 18:01
 */
@Service
public class DingdingMsgServiceImpl implements IDingdingMsgService {

    private static final Logger LOGGER = LogManager.getLogger(DingdingMsgServiceImpl.class);

    /**
     * 微应用ID
     */
    private final static Long AGENT_ID = 1371248246L;

    /**
     * 应用唯一标识
     */
    private final static String APP_KEY = "dingsoj5tjtodroai7bu";

    /**
     * 应用密钥
     */
    private final static String APP_SECRET = "0BFDVVFwxW1gnPC52VP0p-9LggN_SOnm15A0hvEMqfyTY7_pMkYkE4l6Llz-905G";

    /**
     * 获取token地址
     */
    private final static String GET_TOKEN = "https://oapi.dingtalk.com/gettoken";

    /**
     * 根据手机号获取用户ID
     */
    private final static String GET_BY_MOBILE = "https://oapi.dingtalk.com/topapi/v2/user/getbymobile";

    /**
     * 信息发送接口
     */
    private final static String MSG_SEND = "https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2";
    /**
     * 获取部门列表
     */
    private final static String SUB_LIST = "https://oapi.dingtalk.com/topapi/v2/department/listsub";
    private final static String LIST_ID = "https://oapi.dingtalk.com/topapi/user/listid";


    /**
     * 获取访问令牌
     *
     * @return {@link String}
     */
    private static String getAccessToken() {
        DingTalkClient client = new DefaultDingTalkClient(GET_TOKEN);
        OapiGettokenRequest req = new OapiGettokenRequest();
        req.setAppkey(APP_KEY);
        req.setAppsecret(APP_SECRET);
        req.setHttpMethod("GET");
        OapiGettokenResponse rsp;
        try {
            rsp = client.execute(req);
        } catch (Exception e) {
            throw new BusinessException("获取token失败");
        }
        return rsp.getAccessToken();
    }


    /**
     * 发送手机通知
     *
     * @param mobile 移动
     * @return {@link R}<{@link ?}>
     */
    @Override
    public R<?> sendMobileNotification(String mobile) {
        String accessToken = getAccessToken();
        if (StringUtils.isBlank(mobile)) {
            throw new BusinessException();
        }
        //电话号码数组
        String[] split = mobile.split(",");
        List<String> userIdList = new ArrayList<>();
        for (String s : split) {
            DingTalkClient mobileClient = new DefaultDingTalkClient(GET_BY_MOBILE);
            OapiV2UserGetbymobileRequest req = new OapiV2UserGetbymobileRequest();
            req.setMobile(s);
            OapiV2UserGetbymobileResponse rsp;
            try {
                rsp = mobileClient.execute(req, accessToken);
            } catch (ApiException e) {
                throw new BusinessException("获取用户信息失败");
            }
            //获取到Urid就是在公司里要发送到那个人的id
            String userid = rsp.getResult().getUserid();
            userIdList.add(userid);

        }
        this.sendMessage(String.join(",", userIdList), false, null);
        return R.ok("消息发送成功");

    }

    @Override
    public R<?> getAllDeptInfo(Long deptId) {
        String accessToken = getAccessToken();
        DingTalkClient client = new DefaultDingTalkClient(SUB_LIST);
        OapiV2DepartmentListsubRequest req = new OapiV2DepartmentListsubRequest();
        OapiV2DepartmentListsubResponse rsp;
        req.setDeptId(deptId);
        req.setLanguage("zh_CN");
        try {
            rsp = client.execute(req, accessToken);
        } catch (ApiException e) {
            throw new BusinessException("查询部门信息出错");
        }
        return R.ok(rsp.getResult());
    }

    @Override
    public R<?> getListId(Long deptId) {
        DingTalkClient client = new DefaultDingTalkClient(LIST_ID);
        OapiUserListidRequest req = new OapiUserListidRequest();
        req.setDeptId(1L);
        OapiUserListidResponse rsp;
        try {
            rsp = client.execute(req, getAccessToken());
        } catch (ApiException e) {
            throw new BusinessException("");
        }
        return R.ok(rsp.getResult());
    }


    /**
     * 发送消息
     *
     * @param userList   用户列表
     * @param allUser    所有用户
     * @param deptIdList 部门id列表
     */
    private void sendMessage(String userList, Boolean allUser, String deptIdList) {

        DingTalkClient msgSendClient = new DefaultDingTalkClient(MSG_SEND);
        OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        msg.setActionCard(new OapiMessageCorpconversationAsyncsendV2Request.ActionCard());
        msg.getActionCard().setTitle("xxx123411111");
        msg.getActionCard().setMarkdown("### 测试123111");
        msg.getActionCard().setSingleTitle("测试测试");
        msg.getActionCard().setSingleUrl("https://www.dingtalk.com");
        msg.setMsgtype("action_card");
        request.setMsg(msg);
        request.setUseridList(userList);
        request.setAgentId(AGENT_ID);
        //一天只能三次全员通知
        request.setToAllUser(allUser);
        request.setDeptIdList(deptIdList);
        LOGGER.info("获取发送通知消息体和获取发送通知人完成");
        OapiMessageCorpconversationAsyncsendV2Response response;
        try {
            response = msgSendClient.execute(request, getAccessToken());
        } catch (ApiException e) {
            throw new BusinessException("消息发送失败" + e);
        }
        if (Objects.isNull(response) || !response.isSuccess()) {
            LOGGER.info("发送消息失败");
            R.fail("发送消息失败");
            return;
        }

        LOGGER.info("消息任务ID" + response.getTaskId());
        System.out.println(response.getTaskId());
        R.ok();
    }

}
