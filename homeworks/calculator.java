/**
 * Реализовать алгоритм перевода из инфиксной записи в постфиксную 
 * для арифметического выражения. Написать программу вычисляющую значение сложного 
 * арифметического выражения. Для простоты - выражение всегда вычисляемое.
 * 
 */


import java.util.Scanner;
import java.util.Stack;

public class calculator {
  /**
   * метод для перевода введенного выражения в обратную
   * польскую запись (RPN)
   * 
   * @param expr
   * @return
   */
  public static String ExpressionToRPN(String expr) {
    String current = "";
    Stack<Character> stack = new Stack<>();

    int priority;
    for (int i = 0; i < expr.length(); i++) {
      priority = getPriority(expr.charAt(i));

      if (priority == 0)
        current += expr.charAt(i);

      if (priority == 2)
        stack.push(expr.charAt(i));

      if (priority > 2) {
        current += ' ';
        while (!stack.empty()) {
          if (getPriority(stack.peek()) >= priority) {
            current += stack.pop();
          } else
            break;
        }
        stack.push(expr.charAt(i));
      }

      if (priority == 1) {
        current += ' ';
        while (getPriority(stack.peek()) != 2)
          current += stack.pop();
        stack.pop();
      }
    }
    while (!stack.empty()) {
      current += stack.pop();
    }
    return current;
  }

  /**
   * метод для вычисления выражения в форме RPN
   * 
   * @param rpn
   * @return
   */
  public static double RPNtoAnswer(String rpn) {
    String operand = new String();
    Stack<Double> stack = new Stack<>();

    for (int i = 0; i < rpn.length(); i++) {
      if (rpn.charAt(i) == ' ')
        continue;
      if (getPriority(rpn.charAt(i)) == 0) {
        operand = new String();
        while (rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i)) == 0) {
          operand += rpn.charAt(i++);
          if (i == rpn.length())
            break;
        }
        stack.push(Double.parseDouble(operand));
        operand = new String();
      }
      if (getPriority(rpn.charAt(i)) > 2) {
        double a = stack.pop(), b = stack.pop();
        if (rpn.charAt(i) == '+')
          stack.push(b + a);
        if (rpn.charAt(i) == '-')
          stack.push(b - a);
        if (rpn.charAt(i) == '*')
          stack.push(b * a);
        if (rpn.charAt(i) == '/')
          stack.push(b / a);
      }
    }
    return stack.pop();
  }

  // метод для определения приоритета символов выражения
  private static int getPriority(char token) {
    if (token == '*' || token == '/')
      return 4;
    else if (token == '+' || token == '-')
      return 3;
    else if (token == '(')
      return 2;
    else if (token == ')')
      return 1;
    else
      return 0;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // String expression = "(222+2)/2";
    
    System.out.println("Введите математическое выражение (без пробелов): ");
    Scanner scanner = new Scanner(System.in);
    String expression = scanner.next();
    scanner.close();
    
    System.out.println("Вы ввели выражение: " + expression);
    String expr_rpn = ExpressionToRPN(expression);
    System.out.println("Обратная польская форма:" + expr_rpn);
    double result = RPNtoAnswer(expr_rpn);
    System.out.println("Результат вычисления: " + result);
  }
}
