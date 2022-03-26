package com.company;

import java.net.PortUnreachableException;
import java.util.Scanner;

public class GameMenu {
  public static final String TEXT_RESET = "\u001B[0m";
  public static final String TEXT_BLACK = "\u001B[30m";
  public static final String TEXT_RED = "\u001B[31m";
  public static final String TEXT_GREEN = "\u001B[32m";
  public static final String TEXT_YELLOW = "\u001B[33m";
  public static final String TEXT_BLUE = "\u001B[34m";
  public static final String TEXT_PURPLE = "\u001B[35m";
  public static final String TEXT_CYAN = "\u001B[36m";
  public static final String TEXT_WHITE = "\u001B[37m";

  private boolean gameStatus = true;
  private WorldCreator alltherooms;
  private Player player;


  public GameMenu() {
    player = new Player();
  }

  public void mainMenu() {
    alltherooms = new WorldCreator();
    alltherooms.allRooms();
    welcomeMessage();

    player.setPlayerRoom(alltherooms.getStarterRoom());

    while (gameStatus) {
      Scanner sc = new Scanner(System.in);
      System.out.print(TEXT_WHITE + "Shit.. where should i " + TEXT_BLUE + "go to" + TEXT_RESET + TEXT_WHITE + "?\nMaybe take a " + TEXT_BLUE + "look" + TEXT_RESET + TEXT_WHITE + " around or " + TEXT_BLUE +
          "search" + TEXT_RESET + TEXT_WHITE + " for items in the room?\nor " + TEXT_BLUE + "take | drop | eat | Equip | Unequip | Attack | Ammo | map |\n" + TEXT_RESET
          + TEXT_WHITE + "If you need help press: " + TEXT_BLUE + "HELP\n\n" + TEXT_PURPLE + "Whats you next move?: " + TEXT_RESET);

      String nextMove = sc.nextLine();
      nextMove = nextMove.toLowerCase();

      String text = nextMove;

      String førsteOrd = "";
      String andetOrd = "";
      int index = text.indexOf(' ');

      if (index > -1) { // Check if there is more than one word.

        førsteOrd = text.substring(0, index).trim(); // Extract first word.
        andetOrd = text.substring(index + 1, text.length());
        System.out.println("førsteord" + førsteOrd);
        System.out.println("andetord" + andetOrd);

        if (førsteOrd.equals("go")) {
          førsteOrd = andetOrd;
        }

      } else {
        førsteOrd = text; // Text is the first word itself.
      }

      System.out.println(text);
      System.out.println(førsteOrd);
      System.out.println(andetOrd);


      System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

      //footPrints();
      System.out.println("-----------------------------------------------------------");

      switch (førsteOrd) {
        case "e", "east", "go east" -> {
          System.out.println("going east");
          System.out.println();
          String move = "east";
          player.goDirection(move);
        }
        case "s", "south", "go south" -> {
          System.out.println("Going south");
          System.out.println();
          String move = "south";
          player.goDirection(move);
        }
        case "n", "north", "go north" -> {
          System.out.println("Going North");
          System.out.println();
          String move = "north";
          player.goDirection(move);
        }
        case "w", "west", "go west" -> {
          System.out.println("Going West");
          System.out.println();
          String move = "west";
          player.goDirection(move);
        }
        case "l", "look" -> {
          look();
        }
        case "show", "sh", "search" -> {
          // Viser hvilke items der er i det room som player er inden i
          System.out.println("searching for items in the room...");
          System.out.println("While searching for items,\nyou find this inside the room:");
          printItemsInRoom();
        }
        case "take", "t" -> {
          // Her tager vi en item og sætter den ind i playerens inventory
          if (text.equals("take")) {
            takeItem();
          } else {
            takeItem(andetOrd);
          }
        }
        case "drop", "d" -> {
          if (text.equals("drop")) {
            dropItems();
          } else {
            dropItems(andetOrd);
          }
        }
        case "inventory", "i" -> {
          System.out.print("Your inventory:");
          System.out.println(player.getPlayerInventory());
        }
        case "h", "help" -> {
          help();
        }
        case "map", "m" -> {
          showMap();
        }
        case "exit", "end" -> {
          exitGame();
        }
        case "eat" -> {
          eat();
        }
        case "teleport" -> {
          String teleport = sc.nextLine();
          player.teleport(teleport);
        }
        case "ammo", "magazine" -> {
          showAmmo();
        }
        case "equip" -> {
          equipWeapon();
        }
        case "use", "shoot", "attack" -> {
          useWeapon();
        }
        case "unequip" -> {
          unEquipWeapon();
        }
        default -> System.out.println("What are you trying to do? Make it make sense.\nWhat does " + TEXT_RED + førsteOrd + TEXT_RESET + " mean?");
      }
      System.out.println("-----------------------------------------------------------");

      //Check om spilleren har nøglen til rummet, hvis han har så lås rummet op
      checkRoomObstacles(player.getPlayerRoom());
      checkKey(player.getPlayerRoom());
      checkRope(player.getPlayerRoom());
      checkHealth();
      checkGameStatus();
      System.out.println();
    }
  }

