package kr.bbmm.apitest.controllers.exception;

import kr.bbmm.apitest.advice.exception.CAuthenticationEntryPointException;
import kr.bbmm.apitest.model.reponse.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {

    @GetMapping(value = "/entrypoint")
    public CommonResult entrypointException() {
        throw new CAuthenticationEntryPointException();
    }

    @GetMapping(value = "/accessdenied")
    public CommonResult accessDeniedException() {
        throw new AccessDeniedException("");
    }
}
