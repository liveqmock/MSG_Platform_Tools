package com.neusoft.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressTools {
	/**
	 * 
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static String compress(String str) throws IOException {  
        if (str == null || str.length() == 0) {  
            return str;  
        }  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        GZIPOutputStream gzip = new GZIPOutputStream(out);  
        gzip.write(str.getBytes());  
        gzip.close();  
        return out.toString();  
    }  
	/**
	 * 
	 * @param str
	 * @return
	 * @throws IOException
	 */
    public static String uncompress(String str) throws IOException {  
        if (str == null || str.length() == 0) {  
            return str;  
        }  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());  
        GZIPInputStream gunzip = new GZIPInputStream(in);  
        byte[] buffer = new byte[256];  
        int n;  
        while ((n = gunzip.read(buffer)) >= 0) {  
            out.write(buffer, 0, n);  
        }  
        return out.toString();  
    }  
    
    public static void main(String[] args) throws IOException{
    	System.out.println(compress("53FB5EDCAF3974E40354BA8EEEF06FB6B9991E0D226AE3172789461953AAA8BAA8633C79272BC5C528D42CE87AFDCCC7367D451710E37676EB41AC20E3E5BD3C"));
    }
}
