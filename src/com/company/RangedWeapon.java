package com.company;

public class RangedWeapon extends Weapon {
  private int bullets;

  public RangedWeapon(String description, int damage) {
    super(description, damage);
    int bullets = (int) Math.floor(Math.random() * (6 - 2 + 1) + 2);
    this.bullets = bullets;
  }

  public int getBullets() {
    return bullets;
  }

  public void setBullets(int bullets) {
    this.bullets = bullets;
  }

  public void useRangedGun() {
    this.bullets -= 1;
    if (bullets <= 0) {
      bullets = 0;
      System.out.println("no more ammo");
    } else {
      System.out.println("BANG!");
    }
  }
}
