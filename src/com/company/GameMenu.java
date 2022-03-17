package com.company;

import java.util.Scanner;

public class GameMenu {
  private Room currentRoom;
  private Room requestedRoom;
  private boolean gameStatus = true;

  private Worldcreator alltherooms;
  private Player player;


  public GameMenu() {
    player = new Player();
  }

  public void goDirection(String direction) {
    requestedRoom = null;

    switch (direction) {
      case "north" -> requestedRoom = currentRoom.getNorth();
      case "south" -> requestedRoom = currentRoom.getSouth();
      case "east" -> requestedRoom = currentRoom.getEast();
      case "west" -> requestedRoom = currentRoom.getWest();
    }

    if (requestedRoom != null) {
      if (requestedRoom.isLocked()) {
        System.out.println("Its locked!");
      } else {
        currentRoom = requestedRoom;
        System.out.println("You are in a:");
        System.out.println(currentRoom.nameDescription());
      }
    } else {
      System.out.println("You went into the wall, there is no door here");
    }
  }

  public void mainMenu() {
    alltherooms = new Worldcreator();
    alltherooms.allRooms();
    welcomeMessage();
    currentRoom = alltherooms.getStarterRoom();

    while (gameStatus) {
      Scanner sc = new Scanner(System.in);

      String nextMove = sc.nextLine();
      nextMove = nextMove.toLowerCase();

      switch (nextMove) {
        case "e", "east", "go east" -> {
          System.out.println("going east");
          String move = "east";
          goDirection(move);
        }
        case "s", "south", "go south" -> {
          System.out.println("Going south");
          String move = "south";
          goDirection(move);
        }
        case "n", "north", "go north" -> {
          System.out.println("Going North");
          String move = "north";
          goDirection(move);
        }
        case "w", "west", "go west" -> {
          System.out.println("Going West");
          String move = "west";
          goDirection(move);
        }
        case "l", "look", "look around" -> {
          look();
        }
        case "h", "help", "need help" -> {
          help();
        }
        case "exit", "end" -> {
          exitGame();
        }
        default -> System.out.println("What are you trying to do? Make it make sense. What does " + nextMove + "mean?");
      }
      System.out.println("What's your next move?");
      checkIfLocked(currentRoom);

      checkGameStatus();
    }
  }

  public void look() {
    System.out.println(currentRoom.decsriptionDescription());
  }

  public void exitGame() {
    gameStatus = false;
    System.out.println("The game has ended");
  }

  public void help() {
    System.out.println("Welcome to the adventure game!");
    System.out.println("Choose between the ACtions:\nNorth = 'n'\nEast = 'e'\nWest = 'w'\nSouth = 's'\n");
    System.out.println("You can also look around.\nTo look around press:\t'l");
    System.out.println("You can also exit the game.\nTo exit the game press\t'e");
    System.out.println("If you need any help press \t'h'");
    System.out.print("Choose your next move: ");
  }

  public void checkIfLocked(Room currentRoom) {
    if (currentRoom.isLocked() == true) {
      System.out.println("The door is locked");
    } else if (currentRoom.isLocked() == false) {
    }
  }

  public void checkGameStatus() {
    if (currentRoom == alltherooms.getWinnerRoom()) {
      gameStatus = false;
      System.out.println("You have won the game");
    }
  }

  public void welcomeMessage() {
    System.out.println("*THERE IS A NOTE ON THE TABLE*");
    System.out.println("In the notes it states:");
    System.out.println("Hello there prisoner 001");
    System.out.println("World war 3 has begun, and therefore we have let everyone loose");
    System.out.println("You were in a coma, and you did not want to wake up");
    System.out.println("If you wake up one day, you are free to go");
    System.out.println();
    GameMenu instructions = new GameMenu();
    instructions.help();
  }
}
