public class App {
    public static void main(String[] args) {

        //test case #1
        //args = new String[1];
        //args[0] = "1 + 2, VI / II,V/II,II + 3, 3,4 * 3, 10 + 1,XXXI + V,XX1 + X,10-7, X++1";

        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                String[] incomingValues = args[i].split(",");
                for (int j = 0; j < incomingValues.length; j++) {
                    SymbolDispatcher symbolDispatcher = new SymbolDispatcher(incomingValues[j]);
                    float result = symbolDispatcher.calc();
                    if (result != 0)
                        System.out.println("Результат вычисления: [" + incomingValues[j] + "] = " + result);
                    else
                        System.out.println("Некорректный ввод выражения [" + incomingValues[j] + "], используйте формат: a + b, a - b, a * b, a / b.");
                    }
            }
        }else{
            System.out.println("Вы ничего не ввели. Работа программы завершена.");
        }
    }
}


