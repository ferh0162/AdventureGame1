package com.company;

import java.util.ArrayList;

public class Player {
  private Room playerRoom;
  private Room requestedRoom;
  private ArrayList<Item> playerInventory;

  public void setPlayerRoom(Room playerRoom) {
    this.playerRoom = playerRoom;
  }

  public Room getPlayerRoom() {
    return playerRoom;
  }

  public ArrayList<Item> getPlayerInventory() {
    return playerInventory;
  }

  public Player() {
    playerInventory = new ArrayList<>();

  }

  // her looper vi gennemm de items som der er i rummet, og ser om vores efterspurgte item ligger her
  public Item findItem(String itemName) {
    // Her loop den de items som er inde i player room igennem. .getItems() returnere blot roominventory. (array af items i roomet)
    for (Item item : playerRoom.getItems()) {
      // Hvis item i Stirng == vores itemname, så skal den returnere vores item
      if (item.getDescription().equals(itemName)) {
        return item;
      }
    }
    // ellers returnere den null
    return null;
  }

  public String takeItem(String itemName /* "Kniv" */) {
    // Vi modtager her en items navn. Dvs. den item som skal samles op. F.eks. "Kniv"
    // Herfra bruger vi metoden findItem, til at finde "kniv" i rummet
    Item item = findItem(itemName/* "Kniv" */);

    // Hvis item ikke er null, dvs. Item er true, så betyder det at der er en item der hedder "kniv"
    if (item != null) {

      // Herfra tilføjer vi item, som vi lige har oprettet, til playerinventorys Arraylist
      playerInventory.add(item /* "Kniv" */);

      // Her sletter vi så item kniv fra rummet. Dvs. det room som playeren er ind, skal have et item fjernet
      playerRoom.removeItem(item /* "Kniv" */);
      return "The Item " + itemName + " has been taken";

      // Hvis item er == null, så betyder det at der ikke er et item som matchter
    } else {
      return "Denne item kan ikke tages op";
    }

  }

  public void goDirection(String direction) {
    // Vi sætter vores requested room til null, da vi skal bruge dens boolean længere ned i et if statetement.
    // Når et room er null, vil det betyder at man ikke kan komme igennem.
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
