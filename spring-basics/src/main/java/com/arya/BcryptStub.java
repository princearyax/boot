package com.arya;


public class BcryptStub implements PasswordEncoder{

	@Override
	public String encode(String raw) {
		return "encrypted_"+raw;
	}

	@Override
	public boolean matches(String raw, String encoded) {
		return encoded.equals("encrypted_"+raw);
	}
	
}
