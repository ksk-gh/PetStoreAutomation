package api.endpoints;

import api.payload.User;

/*
 * Swagger URI -->https://petstore.swagger.io
 * 
 * Create user(post) : https://petstore.swagger.io/v2/user
 * Get User (Get) : https://petstore.swagger.io/v2/user/{username}
 * Update user (Put) : https://petstore.swagger.io/v2/user/{username}
 * Delete user (Delete) : https://petstore.swagger.io/v2/user/{username}
 * */

public class Routes {
	User use= new User();
	String username=use.getUserName();
public static String base_url="https://petstore.swagger.io/v2";

public static String post_url=base_url+"/user";
public static String get_url=base_url+"/user/{username}";
//public static String get_url=base_url+"/user/"+username;

public static String update_url=base_url+"/user/{username}";
public static String delete_url=base_url+"/user/{username}";


}
