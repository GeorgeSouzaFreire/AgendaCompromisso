package br.poc.agendacompromisso.util;


import java.util.Calendar;

/**
 * Created by 36736636825 on 17/06/2016.
 */
public class AndroidUtil {

    public static long horaAtualMillis() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public static String getHoraAtual() {
        Calendar calendar = Calendar.getInstance();
        String customDate = zeroEsquerda(calendar.get(Calendar.DAY_OF_MONTH)) + "/" + zeroEsquerda((calendar.get(Calendar.MONTH) + 1)) + "/" + calendar.get(Calendar.YEAR) + " " + zeroEsquerda(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + zeroEsquerda(calendar.get(Calendar.MINUTE));
        return customDate;
    }

    private static String zeroEsquerda(int numero) {
        String numeroZero = "";
        if (String.valueOf(numero).length() == 1) {
            numeroZero = "0" + String.valueOf(numero);
        } else {
            numeroZero = String.valueOf(numero);
        }
        return numeroZero;
    }

    public static String formatDataHoraNota(String data, String hora) {
        return data + " " + hora;
    }
}
