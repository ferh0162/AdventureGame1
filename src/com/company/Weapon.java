package com.company;

public class Weapon extends Item {
  private int damage;

  public Weapon(String description, int damage) {
    super(description);
    this.damage = damage;
  }

  public void useWeapon(Weapon weapon) {
    if (weapon instanceof RangedWeapon) {
      if (((RangedWeapon) weapon).getBullets() < 1) {

      }

    }
  }

}
