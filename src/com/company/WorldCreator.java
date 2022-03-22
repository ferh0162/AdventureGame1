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
    Room room1 = new Room("Prison hospital", "You look around the room and find yourself all alone\n" +
        "The room is small, empty and bloody...");

    Room room2 = new Room("Mikails Cell", "This cell looks empty, \n" +
        "There is only a toilet in the corner" +
        "\nThe sink is still running" +
        "\nIt smells, looks like someone forgot to flush");

    Room room3 = new Room("Poolroom", "You have entered a room with a pool\n" +
        "The pool is filled with dead bodies and blood maybe the bodies are from the prisoners of this prison");

    Room room4 = new Room("Prison canteen", "You look around the Canteen\n" +
        "It looks like a mess\n" +
        "There is knives and forks everywhere on the floor\n" +
        "Food on the ground...\n" +
        "And it smells absoloutely horrifc");

    Room room5 = new Room("Outside to freedom", "You`re now ontop of a hill the sun is shinning on you.\n" +
        "And your now relieved that your finally out of the prison\n" +
        "As you look up at the hot sun burning your skin, you hear a helicopter a far");

    Room room6 = new Room("The dark room", "Cant see anything, but a dim light in the end of the room.");

    Room room7 = new Room("Partyroom", "You have entered a festival room with alot of musical instruments\n" +
        "as you walk further into the room you hear someone playing a musical instrument");

    Room room8 = new Room("Prison yard", "You`re now in a prison yard it looks uninhabited\n " +
        "but watch out there may be something lurking in the dark corners\n" +
        "further behind the trees you see a chest its unlocked(Looks very old)");

    Room room9 = new Room("Torture room", "You have entere a torture room\n" +
        "with a few torture instruments which have been used to execute deathrow prisoners\n" +
        "There is a table in the corner with a bloody key on it");

    starterRoom = room1;
    winnerRoom = room5;
    //Rum 1
    room1.setEast(room2);
    room1.setSouth(room4);
    Food tuna = new Food("tuna", 50);
    room1.addItem(tuna);

    //Rum 2
    room2.lock();
    room2.setWest(room1);
    room2.setEast(room3);

    //Rum 3
    Item rope = new Item("rope");
    room3.addItem(rope);
    room3.setWest(room2);
    room3.setSouth(room6);
    //Rum 4
    Item knife = new Item("knife");
    room4.addItem(knife);
    Item fork = new Item("fork");
    room4.addItem(fork);
    room4.setNorth(room1);
    room4.setSouth(room7);

    //rum 5
    room5.lock();
    room5.setSouth(room8);
    //rum 6
    room6.setNorth(room3);
    room6.setSouth(room9);
    room6.lock();
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
    Item key = new Item("key");
    room9.addItem(key);


  }
}
