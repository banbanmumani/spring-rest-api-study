package kr.bbmm.apitest.controllers.v1;

import io.swagger.annotations.*;
import kr.bbmm.apitest.advice.exception.CUserNotFoundException;
import kr.bbmm.apitest.domain.User;
import kr.bbmm.apitest.model.reponse.CommonResult;
import kr.bbmm.apitest.model.reponse.ListResult;
import kr.bbmm.apitest.model.reponse.SingleResult;
import kr.bbmm.apitest.repo.UserJpaRepo;
import kr.bbmm.apitest.services.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"1. User"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class UserController {

    private final UserJpaRepo userJpaRepo;
    private final ResponseService responseService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "access_token after log in success", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "get all user info", notes = "get all user info")
    @GetMapping(value = "/users")
    public ListResult<User> findAllUsers() {
        return responseService.getListResult(userJpaRepo.findAll());
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "access_token after log in success", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "get single user info", notes = "get single user info by Id")
    @GetMapping(value = "/user/{msrl}")
    public SingleResult<User> findUserById(
            @ApiParam(value = "userId", required = true) @PathVariable(value = "msrl") Long msrl,
            @ApiParam(value = "language", defaultValue = "ko") @RequestParam String lang
    ) {
        return responseService.getSingleResult(userJpaRepo.findById(msrl).orElseThrow(CUserNotFoundException::new));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "access_token after log in success", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "add user info", notes = "add user info")
    @PostMapping(value = "/user")
    public SingleResult<User> save(
            @ApiParam(value = "user id", required = true) @RequestParam String uid,
            @ApiParam(value = "user name", required = true) @RequestParam String name
    ) {
        User user = User.builder()
                .uid(uid)
                .name(name)
                .build();
        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "access_token after log in success", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "update user info", notes = "update user info")
    @PutMapping(value = "/user")
    public SingleResult<User> modify(
            @ApiParam(value = "user number", required = true) @RequestParam long msrl,
            @ApiParam(value = "user id", required = true) @RequestParam String uid,
            @ApiParam(value = "user name", required = true) @RequestParam String name
    ) {
        User user = User.builder()
                .msrl(msrl)
                .uid(uid)
                .name(name)
                .build();
        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "access_token after log in success", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "delete user info", notes = "delete user info")
    @DeleteMapping(value = "/user/{msrl}")
    public CommonResult delete(
            @ApiParam(value = "user number", required = true) @PathVariable long msrl
    ) {
        userJpaRepo.deleteById(msrl);
        return responseService.getSuccessResult();
    }
}
