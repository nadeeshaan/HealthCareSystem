package org.wso2.sample.healthcare.util;

import org.wso2.sample.healthcare.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by nadeeshaan on 7/20/16.
 */
public class HealthCareUtil {

    public static int appointmentNumber = 0;

    public static Appointment makeNewAppointment(AppointmentRequest appointmentRequest) {

        Appointment newAppointment = new Appointment();
        Doctor doctor = HospitalDAO.findDoctorByName(appointmentRequest.getDoctor());
        newAppointment.setAppointmentNumber(appointmentNumber++);
        newAppointment.setDoctor(doctor);
        newAppointment.setPatient(appointmentRequest.getPatient());
        newAppointment.setFee(123.456);
        newAppointment.setConfirmed(false);

        return newAppointment;
    }

    //Discount is calculated by checking the age considering the birt year only
    public static int checkForDiscounts(String dob) {
        int yob = Integer.parseInt(dob.split("-")[0]);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int age = currentYear - yob;

        if (age < 12) {
            return 15;
        } else if (age > 55){
            return 20;
        } else {
            return 0;
        }
    }

    public static Payment createNewPaymentEntry(Appointment appointment, int discount) {
        Payment payment = new Payment();
        String doctor = appointment.getDoctor().getName();
        payment.setActualFee(HospitalDAO.findDoctorByName(doctor).getFee());
        payment.setDiscount(discount);
        double discounted = (((HospitalDAO.findDoctorByName(doctor).getFee())/100)*(100-discount));
        payment.setDiscounted(discounted);
        payment.setPatient(appointment.getPatient().getName());

        return payment;
    }
}
