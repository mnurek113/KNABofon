package com.code.knab.knabofon.ui.main

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.code.knab.knabofon.R
import com.code.knab.knabofon.ui.main.dagger.MainInjector
import com.code.knab.knabofon.ui.main.mvp.MainMVP
import com.code.knab.knabofon.ui.transmition.TransmitionActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainMVP.View {

    private val TAG: String = "MainActivity"

    @Inject
    lateinit var presenter: MainMVP.Presenter

    private var bluetoothDevicesList = ArrayList<BluetoothDevice>()
    private lateinit var deviceListAdapter: DeviceListAdapter

    private lateinit var newDevicesListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainInjector().inject(this)

        newDevicesListView = findViewById(R.id.newDevicesListView)

        onOffButton.setOnClickListener {

        }
        discoverButton.setOnClickListener {

        }

        newDevicesListView.setOnItemClickListener({parent, view, position, id -> pairDevices(position) })

    }

    private fun pairDevices(position: Int) {

        var device: BluetoothDevice = bluetoothDevicesList.get(position)

        if(device.name.equals("HC-05")) {
            if(!device.createBond())
                startNextActivity(device)
        }
        else {
            Log.d(TAG, "Error - check out your device")
            this.finish()
        }

        val pairingDevicesIntent: IntentFilter = IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED)
        registerReceiver(presenter.bondingDevicesReceiver, pairingDevicesIntent)
    }

    override fun enableBT() {
        val enableBTIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        startActivity(enableBTIntent)
        Log.d(TAG, "Bluetooth enabled")
    }

    override fun disableBT() {
        Log.d(TAG, "bluetooth disabled")
    }

    override fun error() {
        Log.d(TAG, "bluetooth not available")
        this.finish()
    }

    override fun discoverDevices() {
        if(deviceListAdapter != null)
            deviceListAdapter.clear()
        val btDiscoverIntent = IntentFilter(BluetoothDevice.ACTION_FOUND)
        registerReceiver(presenter.discoveredDevicesReceiver, btDiscoverIntent)
    }

    override fun listDevices(context: Context, device: BluetoothDevice) {
        bluetoothDevicesList.add(device)
        deviceListAdapter = DeviceListAdapter(context, R.layout.device_adapter_view, bluetoothDevicesList)
        newDevicesListView.adapter = deviceListAdapter
    }

    override fun startNextActivity(device: BluetoothDevice) {
        val intent = Intent(this, TransmitionActivity::class.java)
        intent.putExtra("device", device)
        startActivity(intent)
        this.finish()
    }
}
