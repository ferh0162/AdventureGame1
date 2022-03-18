package com.company;

public class Adventure {
  public static void main(String[] args) {
    // write your code here
    Adventure obj = new Adventure();
    GameMenu startMenu = new GameMenu();
    WorldCreator verden = new WorldCreator();

    startMenu.mainMenu();
  }
}
