package pl.wswoimtempie.utils;

public class Storage {

    public static Double priceTotalValue;

    public static Double getPriceDouble(String value) {
        String[] data = value.split(" ");
        String replaceData = data[0].replace(',','.');
        double result = Double.parseDouble(replaceData);
        return result;
    }


}
