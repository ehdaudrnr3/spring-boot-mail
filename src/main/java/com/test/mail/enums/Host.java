package com.test.mail.enums;

import java.util.Arrays;

public enum Host {
	NAVER, GOOGLE, DAUM, EMPTY;
	
	public static Host findByHost(String hostName) {
		return Arrays.asList(Host.values()).stream()
		.filter(host->host.name().equalsIgnoreCase(hostName))
		.findAny()
		.orElse(EMPTY);
	}
	
}
