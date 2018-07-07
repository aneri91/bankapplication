package com.cognitivescale.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PasswordUtils {
	private static final Log LOG = LogFactory.getLog(PasswordUtils.class);
	private static SecretKeySpec secretKey;
	private static byte[] key;

	private PasswordUtils() {
		super();
	}

	static {
		try {
			PasswordUtils.setKey("test!");
		} catch (Exception e) {
			LOG.info(e);
		}
	}

	public static void setKey(String myKey) {
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			LOG.info(e);
		}
	}

	public static String decrypt(String data) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.decodeBase64(data)));
		} catch (Exception e) {
			LOG.info(e);
			LOG.debug("Error while decrypting: " + e.toString());
		}
		return null;
	}

	public static String encrypt(byte[] data) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.encodeBase64String(cipher.doFinal(data));
		} catch (Exception e) {
			LOG.info(e);
			LOG.debug("Error while encrypting: " + e.toString());
		}
		return null;
	}

}
