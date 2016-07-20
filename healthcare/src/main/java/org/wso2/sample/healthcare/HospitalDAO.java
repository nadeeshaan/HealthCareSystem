package org.wso2.sample.healthcare;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vijitha on 7/8/16.
 */
public class HospitalDAO {

    public static List<Doctor> doctorsList = new ArrayList<>();
    public static List<String> catergories = new ArrayList<>();

    public static List<Doctor> findDoctorByCategory(String category) {
        List<Doctor> list = new ArrayList<>();
        for (Doctor doctor: doctorsList) {
            if (category.equals(doctor.getCategory())) {
                list.add(doctor);
            }
        }
        return list;
    }

    public static Doctor findDoctorByName(String name) {
        for (Doctor doctor: doctorsList) {
            if (doctor.getName().equals(name)) {
                return doctor;
            }
        }

        return null;
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
