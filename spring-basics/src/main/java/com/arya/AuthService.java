package com.arya;


public class AuthService {
    private final PasswordEncoder passwordEncoder;
    
//    DI
    public AuthService(PasswordEncoder passwordEncoder) {
    	this.passwordEncoder = passwordEncoder;
    }
    
    public void login(String raw) {
//    	use dependency
    	String encoded = passwordEncoder.encode("secret");
    	if(passwordEncoder.matches(raw, encoded)) {
    		System.out.println("PAssword match");
    	}else {
    		System.out.println("not match");
    	}
    }
}


