package com.tesis.tiendavirtualbackend.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class MailUtils {

    private final static Properties properties = new Properties();
        private static String password = "gbftnackdwtxksgz";
    private static String username = "panagpanaderia";

    private static Session session;
    private static void init() {

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.auth", "true");

        session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    public static void sendEmail(String accion, String codigo, String usuarioCodigo, String usuarioDestino, String nombreSolicitanteCodigo, String nombreSolicitanteDestino, String anticipo, Date fecha, boolean admin){

        init();
        try{

            String mensaje = "";

            if (accion.equals("AS")){
                mensaje = mensajeAnticipo(codigo, usuarioCodigo, usuarioDestino, nombreSolicitanteCodigo, nombreSolicitanteDestino, anticipo);
            } else if (accion.equals("EU")) {
                mensaje = mensajeEliminarUsuario(codigo, usuarioCodigo, usuarioDestino, nombreSolicitanteCodigo, nombreSolicitanteDestino);
            }  else if (accion.equals("AU")) {
                mensaje = mensajeActivarUsuario(codigo, usuarioCodigo, nombreSolicitanteCodigo, admin);
            } else if (accion.equals("CC")) {
                mensaje = mensajeCambiarContrasenia(codigo, usuarioCodigo, nombreSolicitanteCodigo);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("panagpanaderia@gmail.com", "Panaderia"));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress("renemarioluna@gmail.com"));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress("betzabegudiel07@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("sid.kfajardo@gmail.com"));
            message.setSubject("Código para confirmación - " + usuarioCodigo.toUpperCase() + " " +sdf.format(fecha));
            message.setHeader("Content-Type", "text/html");

            message.setContent(mensaje, "text/html; charset=UTF-8");

            Transport transport = session.getTransport("smtp");
            transport.send(message);
            transport.close();
        }catch (MessagingException | UnsupportedEncodingException me){
            System.out.println(me);
            return;
        }
    }

    private static String mensajeAnticipo(String codigo, String usuarioCodigo, String usuarioAnticipo, String nombreSolicitanteCodigo, String nombreSolicitanteAnticipo, String anticipo) {

        String response = "";
        response += "<p style=\"font-family:Verdana; white-space: pre-line;\">" +
                "El código solicitado por parte del usuario administrador " +
                "<span style=\"font-weight: bold\">"+nombreSolicitanteCodigo + "(" + usuarioCodigo + ")" + "</span> " +
                "para confirmar el <span style=\"font-weight: bold\">Anticipo de Salario</span> \nsolicitado por parte del usuario ";
        response += "<span style=\"font-weight: bold\">"+nombreSolicitanteAnticipo + "(" + usuarioAnticipo + ")" +"</span> " +
                " por un monto de "+
                "<span style=\"font-weight: bold\"> Q" + anticipo + "</span> " +
                "es el siguiente:</p>\n";
        response += "<h2 style=\"font-family:Verdana;\">"+codigo+"</h2>\n";
        response += "<p style=\"font-family:Verdana;\">Debe proporcionarlo al usuario que solicitó el código para completar la acción correspondiente.</p>\n";
        response += "<span style=\"font-family:Verdana;\">Saludos.</span>";

        return response;
    }


    private static String mensajeEliminarUsuario(String codigo, String usuarioCodigo, String usuarioEliminar, String nombreSolicitanteCodigo, String nombreUsuarioEliminar) {

        String response = "";
        response += "<p style=\"font-family:Verdana; white-space: pre-line;\">" +
                "El código solicitado por parte del usuario administrador " +
                "<span style=\"font-weight: bold\">"+nombreSolicitanteCodigo + "(" + usuarioCodigo + ")" + "</span> " +
                "para confirmar la <span style=\"font-weight: bold\">Eliminación</span>  del usuario <span style=\"font-weight: bold\">"+nombreUsuarioEliminar + "(" + usuarioEliminar + ")" +" </span> \n"+
                "es el siguiente:</p>\n";
        response += "<h2 style=\"font-family:Verdana;\">"+codigo+"</h2>\n";
        response += "<p style=\"font-family:Verdana;\">Debe proporcionarlo al usuario que solicitó el código para completar la acción correspondiente.</p>\n";
        response += "<span style=\"font-family:Verdana;\">Saludos.</span>";

        return response;
    }

    private static String mensajeActivarUsuario(String codigo, String usuarioCodigo, String nombreSolicitanteCodigo, boolean admin) {

        String response = "";
        String administrador = admin ? " con privilegios de <span style=\"font-weight: bold\">Administrador</span> " : "";
        response += "<p style=\"font-family:Verdana; white-space: pre-line;\">" +
                "El código solicitado por parte del usuario " +
                "<span style=\"font-weight: bold\">"+nombreSolicitanteCodigo + "(" + usuarioCodigo + ") </span>" + administrador  +
                "para confirmar la <span style=\"font-weight: bold\">Activación de su Cuenta</span> como nuevo usuario es el siguiente:</p>\n";
        response += "<h2 style=\"font-family:Verdana;\">"+codigo+"</h2>\n";
        response += "<p style=\"font-family:Verdana;\">Debe proporcionarlo al usuario que solicitó el código para completar la acción correspondiente.</p>\n";
        response += "<span style=\"font-family:Verdana;\">Saludos.</span>";

        return response;
    }

    private static String mensajeCambiarContrasenia(String codigo, String usuarioCodigo, String nombreSolicitanteCodigo ) {

        String response = "";
        response += "<p style=\"font-family:Verdana; white-space: pre-line;\">" +
                "El código solicitado por parte del usuario " +
                "<span style=\"font-weight: bold\">"+nombreSolicitanteCodigo + "(" + usuarioCodigo + ")" + "</span> " +
                "para confirmar el <span style=\"font-weight: bold\">Cambio de Contraseña</span> para su cuenta es el siguiente:</p>\n";
        response += "<h2 style=\"font-family:Verdana;\">"+codigo+"</h2>\n";
        response += "<p style=\"font-family:Verdana;\">Debe proporcionarlo al usuario que solicitó el código para completar la acción correspondiente.</p>\n";
        response += "<span style=\"font-family:Verdana;\">Saludos.</span>";

        return response;
    }


}