  public void look() {
    System.out.println(player.getPlayerRoom().decsriptionDescription());
  }

  public void checkRoomObstacles(Room currenRoom) {
    if (player.getPlayerRoom().nameDescription() == "Inside swimming area") {
      System.out.println(TEXT_RED + "You start throwing up because of the terrible smell!" + TEXT_RESET);
      int playerLife = player.getHealth() - 15;
      player.setHealth(playerLife);
    } else if (player.getPlayerRoom().nameDescription() == "Prison yard") {
      System.out.println(TEXT_RED + "youre getting eaten by mosquitoes" + TEXT_RESET);
      int playerLife = player.getHealth() - 5;
      player.setHealth(playerLife);
    }
  }

  public void exitGame() {
    gameStatus = false;
    System.out.println("You have now committet suicide");
  }

  public void help() {
    System.out.println("Choose between the Actions:\n" +
        "North  =  " + TEXT_BLUE + "'n' or 'go north'\n" + TEXT_RESET +
        "East   =  " + TEXT_BLUE + "'e' or 'go east'\n" + TEXT_RESET +
        "West   =  " + TEXT_BLUE + "'w' or 'go west'\n" + TEXT_RESET +
        "South  =  " + TEXT_BLUE + "'s' or go 'south'\n" + TEXT_RESET);
    System.out.println(TEXT_BLUE + "'Look' or 'l'        " + TEXT_RESET + "To look arund the room");
    System.out.println(TEXT_BLUE + "'Show' or 'search'   " + TEXT_RESET + "To show the items in the room");
    System.out.println(TEXT_BLUE + "'Take' or 't'        " + TEXT_RESET + "To take  items. You will then be asked which item you want to choose");
    System.out.println(TEXT_BLUE + "'drop ' or 'd'       " + TEXT_RESET + "To drop an item");
    System.out.println(TEXT_BLUE + "'eat'                " + TEXT_RESET + "To eat an item");
    System.out.println(TEXT_BLUE + "'inventory'          " + TEXT_RESET + "To show inventory");
    System.out.println(TEXT_BLUE + "'map' or m'          " + TEXT_RESET + "To show map");
    System.out.println(TEXT_BLUE + "'Attack' or Shoot    " + TEXT_RESET + "To Attack");
    System.out.println(TEXT_BLUE + "'Exit' or 'end'      " + TEXT_RESET + "To exit the game");
    System.out.println(TEXT_BLUE + "'Help' or 'h'        " + TEXT_RESET + "If you need any help");
    System.out.println();

  }

  public void showMap() {
    System.out.println();
    System.out.println(TEXT_WHITE +
        "----------------------------------------------------\n" +
        "|                |                |                |\n" +
        "|  Hospital Room |  Mikails Cell  | Swimming Area  |\n" +
        "|                |                |                |\n" +
        "|----------------|----------------|----------------|\n" +
        "|                |                |                |\n" +
        "|    Canteen     |     Outside    |    Dark Room   |\n" +
        "|                |       Win      |                |\n" +
        "|----------------|----------------|----------------|\n" +
        "|                |                |                |\n" +
        "|    Party Room  |   Prison Yard  |  Torture Room  |\n" +
        "|                |                |                |\n" +
        "----------------------------------------------------\n"
        + TEXT_RESET
    );
  }

