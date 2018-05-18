package tdd.dependencyBreaking.account;

import java.util.Date;

public class Account{

  private final DateMaker dateMaker;
  private Date deactivatedAt;
    private boolean active = true;

  public Account(DateMaker dateMaker) {
    this.dateMaker = dateMaker;
  }

  public void deactivate(){
        
        this.active = false;
        this.deactivatedAt = dateMaker.now();
        
        // ...
    }

  public Date getDeactivatedAt() {
        return deactivatedAt;
    }

    public boolean isActive() {
        return active;
    }
}