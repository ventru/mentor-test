public class SymbolDispatcher {

    private float a;
    private float b;
    private boolean roman;
    private boolean sum = false;
    private boolean difference = false;
    private boolean division = false;
    private boolean multiply = false;
    private boolean oneOperand = false;
    private boolean compatible = true;

    public SymbolDispatcher(String incomingString) {
        initialValues(incomingString.replaceAll(" ", ""));
    }

    private void initialValues(String incomingString){
        char[] values = incomingString.toCharArray();

        //Сборка первого числа
        int counter = 0;

        for (int i = 0; i < values.length; i++) {
            if (buildCounter(values[i])) counter++;
            else break;
        }

        if (counter == 0) {
            this.compatible = false;
            System.out.println("Не найдено первое число. Расчет невозможен. Используйте формат: a+b,a-b,a*b,a/b");
        }

        String firstValue = "";
        for (int i = 0; i < counter; i++) {
            firstValue +=  values[i];
        }

        //Сборка второго числа
        counter = 0;
        for (int i = values.length - 1; i > 0; i--) {
            if (buildCounter(values[i])) counter++;
            else break;
        }

        if (counter == 0) {
            this.compatible = false;
            System.out.println("Не найдено второе число. Расчет невозможен. Используйте формат: a+b,a-b,a*b,a/b");
        }

        String secondValue = "";
        for (int i = values.length - counter; i < values.length; i++) {
            secondValue += values[i];
        }

        //Поиск операнда
        for (int i = 0; i < values.length; i++) {
            if ((isOperand(values[i]) && oneOperand)){
                this.compatible = false;
            }
            if ((isOperand(values[i]) && !oneOperand)){
                initialOperand(values[i]);
                oneOperand = true;
            }
        }

        try {
            if (this.compatible) {
                if (romanSymbolsCheck(firstValue) == 0) {
                    this.a = Float.valueOf(firstValue);
                    this.roman = false;
                } else {
                    this.a = new ArabicToRoman().romeToArab(firstValue);
                    this.roman = true;
                }
                if ((romanSymbolsCheck(secondValue) == 0) && (!this.roman)) {
                    this.b = Float.valueOf(secondValue);
                    this.roman = false;
                }
                if ((romanSymbolsCheck(firstValue) == 0) && (this.roman)) {
                    this.compatible = false;
                    System.out.println("Несовместимые типы чисел a= " + firstValue + " c b= " + secondValue);
                    System.exit(0);
                }
                if ((romanSymbolsCheck(secondValue) == 0) && (this.roman)) {
                    this.compatible = false;
                    System.out.println("Несовместимые типы чисел b= " + secondValue + " с a= " + firstValue);
                    System.exit(0);
                }
                if ((romanSymbolsCheck(secondValue) != 0) && (this.roman)) {
                    this.b = new ArabicToRoman().romeToArab(secondValue);
                }
            }
        }catch (NumberFormatException e){
            System.err.println("Некорректный ввод выражения " + incomingString + "\n");
            //e.printStackTrace();
        }catch (ArrayIndexOutOfBoundsException e){
            System.err.println("Некорректный ввод выражения " + incomingString + "\n");
            //e.printStackTrace();
        }
    }


    public float calc(){
        if (!this.compatible) return 0;
        if ((this.sum) && (!this.roman)) return this.a + this.b;
        if ((this.difference) && (!this.roman)) return this.a - this.b;
        if ((this.division) && (!this.roman)) return this.a / this.b;
        if ((this.multiply) && (!this.roman)) return this.a * this.b;

        return 0;
    }

    public String romanCalc(){

        int calcResult = 0;

        if (!this.compatible) return null;
        if ((this.sum) && (this.roman)) calcResult = (int) (this.a + this.b);
        if ((this.difference) && (this.roman)) calcResult = (int) (this.a - this.b);
        if ((this.division) && (this.roman)) calcResult = (int) (this.a / this.b);
        if ((this.multiply) && (this.roman)) calcResult = (int) (this.a * this.b);

        return new RomanToArabic().arabicToRoman(calcResult);
    }

    private boolean buildCounter(char value){
        if (!isOperand(value)){
            if (!isExtraSymbols(value)){
                return true;
            } else {
                System.out.println(
                        "Недопустимый символ: " + value + ", используйте формат: a+b,a-b,a*b,a/b");
                System.exit(0);
            }
        } else return false;
        return false;
    }

    private int romanSymbolsCheck(String value) {
        char[] values = value.toCharArray();
        int cheker = 0;
        for (int i = 0; i < values.length; i++) {
            switch (values[i]) {
                case 'I': {
                    cheker = 1;
                    break;
                }
                case 'V': {
                    cheker = 2;
                    break;
                }
                case 'X': {
                    cheker = 3;
                    break;
                }
            }
        }
        return cheker;
    }

    private boolean isExtraSymbols(char symbol){
        switch (symbol){
            case '!': return true;
            case '@': return true;
            case '#': return true;
            case '$': return true;
            case '%': return true;
            case '^': return true;
            case '&': return true;
            case '.': return true;
            case '(': return true;
            case ')': return true;
            case '_': return true;
            case '=': return true;
        }
        return false;
    }

    private boolean isOperand(char symbol){
        switch (symbol){
            case '-': return true;
            case '+': return true;
            case '/': return true;
            case '*': return true;
        }
        return false;
    }

    private void initialOperand(char symbol){
        switch (symbol){
            case '+': {
                this.sum = true;
                break;
            }
            case '-': {
                this.difference = true;
                break;
            }
            case '/': {
                this.division = true;
                break;
            }
            case '*': {
                this.multiply = true;
                break;
            }
        }
    }

    public boolean isRoman() {
        return roman;
    }

    public boolean isCompatible() {
        return compatible;
    }
}
