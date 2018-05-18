package tdd.dependencyBreaking.account;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;

public class AccountTest {

  private final Account account = new Account(new DateMaker());

  @Test
  public void deactivateAtSpecifiedTime() throws Exception {
    account.deactivate();

    Date deactivatedAt = account.getDeactivatedAt();

    Thread.sleep(1);

    assertThat(deactivatedAt).isSameAs(new Date());
  }
}