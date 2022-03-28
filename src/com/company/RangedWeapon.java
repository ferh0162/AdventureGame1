package com.company;

public class RangedWeapon extends Weapon {

  public RangedWeapon(String description, int damage) {
    super(description, damage);
    int numberOfUses = (int) Math.floor(Math.random() * (6 - 2 + 1) + 2);
    setNumberOfUsesLeft(numberOfUses);
  }

  }

