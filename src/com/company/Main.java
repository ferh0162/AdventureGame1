package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Welcome to the adventure game!");

            System.out.println("Choose between:\nNorth = 'n'\nEast = 'e'\nWest = 'w'\nSouth = 's'");
        System.out.println("You can also look around. To look around press 'l");
        System.out.println("You can aslo exit the game. To exit the game press 'e");
        System.out.println("If you need any help press 'h");
            System.out.print("Choose your next move: ");
            Scanner sc = new Scanner(System.in);
            char nextMove = sc.next().charAt(0);
            System.out.println();

            if (nextMove == 'n') {
//                playerPosition = obj.moveForward(playerPosition);
                System.out.println("Going North");
            } else if (nextMove == 'e') {
                System.out.println("Going East");
            } else if (nextMove == 'w') {
                System.out.println("Going West");
            } else if (nextMove == 's'){
                System.out.println("Going South");
            } else if (nextMove == 'l'){
                System.out.println("You are looking around the room");
            } else if (nextMove == 'h'){
                System.out.println(".....");
            } else if (nextMove == 'e'){
                System.out.println("Exitting game");
            }

Room currentroom = new Room("Current room", "amk");
currentroom = room1

        Room room1 = new Room("NorthWest", "Start rum");
        Room room2 = new Room("North", "Rummet øverst i midten");
        Room room3 = new Room("NorthEast", "Rummet øverst til højre");
        Room room4 = new Room("West", "Rummet til hæjre i midten");
        Room room5 = new Room("Midterste rum", "rummet i midten");
        Room room6 = new Room("East", "Rummet helt til højre");
        Room room7 = new Room("SouthWest", "Rum nederst til venstre");
        Room room8 = new Room("South", "Rummet nederst");
        Room room9 = new Room("SouthEast", "Rummet nederst til højre");


        room1.setEast(room2);

    }
}
