import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// Created by Bing Liang on 11/02/2016
public class GenerateID
{
   private static ArrayList<String> info;

   public static void readFile(File f) throws FileNotFoundException
   {
      info = new ArrayList<String>();
      Scanner in = new Scanner(f);
      while (in.hasNextLine())
      {
         info.add(in.nextLine());
      }
      in.close();
   }

   public static int getID()
   {
      return info.hashCode();
   }
}
