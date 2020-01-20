package com.example.mybatisdemo1.controller;

import com.example.mybatisdemo1.domin.UserInfo;
import com.example.mybatisdemo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/myself")
public class BatchSelectController {

    @Autowired
    private UserService userService;

    //批量查询返回结果为list和map
    @PostMapping(value = "/batchselect")
    public List<UserInfo> batchGetUser(@RequestParam(value = "userInfoId") List<Long> list){
        return userService.batchSelect(list);
    }

    @RequestMapping(value = ("mapBatchResult"))
    Map<Long, UserInfo> batchMapResult(@RequestParam(value = "userInfoId") List<Long> list){
        return userService.batchSelectReturnMap(list);
    }

    //批量插入
    @PostMapping(value = "/batchInsert")
    public Integer  batchInsert(@RequestBody List<UserInfo> list){
        return userService.batchInsert(list);
    }

    //批量更新
    @PostMapping(value = "/batchUpdate1")//伪批量更新，逐条更新
    public String  batchUpdate1(@RequestBody List<UserInfo> list){
        int update=userService.batchUpdate(list);
        if (update!=0){
            return "更新了"+update+"条数据";
        }else {
            return "更新失败";
        }
    }

    @PostMapping(value = "/batchUpdate2")//真批量更新，单一字段（age）更新
    public String  batchUpdate2(@RequestBody List<UserInfo> list) {
        int update = userService.batchUpdateOneField(list);
        if (update != 0) {
            return "更新了" + update + "条数据";
        } else {
            return "更新失败";
        }
    }

    @PostMapping(value = "/batchUpdate3")//真批量更新，多字段更新
    public String  batchUpdate3(@RequestBody List<UserInfo> list) {
        int update = userService.batchUpdateMultiField(list);
        if (update != 0) {
            return "更新了" + update + "条数据";
        } else {
            return "更新失败";
        }
    }

    //批量删除
    @GetMapping(value = "/batchDelete")
    public String batchDelete(@RequestParam(value = "userInfoId") List<Long> list){
        int delete = userService.batchDelete(list);
        if (delete != 0) {
            return "更新了" + delete + "条数据";
        } else {
            return "更新失败";
        }
    }

}
