# GTPrinter
贯通云网手持打印机sdk
集成方法：

1、项目的gradle文件 repositories目录中添加maven {
            url "https://jitpack.io"
        }
        
2、app的gradle文件 dependencies目录中添加 implementation 'com.github.GTOptimus:GTPrinter:1.*.*'


使用方法：

GTPrinterInterface.print(Context context,GTExpressBean gtExpressBean);即可打印
注 ： GTExpressBean为打印所需内容，

其中必填字段：gt_ExCompanyName：快递公司名称，gt_ExTripleCode：大头笔
gt_ExReceiverName：收件人姓名，gt_ExReceiverPhone：收件人手机号，gt_ExReceiverAddressContent：收件人详细地址，
gt_ExSenderName：发件人姓名，gt_ExSenderPhone：发件人手机号，gt_ExSenderAddressContent：发件人详细地址，
gt_ExpressCode：单号；

选填字段：收件人省市区字段，发件人省市区字段，二维码
