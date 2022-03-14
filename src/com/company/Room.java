package com.company;

public class Room {
  private String name;
  private String description;

  private Room north;
  private Room east;
  private Room west;
  private Room south;

  Room room1 = new Room("NorthWest", "Start rum");
  Room room2 = new Room("North", "ja tak");

  room1.setEast(room2)

  public Room(String name, String description){
    this.name = name;
    this.description = description;

  }

  public void setEast(Room east) {
    this.east = east;
  }
}
