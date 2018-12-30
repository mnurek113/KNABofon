package com.code.knab.knabofon.ui.main

import android.bluetooth.BluetoothDevice
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.code.knab.knabofon.R
import java.util.ArrayList

class DeviceListAdapter(context: Context, private val viewResourceId: Int, private val devicesList: ArrayList<BluetoothDevice>) : ArrayAdapter<BluetoothDevice>(context, viewResourceId, devicesList) {

    private val layoutInflater: LayoutInflater

    init {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val convertView = layoutInflater.inflate(viewResourceId, null)

        val device = devicesList[position]

        if (device != null) {
            val deviceName = convertView!!.findViewById<View>(R.id.tvDeviceName) as TextView
            val deviceAdress = convertView.findViewById<View>(R.id.tvDeviceAddress) as TextView
            //
            if (deviceName != null) {
                deviceName.text = device.name
            }
            if (deviceAdress != null) {
                deviceAdress.text = device.address
            }
        }

        return convertView
    }

}