  public void checkKey(Room currentRoom) {
    for (Item item : player.getPlayerInventory()) {
      // Hvis item i Stirng == vores itemname, så skal den returnere vores item
      if (item.getDescription().equals("key")) {
        currentRoom.unlock();
      }
    }
  }

  public void checkRope(Room currentRoom) {
    for (Item item : player.getPlayerInventory()) {
      // Hvis item i Stirng == vores itemname, så skal den returnere vores item
      if (item.getDescription().equals("rope")) {
        currentRoom.escapeOpen();
      }
    }
  }

  public void checkGameStatus() {
    if (player.getPlayerRoom() == alltherooms.getWinnerRoom()) {
      gameStatus = false;
      System.out.println(player.getPlayerRoom().decsriptionDescription());
      System.out.println(TEXT_YELLOW + "You have won the game!" + TEXT_RESET);
    }
  }

  public void welcomeMessage() {
    System.out.println(TEXT_GREEN + "Welcome to the adventure game!");
    System.out.println();
    System.out.println(TEXT_YELLOW + "*THERE IS A NOTE ON THE TABLE*");
    System.out.println(TEXT_YELLOW + "In the notes it states:");
    System.out.println(TEXT_YELLOW + "Hello there prisoner 31");
    System.out.println(TEXT_YELLOW + "Covid-20 got taken outta hand, and therefore we have let everyone loose");
    System.out.println("The worlds future is not looking bright kid");
    System.out.println(TEXT_YELLOW + "You were in a coma, and we could not get everybody out, so we left you behind");
    System.out.println(TEXT_YELLOW + "If you wake up one day, you are free to go, every man is for himself");
    System.out.println(TEXT_YELLOW + "I wish you the best of luck\n *UGLY ASS SIGNATURE*\n2023/6/17" + TEXT_RESET);
    System.out.println();
    help();
  }

  public void dropItems() {
    Scanner sc = new Scanner(System.in);
    System.out.println("What item should be dropped?");
    System.out.print("Your inventory:");
    System.out.println(player.getPlayerInventory());
    String valgteItem = sc.nextLine();
    System.out.println();


    System.out.println(player.dropItem(valgteItem));
    System.out.print("Your inventory:");
    System.out.println(player.getPlayerInventory());
  }

  public void dropItems(String item) {
    String valgteItem = item;
    System.out.println();

    System.out.println(player.dropItem(valgteItem));
    System.out.print("Your inventory:");
    System.out.println(player.getPlayerInventory());
  }

  public void eat() {
    Scanner sc = new Scanner(System.in);
    System.out.println("What item should be eaten?");
    System.out.print("Your inventory:");
    System.out.println(player.getPlayerInventory());
    String valgteItem = sc.nextLine();
    System.out.println();

    System.out.println(player.eat(valgteItem));
    System.out.print("Your inventory:");
    System.out.println(player.getPlayerInventory());

  }

