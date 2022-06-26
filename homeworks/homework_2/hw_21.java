package homeworks.homework_2;

/**
 * Написать программу выполняющую алгоритм сортировки слиянием.
 * На занятии указано ввод чисел выполнять с клавиатуры или читать из файла,
 * вывод отсортированного массива - в файл. В программе будем исходный массив 
 * чисел вводить с клавиатуры (сделана заготовка чтения из файла), 
 * а отсортированный массив выводить на экран и записывать в файл. 
 * Вычисление реализовано c использованием процедуры рекурсии.
 */

// import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
// import java.util.Arrays;
import java.util.Arrays;
import java.util.Scanner;

public class hw_21 {

  // public static String fileRead() throws IOException {
  //   File file = new File("input.txt");
  //   FileReader fileReader = new FileReader(file);
  //   try (BufferedReader reader = new BufferedReader(fileReader)) {
  //     while (reader.ready()) {
  //       String text = reader.readLine();
  //       System.out.println(text);

  //       return text;
  //     }
  //     reader.close();
  //   }
  //   return null;
  // }

  public static String fileWrite(int[] arrOut) throws IOException {
    FileWriter nFile = new FileWriter("output.txt");
    nFile.write(Arrays.toString(arrOut));
    nFile.close();

    return null;
  }
  public static void main(String[] args) throws IOException {
    // int[] array1 = { 8, 0, -3, 5, 6, 9, 8, -4, 2, -99, 43 };
    Scanner scanner = new Scanner(System.in);
    System.out.println("Введите размер массива: ");
    int size = scanner.nextInt();
    System.out.println("Введите элементы массива: ");
    int[] myArray = new int[size];
    for (int i = 0; i < size; i++) {
      myArray[i] = scanner.nextInt();
    }
    int[] result = mergesort(myArray);
    System.out.println(Arrays.toString(result));
    fileWrite(result);
    scanner.close();

  }

  public static int[] mergesort(int[] array1) {
    int[] buffer1 = Arrays.copyOf(array1, array1.length);
    int[] buffer2 = new int[array1.length];
    int[] result = mergesortInner(buffer1, buffer2, 0, array1.length);
    return result;
  }

  public static int[] mergesortInner(int[] buffer1, int[] buffer2,
      int startIndex, int endIndex) {
    if (startIndex >= endIndex - 1) {
      return buffer1;
    }

    // уже отсортирован.
    int middle = startIndex + (endIndex - startIndex) / 2;
    int[] sorted1 = mergesortInner(buffer1, buffer2, startIndex, middle);
    int[] sorted2 = mergesortInner(buffer1, buffer2, middle, endIndex);

    // Слияние
    int index1 = startIndex;
    int index2 = middle;
    int destIndex = startIndex;
    int[] result = sorted1 == buffer1 ? buffer2 : buffer1;
    while (index1 < middle && index2 < endIndex) {
      result[destIndex++] = sorted1[index1] < sorted2[index2]
          ? sorted1[index1++]
          : sorted2[index2++];
    }
    while (index1 < middle) {
      result[destIndex++] = sorted1[index1++];
    }
    while (index2 < endIndex) {
      result[destIndex++] = sorted2[index2++];
    }
    return result;
  }
}
