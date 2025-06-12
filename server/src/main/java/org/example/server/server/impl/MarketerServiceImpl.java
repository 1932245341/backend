package org.example.server.server.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.common.constant.MessageConstant;
import org.example.common.context.BaseContext;
import org.example.common.exception.AccountNotFoundException;
import org.example.common.exception.PasswordErrorException;

import org.example.common.result.PageResult;
import org.example.pojo.dto.MarketerLoginDTO;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Marketer;
import org.example.pojo.entity.Parking;
import org.example.pojo.vo.dataVO;
import org.example.server.mapper.MarketerMapper;
import org.example.server.server.interfa.MarketerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MarketerServiceImpl implements MarketerService {

    @Autowired
    private MarketerMapper marketerMapper;

    /**
     * 商家登录
     *
     * @param marketerLoginDTO
     * @return
     */
    public Marketer login(MarketerLoginDTO marketerLoginDTO) {
        String username = marketerLoginDTO.getUsername();
        String password = marketerLoginDTO.getPassword();
        //1、根据用户名查询数据库中的数据
        Marketer marketer = marketerMapper.getByUsername(username);
        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (marketer == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        //密码比对
        // TODO 后期需要进行md5加密，然后再进行比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(marketer.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        //3、返回实体对象
        return marketer;
    }
    @Override
    public void addMarketer(Marketer marketer) {
        marketer.setPassword(DigestUtils.md5DigestAsHex(marketer.getPassword().getBytes()));//密码md5加密
        marketer.setCreateTime(LocalDateTime.now());
        marketerMapper.insert(marketer);
    }
    @Override
    public void deleteMarketer(int id) {
        marketerMapper.deleteById(id);
    }
    @Override
    public void updateMarketer(Marketer marketer) {
        marketerMapper.update(marketer);
    }

    @Override
    public List<Marketer> qureyApplication() {
        return marketerMapper.qureyApplication();
    }

    /**
     * 管理员端分页查询商家
     * @param pageQueryDTO
     */
    @Override
    public PageResult queryPage(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getPageSize());
        Page<Marketer> page = marketerMapper.pageQuery(pageQueryDTO);
        long total = page.getTotal();
        List<Marketer> records = page.getResult();
        return new PageResult(total,records);
    }

    public dataVO getData() {
        dataVO dataVO = new dataVO();
        dataVO.setMarketerrestaurantTotal(marketerMapper.sumRestaurantTotal(BaseContext.getCurrentId()));
        dataVO.setMarketerhotelTotal(marketerMapper.sumHotelTotal(BaseContext.getCurrentId()));
        return dataVO;
    }
}
