package homeworks.homework_1;

/**
 * Написать программу вычисления n-ого треугольного числа.
 * Вычисление реализовано c использованием процедуры рекурсии.
 * Работаем с типом данных - int, а так как, в условии задачи 
 * не определен ввод-вывод чисел, то реализуем ввод с клавиатуры, 
 * вывод - на экран. 
 */

import java.util.Scanner;

public class hw_1 {

  static int triangle(int n) {
    if (n == 1)
      return 1;
    else
      return (n + triangle(n - 1));
  }

  public static void main(String[] args) {
    Scanner myScanner = new Scanner(System.in);
    System.out.printf("Введите int N: ");
    int x = myScanner.nextInt();
    System.out.printf("N = %d \n", x);
    for (int i = 1; i <= x; i++) {
      System.out.print(triangle(i) + " ");
    }
    myScanner.close();
  }
}
