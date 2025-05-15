package org.example.server.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.example.common.result.Result;
import org.example.pojo.entity.Marketer;
import org.example.server.server.interfa.MarketerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("userMarketerController")
@RequestMapping("/user/marketer")
public class MarketerController {

    @Autowired
    private MarketerService marketerService;

    @PostMapping("/add")
    public Result addMarketer(@RequestBody Marketer marketer) {
        log.info("申请成为商家：{}", marketer);
        marketerService.addMarketer(marketer);
        return Result.success();
    }
}
