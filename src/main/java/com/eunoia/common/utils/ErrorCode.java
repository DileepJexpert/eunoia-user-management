package com.eunoia.common.utils;

public interface ErrorCode<T extends ErrorCode> {
	
	String code();
	
	Class<T> type();

}
