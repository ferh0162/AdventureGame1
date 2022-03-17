package com.company;

public class Worldcreator {
  private Room currentRoom;
  private Room requestedRoom;
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

    Room room6 = new Room("The dark room", "You have entered a dark empty room with no light.");

    Room room7 = new Room("Partyroom", "You have entered a festival room with alot of musical instruments\n" +
        "as you walk further into the room you hear someone playing a musical instrument");

    Room room8 = new Room("Poolroom", "You have entered a room with a pool\n" +
        "The pool is filled with dead bodies and blood maybe the bodies are from the prisoners of this prison");

    Room room9 = new Room("Torture room", "You have entere a torture room\n" +
        "with a few torture instruments which have been used to execute deathrow prisoners");

    currentRoom = room1;
    winnerRoom = room5;
    //Rum 1
    room1.setEast(room2);
    room1.setSouth(room4);

    //Rum 2
    room2.lock();
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
}
