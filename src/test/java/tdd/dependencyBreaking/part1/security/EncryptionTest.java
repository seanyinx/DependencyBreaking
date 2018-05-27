package tdd.dependencyBreaking.part1.security;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

public class EncryptionTest {

  private Encryption encryption;

  @Before
  public void setUp() throws Exception {
    encryption = new Encryption("secret");
  }

  @Test
  public void ableToDecryptEncryptedText() throws Exception {
    Properties properties = new Properties();
    properties.setProperty("username", "jack");
    properties.setProperty("password", "password");

    Properties encrypted = encryption.encrypt(properties);

    Properties decrypted = encryption.decrypt(encrypted);

    assertThat(decrypted).isEqualTo(properties);
  }
}