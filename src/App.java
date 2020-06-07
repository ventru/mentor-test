import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String str = in.nextLine();

        System.out.println();
        String[] incomingValues = str.split(",");
        for (int j = 0; j < incomingValues.length; j++) {
            SymbolDispatcher symbolDispatcher = new SymbolDispatcher(incomingValues[j]);

            if (!symbolDispatcher.isRoman()) {
                float result = symbolDispatcher.calc();
                if (result != 0)
                    System.out.println("Результат вычисления: [" + incomingValues[j] + "] = " + result);
                else
                    System.out.println("Некорректный ввод выражения [" + incomingValues[j] + "], используйте формат: a + b, a - b, a * b, a / b.");
            } else {
                String result = symbolDispatcher.romanCalc();
                if (result != null)
                    System.out.println("Результат вычисления: [" + incomingValues[j] + "] = " + result);
                else
                    System.out.println("Некорректный ввод выражения [" + incomingValues[j] + "], используйте формат: a + b, a - b, a * b, a / b.");
            }
        }

    }
}


