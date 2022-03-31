package com.company;

import java.util.ArrayList;

public class Enemy {
  private int health;
  private String name;
  private ArrayList<Weapon> enemyInventory;

  public Enemy(String name, int health, Weapon weapon) {
    this.health = health;
    this.name = name;
    enemyInventory = new ArrayList<>();
    enemyInventory.add(weapon);
  }

  public String getEnemyName() {
    return name;
  }

  public int getHealth() {
    return health;
  }

  public ArrayList<Weapon> getEnemyInventory() {
    return enemyInventory;
  }

  public void setHealth(int health) {
    this.health = health;
  }
}
