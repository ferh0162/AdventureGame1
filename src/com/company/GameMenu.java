package com.company;

import java.util.Scanner;

public class GameMenu {
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
        case "inventory", "show inventory", "i" -> {
          System.out.print("Your inventory:");
          System.out.println(player.getPlayerInventory());
        }
        case "h", "help", "need help" -> {
          help();
        }
        case "exit", "end" -> {
          exitGame();
        }
        default -> System.out.println("What are you trying to do? Make it make sense. What does " + nextMove + "mean?");
      }
      System.out.println();
      System.out.println("What's your next move?");

      //Check om spilleren har nøglen til rummet, hvis han har så lås rummet op
      checkKey(player.getPlayerRoom());
      checkRope(player.getPlayerRoom());


      checkGameStatus();
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
        "North = 'n' or 'go north'\n" +
        "East = 'e' or 'go east'\n" +
        "West = 'w' or 'go west'\n" +
        "South = 's' or go 'south'\n");
    System.out.println("'Look' or 'l'\tTo look arund the room");
    System.out.println("'Show' or 'sh'\tTo show the items in the room");
    System.out.println("'Take' or 't'\tTo take  items. You will then be asked which item you want to choose");
    System.out.println("'drop ' or 'd'\tTo drop an item");
    System.out.println("'Show inventory'\tTo show inventory");
    System.out.println("'Exit' or 'e'\tTo exit the game");
    System.out.println("'Help' or 'h'\tIf you need any help");
    System.out.println();
    System.out.print("Now choose your next move: ");
    System.out.println();
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
    System.out.println("Welcome to the adventure game!");
    System.out.println();
    System.out.println("*THERE IS A NOTE ON THE TABLE*");
    System.out.println("In the notes it states:");
    System.out.println("Hello there prisoner 31");
    System.out.println("World war 3 has begun, and therefore we have let everyone loose");
    System.out.println("You were in a coma, and we could not get everybody out, so we left you behind");
    System.out.println("If you wake up one day, you are free to go, but it wont be easy to leave, most doors are still locked");
    System.out.println("I wish you the best of luck");
    GameMenu instructions = new GameMenu();
    instructions.help();
  }

  public void dropItems() {
    Scanner sc = new Scanner(System.in);
    System.out.println("What item should be dropped?");
    System.out.print("Your inventory:");
    System.out.println(player.getPlayerInventory());
    String valgteItem = sc.nextLine();

    System.out.println(player.dropItem(valgteItem));
    System.out.print("Your inventory:");
    System.out.println(player.getPlayerInventory());
  }

  public void takeItem() {
    // Vi skriver her Items navn. Dvs. det item der skal tages op og sættes ind i inventory
    Scanner sc = new Scanner(System.in);
    System.out.println("What item should be added?");
    String valgteItem = sc.nextLine();

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
}
