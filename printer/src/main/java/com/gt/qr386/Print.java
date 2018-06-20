package com.gt.qr386;

import android.text.TextUtils;

import com.qr.printer.Printer;

/**
 * 类描述：打印
 * 创建人：yangxing
 * 创建时间：2018/6/14 16:53
 * 修改人：yangxing
 * 修改时间：2018/6/14 16:53
 * 修改备注：
 */

public class Print {
    public static void Lable(Printer iPrinter,GTExpressBean gtExpressBean){
        iPrinter.pageSetup(586, 1610);
        //第一联

        iPrinter.drawLine(2, 0, 120, 568+16, 120,false);//第一联横线1

        iPrinter.drawLine(2, 0, 220, 568+16, 220,false);//第一联横线2

        iPrinter.drawLine(2, 420, 260, 568+16, 260,false);//第一联横线3

        iPrinter.drawLine(2, 0, 330, 420, 330,false);//第一联横线4

        iPrinter.drawLine(2, 0, 440, 568+16, 440,false);//第一联横线5

        iPrinter.drawLine(2, 0, 560, 568+16, 560,false);//第一联横线6

        iPrinter.drawLine(2, 0, 750, 568+16, 750,false);//第一联横线7

        iPrinter.drawLine(2, 40, 220, 40, 440,false);//第一联竖线1，从左到右

        iPrinter.drawLine(2, 420, 220, 420, 440,false);//第一联竖线2，从左到右

        iPrinter.drawLine(2, 420, 560, 420, 750,false);//第一联竖线3，从左到右

        //快递公司名字+logo(LOGO未画)
        iPrinter.drawText(2, 5, gtExpressBean.getGt_ExCompanyName(), 6,0, 0, false, false);
        //三段码
        iPrinter.drawText(2+20, 128, gtExpressBean.getGt_ExTripleCode(), 6,0, 0, false, false);

        //服务信息
        iPrinter.drawText(428,228,"服务信息",2,0,  1,false,false);
        //收件人
        iPrinter.drawText(4,230,32,120,"收件人",2,0,  1,false,false);
        //收件人姓名＋电话，最终实施时请用变量替换
        iPrinter.drawText(2+4+32+8,225,375,28,gtExpressBean.getGt_ExReceiverName()+"   "+gtExpressBean.getGt_ExReceiverPhone(),3,0, 1,false,false);
        //收件地址 ，最终实施时请用变量替换
        StringBuilder stringBuilderReceiver = new StringBuilder();
        if (!TextUtils.isEmpty(gtExpressBean.getGt_ExReceiverProvinceName())){
            stringBuilderReceiver.append(gtExpressBean.getGt_ExReceiverProvinceName()+" ");
            stringBuilderReceiver.append(gtExpressBean.getGt_ExReceiverCityName()+" ");
            stringBuilderReceiver.append(gtExpressBean.getGt_ExReceiverDistrictName()+" ");
        }
        stringBuilderReceiver.append(gtExpressBean.getGt_ExReceiverAddressContent());
        iPrinter.drawText(2+4+32+8,265,375,82,stringBuilderReceiver.toString(),2,0, 0 ,false,false);
        //寄件人
        iPrinter.drawText(4,340,32,96,"寄件人",2,0,  1,false,false);
        //寄件人姓名＋电话，
        iPrinter.drawText(2+4+32+8,333,375,28,gtExpressBean.getGt_ExSenderName()+" "+gtExpressBean.getGt_ExSenderPhone(),3,0, 1,false,false);
        //寄件人地址
        StringBuilder stringBuilderSender = new StringBuilder();
        if (!TextUtils.isEmpty(gtExpressBean.getGt_ExSenderProvinceName())){
            stringBuilderSender.append(gtExpressBean.getGt_ExSenderProvinceName()+" ");
            stringBuilderSender.append(gtExpressBean.getGt_ExSenderCityName()+" ");
            stringBuilderSender.append(gtExpressBean.getGt_ExSenderDistrictName()+" ");
        }
        stringBuilderSender.append(gtExpressBean.getGt_ExSenderAddressContent());
        iPrinter.drawText(2+4+32+8,373,375,82,stringBuilderSender.toString(),2,0,  0,false,false);

        if(gtExpressBean.getGt_ExpressCode().length() <= 12){
            //条码
            iPrinter.drawBarCode(32+100, 445,gtExpressBean.getGt_ExpressCode(), 1,  0, 3, 80);
            //条码字符
            iPrinter.drawText(2+96+76, 525, gtExpressBean.getGt_ExpressCode(), 3,0,  1, false, false);
        }else{
            //条码,18位
            iPrinter.drawBarCode(32, 445,gtExpressBean.getGt_ExpressCode(), 1,  0, 3, 80);
            //条码字符
            iPrinter.drawText(2+96+76-64, 528, gtExpressBean.getGt_ExpressCode(), 3,0,  1, false, false);
        }
//		//签收人
        iPrinter.drawText(20,575,"签收人：",2,0,  0,false,false);
//		//时间
        iPrinter.drawText(20,600,"时间：",2,0,  0,false,false);
//		iPrinter.drawText(20,640,32,96,"时间：",2,0,0,false,false);
        //快递公司二维码
//		iPrinter.drawQrCode(504,563,"egnopiejropgjeor",0,80,80);

        //第一联结束， 高度648
        //第二联 从776开始
        iPrinter.drawLine(2,0, 730+120, 568+16, 730+120,false);//第二联横线1，从左到右

        iPrinter.drawLine(2,0, 840+120+30, 420, 840+120+30,false);//第二联横线2，从左到右

        iPrinter.drawLine(2,0, 950+120+60, 568+16, 950+120+60,false);//第二联横线3，从左到右

        iPrinter.drawLine(2, 40, 730+120, 40, 950+120+60,false);//第二联竖线1，从左到右

        iPrinter.drawLine(2, 420, 730+120,420, 950+120+60,false);//第二联竖线2，从左到右

        //快递公司名字+logo(LOGO未画)
        iPrinter.drawText(8, 656+120, gtExpressBean.getGt_ExCompanyName(), 4,0, 0, false, false);
        if(gtExpressBean.getGt_ExpressCode().length() <= 12){
            //条码
            iPrinter.drawBarCode(265, 654+120,gtExpressBean.getGt_ExpressCode(), 1,  0, 2, 50);
            //条码字符
            iPrinter.drawText(307, 704+120, gtExpressBean.getGt_ExpressCode(), 2,0,  1, false, false);
        }else{
            //条码,18位
            iPrinter.drawBarCode(240, 654+120,gtExpressBean.getGt_ExpressCode(), 1,  0, 2, 50);
            //条码字符
            iPrinter.drawText(318, 704+120, gtExpressBean.getGt_ExpressCode(), 2,0,  1, false, false);
        }
        //收件人
        iPrinter.drawText(4,746+120,32,96,"收件人",2,0,  1,false,false);
        //收件人姓名＋电话，最终实施时请用变量替换
        iPrinter.drawText(2+4+32+8,746+120,375,28,gtExpressBean.getGt_ExReceiverName()+"   "+gtExpressBean.getGt_ExReceiverPhone(),3,0, 1,false,false);
        //收件地址 ，最终实施时请用变量替换
        iPrinter.drawText(2+4+32+8,786+120,375,82,stringBuilderReceiver.toString(),2,0, 0 ,false,false);
        //寄件人
        iPrinter.drawText(4,850+120+30,32,96,"寄件人",2,0,  1,false,false);
        //寄件人姓名＋电话，
        iPrinter.drawText(2+8+32+8,845+120+30,375,28,gtExpressBean.getGt_ExSenderName()+" "+gtExpressBean.getGt_ExSenderPhone(),3,0, 1,false,false);
        //寄件人地址
        iPrinter.drawText(2+8+32+8,894+120+30,375,82,stringBuilderSender.toString(),2,0,  0,false,false);

        //快递公司二维码
        if (!TextUtils.isEmpty(gtExpressBean.getGt_ExExtraQRCode())) {
            iPrinter.drawQrCode(423, 730 + 120 + 35, gtExpressBean.getGt_ExExtraQRCode(), 0, 5, 5);
        }
        //第三联 从1150开始
        iPrinter.drawLine(2,0, 1240, 568+16, 1240,false);//第三联横线1，从左到右

        iPrinter.drawLine(2,0, 1340, 420, 1340,false);//第三联横线2，从左到右

        iPrinter.drawLine(2,0, 1450, 568+16, 1450,false);//第三联横线3，从左到右

        iPrinter.drawLine(2, 40, 1250, 40, 1450,false);//第三联竖线1，从左到右

        iPrinter.drawLine(2, 420, 1250,420, 1450,false);//第三联竖线2，从左到右

        //快递公司名字+logo(LOGO未画)
        iPrinter.drawText(8, 1163, gtExpressBean.getGt_ExCompanyName(), 4,0, 0, false, false);

        if(gtExpressBean.getGt_ExpressCode().length() <= 12){
            //条码
            iPrinter.drawBarCode(265, 1160,gtExpressBean.getGt_ExpressCode(), 1,  0, 2, 50);
            //条码字符
            iPrinter.drawText(307, 1214, gtExpressBean.getGt_ExpressCode(), 2,0,  1, false, false);
        }else{
            //条码,18位
            iPrinter.drawBarCode(240, 1160,gtExpressBean.getGt_ExpressCode(), 1,  0, 2, 50);
            //条码字符
            iPrinter.drawText(318, 1214, gtExpressBean.getGt_ExpressCode(), 2,0,  1, false, false);
        }
        //收件人
        iPrinter.drawText(4,1225+20,32,96,"收件人",2,0,  1,false,false);
        //收件人姓名＋电话，最终实施时请用变量替换
        iPrinter.drawText(2+4+32+8,1225+20,375,24,gtExpressBean.getGt_ExReceiverName()+"   "+gtExpressBean.getGt_ExReceiverPhone(),3,0, 1,false,false);
        //收件地址 ，最终实施时请用变量替换
        iPrinter.drawText(2+4+32+8,1261+20,375,78,stringBuilderReceiver.toString(),2,0, 0 ,false,false);
        //寄件人
        iPrinter.drawText(4,1345,32,96,"寄件人",2,0,  1,false,false);
        //寄件人姓名＋电话，
        iPrinter.drawText(2+8+32+8,1345,375,24,gtExpressBean.getGt_ExSenderName()+" "+gtExpressBean.getGt_ExSenderPhone(),3,0, 1,false,false);
        //寄件人地址
        iPrinter.drawText(2+8+32+8,1381,375,78,stringBuilderSender.toString(),2,0,  0,false,false);

        //商家自定义区
        iPrinter.drawText(28,1453,"商家自定义区",2,0,  1,false,false);
        //快递公司二维码
        if (!TextUtils.isEmpty(gtExpressBean.getGt_ExExtraQRCode())) {
            iPrinter.drawQrCode(423, 1250 + 5, gtExpressBean.getGt_ExExtraQRCode(), 0, 5, 5);
        }
        iPrinter.print(0,1);
    }
}
