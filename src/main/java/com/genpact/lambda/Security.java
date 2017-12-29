package com.genpact.lambda;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;


public class Security {
	private static final String CHARSET = "UTF-8";
	private static final String ALGORITHM = "DESede";
	private static final String KEY = "pimco";
	public  static String encrypt(String src){
		return encrypt(src,KEY);
	}
	public static String decrypt(String src) {
		return decrypt(src,KEY);
	}
	private static Cipher getCipher(String keyStr, int model) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] enKey = md5.digest(keyStr.getBytes(CHARSET));
		byte[] temp = new byte[24];
		System.arraycopy(enKey, 0, temp, 0, enKey.length);  

		DESedeKeySpec dks = new DESedeKeySpec(temp);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(model, key);
		return cipher;
	}

	private static String encrypt(String src, String keyStr) {
		String encryptedData = null;
		try {
			Cipher cipher = getCipher(keyStr, Cipher.ENCRYPT_MODE);
			
			encryptedData = filter(Base64.getEncoder().encodeToString(cipher.doFinal(src.getBytes(CHARSET))));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeyException | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		return encryptedData;
	}

	private static String decrypt(String src, String keyStr) {
		String decryptedData = null;
		try {
			Cipher cipher = getCipher(keyStr, Cipher.DECRYPT_MODE);
			
			decryptedData = new String(cipher.doFinal(Base64.getDecoder().decode(src)), CHARSET);
		} catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | IOException e) {
			e.printStackTrace();
		}

		return decryptedData;
	}

	private static String filter(String str) {
		String output = null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			int asc = str.charAt(i);
			if (asc != 10 && asc != 13)
				sb.append(str.subSequence(i, i + 1));
		}
		output = new String(sb);
		return output;
	}
	
	
	public static void main(String[] args) {
		String str = "999-99-99-394621";
		System.out.println(encrypt(str).length());
		System.out.println(encrypt(str));
	}

}
