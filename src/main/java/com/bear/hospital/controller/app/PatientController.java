package com.bear.hospital.controller.app;


import com.bear.hospital.pojo.Patient;
import com.bear.hospital.service.PatientService;
import com.bear.hospital.utils.JwtUtil;
import com.bear.hospital.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController("userController")
@RequestMapping("/user")
public class PatientController {


    @Autowired
    private PatientService patientService;


    /**
     * 登录数据验证
     */
    @PostMapping( "/login")
    public ResponseData login(@RequestParam int pId, @RequestParam String pPassword) {
        Patient patient = this.patientService.login(pId, pPassword);
        if (patient != null) {
            Map<String,String> map = new HashMap<>();
            map.put("pName", patient.getPName());
            map.put("pId", String.valueOf(patient.getPId()));
//            map.put("pCard", patient.getPCard());

            String token = JwtUtil.getToken(map);
            map.put("token", token);
            //response.setHeader("token", token);
            return ResponseData.success("登录成功", map);
        } else {
            return ResponseData.fail("登录失败，密码或账号错误");
        }
    }
}
