package com.wiley.wroxaccessories;

import java.util.HashMap;
import java.util.Map;

public class MQTTMessage {

	public int type;
	public boolean DUP;
	public int QoS;
	public boolean retain;
	public int remainingLength;
	
	public Map<String, Object> variableHeader = new HashMap<String, Object>();
	
	public byte[] payload;
}
