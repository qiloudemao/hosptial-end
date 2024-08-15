package com.bear.hospital.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PatientVo {

    @TableId(value = "p_id")
    @JsonProperty("pId")
    private int pId;
    @JsonProperty("pName")
    private String pName;
    @JsonProperty("pGender")
    private String pGender;
    @JsonProperty("pCard")
    private String pCard;
    @JsonProperty("pEmail")
    private String pEmail;
    @JsonProperty("pPhone")
    private String pPhone;
    @JsonProperty("pState")
    private Integer pState;
    @JsonProperty("pBirthday")
    private String pBirthday;
    @JsonProperty("pAge")
    private Integer pAge;
    @JsonProperty("pType")
    private String pType;

    public PatientVo() {
    }

    public PatientVo(int pId, String pName, String pGender, String pCard, String pEmail, String pPhone, Integer pState, String pBirthday, Integer pAge, String pType) {
        this.pId = pId;
        this.pName = pName;
        this.pGender = pGender;
        this.pCard = pCard;
        this.pEmail = pEmail;
        this.pPhone = pPhone;
        this.pState = pState;
        this.pBirthday = pBirthday;
        this.pAge = pAge;
        this.pType = pType;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpGender() {
        return pGender;
    }

    public void setpGender(String pGender) {
        this.pGender = pGender;
    }

    public String getpCard() {
        return pCard;
    }

    public void setpCard(String pCard) {
        this.pCard = pCard;
    }

    public String getpEmail() {
        return pEmail;
    }

    public void setpEmail(String pEmail) {
        this.pEmail = pEmail;
    }

    public String getpPhone() {
        return pPhone;
    }

    public void setpPhone(String pPhone) {
        this.pPhone = pPhone;
    }

    public Integer getpState() {
        return pState;
    }

    public void setpState(Integer pState) {
        this.pState = pState;
    }

    public String getpBirthday() {
        return pBirthday;
    }

    public void setpBirthday(String pBirthday) {
        this.pBirthday = pBirthday;
    }

    public Integer getpAge() {
        return pAge;
    }

    public void setpAge(Integer pAge) {
        this.pAge = pAge;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    @Override
    public String toString() {
        return "PatientVo{" +
                "pId=" + pId +
                ", pName='" + pName + '\'' +
                ", pGender='" + pGender + '\'' +
                ", pCard='" + pCard + '\'' +
                ", pEmail='" + pEmail + '\'' +
                ", pPhone='" + pPhone + '\'' +
                ", pState=" + pState +
                ", pBirthday='" + pBirthday + '\'' +
                ", pAge=" + pAge +
                ", pType='" + pType + '\'' +
                '}';
    }
}