  public void showAmmo() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Which gun, would you like to check the magazine for?");
    if (player.getPlayerBelt().size() < 1) {
      System.out.println("Weapons in your inventory: ");
      System.out.println(player.getPlayerInventory());

      String valgteItem = sc.nextLine();
      System.out.println();
      player.showAmmoInventory(valgteItem);
    } else {
      System.out.print("Weapons on your belt: ");
      System.out.println(player.getPlayerBelt());

      String valgteItem = sc.nextLine();
      System.out.println();
      player.showAmmo(valgteItem);
    }

  }

  public void takeItem() {
    // Vi skriver her Items navn. Dvs. det item der skal tages op og sættes ind i inventory
    Scanner sc = new Scanner(System.in);
    System.out.print("What item should be added?\nItems in the Room: ");
    printItemsInRoom();
    String valgteItem = sc.nextLine();

    if (valgteItem.equals("shit")) {
      System.out.println("You dig your hands in the toilet and pickup a hard chunk of shit\nabsolutely disgusting...");
    }
    // her printer den, hvad metoden returnere.
    if (player.getInventorySize() <= player.getInventoryCapacity()) {
      System.out.println(player.takeItem(valgteItem));
      System.out.print("Your new inventory:");
      System.out.println(player.getPlayerInventory());
    } else {
      System.out.println(TEXT_RED + "Both your pockets are full\nNo more room in inventory" + TEXT_RESET);
    }

  }

  public void takeItem(String item) {
    // Vi skriver her Items navn. Dvs. det item der skal tages op og sættes ind i inventory

    String valgteItem = item;

    if (valgteItem.equals("shit")) {
      System.out.println("You dig your hands in the toilet and pickup a hard chunk of shit\nabsolutely disgusting...");
    }
    // her printer den, hvad metoden returnere.
    if (player.getInventorySize() <= player.getInventoryCapacity()) {
      System.out.println(player.takeItem(valgteItem));
      System.out.print("Your new inventory:");
      System.out.println(player.getPlayerInventory());
    } else {
      System.out.println(TEXT_RED + "Both your pockets are full\nNo more room in inventory" + TEXT_RESET);
    }

  }
  public void printItemsInRoom() {
    // Den returnere her inventorylist som er inden i rooms klassen
    // så den returnere inventorylist for det room som playeren er inden i
    System.out.println(TEXT_GREEN + player.getPlayerRoom().getItems() + TEXT_RESET);
  }

  public void checkHealth() {
    int playerHealth = player.getHealth();
    System.out.print(TEXT_CYAN + "Health status: " + TEXT_RESET + playerHealth + " ");
    if (0 < playerHealth && playerHealth <= 25) {
      System.out.println(TEXT_RED + "You have very low health, you are almost bleeding out" + TEXT_RESET);
    } else if (25 <= playerHealth && playerHealth < 50) {
      System.out.println(TEXT_RED + "You're health is low, take it easy" + TEXT_RESET);
    } else if (50 <= playerHealth && playerHealth < 75) {
      System.out.println(TEXT_YELLOW + "You're a little hurt, but you aight" + TEXT_RESET);
    } else if (75 <= playerHealth && playerHealth < 100) {
      System.out.println(TEXT_GREEN + "You health is in good condition. Dont worry" + TEXT_RESET);
    } else if (playerHealth <= 0) {
      System.out.println(TEXT_RED + "Your bleeding out your eyes and nose and everything turns black.\n" + TEXT_RESET +
          TEXT_BLACK + "All your memories goes through your mind\n" +
          "All the doctors yelling at you\n" +
          "Trying to wake you up\n" +
          "You got a chance in life and woke up from coma\n" +
          "You woke up from coma after 3 years\n" +
          "and still you fuck up....\n" +
          TEXT_RED + "You're dead if you haven't figured out" + TEXT_RESET);
      gameStatus = false;
    } else if (playerHealth == 100) {
      System.out.println(TEXT_GREEN + "You have full health" + TEXT_RESET);
    } else if (playerHealth > 100) {
      System.out.println(TEXT_GREEN + "You are stuffed, You have full health" + TEXT_RESET);
      player.setHealth(100);
    }
  }

  public void equipWeapon() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Which weapon, would you like to equip?");
    System.out.print("Your inventory: ");
    System.out.println(player.getPlayerInventory());
    String valgteItem = sc.nextLine();
    System.out.println();

    System.out.println(player.equip(valgteItem));
  }

  public void unEquipWeapon() {
    if (player.getPlayerInventory().size() <= player.getInventoryCapacity()) {
      Scanner sc = new Scanner(System.in);
      System.out.println("Which weapon, would you like to Unequip?");
      System.out.print("Your belt: ");
      System.out.println(player.getPlayerBelt());
      String valgteItem = sc.nextLine();
      System.out.println();

      System.out.println(player.unEquip(valgteItem));
    } else {
      System.out.println(TEXT_RED + "Inventory is Full" + TEXT_RESET + "You need to drop something first");
    }

  }

  public void useWeapon() {
    if (player.getPlayerBelt().size() < 1) {
      System.out.println("You dont have anything to attack with");
    } else {
      player.useWeapon(player.getEquippedWeapon());

    }
  }
}
