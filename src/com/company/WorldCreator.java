package com.company;

public class WorldCreator {
  private Room starterRoom;
  private Room winnerRoom;

  public Room getStarterRoom() {
    return starterRoom;
  }

  public Room getWinnerRoom() {
    return winnerRoom;
  }

  public void allRooms() {
    Room room1 = new Room("Prison hospital room", "\u001B[33m" + "You look around the room and find yourself all alone\n" +
        "The room is small, cold and bloody...\n" +
        "all  the supplies are gone\n" +
        "But on the note, that they left me, there is a Twix bar\n" +
        "The watch on the table states that the todays date is 2026/7/16\n" +
        "There is also a pill that says" + "\u001B[31m" + " 'NOT FOR HUMANS'" + "\u001B[0m\n");

    Room room2 = new Room("Mikails Cell", "\u001B[33m" + "This cell looks empty, \n" +
        "There is only a toilet in the corner" +
        "\nThe sink is still running" +
        "\nIt looks like someone forgot to flush" + "\u001B[0m");

    Room room3 = new Room("Inside swimming area", "\u001B[33m" + "You look around the the swimming pool\n" +
        "around the pool is dirty as hell and the place stinks of waste\n" +
        "The pool is filled with....:\ndead\nrotten\nbodies\n" +
        "The water has turned dark red, from the blood of all of the bodies" + "\u001B[0m");

    Room room4 = new Room("Prison canteen", "\u001B[33m" + "You look around the Canteen\n" +
        "It looks like a mess\n" +
        "There is knives and forks everywhere on the floor\n" +
        "Food on the ground...\n" +
        "And it smells absoloutely horrifc" + "\u001B[0m");

    Room room5 = new Room("Outside to freedom", "\u001B[33m" + "You`re now ontop of a hill the sun is shinning on you.\n" +
        "And your now relieved that your finally out of the prison\n" +
        "As you look up at the hot sun burning your skin, you hear a helicopter a far" + "\u001B[0m");

    Room room6 = new Room("The dark room", "\u001B[33m" + "You look around, but cant se a thing\n" +
        "It does smell like there is a dead body inside." + "\u001B[0m");

    Room room7 = new Room("Partyroom", "\u001B[33m" + "You look around the festival room\nThere is a lot of musical instruments\n" +
        "Whats that...?\n" +
        "You hear the drums playing very lightly\n" +
        "But there is no rhytm to it" +
        "There is a stack of insects inside the drum.\nDisgusting..." + "\u001B[0m\n");

    Room room8 = new Room("Prison yard", "\u001B[33m" + "You look around the prison yard\n" +
        "The grass has gotten tall, and it actually looks pretty beautiful " +
        "but watch out there may be something lurking in the dark corners\n" +
        "further behind the tall grass you see a suitcase\n" +
        "it looks like something from the ARMY\n" + "\u001B[0m");

    Room room9 = new Room("Torture room", "\u001B[33m" + "You have entere a torture room\n" +
        "with a few torture instruments which have been used to execute deathrow prisoners\n" +
        "There is a table in the corner with a bloody key on it\n" +
        "How humane... There is also a medical bag" + "\u001B[0m");

    starterRoom = room1;
    winnerRoom = room5;
    //Rum 1
    room1.setEast(room2);
    room1.setSouth(room4);
    room1.addItem(new Food("twix"));
    room1.addItem(new Food("pill", -101));

    //Rum 2
    room2.lock();
    Food shit = new Food("shit", -40);
    room2.addItem(shit);
    room2.setWest(room1);
    room2.setEast(room3);

    //Rum 3
    room3.addItem(new Item("diving goggles"));
    room3.setWest(room2);
    room3.setSouth(room6);

    //Rum 4
    room4.addItem(new Item("knife"));
    room4.addItem(new Item("fork"));
    room4.setNorth(room1);
    room4.setSouth(room7);

    //rum 5
    room5.escapeClosed();
    room5.setSouth(room8);

    //rum 6
    room6.addItem(new Item("rope"));
    room6.setNorth(room3);
    // room6.setSouth(room9);

    //rum 7
    room7.addItem(new Item("guitar"));
    room7.addItem(new Food("insect", -15));
    room7.setNorth(room4);
    room7.setEast(room8);

    //rum 8
    room8.setNorth(room5);
    room8.setEast(room9);
    room8.setWest(room7);
    room8.addItem(new Food("painkillers", 50));

    //Rum 9
    //room9.setNorth(room6);
    room9.setWest(room8);
    room9.addItem(new Item("key"));


  }

}
