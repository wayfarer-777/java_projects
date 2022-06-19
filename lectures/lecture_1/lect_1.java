package lectures.lecture_1;

/**
 * Program
 */

import java.util.Scanner;

public class lect_1 {
  public static void main(String[] args) {
    System.out.println("Здравствуй МИР!");
    Scanner myScanner = new Scanner(System.in);
    System.out.printf("Введите свое имя: ");
    String name = myScanner.nextLine();
    System.out.printf("Привет, %s", name);
    System.out.printf("\n Введите int a: ");
    int x = myScanner.nextInt();
    System.out.printf("\n Введите double a: ");
    double y = myScanner.nextDouble();
    System.out.printf("%d + %f = %f", x, y, x + y);
    myScanner.close();
  }

}
