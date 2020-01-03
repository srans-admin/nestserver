package com.srans.nestserver.util;

public class GenerateUniquePassword {

	public static String Code() {
		long code = (long) ((Math.random() * 9 * Math.pow(10, 15)) + Math.pow(10, 15));
		String unique_password = "";
		for (long i = code; i != 0; i /= 100)
		{
			long digit = i % 100;
			if (digit <= 90)
				digit = digit + 32;
			
			char ch = (char) digit;
			
			unique_password = ch + unique_password;
		}
		
		return unique_password;
	}

}
