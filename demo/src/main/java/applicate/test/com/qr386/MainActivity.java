package applicate.test.com.qr386;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gt.qr386.DeviceListActivity;
import com.gt.qr386.GTExpressBean;
import com.gt.qr386.GTPrinterInterface;

public class MainActivity extends AppCompatActivity {
    GTExpressBean gtExpressBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gtExpressBean = new GTExpressBean();
        gtExpressBean.setGt_ExCompanyName("快递公司");
        gtExpressBean.setGt_ExTripleCode("2434-53442-64534");

        gtExpressBean.setGt_ExReceiverName("发件人姓名");
        gtExpressBean.setGt_ExReceiverPhone("13122225555");
        gtExpressBean.setGt_ExReceiverProvinceName("北京市");
        gtExpressBean.setGt_ExReceiverCityName("北京市");
        gtExpressBean.setGt_ExReceiverDistrictName("海淀区");
        gtExpressBean.setGt_ExReceiverAddressContent("海淀区地址");

        gtExpressBean.setGt_ExSenderName("收件人姓名");
        gtExpressBean.setGt_ExSenderPhone("13111112222");
        gtExpressBean.setGt_ExSenderAddressContent("朝阳区 工体");

        gtExpressBean.setGt_ExpressCode("gt121631611214");
        gtExpressBean.setGt_ExExtraQRCode("odpjgpdk1541561");
        findViewById(R.id.tv_group).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GTPrinterInterface.print(MainActivity.this,gtExpressBean);
            }
        });
    }
}
