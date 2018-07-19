package com.airtel.merchant.scheduler.security;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * The Class TripleDESCrypt provides encyption and decryption methods.
 */
public class TripleDESCrypt {

	/** The Constant UNICODE_FORMAT. */
	private static final String UNICODE_FORMAT = "UTF8";

	/** The Constant ENCRYPTION_KEY_TYPE. */
	private static final String ENCRYPTION_KEY_TYPE = "DESede";

	/**
	 * Instantiates a new triple DES crypt.
	 */
	private TripleDESCrypt() {

	}

	/**
	 * This method will encryt the string with given key.
	 *
	 * @param msg
	 *            the msg
	 * @param key
	 *            the key
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public static String encrypt(String msg, String key) throws Exception {
		DESedeKeySpec keySpec = new DESedeKeySpec(getPhrase(key));
		SecretKey skey = SecretKeyFactory.getInstance(ENCRYPTION_KEY_TYPE).generateSecret(keySpec);
		Cipher ecipher = Cipher.getInstance(skey.getAlgorithm());
		ecipher.init(Cipher.ENCRYPT_MODE, skey);
		byte[] utf8 = msg.getBytes(UNICODE_FORMAT);
		byte[] enc = ecipher.doFinal(utf8);
		return Base64.encodeBase64String(enc);
	}

	/**
	 * Gets the phrase.
	 *
	 * @param phrase
	 *            the phrase
	 * @return the phrase
	 */
	private static byte[] getPhrase(String phrase) {
		byte[] passPhrase = new byte[24];
		if (phrase == null) {
			new Random().nextBytes(passPhrase);
		} else {
			System.arraycopy(phrase.getBytes(), 0, passPhrase, 0, 24);
		}
		return passPhrase;
	}
}
