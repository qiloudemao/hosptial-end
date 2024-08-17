package com.bear.hospital.controller.app;


import cn.hutool.core.bean.BeanUtil;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bear.hospital.pojo.Orders;
import com.bear.hospital.pojo.Patient;
import com.bear.hospital.pojo.vo.PatientVo;
import com.bear.hospital.service.DoctorService;
import com.bear.hospital.service.OrderService;
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

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private OrderService orderService;


    /**
     * 登录数据验证
     */
    @PostMapping( "/login")
    public ResponseData login(int pId, String pPassword) {
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

    /**
     *
     * @param token 登录token
     * @return 用户id
     */
    @GetMapping("getId")
    public ResponseData getIdByToken(String token) {
        DecodedJWT verify = JwtUtil.verify(token);
        String pId = verify.getClaim("pId").asString();
        return ResponseData.success("获取用户id成功", pId);
    }

    /**
     * 根据患者id查询患者信息
     */
    @GetMapping("findPatientById")
    public ResponseData findPatientById(@RequestParam int pId){
        return ResponseData.success("返回患者信息成功！",
                BeanUtil.copyProperties(this.patientService.findPatientById(pId), PatientVo.class));
    }

    /**
     * 根据科室查询所有医生信息
     */
    @GetMapping("findDoctorBySection")
    public ResponseData findDoctorBySection(String dSection){
        return ResponseData.success("根据科室查询所有医生信息成功", this.doctorService.findDoctorBySection(dSection));
    }

    /**
     * 增加挂号信息
     */
    @GetMapping("addOrder")
    public ResponseData addOrder(Orders order,String arId){
        System.out.println(arId);
        if (this.orderService.addOrder(order, arId))
            return ResponseData.success("插入挂号信息成功");
        return ResponseData.fail("插入挂号信息失败");
    }

    /**
     * 根据pId查询挂号
     */
    @GetMapping("findOrderByPid")
    public ResponseData findOrderByPid(@RequestParam(value = "pId") int pId){
        return ResponseData.success("返回挂号信息成功", this.orderService.findOrderByPid(pId)) ;
    }

    /**
     * 查询用户未缴费信息
     * @param pId
     * @return
     */
//    @GetMapping("findUnpay")
//    public ResponseData findUnpay(@RequestParam Integer pId) {
//        return orderService.findUpay(pId);
//    }

    /**
     * 用户进行缴费
     * @param oId
     * @return boolean
     */
    @GetMapping("pay")
    public ResponseData pay(Integer oId) {
        if (this.orderService.updatePrice(oId))
            return ResponseData.success("根据id设置缴费状态成功");
        return ResponseData.success("根据id设置缴费状态失败");
    }

    @GetMapping("/findUnpay")
    public ResponseData findPaymentInfo(@RequestParam(name = "pId") int pId){
        int state=this.orderService.updatePrice(pId)==true? 200:400;
        return ResponseData.success("缴费成功",state,this.orderService.findPaymentInfo(pId));
    }

}
