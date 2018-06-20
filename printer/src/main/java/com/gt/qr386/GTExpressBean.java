package com.gt.qr386;

import java.io.Serializable;

/**
 * 类描述：打印所需参数model
 * 创建人：yangxing
 * 创建时间：2018/6/14 12:26
 * 修改人：yangxing
 * 修改时间：2018/6/14 12:26
 * 修改备注：
 */

public class GTExpressBean implements Serializable {
    //快递公司名称
    private String gt_ExCompanyName;
    //三段码(大头笔)
    private String gt_ExTripleCode;

    //收件人姓名
    private String gt_ExReceiverName;
    //收件人手机号
    private String gt_ExReceiverPhone;
    //收件人省地址
    private String gt_ExReceiverProvinceName;
    //收件人市地址
    private String gt_ExReceiverCityName;
    //收件人区地址
    private String gt_ExReceiverDistrictName;
    //收件人详细地址
    private String gt_ExReceiverAddressContent;

    //寄件人姓名
    private String gt_ExSenderName;
    //寄件人手机号
    private String gt_ExSenderPhone;
    //寄件人省地址
    private String gt_ExSenderProvinceName;
    //寄件人市地址
    private String gt_ExSenderCityName;
    //寄件人区地址
    private String gt_ExSenderDistrictName;
    //寄件人详细地址
    private String gt_ExSenderAddressContent;

    //快递单号
    private String gt_ExpressCode;

    //一下为选填项
    private String gt_ExExtraQRCode;//二维码

    public String getGt_ExCompanyName() {
        return gt_ExCompanyName;
    }

    public void setGt_ExCompanyName(String gt_ExCompanyName) {
        this.gt_ExCompanyName = gt_ExCompanyName;
    }

    public String getGt_ExTripleCode() {
        return gt_ExTripleCode;
    }

    public void setGt_ExTripleCode(String gt_ExTripleCode) {
        this.gt_ExTripleCode = gt_ExTripleCode;
    }

    public String getGt_ExReceiverName() {
        return gt_ExReceiverName;
    }

    public void setGt_ExReceiverName(String gt_ExReceiverName) {
        this.gt_ExReceiverName = gt_ExReceiverName;
    }

    public String getGt_ExReceiverPhone() {
        return gt_ExReceiverPhone;
    }

    public void setGt_ExReceiverPhone(String gt_ExReceiverPhone) {
        this.gt_ExReceiverPhone = gt_ExReceiverPhone;
    }

    public String getGt_ExReceiverProvinceName() {
        return gt_ExReceiverProvinceName;
    }

    public void setGt_ExReceiverProvinceName(String gt_ExReceiverProvinceName) {
        this.gt_ExReceiverProvinceName = gt_ExReceiverProvinceName;
    }

    public String getGt_ExReceiverCityName() {
        return gt_ExReceiverCityName;
    }

    public void setGt_ExReceiverCityName(String gt_ExReceiverCityName) {
        this.gt_ExReceiverCityName = gt_ExReceiverCityName;
    }

    public String getGt_ExReceiverDistrictName() {
        return gt_ExReceiverDistrictName;
    }

    public void setGt_ExReceiverDistrictName(String gt_ExReceiverDistrictName) {
        this.gt_ExReceiverDistrictName = gt_ExReceiverDistrictName;
    }

    public String getGt_ExReceiverAddressContent() {
        return gt_ExReceiverAddressContent;
    }

    public void setGt_ExReceiverAddressContent(String gt_ExReceiverAddressContent) {
        this.gt_ExReceiverAddressContent = gt_ExReceiverAddressContent;
    }

    public String getGt_ExSenderName() {
        return gt_ExSenderName;
    }

    public void setGt_ExSenderName(String gt_ExSenderName) {
        this.gt_ExSenderName = gt_ExSenderName;
    }

    public String getGt_ExSenderPhone() {
        return gt_ExSenderPhone;
    }

    public void setGt_ExSenderPhone(String gt_ExSenderPhone) {
        this.gt_ExSenderPhone = gt_ExSenderPhone;
    }

    public String getGt_ExSenderProvinceName() {
        return gt_ExSenderProvinceName;
    }

    public void setGt_ExSenderProvinceName(String gt_ExSenderProvinceName) {
        this.gt_ExSenderProvinceName = gt_ExSenderProvinceName;
    }

    public String getGt_ExSenderCityName() {
        return gt_ExSenderCityName;
    }

    public void setGt_ExSenderCityName(String gt_ExSenderCityName) {
        this.gt_ExSenderCityName = gt_ExSenderCityName;
    }

    public String getGt_ExSenderDistrictName() {
        return gt_ExSenderDistrictName;
    }

    public void setGt_ExSenderDistrictName(String gt_ExSenderDistrictName) {
        this.gt_ExSenderDistrictName = gt_ExSenderDistrictName;
    }

    public String getGt_ExSenderAddressContent() {
        return gt_ExSenderAddressContent;
    }

    public void setGt_ExSenderAddressContent(String gt_ExSenderAddressContent) {
        this.gt_ExSenderAddressContent = gt_ExSenderAddressContent;
    }

    public String getGt_ExpressCode() {
        return gt_ExpressCode;
    }

    public void setGt_ExpressCode(String gt_ExpressCode) {
        this.gt_ExpressCode = gt_ExpressCode;
    }

    public String getGt_ExExtraQRCode() {
        return gt_ExExtraQRCode;
    }

    public void setGt_ExExtraQRCode(String gt_ExExtraQRCode) {
        this.gt_ExExtraQRCode = gt_ExExtraQRCode;
    }
}


