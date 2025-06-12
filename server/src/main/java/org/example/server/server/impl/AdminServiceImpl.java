package org.example.server.server.impl;

import org.example.common.constant.MessageConstant;
import org.example.common.exception.PasswordErrorException;
import org.example.pojo.entity.Admin;
import org.example.pojo.dto.AdminLoginDTO;
import org.example.pojo.vo.dataVO;
import org.example.server.mapper.AdminMapper;
import org.example.server.server.interfa.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.example.common.exception.AccountNotFoundException;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 管理员登录
     *
     * @param adminLoginDTO
     * @return
     */
    public Admin login(AdminLoginDTO adminLoginDTO) {
        String username = adminLoginDTO.getUsername();
        String password = adminLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Admin admin = adminMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (admin == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        // TODO 后期需要进行md5加密，然后再进行比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(admin.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        //3、返回实体对象
        return admin;
    }


    public dataVO getData() {
        dataVO dataVO = new dataVO();
        dataVO.setUserCount(adminMapper.getUserCount());
        dataVO.setSpecialtyTotal(adminMapper.sumSpecialtyTotal());
        dataVO.setScenicTotal(adminMapper.sumScenicTotal());
        dataVO.setRestaurantTotal(adminMapper.sumRestaurantTotal());
        dataVO.setHotelTotal(adminMapper.sumHotelTotal());
        return dataVO;
    }



}
