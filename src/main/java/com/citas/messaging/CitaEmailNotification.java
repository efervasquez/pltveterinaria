package com.citas.messaging;

import com.citas.dto.ClienteDTO;
import com.citas.model.Cita;
import java.io.Serializable;
import java.util.Objects;
import java.time.format.DateTimeFormatter;

public class CitaEmailNotification implements Serializable {
    private String to;
    private String subject;
    private String body;

    public CitaEmailNotification() {}

    public CitaEmailNotification(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    // Create notification for new user registration
    public static CitaEmailNotification fromCita(Cita cita , ClienteDTO cliente) {
        String to = cliente.getCorreo();
        String subject = "Confirmacion de cita veterinaria!";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a"); // 12h + AM/PM
        String horaFormateada = cita.getHorainicio().format(formatter);
        String body = "Hola " + cliente.getNombres()
                + ", tu cita ha sido registrada exitosamente para el día "
                + cita.getFecha() + " a las " + horaFormateada;
        return new CitaEmailNotification(to, subject, body);
    }

    // equals, hashCode and toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CitaEmailNotification that = (CitaEmailNotification) o;
        return Objects.equals(to, that.to) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(to, subject, body);
    }

    @Override
    public String toString() {
        return "CitaEmailNotification{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

}
