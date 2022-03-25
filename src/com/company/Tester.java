package com.company;

import java.util.Scanner;

public class Tester {

  public static void main(String[] args) {
    // write your code here
    String mitNavn = "Ferhat";
    mitNavn = mitNavn + " ";


    int firstIndex = mitNavn.indexOf(mitNavn);
    String forNavn = mitNavn.substring(0, firstIndex);


    int lastIndex = mitNavn.lastIndexOf(" ");
    String efterNavn = mitNavn.substring(lastIndex);


  }
}
