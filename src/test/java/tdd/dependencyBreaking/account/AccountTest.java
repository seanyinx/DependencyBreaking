package tdd.dependencyBreaking.account;

import static org.fest.assertions.api.Assertions.assertThat;

import java.time.ZonedDateTime;
import java.util.Date;

import org.junit.Test;

public class AccountTest {

  private final Date now = Date.from(ZonedDateTime.now().toInstant());

  private final Account account = new Account(new MockDateMaker(now));

  @Test
  public void deactivateAtSpecifiedTime() throws Exception {
    account.deactivate();

    Date deactivatedAt = account.getDeactivatedAt();

    Thread.sleep(1);

    assertThat(deactivatedAt).isSameAs(now);
  }

  private static class MockDateMaker extends DateMaker {
    private final Date now;

    MockDateMaker(Date now) {
      this.now = now;
    }

    @Override
    public Date now() {
      return now;
    }
  }
}