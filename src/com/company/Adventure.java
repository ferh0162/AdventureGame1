package com.company;

import java.util.Scanner;

public class Adventure {
  private Room currentRoom;
  private Room requestedRoom;
  private Room winnerRoom;
  private boolean gameStatus = true;


  public void checkGameStatus() {
    if (currentRoom == winnerRoom) {
      gameStatus = false;
      System.out.println("You have won the game");
    }
  }

public void checkIfLocked(Room currentRoom){
    if (currentRoom.isLocked()==true){
      System.out.println("The door is locked");
    } else if (currentRoom.isLocked() == false){
    }
}

  public static void main(String[] args) {
    // write your code here
    Adventure obj = new Adventure();
    obj.mainMenu();

  }
}
