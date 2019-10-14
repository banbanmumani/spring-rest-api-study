package kr.bbmm.apitest.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    /*
    1. Print Hello world on screen
    */
    @GetMapping(value = "/helloworld/string")
    @ResponseBody
    public String helloWorldString() {
        return "hello world";
    }

    /*
    2. print {message: "hello world"}
    */
    @GetMapping(value = "/helloworld/json")
    @ResponseBody
    public Hello helloWorldJson() {
        Hello hello = new Hello();
        hello.message = "hello world";
        return hello;
    }

    /*
    3. print contents of helloworld.ftl file
    */
    @GetMapping(value = "/helloworld/page")
    public String helloworld() {
        return "helloworld";
    }

    @Getter
    @Setter
    public static class Hello {
        private String message = "";
    }
}
