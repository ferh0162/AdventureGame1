package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
  private Room playerRoom;
  private Room requestedRoom;
  private ArrayList<Item> playerInventory;
  private ArrayList<Weapon> playerBelt;
  private int invetoryMaxCapacity;
  private int health;

  public Player() {
    playerInventory = new ArrayList<>();
    this.invetoryMaxCapacity = 1; //1 er 2. Husk at index starter på 0
    this.health = 100;
    playerBelt = new ArrayList<>();
  }

  public int getInventoryCapacity() {
    return invetoryMaxCapacity;
  }

  public int getInventorySize() {
    return playerInventory.size();
  }

  public void setPlayerRoom(Room playerRoom) {
    this.playerRoom = playerRoom;
  }

  public Room getPlayerRoom() {
    return playerRoom;
  }

  public ArrayList<Item> getPlayerInventory() {
    return playerInventory;
  }

  public ArrayList<Weapon> getPlayerBelt() {
    return playerBelt;
  }

  public String getEquippedWeapon() {
    return String.valueOf(playerBelt.get(0));
  }

  public Item findIteminRoom(String itemName) {
    // her looper vi gennemm de items som der er i rummet, og ser om vores efterspurgte item ligger her

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

  public Enemy findEnemyinRoom(String enemyName) {
    for (Enemy enemy : playerRoom.getEnemy()) {
      if (playerRoom.getEnemy().equals(enemyName)) {
        return enemy;
      }
    }
    return null;
  }

  public Enemy findEnemyinRoom() {
    return playerRoom.getEnemy().get(0);
  }

  public boolean hasKey() {
    // Her loop den de items som er inde i player room igennem. .getItems() returnere blot roominventory. (array af items i roomet)
    for (Item item : getPlayerInventory()) {
      // Hvis item i Stirng == vores itemname, så skal den returnere vores item
      if (item.getDescription().equals("key")) {
        System.out.println("The door is now unlocked");
        System.out.println();
        return true;
      }
    }
    // ellers returnere den null
    return false;
  }

  public boolean hasRope() {
    // Her loop den de items som er inde i player room igennem. .getItems() returnere blot roominventory. (array af items i roomet)
    for (Item item : getPlayerInventory()) {
      // Hvis item i Stirng == vores itemname, så skal den returnere vores item
      if (item.getDescription().equals("rope")) {
        System.out.println("You used the rope to climb over the wall...");
        System.out.println();
        return true;
      }
    }
    // ellers returnere den null
    return false;
  }


  public Item findIteminPlayerInventory(String itemName) {
    // Her loop den de items som er inde i player room igennem. .getItems() returnere blot roominventory. (array af items i roomet)
    for (Item item : getPlayerInventory()) {
      // Hvis item i Stirng == vores itemname, så skal den returnere vores item
      if (item.getDescription().equals(itemName)) {
        return item;
      }
    }
    // ellers returnere den null
    return null;
  }

  public Item findIteminPlayerBelt(String itemName) {
    // Her loop den de items som er inde i player room igennem. .getItems() returnere blot roominventory. (array af items i roomet)
    for (Weapon item : getPlayerBelt()) {
      // Hvis item i Stirng == vores itemname, så skal den returnere vores item
      if (item.getDescription().equals(itemName)) {
        return item;
      } else {
        System.out.println(itemName + " does not exist in inventory");
      }
    }
    // ellers returnere den null
    return null;
  }

  public String takeItem(String itemName /* "Kniv" */) {
    // Vi modtager her en items navn. Dvs. den item som skal samles op. F.eks. "Kniv"
    // Herfra bruger vi metoden findItem, til at finde "kniv" i rummet
    Item item = findIteminRoom(itemName/* "Kniv" */);

    // Hvis item ikke er null, dvs. Item er true, så betyder det at der er en item der hedder "kniv"
    if (item != null) {

      // Herfra tilføjer vi item, som vi lige har oprettet, til playerinventorys Arraylist
      playerInventory.add(item /* "Kniv" */);

      // Her sletter vi så item kniv fra rummet. Dvs. det room som playeren er ind, skal have et item fjernet
      playerRoom.removeItem(item /* "Kniv" */);
      return "The Item " + "\u001B[32m" + itemName + "\u001B[0m" + " has been taken";

      // Hvis item er == null, så betyder det at der ikke er et item som matchter
    } else {
      System.out.println("\u001B[31m" + itemName + "\u001B[0m" + " does not exist in the room\n");

      return "";
    }

  }

  public String dropItem(String itemName) {
    Item item = findIteminPlayerInventory(itemName);

    if (item != null) {
      playerInventory.remove(item);

      playerRoom.addItem(item);
      System.out.print("You have dropped the item " + itemName);
    } else {
      return "This item does not exist in your inventory";
    }
    return "";
  }

  public String eat(String itemName) {
    Item item = findIteminPlayerInventory(itemName);

    if (item instanceof Food) {
      if (((Food) item).getHealthPoints() < 0) { //If food is poison
        System.out.println("\u001B[31m" + "What the fuck did i just eat...\n" +
            "I dont feel so good" + "\u001B[0m");

      } else if (((Food) item).getHealthPoints() > 0 && health == 100) { //if player has full health and decides to eat
        System.out.println("Well that was a waste off food\n" +
            "I was already full");
        //  health += ((Food) item).getHealthPoints();
      } else if ((((Food) item).getHealthPoints() + health) > 100) { //if food is more han enough
        System.out.println("There is too much to eat, i am full now");
      }
      playerInventory.remove(item);

      System.out.println("You have eaten: " + itemName);
      health += ((Food) item).getHealthPoints();
      if (item.getDescription() == "pill") {
        System.out.print("\u001B[37m" + "Damn you're a fucking idiot" + "\u001B[0m");
      }
    } else {
      return Color.RED + itemName + Color.RESET + " is not eatable";
    }
    System.out.println();
    return "";
  }

  public String showAmmo(String itemName) {
    Item item = findIteminPlayerBelt(itemName);

    if (item instanceof RangedWeapon) {
      System.out.println(itemName + " has " + ((RangedWeapon) item).getNumberOfUsesLeft() + " bullets left");
    } else {
      System.out.println("This item does not have a magazine");
    }

    return "";
  }

  public String showAmmoInventory(String itemName) {
    Item item = findIteminPlayerInventory(itemName);

    if (item instanceof RangedWeapon) {
      System.out.println(itemName + " has " + ((RangedWeapon) item).getNumberOfUsesLeft() + " bullets left");
    } else {
      System.out.println("This item does not have a magazine");
    }

    return "";
  }

  public String equip(String itemName) {
    Item item = findIteminPlayerInventory(itemName);

    if (item instanceof Weapon) {
      // Hvis item ikke er null, dvs. Item er true, så betyder det at der er en item der hedder "kniv"
      if (item != null) {

        // Herfra tilføjer vi item, som vi lige har oprettet, til playerinventorys Arraylist
        playerBelt.add((Weapon) item);

        // Her sletter vi så item kniv fra rummet. Dvs. det room som playeren er ind, skal have et item fjernet
        removeFromPlayerInventory(item /* "Kniv" */);
        return "The Item " + "\u001B[32m" + itemName + "\u001B[0m" + " has been equipped";

      }
    }
    return itemName + " does not exist";
  }

  public String unEquip(String itemName) {
    Item item = findIteminPlayerBelt(itemName);

    if (item != null) {
      playerBelt.remove(item);

      playerInventory.add(item);
      System.out.print("You have uneqipped the item " + itemName);
    } else {
      return "Your inventory is full";
    }
    return "";
  }

  public void useWeapon(String itemName) {
    Item item = findIteminPlayerBelt(itemName); //finder våbnet i inventory, og tjekker om det findes

    if (((Weapon) item).getNumberOfUsesLeft() == 0) {
      System.out.println("You dont have no more ammo left");
    } else if (playerRoom.getEnemy().size() < 1) {
      System.out.println("There is no enemy");
    } else {
      ((Weapon) item).useWeapon();
      Enemy nearestEnemy = findEnemyinRoom(); //finds nearest enemy
      System.out.println("You attacked: " + Color.RED_BOLD + nearestEnemy.getEnemyName() + Color.RESET);

      //fjerner liv fra enemy
      int enemyHealth = nearestEnemy.getHealth();
      int enemyNewHealth = enemyHealth - ((Weapon) item).getDamage();
      nearestEnemy.setHealth(enemyNewHealth);
      if (enemyNewHealth <= 0) {
        System.out.println(Color.GREEN_BOLD + "Enemy died" + Color.RESET);
        String enemyWeapon = nearestEnemy.getEnemyInventory().get(0).toString();
        playerRoom.addItem(nearestEnemy.getEnemyInventory().get(0));
        nearestEnemy.getEnemyInventory().remove(0);

        System.out.println("Enemy have dropped the item " + enemyWeapon);
        playerRoom.getEnemy().remove(0);


      } else {
        System.out.println("Enemy health: " + Color.GREEN + nearestEnemy.getHealth() + Color.RESET);
        int enemyDamage = nearestEnemy.getEnemyInventory().get(0).getDamage();

        System.out.println(Color.RED_BOLD + "Enemy damaged you: " + enemyDamage + Color.RESET);
        this.health -= enemyDamage;
      }
    }
  }

  public void removeFromPlayerInventory(Item item) {
    playerInventory.remove(item);
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
      if (requestedRoom.isLocked() && !hasKey()) {
        System.out.println("Its locked!");
      } else if (requestedRoom.isEscapeOpen() && !hasRope()) {
        System.out.println("its just a big wall/fence, but the barb wires are all broken");
        System.out.println("hmmm......\nmaybe i could use something to climb over the wall with?");
      } else {
        playerRoom = requestedRoom;
        System.out.println("You are in:");
        System.out.println(playerRoom.nameDescription());
      }
    } else {
      System.out.println("You went into the wall, there is no door here");
    }
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public void teleport(String direction) {
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
      playerRoom = requestedRoom;
      System.out.println("You are in:");
      System.out.println(playerRoom.nameDescription());
    } else {
      System.out.println("You went into the wall, there is no door here");
    }
  }
}
