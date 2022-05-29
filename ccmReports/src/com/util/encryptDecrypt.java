package com.util;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class encryptDecrypt {
	
	
	private static final IvParameterSpec FIXED_128BIT_IV_FOR_TESTS = new IvParameterSpec("DEADD00D8BADF00D".getBytes()); 
	
	private static SecretKeySpec secretKey;
	private static byte[] key;

	
	/*key is set using this method*/
	public static void setKey(String myKey) 
	{	
		System.out.println(FIXED_128BIT_IV_FOR_TESTS);
		
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16); 
			secretKey = new SecretKeySpec(key, "AES");
		} 
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	 /*encryption with iv */
	public static String encrypt(String strToEncrypt, String secret) 
	{
		try 
		{
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, FIXED_128BIT_IV_FOR_TESTS);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} 
		catch (Exception e) 
		{
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}

	/*decryption method*/
	public static String decrypt(String strToDecrypt, String secret) 
	{
		try 
		{
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, FIXED_128BIT_IV_FOR_TESTS);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} 
		catch (Exception e) 
		{
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}

	
}


