import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input");
        String input = scanner.nextLine();
        System.out.println("Output:");
        System.out.println(calc(input));
    }

    public static String calc(String args) throws Exception {

        String noSpacesInput = args.replace(" ", "");
        String[] arguments = noSpacesInput.split("[+/*-]");
        if (arguments.length != 2) {
            throw new Exception();
        }
        int arg1;
        int arg2;
        boolean areArgumentsRoman = false;
        int result;
        if (areBothArgumentsIntUnder10(arguments[0], arguments[1])) {
            arg1 = Integer.parseInt(arguments[0]);
            arg2 = Integer.parseInt(arguments[1]);
        } else if (areBothArgumentsRomUnder10(arguments[0], arguments[1])){
            arg1 = RomanDigit.valueOf(arguments[0]).getArabicDigit();
            arg2 = RomanDigit.valueOf(arguments[1]).getArabicDigit();
            areArgumentsRoman = true;
        } else {
            throw new Exception();
        }

        //check if it is only one operation symbol
        if (isOperandPresentedOnce(noSpacesInput, '+')) {
            result = arg1 + arg2;
        } else if (isOperandPresentedOnce(noSpacesInput, '-')) {
            result = arg1 - arg2;
        } else if (isOperandPresentedOnce(noSpacesInput, '/')) {
            result = arg1 / arg2;
        } else if (isOperandPresentedOnce(noSpacesInput, '*')) {
            result = arg1 * arg2;
        } else {
            throw new Exception();
        }

        if (areArgumentsRoman) {
            if (result > 0) {
                return convertToRoman(result);
            } else {
                throw new Exception();
            }
        } else {
            return String.valueOf(result);
        }
    }

    static String convertToRoman(int arabicNumber) {
        int decimalPlace = arabicNumber / 10;
        int digit = arabicNumber % 10;
        String decimalPlaceStr = "";
        String digitStr = "";
        if (decimalPlace > 0) {
            decimalPlaceStr = RomanDecimalPlace.getRomanDecimalPlace(decimalPlace).toString();
        }
        if (digit > 0) {
            digitStr = RomanDigit.getRomanDigit(digit).toString();
        }
        return decimalPlaceStr + digitStr;
    }

    static boolean isOperandPresentedOnce(String inputString, Character operationChar) {
        if ((inputString.indexOf(operationChar) == inputString.lastIndexOf(operationChar))
                && (inputString.indexOf(operationChar) != -1)) {
            return true;
        }
        return false;
    }
    static boolean areBothArgumentsIntUnder10(String arg1, String arg2) {
        try {
            int i1 = Integer.parseInt(arg1);
            int i2 = Integer.parseInt(arg2);
            if (((i1 > 0) && (i1 < 11)) && ((i2 > 0) && (i2 < 11))) {
                return true;
            }
        } catch (Exception e) {
            //it is ok
        }
        return false;
    }

    static boolean areBothArgumentsRomUnder10(String arg1, String arg2) {
        try {
            int i1 = RomanDigit.valueOf(arg1).getArabicDigit();
            int i2 = RomanDigit.valueOf(arg2).getArabicDigit();
            if (((i1 > 0) && (i1 < 11)) && ((i2 > 0) && (i2 < 11))) {
                return true;
            }
        } catch (Exception e) {
            //it is ok
        }
        return false;
    }

}

enum RomanDigit {
    I(1),
    II(2),
    III(3),
    IV(4),
    V(5),
    VI(6),
    VII(7),
    VIII(8),
    IX(9),
    X(10);

    int arabicDigit;

    RomanDigit(int arabicDigit) {
        this.arabicDigit = arabicDigit;
    }

    int getArabicDigit() {
        return arabicDigit;
    }

    static RomanDigit getRomanDigit(int arabicDigit) {
        return RomanDigit.values()[arabicDigit - 1];
    }
}

enum RomanDecimalPlace {
    X(10),
    XX(20),
    XXX(30),
    XL(40),
    L(50),
    LX(60),
    LXX(70),
    LXXX(80),
    XC(90),
    C(100);

    private int arabicDecimalPlace;

    RomanDecimalPlace(int arabicDecimalPlace) {
        this.arabicDecimalPlace = arabicDecimalPlace;
    }

    int getArabicDecimalPlace() {
        return arabicDecimalPlace;
    }

    static RomanDecimalPlace getRomanDecimalPlace(int arabicDecimalPlace) {
        return RomanDecimalPlace.values()[arabicDecimalPlace - 1];
    }
}