package com.company;

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

      String nextMove = sc.nextLine();
      nextMove = nextMove.toLowerCase();

      switch (nextMove) {
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
        case "l", "look", "look around" -> {
          look();
        }
        // Viser hvilke items der er i det room som player er inden i
        case "show", "show items", "sh" -> {
          printItemsInRoom();
        }
        // Her tager vi en item og sætter den ind i playerens inventory
        case "take", "take items", "take item", "t" -> {
          takeItem();
        }
        case "drop item", "drop", "d" -> {
          dropItems();
        }
        case "inventory", "show inventory", "i", "show inv" -> {
          System.out.print("Your inventory:");
          System.out.println(player.getPlayerInventory());
        }
        case "h", "help", "need help" -> {
          help();
        }
        case "exit", "end" -> {
          exitGame();
        }
        case "eat" -> {
          eat();
        }
        default -> System.out.println("What are you trying to do? Make it make sense. What does " + nextMove + " mean?");
      }
      //Check om spilleren har nøglen til rummet, hvis han har så lås rummet op
      checkKey(player.getPlayerRoom());
      checkCrowbar(player.getPlayerRoom());
      checkRope(player.getPlayerRoom());
      checkHealth();
      checkGameStatus();

      System.out.println();
      System.out.println("What's your next move?");
      System.out.println();
    }
  }

  public void look() {
    System.out.println(player.getPlayerRoom().decsriptionDescription());
  }

  public void exitGame() {
    gameStatus = false;
    System.out.println("You have now committet suicide");
  }
  public void help() {
    System.out.println("Choose between the Actions:\n" +
        "North =" + TEXT_BLUE + "'n' or 'go north'\n" + TEXT_RESET +
        "East =" + TEXT_BLUE + " 'e' or 'go east'\n" + TEXT_RESET +
        "West =" + TEXT_BLUE + " 'w' or 'go west'\n" + TEXT_RESET +
        "South = " + TEXT_BLUE + "'s' or go 'south'\n" + TEXT_RESET);
    System.out.println(TEXT_BLUE + "'Look' or 'l'\t" + TEXT_RESET + "To look arund the room");
    System.out.println(TEXT_BLUE + "'Show' or 'sh'\t" + TEXT_RESET + "To show the items in the room");
    System.out.println(TEXT_BLUE + "'Take' or 't'\t" + TEXT_RESET + "To take  items. You will then be asked which item you want to choose");
    System.out.println(TEXT_BLUE + "'drop ' or 'd'\t" + TEXT_RESET + "To drop an item");
    System.out.println(TEXT_BLUE + "'eat'\t\t\t" + TEXT_RESET + " To eat an item");
    System.out.println(TEXT_BLUE + "'Show inventory'" + TEXT_RESET + "To show inventory");
    System.out.println(TEXT_BLUE + "'Exit' or 'e'\t" + TEXT_RESET + "To exit the game");
    System.out.println(TEXT_BLUE + "'Help' or 'h'\t" + TEXT_RESET + "If you need any help");
    System.out.println();
    System.out.print("Now choose your next move: ");
    System.out.println();
  }

  public void checkCrowbar(Room currentRoom) {
    for (Item item : player.getPlayerInventory()) {
      // Hvis item i Stirng == vores itemname, så skal den returnere vores item
      if (item.getDescription().equals("crowbar")) {
        currentRoom.unlock();
      }
    }
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
        currentRoom.unlock();
      }
    }
  }

  public void checkGameStatus() {
    if (player.getPlayerRoom() == alltherooms.getWinnerRoom()) {
      gameStatus = false;
      System.out.println(player.getPlayerRoom().decsriptionDescription());
      System.out.println("You have won the game");
    }
  }

  public void welcomeMessage() {
    System.out.println(TEXT_GREEN + "Welcome to the adventure game!");
    System.out.println();
    System.out.println(TEXT_YELLOW + "*THERE IS A NOTE ON THE TABLE*");
    System.out.println(TEXT_YELLOW + "In the notes it states:");
    System.out.println(TEXT_YELLOW + "Hello there prisoner 31");
    System.out.println(TEXT_YELLOW + "World war 3 has begun, and therefore we have let everyone loose");
    System.out.println(TEXT_YELLOW + "You were in a coma, and we could not get everybody out, so we left you behind");
    System.out.println(TEXT_YELLOW + "If you wake up one day, you are free to go, but it wont be easy to leave, most doors are still locked");
    System.out.println(TEXT_YELLOW + "I wish you the best of luck" + TEXT_RESET);
    GameMenu instructions = new GameMenu();
    instructions.help();
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

  public void takeItem() {
    // Vi skriver her Items navn. Dvs. det item der skal tages op og sættes ind i inventory
    Scanner sc = new Scanner(System.in);
    System.out.println("What item should be added?");
    String valgteItem = sc.nextLine();
    System.out.println();

    // her printer den, hvad metoden returnere.
    System.out.println(player.takeItem(valgteItem));
    System.out.print("Your inventory:");
    System.out.println(player.getPlayerInventory());
  }

  public void printItemsInRoom() {
    // Den returnere her inventorylist som er inden i rooms klassen
    // så den returnere inventorylist for det room som playeren er inden i
    System.out.println(player.getPlayerRoom().getItems());
  }

  public void checkHealth() {
    int playerHealth = player.getHealth();
    System.out.print(TEXT_CYAN + "Health status: " + TEXT_RESET);
    if (playerHealth <= 25) {
      System.out.println(TEXT_RED + "You have very low health, you are almost bleeding out" + TEXT_RESET);
    } else if (25 <= playerHealth && playerHealth < 50) {
      System.out.println(TEXT_RED + "You're health is low, take it easy" + TEXT_RESET);
    } else if (50 <= playerHealth && playerHealth < 75) {
      System.out.println(TEXT_YELLOW + "You're a little hurt, but you aight" + TEXT_RESET);
    } else if (75 <= playerHealth && playerHealth <= 100) {
      System.out.println(TEXT_GREEN + "You health is in good condition. You could need a snack though" + TEXT_RESET);
    } else if (playerHealth <= 0) {
      System.out.println(TEXT_RED + "Your bleeding out, and everything turns black.\n You're dead if you haven't figured out" + TEXT_RESET);
      gameStatus = false;
    } else if (playerHealth == 100) {
      System.out.println(TEXT_GREEN + "You have full health" + TEXT_RESET);
    } else if (playerHealth > 100) {
      System.out.println(TEXT_GREEN + "You are stuffed, You have full health" + TEXT_RESET);
      player.setHealth(100);
    }
  }
}
