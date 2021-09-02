package ru.laptseu.bankapp.utilities;

import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Client;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class NumberGeneratorForAccounts {
    //todo нужно бы его на уникальность проверять. при 2мрд вариантов она имеет вероятность быть
    public static int generate(Bank b, Client c) {
        Calendar calendar = new GregorianCalendar();
        String number = "";
        if (b != null) number = number + b.hashCode();
        if (c != null) number = number + c.hashCode();
        number = number + calendar.get(Calendar.MILLISECOND);
        while (number.startsWith("0")) {
            number.substring(1);
        }
        int returning;
        try {
            returning = Integer.valueOf(number);
        } catch (java.lang.NumberFormatException e) {
            //over int.size
            number = number.substring(number.length() - 9);
            returning = Integer.valueOf(number);
        }
        return returning;
    }
}
