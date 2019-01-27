package stao.BMS.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class HelloWorldController {

    @RequestMapping("/hello")
    public String hello(@RequestParam("str") String hh){
        return "hello"+hh;
    }
}
