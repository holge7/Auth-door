package com.door.configuration;

public class Routes {
	public static final String[] PUBLIC_ROUTES = new String[] {
			"/api/resource/public/**",
			"/api/auth/login",
			"/api/auth/register"
	};
	
	public static final String[] USER_ROUTES = new String[] {
		"/api/resource/**"	
	};
	
}
