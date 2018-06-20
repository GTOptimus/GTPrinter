/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gt.qr386;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qr.printer.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 作者：zhougf
 * 邮箱：edivista@vip.qq.com
 * QQ: 77175792
 * 微信：edivista
 */

public class DeviceListActivity extends Activity implements DevicesAdapter.OnExpandItemClickListener {
    // Debugging
    private static final String TAG = "DeviceListActivity";
    private static final boolean D = true;

    // Return Intent extra
    public static String EXTRA_DEVICE_ADDRESS = "device_address";
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    // Member fields
    private BluetoothAdapter mBtAdapter;

    private ExpandableListView expandableListView;

    private String[] titles = new String[]{"已配对设备", "可用设备"};
    //可用设备列表，子列表
    private List<BluetoothDevice> newList = new ArrayList<BluetoothDevice>();
    //已配对以及可用设备列表
    private List<Device> deviceList = new ArrayList<Device>();
    private DevicesAdapter devicesAdapter;

    private BluetoothAdapter mBluetoothAdapter = null;
    private ArrayAdapter<String> mNewDevicesArrayAdapter;
    /**
     * 打印机参数
     *
     * @param savedInstanceState
     */
    private Printer printPP_cpcl;
    private boolean isConnected = false;
    private String address = "";
    private String name = "";
    private boolean isSending = false;
    private int printCount;//打印的数量

    private TextView mTitle;

    private GTExpressBean gtExpressBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setup the window
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.device_list);

        // Set result CANCELED incase the user backs out
        setResult(Activity.RESULT_CANCELED);
        gtExpressBean = (GTExpressBean) getIntent().getSerializableExtra("gtExpressBean");
        mTitle = findViewById(R.id.title_right_text);
        mTitle.setText(R.string.title_not_connected);
        //obtain the local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //If the Bluetooth adapter is not supported,programmer is over
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        //打印
        printPP_cpcl = new Printer();
        // Initialize array adapters. One for already paired devices and
        // one for newly discovered devices
        expandableListView = findViewById(R.id.expand_listview);
        // Register for broadcasts when a device is discovered
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        this.registerReceiver(mReceiver, filter);

        // Register for broadcasts when discovery has finished
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.registerReceiver(mReceiver, filter);

        devicesAdapter = new DevicesAdapter(DeviceListActivity.this, deviceList, DeviceListActivity.this);
        expandableListView.setAdapter(devicesAdapter);
        expandableListView.setOnGroupExpandListener(
                new ExpandableListView.OnGroupExpandListener() {

                    @Override
                    public void onGroupExpand(int groupPosition) {
                        for (int i = 0; i < devicesAdapter.getGroupCount(); i++) {
                            if (groupPosition != i) {
                                expandableListView.collapseGroup(i);
                            }
                        }

                    }
                }
        );
        mNewDevicesArrayAdapter = new ArrayAdapter<String>(this, R.layout.item_child);

        queryDevices();

