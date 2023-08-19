package br.com.restspring.api.getway.converters;

import br.com.restspring.api.getway.exceptions.UnsupportedMathOperationException;

public
class NumberConverter {
    public static
    boolean isDoubleNumeric (String numberOne, String numberTwo) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            try {
                throw new UnsupportedMathOperationException("Por favor set um valor numerico.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return true;
    }

    public static
    Double converToDouble(String strNumber) {
        if (strNumber == null) return 0D;

        String number = strNumber.replaceAll(",", ".");
        if (isNumeric(number)) return Double.parseDouble(number);

        return 0D;
    }

    public static
    boolean isNumeric(String strNumber) {
        if (strNumber == null) return false;

        String number = strNumber.replaceAll(",", ".");

        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
