package com.neusoft.tools;

public class SecJni {
	public static String loadHashCode(){
		return String.valueOf(org.apache.hadoop.io.MD5Hash.digest("rivues").halfDigest()).replace("-", "a") ; 
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		System.out.println(loadHashCode());
	}

}

