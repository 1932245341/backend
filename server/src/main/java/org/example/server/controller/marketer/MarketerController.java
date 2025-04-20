package org.example.server.controller.marketer;

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
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/marketer")
public class MarketerController {

   @Autowired
    private MarketerService marketerService;

    @Autowired
    private JwtProperties jwtProperties;


    /**
     * 登录
     * @param marketerLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<MarketerLoginVO> login(@RequestBody MarketerLoginDTO marketerLoginDTO) {
        log.info("商家登录：{}", marketerLoginDTO);

        Marketer marketer = marketerService.login(marketerLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.MARKER_ID, marketer.getId());

        //构造令牌
        String token = JwtUtils.createJWT(
                //签名所用的密匙，令牌类型 it
                //jwtProperties是配置属性类 配置了签名算法的类型，过期时间
                jwtProperties.getMarketerSecretKey(),
                //过期时间
                jwtProperties.getMarketerTtl(),
                //令牌中间的值
                claims);

        //生成对象响应给前端
        MarketerLoginVO marketerLoginVO = MarketerLoginVO.builder().id(marketer.getId()).userName(marketer.getUsername()).name(marketer.getName()).token(token).build();

        return Result.success(marketerLoginVO);
    }

    @PostMapping("/add")
    public Result addMarketer(@RequestBody Marketer marketer) {
        log.info("申请成为商家：{}", marketer);
        marketerService.addMarketer(marketer);
        return Result.success();
    }

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
}
