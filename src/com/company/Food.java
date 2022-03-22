package com.company;

public class Food extends Item {
  private int healthPoints;

  public Food(String description) {
    super(description);
    this.healthPoints = 10;
  }

  public Food(String description, int healthPoints) {
    super(description);
    this.healthPoints = healthPoints;
  }

  public int getHealthPoints() {
    return healthPoints;
  }
}
