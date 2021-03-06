package homeworks.homework_3;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Написать программу выполняющую алгоритм обхода конем шахматной доски
 * размером NxN так, чтобы фигура в каждой клетке была строго один раз.
 * Размер шахматной доски будем задавать с клавиатуры, а так как шахматная доска
 * - квадрат, будем вводить только одно число. Вывод вариантов решения на экран
 * в виде матриц, где число - порядковый номер хода коня. Несколько комментариев по
 * алгоритму:
 * - порядок, в котором будет двигаться конь, круговой и будет оптимальным.
 * Используя такой порядок, мы попадем на свободную позицию за несколько ходов.
 * - кроме того, всегда лучше начинать откат с любого угла шахматной доски.
 * Если мы начнем где-то с середины, конь может пойти в 8 разных направлениях,
 * а если мы начнем с угла, как в данной реализации, то конь сможет пройти оттуда 
 * только в две точки.
 * - при значениях N > 5, кол-во вариантов решения значительно, например при N=8,
 * более 40 000, выход из программы по <Ctrl+C>
 */

public class hw_31 {

  // Ниже массивы детализируют все восемь возможных движений коня,
  // так называемая вспомогательная матрица D
  public static final int[] row = { 2, 1, -1, -2, -2, -1, 1, 2, 2 };
  public static final int[] col = { 1, 2, 2, 1, -1, -2, -2, -1, 1 };

  // Проверка: являются ли (x, y) действительными координатами шахматной доски,
  // (конь не может выйти за пределы доски)
  private static boolean isValid(int x, int y, int N) {
    if (x < 0 || y < 0 || x >= N || y >= N) {
      return false;
    }

    return true;
  }

  private static void print(int[][] visited) {
    for (var r : visited) {
      System.out.println(Arrays.toString(r));
    }
    System.out.println();
  }

  // Рекурсивная функция для выполнения обхода коня
  public static void horseTour(int[][] visited, int x, int y, int pos, int N) {
    // отметить текущий квадрат как посещенный
    visited[x][y] = pos;
    // если все квадраты посещены, выводим решение
    if (pos >= N * N) {
      print(visited);
      // откат перед возвратом
      visited[x][y] = 0;
      return;
    }

    // проверка всех восьми возможных движений коня
    // и повторяться для каждого допустимого движения
    for (int k = 0; k < 8; k++) {
      // получаем новую позицию коня из текущей
      int newX = x + row[k];
      int newY = y + col[k];
      // если новая позиция действительна и еще не посещалась
      if (isValid(newX, newY, N) && visited[newX][newY] == 0) {
        horseTour(visited, newX, newY, pos + 1, N);
      }
    }
    // вернуться из текущего квадрата и удалить его из текущего пути
    visited[x][y] = 0;
  }

  public static void main(String args[]) throws IOException {
    System.out.println("Введите размер доски: ");
    Scanner scanner = new Scanner(System.in);
    int size = scanner.nextInt();
    scanner.close();

    // Шахматная доска (N × N)
    int N = size;

    // отслеживаем поля, задействованные в обходе коня,
    // храним порядок посещения квадратов.
    int[][] visited = new int[N][N];
    int pos = 1;

    // начинаем обход конем с углового квадрата (0, 0)
    horseTour(visited, 0, 0, pos, N);
  }
}