package com.arya;

public interface PasswordEncoder{
	String encode(String raw);
	boolean matches(String raw, String encoded);
}