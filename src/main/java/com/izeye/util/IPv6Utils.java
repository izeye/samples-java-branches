package com.izeye.util;

import com.google.common.net.InetAddresses;

/**
 * Created by izeye on 16. 1. 21..
 */
public class IPv6Utils {
	
	public static String uncompress(String ipAddress) {
		byte[] addressBytes = InetAddresses.forString(ipAddress).getAddress();
		Byte[] wrapped = TypeUtils.toWrapped(addressBytes);
		return String.format("%02x%02x:%02x%02x:%02x%02x:%02x%02x:%02x%02x:%02x%02x:%02x%02x:%02x%02x", wrapped);
	}
	
}
