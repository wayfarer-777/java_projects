// import java.io.Console;

public class equation {
  /**
   * Задано уравнение вида q + w = e, q, w, e >= 0.
   * Некоторые цифры могут быть заменены знаком вопроса, например 2? + ?5 = 69.
   * Требуется восстановить выражение до верного равенства.
   * Предложить хотя бы одно решение или сообщить, что его нет.
   */

  public static void main(String[] args) {
    var s = "?2+34=4?"; // Console.ReadLine();
    var b = s.toCharArray();
    System.out.println(s + " ");
    System.out.println(Run(b, b.length) ? new String(b) : "No");
    System.out.println();
  }

  /**
   * @param s
   * @param p
   * @param v
   * @return
   */
  private static boolean Run(char[] s, int p, int v=0, int f=-1) {
    if (--p < 0) return v == 0;
    switch(s[p]) {
        case '=': case '+': return Run(s, p, v, 1);
        case '?':
            for (s[p] = '0'; s[p] <= '9'; s[p]++)
                if (Run(s, p, v + f * (s[p] - '0'), f * 10))
                    return true;
            s[p] = '?';
            return false;
        default: return Run(s, p, v + f * (s[p] - '0'), f * 10); 
    }
  }

}