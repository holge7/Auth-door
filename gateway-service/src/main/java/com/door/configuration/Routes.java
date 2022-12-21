package com.door.configuration;

public class Routes {
	public static final String[] PUBLIC_ROUTES = new String[] {
			"/api/resource/public/**",
			"/api/login/**",
			"/api/user/login",
			"/api/user/test",
			"/api/user/register",
			"/api/auth/login",
			"/login"
	};
	
	public static final String[] USER_ROUTES = new String[] {
		"/api/resource/user/**"	
	};
	
	public static final String[] MODERATOR_ROUTES = new String[] {
		"/api/resource/mod/**"	
	};
	
	public static final String[] ADMIN_ROUTES = new String[] {
		"/api/resource/admin/**"	
	};
	
}
