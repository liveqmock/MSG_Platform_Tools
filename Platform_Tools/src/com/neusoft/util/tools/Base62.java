package com.neusoft.util.tools;

import java.math.BigInteger;

public class Base62 {
	public static final BigInteger BASE = BigInteger.valueOf(62);
	  public static final String DIGITS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	  public static final String REGEXP = "^[0-9A-Za-z]+$";
	  
	  /**
	   * 62进制值转换为10进制
	   * @param {String} str62 62进制值
	   * @return {String} 10进制值
	   */
	  public static int str62to10(String str62) {
	  	int i10 = 0;
	  	for (int i = 0; i < str62.length(); i++)
	  	{
	  		int n = str62.length() - i - 1;
	  		char s = str62.charAt(i);
	  		i10 += DIGITS.indexOf(s) * Math.pow(62, n);
	  	}
	  	return i10;
	  };
	  
	  /**
	   * 10进制值转换为62进制
	   * @param {String} int10 10进制值
	   * @return {String} 62进制值
	   */

	  public static String int10to62(int int10) {
		  String s62 = "";
			int r = 0;
			while (int10 != 0)
			{
				r = int10 % 62;
				s62 = DIGITS.charAt(r) + s62;
				int10 = (int)(int10 / 62);
			}
			return s62;
	  };
	  
	  
	  public static void main(String[] args){
			System.out.println(decode("yywkUdNgM") );  //3426869200785013
		}
	  /**
	   * Encodes a number using Base62 encoding.
	   *
	   * @param  number a positive integer
	   * @return a Base62 string
	   * @throws IllegalArgumentException if <code>number</code> is a negative integer
	   */
	  public static String encode(String mid) {
		  StringBuffer strb = new StringBuffer();
		  for (int i = mid.length() - 7; i > -7; i = i - 7)	//从最后往前以7字节为一组读取mid
			{
				int offset1 = i < 0 ? 0 : i;
				int offset2 = i + 7;
				int num = Integer.parseInt(mid.substring(offset1, offset2));
				
				String value = int10to62(num);
				strb.insert(0 , value);
			}
			
			return strb.toString();
	  }

	  /**
	   * Decodes a string using Base62 encoding.
	   *
	   * @param  string a Base62 string
	   * @return a positive integer
	   * @throws IllegalArgumentException if <code>string</code> is empty
	   */
	  public static String decode(final String url) {
		  StringBuffer strb = new StringBuffer();
		  for (int i = url.length() - 4; i > -4; i = i - 4)	//从最后往前以4字节为一组读取URL字符
			{
				int offset1 = i < 0 ? 0 : i;
				int offset2 = i + 4;
				String str = url.substring(offset1, offset2);
				
				str = String.valueOf(str62to10(str));
				if (offset1 > 0)	//若不是第一组，则不足7位补0
				{
					while (str.length() < 7)
					{
						str = '0' + str;
					}
				}
				
				strb.insert(0, str);
			}
			
			return strb.toString();
	  }

}
