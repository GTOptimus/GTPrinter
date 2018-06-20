package com.gt.qr386;

import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * 类描述：
 * 创建人：yangxing
 * 创建时间：2018/6/11 16:50
 * 修改人：yangxing
 * 修改时间：2018/6/11 16:50
 * 修改备注：
 */

public class QRUtils {
    private static Dialog dialog;//通用dialog

    public static void ensureDiscoverable(boolean isDebug,String tag,Context context,BluetoothAdapter mBluetoothAdapter) {
        if (isDebug) {
            Log.d(tag, "ensure discoverable");
        }
        if (mBluetoothAdapter != null && mBluetoothAdapter.getScanMode() !=
                BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            context.startActivity(discoverableIntent);
        }
    }

    public static View confirmPrint(Context context){
        dialog = new Dialog( context, R.style.dialog);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = inflater.inflate(R.layout.view_alert_common,null);

        dialog.setContentView(convertView);
        //取消
        convertView.findViewById(R.id.tv_dialog_tip_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelDialog();
            }
        });
        dialog.show();
        return convertView;
    }
    /**
     * 关闭选择照片弹框
     */
    public static void cancelDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.cancel();
            dialog = null;
        }
    }
}
