package org.example.server.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.example.common.constant.JwtClaimsConstant;
import org.example.common.properties.JwtProperties;
import org.example.common.result.PageResult;
import org.example.common.result.Result;
import org.example.common.utils.JwtUtils;
import org.example.pojo.dto.MarketerLoginDTO;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Marketer;
import org.example.pojo.vo.MarketerLoginVO;
import org.example.server.server.interfa.MarketerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController("adminmarketer")
@RequestMapping("/admin/marketer")
public class MarketerController {

   @Autowired
    private MarketerService marketerService;



   @GetMapping("/page")
    public Result<PageResult> page(PageQueryDTO pageQueryDTO) {
        log.info("分页查询商家：{}", pageQueryDTO);
        PageResult pageResult = marketerService.queryPage(pageQueryDTO);
        return Result.success(pageResult);
    }

    @PutMapping("/update")
    public Result<Marketer> update(@RequestBody Marketer marketer) {
        log.info("修改商家信息：{}", marketer);
        marketerService.updateMarketer(marketer);
        return Result.success();
    }

    /**
     * 根据id删除商家
     * @param id
     * @return
     */
    @DeleteMapping
    public Result<String> deleteById(Integer id) {
        log.info("删除商家：{}", id);
        marketerService.deleteMarketer(id);
        return Result.success();
    }

    //查看申请中的商家
    @GetMapping("/application")
    public Result<List<Marketer>> qureyApplication() {
        log.info("查看申请中的商家");
        List<Marketer> list = marketerService.qureyApplication();
        return Result.success(list);
    }
}
