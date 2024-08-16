package com.bear.hospital.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.bear.hospital.pojo.Doctor;
import com.bear.hospital.pojo.Patient;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrdersVo {
    @TableId(value = "o_id")
    @JsonProperty("oId")
    private int oId;
    @JsonProperty("oDrug")
    private String oDrug;
    @JsonProperty("oCheck")
    private String oCheck;
    @JsonProperty("oTotalPrice")
    private Double oTotalPrice;

    public OrdersVo() {
    }

    public OrdersVo(int oId, String oDrug, String oCheck, Double oTotalPrice) {
        this.oId = oId;
        this.oDrug = oDrug;
        this.oCheck = oCheck;
        this.oTotalPrice = oTotalPrice;
    }

    public String getoDrug() {
        return oDrug;
    }

    public void setoDrug(String oDrug) {
        this.oDrug = oDrug;
    }

    public String getoCheck() {
        return oCheck;
    }

    public void setoCheck(String oCheck) {
        this.oCheck = oCheck;
    }

    public Double getoTotalPrice() {
        return oTotalPrice;
    }

    public void setoTotalPrice(Double oTotalPrice) {
        this.oTotalPrice = oTotalPrice;
    }

    @Override
    public String toString() {
        return "OrdersVo{" +
                "oId=" + oId +
                ", oDrug='" + oDrug + '\'' +
                ", oCheck='" + oCheck + '\'' +
                ", oTotalPrice=" + oTotalPrice +
                '}';
    }
}
