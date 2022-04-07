package com.company;

import java.util.Locale;
import java.util.Scanner;

public class GameMenu {
  public static final String TEXT_RESET = "\u001B[0m";
  private boolean gameStatus = true;
  private WorldCreator alltherooms;
  private Player player;
  private int swimmingArea = 0; //Bruges til at tjekke om man har været i swimming area før
  private Room tidligererum;
  private int hospital = 0;
  private int canteen = 0;
  private int partyRoom = 0;
  private int prisonYard = 0;
  private int tortureRoom = 0;
  private int mikailscell = 0;
  private int DarkRoom = 0;
  private int swimmingRoom = 0;


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
      System.out.print(Color.BLUE + "Go + direction | Search | take | drop | eat | Equip | Unequip | Attack | Ammo | map |  HELP  |\n" + TEXT_RESET
          + Color.WHITE_BOLD + Color.PURPLE_BOLD + "Whats you next move?: " + TEXT_RESET);

      this.tidligererum = player.getPlayerRoom();

      String nextMove = sc.nextLine();
      nextMove = nextMove.toLowerCase();

      String text = nextMove;

      String firstWord = "";
      String secondWord = "";
      int index = text.indexOf(' ');

      if (index > -1) { // Check if there is more than one word.

        firstWord = text.substring(0, index).trim(); // Extract first word.
        secondWord = text.substring(index + 1, text.length());

        if (firstWord.equals("go")) {
          firstWord = secondWord;
        }

      } else {
        firstWord = text; // Text is the first word itself.
      }

      System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
      System.out.println("------------------------------------------------------------");

      switch (firstWord) {
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
          System.out.println("...");
          System.out.println("...");
          System.out.println("While searching for items,\nyou find this inside the room:");
          printItemsInRoom();
        }
        case "take", "t" -> {
          // Her tager vi en item og sætter den ind i playerens inventory
          if (text.equals("take")) {
            takeItem();
          } else {
            takeItem(secondWord);
          }
        }
        case "drop", "d" -> {
          if (text.equals("drop") || text.equals("d")) {
            dropItems();
          } else {
            dropItems(secondWord);
          }
        }
        case "inventory", "i" -> {
          System.out.print("Your inventory:");
          System.out.println(player.getPlayerInventory());
          System.out.print("Weapons on your belt");
          System.out.println(player.getPlayerBelt());
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
        case "use", "eat" -> {
          if (text.equals("eat")) {
            eat();
          } else if (text.equals("use")) {
            eat();
          } else {
            eat(secondWord);
          }
        }
        case "teleport" -> {
          String teleport = sc.nextLine();
          player.teleport(teleport);
        }
        case "ammo", "magazine" -> {
          showAmmo();
        }
        case "equip" -> {
          if (text.equals("equip")) {
            equipWeapon();
          } else {
            equipWeapon(secondWord);
          }
        }
        case "shoot", "attack" -> {
          attack();
        }
        case "unequip" -> {
          unEquipWeapon();
        }
        default -> System.out.println("What are you trying to do? Make it make sense.\nWhat does " + Color.RED + firstWord + TEXT_RESET + " mean?");
      }
      //check om der er enemies i rummet, og giv besked til spilleren
      showEnemies();
      checkLook();

      //check om der er obstacles i rummet
      checkRoomObstacles(player.getPlayerRoom());

      System.out.println("------------------------------------------------------------");
      checkHealth();

