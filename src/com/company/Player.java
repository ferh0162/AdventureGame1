package com.company;

import java.util.ArrayList;

public class Player {
  private Room playerRoom;
  private Room requestedRoom;
  private ArrayList<Item> inventoryPlayer;

  public void setPlayerRoom(Room playerRoom) {
    this.playerRoom = playerRoom;
  }

  public Room getPlayerRoom() {
    return playerRoom;
  }

  public ArrayList<Item> getInventoryPlayer() {
    return inventoryPlayer;
  }

  public Player() {
    inventoryPlayer = new ArrayList<>();

  }

  public Item findItem(String itemName) {
    for (Item item : playerRoom.getItems()
    ) {
      if (item.getDescription().equals(itemName)) {
        return item;
      }
    }
    return null;
  }

  public String takeItem(String itemName) {
    Item item = findItem(itemName);
    if (item != null) {
      inventoryPlayer.add(item);
      playerRoom.removeItem(item);
      return "The Item " + itemName + " has been taken";
    } else {
      return "Denne item kan ikke tages op";
    }

  }

  public void goDirection(String direction) {
    requestedRoom = null;

    switch (direction) {
      case "north" -> requestedRoom = playerRoom.getNorth();
      case "south" -> requestedRoom = playerRoom.getSouth();
      case "east" -> requestedRoom = playerRoom.getEast();
      case "west" -> requestedRoom = playerRoom.getWest();
    }
    if (requestedRoom != null) {
      if (requestedRoom.isLocked()) {
        System.out.println("Its locked!");
      } else {
        playerRoom = requestedRoom;
        System.out.println("You are in a:");
        System.out.println(playerRoom.nameDescription());
      }
    } else {
      System.out.println("You went into the wall, there is no door here");
    }
  }


}
