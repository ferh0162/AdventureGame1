package com.company;

public abstract class Weapon extends Item {
  private int damage;
  private double numberOfUsesLeft = Double.POSITIVE_INFINITY;

  public Weapon(String description, int damage) {
    super(description);
    this.damage = damage;
  }

  public void useWeapon() {
    if (numberOfUsesLeft == Double.POSITIVE_INFINITY) {
      // ER infinity weapon
      System.out.println("You have attacked with the meleeweapon");
    } else {
      numberOfUsesLeft -= 1;
      System.out.println("                      " + Color.YELLOW_BACKGROUND + "   BANG!!!!  " + Color.RESET);
      }
    }



  public int getDamage() {
    return damage;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }

  public double getNumberOfUsesLeft() {
    return numberOfUsesLeft;
  }

  public void setNumberOfUsesLeft(double numberOfUsesLeft) {
    this.numberOfUsesLeft = numberOfUsesLeft;
  }
}
