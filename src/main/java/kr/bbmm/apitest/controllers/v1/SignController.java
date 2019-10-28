package kr.bbmm.apitest.controllers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.bbmm.apitest.advice.exception.CEmailSigninFailedException;
import kr.bbmm.apitest.config.security.JwtTokenProvider;
import kr.bbmm.apitest.domain.User;
import kr.bbmm.apitest.model.reponse.CommonResult;
import kr.bbmm.apitest.model.reponse.SingleResult;
import kr.bbmm.apitest.repo.UserJpaRepo;
import kr.bbmm.apitest.services.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Api(tags = {"1. Sign"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class SignController {

    private final UserJpaRepo userJpaRepo;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;

    @ApiOperation(value = "log in", notes = "user log in by email")
    @GetMapping(value = "/signin")
    public SingleResult<String> signin(
            @ApiParam(value = "user id: email", required = true) @RequestParam String id,
            @ApiParam(value = "password", required = true) @RequestParam String password
    ) {
        User user = userJpaRepo.findByUid(id).orElseThrow(CEmailSigninFailedException::new);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new CEmailSigninFailedException();
        }

        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getMsrl()), user.getRoles()));
    }

    @ApiOperation(value = "sign up", notes = "user sign up")
    @GetMapping(value = "/signup")
    public CommonResult signup(
            @ApiParam(value = "user id: email", required = true) @RequestParam String id,
            @ApiParam(value = "password", required = true) @RequestParam String password,
            @ApiParam(value = "name", required = true) @RequestParam String name
    ) {

        userJpaRepo.save(User.builder()
                .uid(id)
                .password(passwordEncoder.encode(password))
                .name(name)
                .roles(Collections.singletonList("ROLE_USER"))
                .build());


        return responseService.getSuccessResult();
    }
}
