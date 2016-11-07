package local;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// Created by Bing Liang on 11/02/2016
/*
 * every time when user add a Node, the program will generate a file
 * and I use the content of that file generate a unique ID
 */

/**
 * A class that can generate a unique ID
 * 
 * @author BingLiang
 *
 */
public class GenerateID {
  private static ArrayList<String> info; // the content of the file

  /**
   * 
   * @param f the file that the program generate
   * @throws FileNotFoundException throws FileNotFoundException if can't find
   *           that file
   */
  public static void readFile(File f) throws FileNotFoundException {
    info = new ArrayList<String>();
    Scanner in = new Scanner(f);
    while (in.hasNextLine()) {
      info.add(in.nextLine());
    }
    in.close();
  }

  /**
   * Gets the ID
   * 
   * @return the ID
   */
  public static int getID() {
    return info.hashCode();
  }
}
