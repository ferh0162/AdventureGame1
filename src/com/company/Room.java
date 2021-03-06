package com.company;

import java.util.ArrayList;

public class Room {
  private String name;
  private String description;

  private Room north;
  private Room east;
  private Room west;
  private Room south;
  private ArrayList<Item> roomInventory;
  private ArrayList<Enemy> roomEnemies;

  private boolean locked;
  private boolean escape;

  public Room(String name, String description) {
    this.name = name;
    this.description = description;
    this.locked = false;
    roomInventory = new ArrayList<>();
    roomEnemies = new ArrayList<>();

  }

  public void addEnemy(Enemy enemy) {
    roomEnemies.add(enemy);
  }

  public ArrayList<Enemy> getEnemy() {
    return roomEnemies;
  }

  public int getnEnemyHealth() {
    return roomEnemies.get(0).getHealth();
  }

  public void addItem(Item item) {
    roomInventory.add(item);
  }

  public ArrayList<Item> getItems() {
    return roomInventory;
  }

  public void removeItem(Item item) {
    roomInventory.remove(item);
  }

  public String nameDescription() {
    return name;
  }

  public String decsriptionDescription() {
    return description;
  }

  public void lock() {
    locked = true;
  }

  public void unlock() {
    locked = false;
  }

  public void escapeClosed() {
    escape = true;
  }

  public void escapeOpen() {
    escape = false;
  }

  public boolean isEscapeOpen() {
    return escape;
  }

  public boolean isLocked() {
    return locked;
  }

  public Room getNorth() {
    return north;
  }

  public void setNorth(Room north) {
    this.north = north;
  }

  public Room getEast() {
    return east;
  }

  public void setEast(Room east) {
    this.east = east;
  }

  public Room getWest() {
    return west;
  }

  public void setWest(Room west) {
    this.west = west;
  }

  public Room getSouth() {
    return south;
  }

  public void setSouth(Room south) {
    this.south = south;
  }
}
