package homeworks.homework_2;

/**
 * Написать программу выполняющую алгоритм пирамидальной сортировки (HeapSort).
 * На занятии указано ввод чисел выполнять с клавиатуры или читать из файла,
 * вывод отсортированного массива - в файл. В программе будем исходный массив 
 * чисел вводить с клавиатуры, а отсортированный массив выводить на экран и записывать в 
 * файл. Вычисление реализовано c использованием процедуры рекурсии.
 */

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class hw_22 {
  public static void heapSort(int[] myArray, int length) {
    int temp;
    int size = length - 1;
    for (int i = (length / 2); i >= 0; i--) {
      heapify(myArray, i, size);
    }
    ;
    for (int i = size; i >= 0; i--) {
      temp = myArray[0];
      myArray[0] = myArray[size];
      myArray[size] = temp;
      size--;
      heapify(myArray, 0, size);
    }
    System.out.println(Arrays.toString(myArray));
  }

  public static void heapify(int[] myArray, int i, int heapSize) {
    int a = 2 * i;
    int b = 2 * i + 1;
    int largestElement;
    if (a <= heapSize && myArray[a] > myArray[i]) {
      largestElement = a;
    } else {
      largestElement = i;
    }
    if (b <= heapSize && myArray[b] > myArray[largestElement]) {
      largestElement = b;
    }
    if (largestElement != i) {
      int temp = myArray[i];
      myArray[i] = myArray[largestElement];
      myArray[largestElement] = temp;
      heapify(myArray, largestElement, heapSize);
    }
  }

  public static String fileWrite(int[] arrOut) throws IOException {
    FileWriter nFile = new FileWriter("output.txt");
    nFile.write(Arrays.toString(arrOut));
    nFile.close();

    return null;
  }

  public static void main(String args[]) throws IOException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Введите размер массива: ");
    int size = scanner.nextInt();
    System.out.println("Введите элементы массива: ");
    int[] myArray = new int[size];
    for (int i = 0; i < size; i++) {
      myArray[i] = scanner.nextInt();
    }
    heapSort(myArray, size);
    fileWrite(myArray);
    scanner.close();
  }
}
