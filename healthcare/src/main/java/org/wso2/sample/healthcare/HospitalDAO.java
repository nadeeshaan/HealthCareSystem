package org.wso2.sample.healthcare;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vijitha on 7/8/16.
 */
public class HospitalDAO {

    public static List<Doctor> doctorsList = new ArrayList<>();

    public static List<Doctor> findDoctorByCategory(String category) {
        List<Doctor> list = new ArrayList<>();
        for (Doctor doctor: doctorsList) {
            if (category.equals(doctor.getCategory())) {
                list.add(doctor);
            }
        }
        return list;
    }

    public static Appointment makeAppointment(String category, String doctor) {
        Appointment appointment = new Appointment();
//        for (Doctor doctor: doctorsList) {
//            if (category.equals(doctor.getCategory())) {
//                list.add(doctor);
//            }
//        }
        return appointment;
    }

}
