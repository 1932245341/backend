package org.example.server.controller.user;

import org.example.common.context.BaseContext;
import org.example.common.result.Result;
import org.example.pojo.entity.Address;
import org.example.server.server.interfa.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * 查询当前登录用户的所有地址信息
     *
     * @return
     */
    @GetMapping("/list")
    public Result<List<Address>> list() {
        Address address = new Address();
        address.setUserId(BaseContext.getCurrentId());
        List<Address> list = addressService.list(address);
        return Result.success(list);
    }

    /**
     * 新增地址
     *
     * @param address
     * @return
     */
    @PostMapping
    public Result<String> save(@RequestBody Address address) {
        addressService.save(address);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Address> getById(@PathVariable Long id) {
        Address address = addressService.getById(id);
        return Result.success(address);
    }

    /**
     * 根据id修改地址
     *
     * @param address
     * @return
     */
    @PutMapping
    public Result<String> update(@RequestBody Address address) {
        addressService.update(address);
        return Result.success();
    }

    /**
     * 设置默认地址
     *
     * @param address
     * @return
     */
    @PutMapping("/default")
    public Result<String> setDefault(@RequestBody Address address) {
        addressService.setDefault(address);
        return Result.success();
    }

    /**
     * 根据id删除地址
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public Result<String> deleteById(Long id) {
        addressService.deleteById(id);
        return Result.success();
    }

    /**
     * 查询默认地址
     */
    @GetMapping("default")
    public Result<Address> getDefault() {
        // SQL:select * from address where user_id = ? and is_default = 1
        Address address = new Address();
        address.setIsDefault(1);
        address.setUserId(BaseContext.getCurrentId());
        List<Address> list = addressService.list(address);

        if (list != null && list.size() == 1) {
            return Result.success(list.get(0));
        }

        return Result.error("没有查询到默认地址");
    }

}