//        if (pairedDevices.size() > 0) {
//            findViewById(R.id.title_paired_devices).setVisibility(View.VISIBLE);
//            for (BluetoothDevice device : pairedDevices) {
//                mPairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
//            }
//        } else {
//            String noDevices = getResources().getText(R.string.none_paired).toString();
//            mPairedDevicesArrayAdapter.add(noDevices);
//        }
        if (Build.VERSION.SDK_INT >= 6.0) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    5);
        }
    }

    /**
     * 搜索设备，已连接设备以及可用设备
     */
    private void queryDevices() {
        deviceList.clear();
        doUsedDevices();
        //搜索可用设备
        doDiscovery();
    }

    //查询已配对蓝牙设备的列表
    private void doUsedDevices() {
        // Get the local Bluetooth adapter
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();

        // Get a set of currently paired devices
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
        List<BluetoothDevice> list = new ArrayList<BluetoothDevice>(pairedDevices);
        // If there are paired devices, add each one to the ArrayAdapter
        deviceList.add(new Device(titles[0], list));

        devicesAdapter.updateData(deviceList);
        expandableListView.expandGroup(0);
    }

    @Override
    public void onStart() {
        super.onStart();
        // If BT is not on, request that it be enabled
        // setupChat() will then be called during onActivityRe//sultsetupChat
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (D) {
            Log.d(TAG, "onActivityResult " + resultCode);
        }

        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE:
                if (resultCode == Activity.RESULT_OK) {
                    if (isConnected & (printPP_cpcl != null)) {
                        printPP_cpcl.disconnect();
                        isConnected = false;
                    }
                    String sdata = data.getExtras()
                            .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                    address = sdata.substring(sdata.length() - 17);
                    name = sdata.substring(0, (sdata.length() - 17));
                    if (!isConnected) {
                        // if (printPP_cpcl.connect("QR-380A", "00:0C:BF:0A:E0:2D")) {
                        //	 if (printPP_cpcl.connect("QR-380A", "8C:DE:52:F3:1A:03")) {
                        if (printPP_cpcl.connect(name, address)) {
                            // if (printPP_cpcl.connect("QR-386A", "DC:1D:30:00:02:49")) {

                            isConnected = true;
                            mTitle.setText(R.string.title_connected_to);
                            mTitle.append(name);


                        } else {

                            isConnected = false;

                        }

                    }

                }
                break;
            case REQUEST_ENABLE_BT:

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Make sure we're not doing discovery anymore
        if (mBtAdapter != null) {
            mBtAdapter.cancelDiscovery();
        }

        // Unregister broadcast listeners
        this.unregisterReceiver(mReceiver);
    }

    /**
     * Start device discover with the BluetoothAdapter
     */
    private void doDiscovery() {
        if (D) Log.d(TAG, "doDiscovery()");

        // Indicate scanning in the title
        setProgressBarIndeterminateVisibility(true);
        setTitle(R.string.scanning);

        // If we're already discovering, stop it
        if (mBtAdapter.isDiscovering()) {
            mBtAdapter.cancelDiscovery();
        }

        // Request discover from BluetoothAdapter
        mBtAdapter.startDiscovery();
    }

    // The BroadcastReceiver that listens for discovered devices and
    // changes the title when discovery is finished
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // If it's already paired, skip it, because it's been listed already
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    newList.add(device);
                }
                // When discovery is finished, change the Activity title
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                setProgressBarIndeterminateVisibility(false);
                setTitle(R.string.select_device);
                //加入可用设备列表
                deviceList.add(new Device(titles[1], newList));
                //刷新expandleList
                devicesAdapter.updateData(deviceList);
                if (deviceList.get(0).getDeviceSets() != null && !deviceList.get(0).getDeviceSets().isEmpty()) {
                    expandableListView.expandGroup(0);
                }
                if (newList != null && !newList.isEmpty()) {
                    expandableListView.expandGroup(1);
                }
            }
        }
    };

    @Override
    public void groupItemClick() {
        //刷新列表
        if (Build.VERSION.SDK_INT >= 6.0) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    5);
        }
        queryDevices();
    }

    @Override
    public void childItemClick(String deviceInfo) {

        // Cancel discovery because it's costly and we're about to connect
        mBtAdapter.cancelDiscovery();

        // Get the device MAC address, which is the last 17 chars in the View
//        String info = ((TextView) v).getText().toString();
        // String address = info.substring(info.length() - 17);

        // Create the result Intent and include the MAC address
//        Intent intent = new Intent();
//        intent.putExtra(EXTRA_DEVICE_ADDRESS, deviceInfo);
//
//        // Set result and finish this Activity
//        setResult(Activity.RESULT_OK, intent);
//        finish();
        setConnect(deviceInfo);
    }

    /**
     * 选择蓝牙设备。准备打印
     */
    private void setConnect(String deviceInfo) {
        if (isConnected & (printPP_cpcl != null)) {
            printPP_cpcl.disconnect();
            isConnected = false;
        }
        address = deviceInfo.substring(deviceInfo.length() - 17);
        name = deviceInfo.substring(0, (deviceInfo.length() - 17));
        if (!isConnected) {
            // if (printPP_cpcl.connect("QR-380A", "00:0C:BF:0A:E0:2D")) {
            //	 if (printPP_cpcl.connect("QR-380A", "8C:DE:52:F3:1A:03")) {
            if (printPP_cpcl.connect(name, address)) {
                // if (printPP_cpcl.connect("QR-386A", "DC:1D:30:00:02:49")) {
                isConnected = true;
                mTitle.setText(R.string.title_connected_to);
                mTitle.append(name);
                View view = QRUtils.confirmPrint(DeviceListActivity.this);
                view.findViewById(R.id.tv_dialog_tip_left).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        QRUtils.cancelDialog();
                        //可以打印了
                        print(1);
                    }
                });

            } else {
                isConnected = false;
                Toast.makeText(this,"连接失败",Toast.LENGTH_SHORT).show();
            }

        }
    }

    /**
     * 打印数量
     *
     * @param count
     */
    private void print(final int count) {
        this.printCount = count <= 0 ? 1 : count;
        if (!isSending) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < printCount; i++) {
                        Log.e(TAG, String.valueOf(i));
                        isSending = true;
                        if (isConnected) {
                            Print pl = new Print();
                            pl.Lable(printPP_cpcl,gtExpressBean);
                            //Bitmap rawBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.cainiao_logo);
                            //pl.label2(printPP_cpcl, rawBitmap);

                        }

                        try {
                            Thread.sleep(0);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (i == (printCount - 1)) {
                            isSending = false;
                        }

                    }
                }
            }).start();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 5: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //permission granted!
                }
                return;
            }
        }
    }
}
