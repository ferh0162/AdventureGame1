package com.company;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Tester {

  public static void main(String[] args) {
    // write your code here
    Tester obj = new Tester();
    String text = "Ferhat";

    String førsteOrd = "";
    String andetOrd = "";
    int index = text.indexOf(' ');

    if (index > -1) { // Check if there is more than one word.

      førsteOrd = text.substring(0, index).trim(); // Extract first word.
      andetOrd = text.substring(index + 1, text.length());
      System.out.println("førsteord" + førsteOrd);
      System.out.println("andetord" + andetOrd);

    } else {
      System.out.println("else " + text);
      ; // Text is the first word itself.
    }
    System.out.println(text);
    System.out.println(førsteOrd);
    System.out.println(andetOrd);
  }
  }

