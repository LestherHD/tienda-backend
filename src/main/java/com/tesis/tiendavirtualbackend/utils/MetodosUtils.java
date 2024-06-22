package com.tesis.tiendavirtualbackend.utils;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MetodosUtils {

    public static String generarCodigo(){
        String response = "";

        Random random = new Random();

        Calendar cal = Calendar.getInstance();
        String horas = cal.get(Calendar.HOUR_OF_DAY) < 10 ? "0" + String.valueOf(cal.get(Calendar.HOUR_OF_DAY))
                : String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
        String minutos = cal.get(Calendar.MINUTE) < 10 ? "0" + String.valueOf(cal.get(Calendar.MINUTE))
                : String.valueOf(cal.get(Calendar.MINUTE));
        String segundos = cal.get(Calendar.SECOND) < 10 ? "0" + String.valueOf(cal.get(Calendar.SECOND))
                : String.valueOf(cal.get(Calendar.SECOND));

        for (int i = 0; i < 9; i++) {
            int valor = random.nextInt(9);
            if (valor%2==0) {
                response += valor;
            } else {
                response += (char) (random.nextInt(26) + 'A');
            }
        }

        response = response + horas + minutos + segundos;

        return response;
    }

    public static String getFechaStringByFormat(Date fecha, String formato) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
        return dateFormat.format(fecha);
    }

    public static byte[] decodeBase64String(String base64String) {
        if (base64String != null){
            return Base64.getDecoder().decode(base64String);
        }
        return null;
    }

    public static String encodeBase64String(byte[] bytes) {
        if (bytes != null){
            return Base64.getEncoder().encodeToString(bytes);
        }
        return null;
    }

}
