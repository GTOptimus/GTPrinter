package com.gt.qr386;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * 类描述：
 * 创建人：yangxing
 * 创建时间：2018/6/14 12:25
 * 修改人：yangxing
 * 修改时间：2018/6/14 12:25
 * 修改备注：
 */

public class GTPrinterInterface {

    public static void print(Context context,GTExpressBean gtExpressBean){
        if (gtExpressBean == null){
            Toast.makeText(context,"打印信息不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(gtExpressBean.getGt_ExCompanyName())) {
            Toast.makeText(context,"快递公司名称不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(gtExpressBean.getGt_ExTripleCode())) {
            Toast.makeText(context,"大头笔不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(gtExpressBean.getGt_ExReceiverName())) {
            Toast.makeText(context,"收件人姓名不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(gtExpressBean.getGt_ExReceiverPhone())) {
            Toast.makeText(context,"收件人手机号不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(gtExpressBean.getGt_ExReceiverAddressContent())) {
            Toast.makeText(context,"收件人详细地址不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(gtExpressBean.getGt_ExSenderName())) {
            Toast.makeText(context,"发件人姓名不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(gtExpressBean.getGt_ExSenderPhone())) {
            Toast.makeText(context,"发件人手机号不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(gtExpressBean.getGt_ExSenderAddressContent())) {
            Toast.makeText(context,"发件人详细地址不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(gtExpressBean.getGt_ExpressCode())) {
            Toast.makeText(context,"单号不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(context,DeviceListActivity.class);
        intent.putExtra("gtExpressBean",gtExpressBean);
        context.startActivity(intent);
    }
}
