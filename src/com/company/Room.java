package com.company;

public class Room {
  private String name;
  private String description;

  private Room north;
  private Room east;
  private Room west;
  private Room south;


  public Room(String name, String description){
    this.name = name;
    this.description = description;

  }

  @Override
  public String toString() {
    return
        description;
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
