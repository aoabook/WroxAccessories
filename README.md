# WroxAccessories

This is an Android library for Accessory Programming. It sits on top of the basic Android AOA Libraries, and implements the MQTT protocol for communicating with Arduino based accessories.

### Author/s

This Library was written by Andreas Göransson and David Cuartielles for a book called Android Open Accessory Programming with Arduino.

## Getting the library...

1. **Clone** this repository to your development machine
2. From the Eclipse menu, select **New** -> **Project**
3. In the dialog, expand **Android** and select **Android Project From Existing code**
4. **Browse** to the cloned repository root folder on your machine
5. Click **Finish**

### If there are compilation errors

6. **Right-click** the **WroxAccessories** project in your Package Explorer and select 	**Properties**
7. Set the **Build Target** to be **Google APIs** version **12**
8. Click **OK**

## Getting started with WroxAccessories for Android version 12 and above

### Adding WroxAccessory to an Android Project

1. **Select** your Android Project in the Package explorer and from the menu select **File** -> **Properties**
2. Select **Android** from the list on the left side of the dialog
3. In the **Library** TAB, click **Add**
4. Select **WroxAccessories** and click **OK**
5. Click **OK** again to close the Properties dialog

### Adding the accessory variables

``` java
/**
 * Adding the required Variables
 */
private WroxAccessory mAccessory;
private UsbManager mUsbManager;
private UsbConnection12 connection;
```

### Initializing the variables

``` java
/*
 * 1. Get a handle to the UsbManager service
 * 2. Initialize the UsbConnection12 object, passing the UsbManager reference
 * 3. Initialize WroxAccessory object
 */
@Override
public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  mUsbManager = (UsbManager) getSystemService(USB_SERVICE);
  connection = new UsbConnection12(this, mUsbManager);
  mAccessory = new WroxAccessory(this);
}
```

### Connecting to the accessory

``` java
@Override
protected void onResume() {
  super.onResume();

  try {
    mAccessory.connect(WroxAccessory.USB_ACCESSORY_12, connection);
  } catch (IOException e) {
    e.printStackTrace();
  }
}
```

### Disconnecting from the accessory

``` java
@Override
protected void onDestroy() {
  super.onDestroy();

  try {
    mAccessory.disconnect();
  } catch (IOException e) {
    e.printStackTrace();
  }
}
```

### Edit the AndroidManifest.xml

1. Add the **uses-feature** element to the **manifest**
2. Add the **action** (USB_ACCESSORY_ATTACHED) element to the **intent-filter**
3. Add the **meta-data** element to the **activity**

``` xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.yourproject"
    android:versionCode="1"
    android:versionName="1.0" >

  <uses-sdk
      android:minSdkVersion="8"
      android:targetSdkVersion="15" />

  <uses-feature
      android:name="android.hardware.usb.accessory"
      android:required="true" />

  <application
      android:icon="@drawable/ic_launcher"
      android:label="@string/app_name"
      android:theme="@style/AppTheme" >
    <activity
        android:name=".MainActivity"
        android:label="@string/title_activity_main" >
      <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
				
        <action android:name="android.hardware.usb.action.USB_ACCESSORY_ATTACHED" />
      </intent-filter>

      <meta-data
          android:name="android.hardware.usb.action.USB_ACCESSORY_ATTACHED"
          android:resource="@xml/accessory_filter" />
    </activity>
  </application>
</manifest>
```

### Writing the accessory_filter

Edit **manufacturer** and **model** attributes to be the same as your accessory.

``` xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
  <usb-accessory manufacturer="Your company" model="Accessory name" version="1.0" />
</resources>

```