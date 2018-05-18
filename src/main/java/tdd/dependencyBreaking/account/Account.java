package tdd.dependencyBreaking.account;

import java.util.Date;

public class Account{

    private Date deactivatedAt;
    private boolean active = true;

    public void deactivate(){
        
        this.active = false;
        this.deactivatedAt = new DateMaker().now();
        
        // ...
    }

  public Date getDeactivatedAt() {
        return deactivatedAt;
    }

    public boolean isActive() {
        return active;
    }

  private class DateMaker {
    public Date now() {
      return new Date();
    }
  }
}