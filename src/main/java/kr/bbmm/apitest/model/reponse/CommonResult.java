package kr.bbmm.apitest.model.reponse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResult {

    @ApiModelProperty(value = "Response sucess : true/false")
    private boolean success;

    @ApiModelProperty(value = "Response code : >=0 ok, < 0 worng")
    private int code;

    @ApiModelProperty(value = "response message")
    private String msg;
}
