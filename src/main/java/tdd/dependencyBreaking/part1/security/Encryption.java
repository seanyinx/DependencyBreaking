package tdd.dependencyBreaking.part1.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

class Encryption {

  private static final String UTF_8 = "UTF8";
  private final SecretKey secretKey;
  private final Cipher cipher;

  Encryption(String key)
      throws NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException, UnsupportedEncodingException {
    secretKey = toSecretKey(key);
    cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
  }

  Properties encrypt(Properties unencrypted) throws Exception {
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);

    Properties encrypted = new Properties();
    for (String key : unencrypted.stringPropertyNames()) {
      byte[] cipherBytes = cipher.doFinal(unencrypted.getProperty(key).getBytes(UTF_8));
      encrypted.setProperty(key, Base64.getEncoder().encodeToString(cipherBytes));
    }

    return encrypted;
  }

  Properties decrypt(Properties encrypted) throws Exception {
    cipher.init(Cipher.DECRYPT_MODE, secretKey);

    Properties decrypted = new Properties();
    for (String key : encrypted.stringPropertyNames()) {
      byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encrypted.getProperty(key)));
      decrypted.setProperty(key, new String(decryptedBytes, UTF_8));
    }
    return decrypted;
  }

  private SecretKey toSecretKey(String key) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    MessageDigest sha = MessageDigest.getInstance("SHA-1");

    byte[] keyBytes = key.getBytes(UTF_8);
    keyBytes = sha.digest(keyBytes);
    keyBytes = Arrays.copyOf(keyBytes, 16);

    return new SecretKeySpec(keyBytes, "AES");
  }
}
