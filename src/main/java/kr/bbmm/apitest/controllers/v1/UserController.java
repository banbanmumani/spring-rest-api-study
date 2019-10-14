package kr.bbmm.apitest.controllers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.bbmm.apitest.domain.User;
import kr.bbmm.apitest.repo.UserJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"1. User"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class UserController {

    private final UserJpaRepo userJpaRepo;

    @ApiOperation(value = "get user info", notes = "get all user info")
    @GetMapping(value = "/user")
    public List<User> findAllUsers() {
        return userJpaRepo.findAll();
    }

    @ApiOperation(value = "add user info", notes = "add user info")
    @PostMapping(value = "/user")
    public User save(
            @ApiParam(value = "user id", required = true) @RequestParam String uid,
            @ApiParam(value = "user name", required = true) @RequestParam String name
    ) {
        User user = User.builder()
                .uid(uid)
                .name(name)
                .build();
        return userJpaRepo.save(user);
    }
}
