package com.tcgl.gateway.handler;

import java.io.IOException;

import com.tcgl.gateway.service.ValidateCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.tcgl.common.core.exception.CaptchaException;
import com.tcgl.common.core.web.domain.AjaxResult;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * 验证码获取
 *
 * @author tcgl
 */
@Component
public class ValidateCodeHandler implements HandlerFunction<ServerResponse> {
    @Resource
    private ValidateCodeService validateCodeService;

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        AjaxResult ajax;
        try {
            ajax = validateCodeService.createCaptcha();
        } catch (CaptchaException | IOException e) {
            return Mono.error(e);
        }
        return ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue(ajax));
    }
}
