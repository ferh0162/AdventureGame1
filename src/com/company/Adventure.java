package com.company;

import java.util.Scanner;

public class Adventure {
  private Room currentRoom;
  private Room winnerRoom;

  private boolean gameStatus = true;

  public void allRooms() {
    Room room1 = new Room("Isolation room", "You look around the room and find yourself in the isolation room\n" +
        "So small, empty and cold...");

    Room room2 = new Room("Mikails Cell", "This cell looks empty, \n" +
        "There is only a toilet in the corner" +
        "\nThe water is still running in the Toilet" +
        "\nThe sink also works");

    Room room3 = new Room("Prison yard", "Your now in a prison yard looks uninhabited\n " +
        "but watch out there may be something lurking in the dark corners\n" +
        "further behind the trees you see a chest its unlocked(Looks very old)");

    Room room4 = new Room("Prison canteen", "You look around the Canteen\n" +
        "It looks like a mess\n" +
        "There is knives and forks everywhere on the floor\n" +
        "Food on the ground...\n" +
        "And it smells absoloutely like shite");

    Room room5 = new Room("Outside to freedom", "Your now ontop of a hill the sun is shinning on you.\\n\" +\n" +
        "        \"And your now relieved that your finally out of the prison\\n\" +\n" +
        "        \"As you look up at the sun you say(God bless musa) The end ");

    Room room6 = new Room("East", "Rummet helt til højre");
    Room room7 = new Room("SouthWest", "Rum nederst til venstre");
    Room room8 = new Room("South", "Rummet nederst");
    Room room9 = new Room("SouthEast", "Rummet nederst til højre");

    currentRoom = room1;
    winnerRoom = room5;
    //Rum 1
    room1.setEast(room2);
    room1.setSouth(room4);
    //Rum 2
    room2.setWest(room1);
    room2.setEast(room3);
    //Rum 3
    room3.setWest(room2);
    room3.setSouth(room6);
    //Rum 4
    room4.setNorth(room1);
    room4.setSouth(room7);
    //rum 5
    room5.setSouth(room8);
    //rum 6
    room6.setNorth(room3);
    room6.setSouth(room9);
    //rum 7
    room7.setNorth(room4);
    room7.setEast(room8);
    //rum 8
    room8.setNorth(room5);
    room8.setEast(room9);
    room8.setWest(room7);
    //Rum 9
    room9.setNorth(room6);
    room9.setWest(room8);

  }
  public void help(){
    System.out.println("Welcome to the adventure game!");
    System.out.println("Choose between the ACtions:\nNorth = 'n'\nEast = 'e'\nWest = 'w'\nSouth = 's'\n");
    System.out.println("You can also look around.\nTo look around press:\t'l");
    System.out.println("You can also exit the game.\nTo exit the game press\t'e");
    System.out.println("If you need any help press \t'h'");
    System.out.print("Choose your next move: ");
  }
  public void checkGameStatus(){
    if (currentRoom == winnerRoom){
      gameStatus = false;
      System.out.println("You have won the game");
    }
  }
  public void goSouth(){
    if (currentRoom.getSouth() != null){
      currentRoom = currentRoom.getSouth();
      System.out.println("You are in a:");
      System.out.println(currentRoom.nameDescription());
    } else {
      System.out.println("You went into the wall, there is no door here");
    }
  }
  public void goNorth(){
    if (currentRoom.getNorth() != null){
      currentRoom = currentRoom.getNorth();
      System.out.println("You are in a:");
      System.out.println(currentRoom.nameDescription());
    } else {
      System.out.println("You went into the wall, there is no door here");
    }
  }
  public void goEast(){
    if (currentRoom.getEast() != null){
      currentRoom = currentRoom.getEast();
      System.out.println("You are in a:");
      System.out.println(currentRoom.nameDescription());
    } else {
      System.out.println("You went into the wall, there is no door here");
    }
  }
  public void goWest(){
    if (currentRoom.getWest() != null){
      currentRoom = currentRoom.getWest();
      System.out.println("You are in a:");
      System.out.println(currentRoom.nameDescription());
    } else {
      System.out.println("You went into the wall, there is no door here");
    }
  }
  public void look(){
    System.out.println(currentRoom.decsriptionDescription());
  }

public void mainMenu(){
  allRooms();

  help();
  Scanner sc = new Scanner(System.in);


while (gameStatus) {
  char nextMove = sc.next().charAt(0);
  System.out.println();
  if (nextMove == 'n') {
    System.out.println("Going North");
    goNorth();
  } else if (nextMove == 'e') {
    System.out.println("Going East");
    goEast();
  } else if (nextMove == 'w') {
    System.out.println("Going West");
    goWest();
  } else if (nextMove == 's') {
    System.out.println("Going South");
    goSouth();
  } else if (nextMove == 'l') {
    System.out.println("You are looking around the room");
    look();
  } else if (nextMove == 'h') {
    System.out.println(".....");
  } else if (nextMove == 'e') {
    System.out.println("Exitting game");
  }

  checkGameStatus();
}

}
  public static void main(String[] args) {
    // write your code here
Adventure obj = new Adventure();

obj.mainMenu();
  }
}
