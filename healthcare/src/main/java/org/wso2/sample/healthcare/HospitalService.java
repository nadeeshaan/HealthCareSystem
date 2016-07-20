/*
 * Copyright (c) 2016, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.sample.healthcare;

import com.google.gson.Gson;
import org.wso2.sample.healthcare.util.HealthCareUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the Microservice resource class.
 * See <a href="https://github.com/wso2/msf4j#getting-started">https://github.com/wso2/msf4j#getting-started</a>
 * for the usage of annotations.
 *
 * @since 0.1-SNAPSHOT
 */
@Path("/hospital/categories")
public class HospitalService {

//    private Map<String, List<Doctor>> doctorsList = new HashMap<>();
    private Map<Integer, Appointment> appointments = new HashMap<>();
    private Map<String, Payment> payments = new HashMap<>();

//    HospitalDAO dao = new HospitalDAO();

    public HospitalService() {
        fillCatergories();
        HospitalDAO.doctorsList.add((new Doctor("ANURA BANAGALA", "Asiri Hospital", "SURGERY", "9.00 a.m - 11.00 a.m", 7000)));
        HospitalDAO.doctorsList.add((new Doctor("A.N.K. ABAYAJEEWA", "Durdans Hospital", "SURGERY", "8.00 a.m - 10.00 a.m", 12000)));
        HospitalDAO.doctorsList.add((new Doctor("ANIL P AMBAWATTA", "Apollo Hospitals", "SURGERY", "3.00 p.m - 5.00 p.m", 8000)));
        HospitalDAO.doctorsList.add((new Doctor("K. ALAGARATNAM", "Nawaloka Hospital", "CARDIOLOGY", "9.00 a.m - 11.00 a.m", 10000)));
        HospitalDAO.doctorsList.add((new Doctor("SANJAYA ABEYGUNASEKARA", "Asiri Hospital", "CARDIOLOGY", "8.00 a.m - 10.00 a.m", 4000)));
        HospitalDAO.doctorsList.add((new Doctor("K. ALAGARATNAM", "Durdans Hospital", "GYNAECOLOGY", "9.00 a.m - 11.00 a.m", 8000)));
        HospitalDAO.doctorsList.add((new Doctor("SANJAYA ABEYGUNASEKARA", "Asiri Hospital", "GYNAECOLOGY", "8.00 a.m - 10.00 a.m", 11000)));
        HospitalDAO.doctorsList.add((new Doctor("K. ALAGARATNAM", "Asiri Hospital", "ENT", "9.00 a.m - 11.00 a.m", 4500)));
        HospitalDAO.doctorsList.add((new Doctor("SANJAYA ABEYGUNASEKARA", "Asiri Hospital", "ENT", "8.00 a.m - 10.00 a.m", 6750)));
        HospitalDAO.doctorsList.add((new Doctor("AJITH AMARASINGHE", "Durdans Hospital", "PAEDIATRY", "9.00 a.m - 11.00 a.m", 5500)));
        HospitalDAO.doctorsList.add((new Doctor("SANJAYA ABEYGUNASEKARA", "Nawaloka Hospital", "PAEDIATRY", "8.00 a.m - 10.00 a.m", 10000)));
    }

    public void fillCatergories() {
        HospitalDAO.catergories.add("SURGERY");
        HospitalDAO.catergories.add("CARDIOLOGY");
        HospitalDAO.catergories.add("GYNAECOLOGY");
        HospitalDAO.catergories.add("ENT");
        HospitalDAO.catergories.add("PAEDIATRY");
    }

    @GET
    @Path("/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("category") String category) {
        List<Doctor> stock = HospitalDAO.findDoctorByCategory(category);
        return stock == null ?
                Response.status(Response.Status.NOT_FOUND).build() :
                Response.status(Response.Status.OK).entity(stock).build();
    }

    @POST
    @Path("/{category}/doctors/{doctor_name}/appointments/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAppointment(Appointment appointment) {
        if(appointments.get(appointment.getAppointmentNumber()) != null) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        appointments.put(appointment.getAppointmentNumber(), appointment);
        return Response.status(Response.Status.OK).
                entity("http://localhost:8080/stockquote/" + appointment.getAppointmentNumber()).build();
    }

    @PUT
    @Path("/")
    public void put() {
        // TODO: Implementation for HTTP PUT request
        System.out.println("PUT invoked");
    }

    @DELETE
    @Path("/")
    public void delete() {
        // TODO: Implementation for HTTP DELETE request
        System.out.println("DELETE invoked");
    }

    @POST
    @Path("/{category}/reserve_appointment")
    public Response reserveAppointment(AppointmentRequest appointmentRequest, @PathParam("category") String category) {

        Gson gson = new Gson();
        // Check whether the requested category available
        if (HospitalDAO.catergories.contains(category)) {
            Appointment appointment = HealthCareUtil.makeNewAppointment(appointmentRequest);
            this.appointments.put(appointment.getAppointmentNumber(), appointment);

            String jsonResponse = "{\"appointment_id\":" + appointment.getAppointmentNumber() + "}";
            return Response.ok(jsonResponse, MediaType.APPLICATION_JSON).build();
        } else {
            // Cannot find a doctor for this category
            String jsonResponse = "{\"Status\":\"Invalid Category\"}";
            return Response.ok(jsonResponse, MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Path("/appointments/{appointment_id}/fee")
    public Response checkChannellingFee(@PathParam("appointment_id") int id) {
        //Check for the appointment number validity
        Gson gson = new Gson();
        if (appointments.containsKey(id)) {
            String dob = appointments.get(id).getPatient().getDob();
            int discount = HealthCareUtil.checkForDiscounts(dob);
            Payment payment = HealthCareUtil.createNewPaymentEntry(appointments.get(id), discount);
            payment.setStatus("Not Settled");
            payments.put(payment.getPaymentID(), payment);
            appointments.get(id).setPaymentID(payment.getPaymentID());
            String jsonResponse = gson.toJson(payment);
            return Response.ok(jsonResponse, MediaType.APPLICATION_JSON).build();
        } else {
            String jsonResponse = "{\"Status\":\"Error.Could not Find the Requested appointment ID\"}";
            return Response.ok(jsonResponse, MediaType.APPLICATION_JSON).build();
        }
    }

    @POST
    @Path("/patient/payment/settle")
    public Response settlePayment(PaymentSettlement paymentSettlement) {
        //Check for the appointment number validity
        Gson gson = new Gson();
        if (appointments.containsKey(paymentSettlement.appointmentID)) {

            Appointment appointment = appointments.get(paymentSettlement.getAppointmentID());
            payments.get(appointment.getPaymentID());
            Payment payment = payments.get(appointment.getPaymentID());
            payment.setStatus("Payment Settled");

            String jsonResponse = gson.toJson(payment);
            return Response.ok(jsonResponse, MediaType.APPLICATION_JSON).build();
        } else {
            String jsonResponse = "{\"Status\":\"Error.Could not Find the Requested appointment ID\"}";
            return Response.ok(jsonResponse, MediaType.APPLICATION_JSON).build();
        }
    }

}
