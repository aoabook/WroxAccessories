package com.wiley.wroxaccessories;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

public class BTConnection extends Connection {

	private static final UUID uuid = UUID.fromString("");

	private BluetoothAdapter mBluetoothAdapter;
	private BluetoothSocket mBluetoothSocket;

	public BTConnection(String mac) {
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

		BluetoothDevice mBluetoothDevice = mBluetoothAdapter
				.getRemoteDevice(mac);

		try {
			mBluetoothDevice.createInsecureRfcommSocketToServiceRecord(uuid);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return mBluetoothSocket.getInputStream();
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		return mBluetoothSocket.getOutputStream();
	}

	@Override
	public void close() throws IOException {
		mBluetoothSocket.close();
	}

}
