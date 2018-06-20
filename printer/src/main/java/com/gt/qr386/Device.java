package com.gt.qr386;

import android.bluetooth.BluetoothDevice;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述：
 * 创建人：yangxing
 * 创建时间：2018/6/11 15:02
 * 修改人：yangxing
 * 修改时间：2018/6/11 15:02
 * 修改备注：
 */

public class Device implements Serializable{
    private String title;
    private List<BluetoothDevice> deviceSets;//设备列表

    public Device() {
    }

    public Device(String title, List<BluetoothDevice> deviceSets) {
        this.title = title;
        this.deviceSets = deviceSets;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BluetoothDevice> getDeviceSets() {
        return deviceSets;
    }

    public void setDeviceSets(List<BluetoothDevice> deviceSets) {
        this.deviceSets = deviceSets;
    }
}