      //Check om spilleren har nøglen til rummet, hvis han har så lås rummet op
      checkKey(player.getPlayerRoom());
      checkRope(player.getPlayerRoom());
      checkGameStatus();
      System.out.println();
    }
  }

  public void look() {
    System.out.println(player.getPlayerRoom().decsriptionDescription());
  }

  public void checkLook() {
    if (player.getPlayerRoom().nameDescription() == "Prison hospital room") {
      if (this.hospital == 0) {
        look();
        this.hospital++;
      }
    }
    if (player.getPlayerRoom().nameDescription() == "Mikails Cell") {
      if (mikailscell == 0) {
        look();
      }
      this.mikailscell++;
    }
    if (player.getPlayerRoom().nameDescription() == "Inside swimming area") {
      if (swimmingRoom == 0) {
        look();
      }
      this.swimmingRoom++;
    }
    if (player.getPlayerRoom().nameDescription() == "Prison canteen") {
      if (this.canteen == 0) {
        look();
        this.canteen++;

      }
    }
    if (player.getPlayerRoom().nameDescription() == "The dark room") {
      if (this.DarkRoom == 0) {
        look();
        this.DarkRoom++;
      }
    }
    if (player.getPlayerRoom().nameDescription() == "Partyroom") {
      if (partyRoom == 0) {
        look();
        this.partyRoom++;
      }
    }
    if (player.getPlayerRoom().nameDescription() == "Prison yard") {
      if (this.prisonYard == 0) {
        look();
      }
      this.prisonYard++;
    }
    if (player.getPlayerRoom().nameDescription() == "Torture room") {
      if (tortureRoom == 0) {
        look();
      }
      this.tortureRoom++;
    }
  }

  public void checkRoomObstacles(Room currenRoom) {
    if (player.getPlayerRoom().nameDescription() == "Inside swimming area") {
      // Only throws up once
      if (swimmingArea < 1) {
        swimmingArea++;
        System.out.println(Color.RED + "You start throwing up because of the terrible smell!" + TEXT_RESET);
        int playerLife = player.getHealth() - 15;
        player.setHealth(playerLife);
      }
    } else if (player.getPlayerRoom().nameDescription() == "Prison yard") {
      System.out.println(Color.RED + "you're getting eaten by mosquitoes" + TEXT_RESET);
      int playerLife = player.getHealth() - 3;
      player.setHealth(playerLife);
    }
  }

  public void exitGame() {
    gameStatus = false;
    System.out.println("You have now committet suicide");
  }

  public void help() {
    System.out.println("Choose between the Actions:\n" +
        "North  =  " + Color.BLUE + "'n' or 'go north'\n" + TEXT_RESET +
        "East   =  " + Color.BLUE + "'e' or 'go east'\n" + TEXT_RESET +
        "West   =  " + Color.BLUE + "'w' or 'go west'\n" + TEXT_RESET +
        "South  =  " + Color.BLUE + "'s' or go 'south'\n" + TEXT_RESET);
    System.out.println(Color.BLUE + "'Look' or 'l'        " + TEXT_RESET + "To look arund the room");
    System.out.println(Color.BLUE + "'Show' or 'search'   " + TEXT_RESET + "To show the items in the room");
    System.out.println(Color.BLUE + "'Take' or 't'        " + TEXT_RESET + "To take  items. You will then be asked which item you want to choose");
    System.out.println(Color.BLUE + "'drop ' or 'd'       " + TEXT_RESET + "To drop an item");
    System.out.println(Color.BLUE + "'eat'                " + TEXT_RESET + "To eat an item");
    System.out.println(Color.BLUE + "'inventory'          " + TEXT_RESET + "To show inventory");
    System.out.println(Color.BLUE + "'map' or m'          " + TEXT_RESET + "To show map");
    System.out.println(Color.BLUE + "'Attack' or Shoot    " + TEXT_RESET + "To Attack");
    System.out.println(Color.BLUE + "'Exit' or 'end'      " + TEXT_RESET + "To exit the game");
    System.out.println(Color.BLUE + "'Help' or 'h'        " + TEXT_RESET + "If you need any help");
    System.out.println();
    System.out.println(Color.YELLOW + "NEW FEATURE!" + Color.RESET + " You can also " + Color.BOXING + " take + itemname " + Color.RESET);
    System.out.println();


  }

  public void showMap() {
    System.out.println();
    System.out.println(Color.BANANA_YELLOW +
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
      System.out.println(Color.YELLOW + "You have won the game!" + TEXT_RESET);
    }
  }

  public void welcomeMessage() {
    System.out.println(Color.GREEN + "Welcome to the adventure game!");
    System.out.println();
    help();
    System.out.println(Color.GREEN_BOLD_BRIGHT + "PRESS ENTER TO START GAME" + Color.RESET);
    Scanner sc = new Scanner(System.in);
    String enter = sc.nextLine();
    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    System.out.println("Starting game!");
    System.out.println(".");
    System.out.println("..");
    System.out.println("...");
    System.out.println("....");
    System.out.println("......");
    System.out.println("LOADING..");
    System.out.println("....");
    System.out.println("....");
    System.out.println("....");
    System.out.println("....");
    System.out.println("LOADING..");
    System.out.println("....");
    System.out.println("....");
    System.out.println("....");
    System.out.println("....");
    System.out.println("LOADING..");


    System.out.println();
    System.out.println(Color.YELLOW + "*THERE IS A NOTE ON THE TABLE*");
    System.out.println("In the notes it states:");
    System.out.println("Hello there prisoner 31");
    System.out.println("Covid-20 got taken outta hand, and therefore we have let everyone loose");
    System.out.println("The worlds future is not looking bright kid");
    System.out.println("You were in a coma, and we could not get everybody out, so we left you behind");
    System.out.println("If you wake up one day, you are free to go, every man is for himself");
    System.out.println("I wish you the best of luck\n *UGLY ASS SIGNATURE*\n2023/6/17" + TEXT_RESET);
    System.out.println();
    System.out.print(Color.WHITE_BOLD + "Where should i " + Color.BLUE + "go to" + TEXT_RESET + Color.WHITE_BOLD + "? Maybe take a " + Color.BLUE + "look" + TEXT_RESET + Color.WHITE_BOLD + " around or " + Color.BLUE +
        "search" + TEXT_RESET + Color.WHITE_BOLD + " for items in the room?\nor " + Color.BLUE + "take | drop | eat | Equip | Unequip | Attack | Ammo | map |  HELP  |\n\n" + TEXT_RESET
        + TEXT_RESET);
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

  public void eat(String item) {
    System.out.println(player.eat(item));
    System.out.print("Your new inventory:");
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
      System.out.println(Color.RED + "Both your pockets are full\nNo more room in inventory" + TEXT_RESET);
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
      System.out.println(Color.RED + "Both your pockets are full\nNo more room in inventory" + TEXT_RESET);
    }

  }

  public void printItemsInRoom() {
    // Den returnere her inventorylist som er inden i rooms klassen
    // så den returnere inventorylist for det room som playeren er inden i
    System.out.println(Color.GREEN + player.getPlayerRoom().getItems() + TEXT_RESET);
  }

  public void checkHealth() {
    int playerHealth = player.getHealth();
    System.out.print("                I " + Color.ROSY_PINK + "HP" + TEXT_RESET + " " + playerHealth + " ");
    if (0 < playerHealth && playerHealth <= 25) {
      System.out.print(Color.RED + "You have very low health, you are almost bleeding out" + TEXT_RESET);
    } else if (25 <= playerHealth && playerHealth < 50) {
      System.out.print(Color.RED + "You're health is low, take it easy" + TEXT_RESET);
    } else if (50 <= playerHealth && playerHealth < 75) {
      System.out.print(Color.YELLOW + "You're a little hurt, but you aight" + TEXT_RESET);
    } else if (75 <= playerHealth && playerHealth < 100) {
      System.out.print(Color.GREEN + "You health is in good condition. Dont worry" + TEXT_RESET);
    } else if (playerHealth <= 0) {
      System.out.print(Color.RED + "  |\n" +
          Color.BLACK + "All your memories goes through your mind\n" +
          "All the doctors yelling at you\n" +
          "Trying to wake you up\n" +
          "You got a chance in life and woke up from coma\n" +
          "You woke up from coma after 3 years\n" +
          "and still you fuck up....\n" +

          Color.RED + "Your bleeding out your eyes and nose and everything turns black.\n" + "You're dead if you haven't figured out" + TEXT_RESET);
      gameStatus = false;
    } else if (playerHealth == 100) {
      System.out.print(Color.GREEN + "You have full health" + TEXT_RESET);
    } else if (playerHealth > 100) {
      System.out.print(Color.GREEN + "You are stuffed, You have full health" + TEXT_RESET);
      player.setHealth(100);
    }
    System.out.println("  I");
  }

  public void equipWeapon() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Which weapon, would you like to equip?");
    System.out.print("Your inventory: ");
    System.out.println(player.getPlayerInventory());
    String valgteItem = sc.nextLine();
    System.out.println();
    System.out.println(player.equip(valgteItem));
    System.out.println("Remember to unequip your weapon if you already have one equipped. You cant use both");
    System.out.print("Your equipped weapons / Playerbelt: ");
    System.out.println(player.getPlayerBelt());

  }

  public void equipWeapon(String item) {
    String valgteItem = item;
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
      System.out.println(Color.RED + "Inventory is Full" + TEXT_RESET + "You need to drop something first");
    }

  }

  public void showEnemies() {
    System.out.println();
    Scanner sc = new Scanner(System.in);
    if (player.getPlayerRoom().getEnemy().size() < 1) {
      System.out.print("");
    } else {
      System.out.println(Color.RED + "------------------------------------------------------------" + Color.RESET);
      Enemy nearestEnemy = player.findEnemyinRoom(); //finds nearest enemy
      System.out.println(Color.RED + "You have encountered an enemy!!");
      System.out.println();
      System.out.println(Color.RED_BRIGHT + nearestEnemy.getEnemyName() + Color.RESET + " Is looking at you very angrily!");
      System.out.println();
      System.out.println(Color.YELLOW_BRIGHT + "Choose between the actions: ");
      System.out.println("1. Attack" + Color.BLACK + " If you have the balls to do it" + Color.RESET);
      System.out.println(Color.YELLOW_BRIGHT + "2. Run" + Color.BLACK + " If youre a little pussy" + Color.RESET);
      System.out.println(Color.RED + "--------------------------------" + Color.RESET);
      System.out.print(Color.YELLOW_BRIGHT + "Whats do you Choose? !!: " + TEXT_RESET);
      String newMove = sc.nextLine().toLowerCase(Locale.ROOT);
      System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
      switch (newMove) {
        case "attack", "1" -> {
          attack();
          showEnemies();
          checkHealth();
        }
        case "run", "2" -> {
          System.out.println();
          System.out.println("------------------------------------------------------------");
          System.out.println("You are back in: " + tidligererum.nameDescription());
          player.setPlayerRoom(tidligererum);
        }
        default -> {
          System.out.println(Color.RED + newMove + Color.RESET + " is an invalid move");
          System.out.println();
          System.out.println("------------------------------------------------------------");
          System.out.println("You are back in: " + tidligererum.nameDescription());
          player.setPlayerRoom(tidligererum);
        }
      }
    }
  }


  public void attack() {
    if (player.getPlayerBelt().size() < 1) {
      System.out.println("You dont have any weapon equipped!");
    } else {
      player.useWeapon(player.getEquippedWeapon());
      //hvis spillerens har skud i magasinet, så attack enemy

    }
  }

}
