public class ArabicToRoman {
    private String[] romeSymbols = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X",
            "IX", "V", "IV", "I" };
    private int[] arabianSymbols = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

    public int romeToArab(String rome) {

        StringBuffer romeNumber = new StringBuffer(rome);
        int arabNumber = 0, i = 0;

        if (romeNumber.length() > 0) {

        while (true) {
        do {
            if (romeSymbols[i].length() <= romeNumber.length()) {
                if (romeSymbols[i].equals(romeNumber.substring(0,
                romeSymbols[i].length()))) {
                arabNumber += arabianSymbols[i];
                romeNumber.delete(0, romeSymbols[i].length());
                if (romeNumber.length() == 0) return arabNumber;
                } else break;
            } else break;
        } while (true && romeNumber.length() != 0);
            i++;
            }
        }
        return 0;
    }
}
