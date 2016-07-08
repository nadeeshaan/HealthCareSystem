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

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Array;
import java.util.ArrayList;
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

    private Map<String, List<Doctor>> doctorsList = new HashMap<>();

    public HospitalService() {
        List<Doctor> surgeonsList = new ArrayList<>();
        List<Doctor> cardiologistsList = new ArrayList<>();
        List<Doctor> gynaecologistsList = new ArrayList<>();
        List<Doctor> entList = new ArrayList<>();
        List<Doctor> paediatriciansList = new ArrayList<>();
        surgeonsList.add((new Doctor("ANURA BANAGALA", "Asiri Hospital", "SURGERY", "9.00 a.m - 11.00 a.m")));
        surgeonsList.add((new Doctor("A.N.K. ABAYAJEEWA", "Durdans Hospital", "SURGERY", "8.00 a.m - 10.00 a.m")));
        surgeonsList.add((new Doctor("ANIL P AMBAWATTA", "Apollo Hospitals", "SURGERY", "3.00 p.m - 5.00 p.m")));
        cardiologistsList.add((new Doctor("K. ALAGARATNAM", "Nawaloka Hospital", "CARDIOLOGY", "9.00 a.m - 11.00 a.m")));
        cardiologistsList.add((new Doctor("SANJAYA ABEYGUNASEKARA", "Asiri Hospital", "CARDIOLOGY", "8.00 a.m - 10.00 a.m")));
        gynaecologistsList.add((new Doctor("K. ALAGARATNAM", "Durdans Hospital", "GYNAECOLOGY", "9.00 a.m - 11.00 a.m")));
        gynaecologistsList.add((new Doctor("SANJAYA ABEYGUNASEKARA", "Asiri Hospital", "GYNAECOLOGY", "8.00 a.m - 10.00 a.m")));
        entList.add((new Doctor("K. ALAGARATNAM", "Asiri Hospital", "ENT", "9.00 a.m - 11.00 a.m")));
        entList.add((new Doctor("SANJAYA ABEYGUNASEKARA", "Asiri Hospital", "ENT", "8.00 a.m - 10.00 a.m")));
        paediatriciansList.add((new Doctor("AJITH AMARASINGHE", "Durdans Hospital", "PAEDIATRY", "9.00 a.m - 11.00 a.m")));
        paediatriciansList.add((new Doctor("SANJAYA ABEYGUNASEKARA", "Nawaloka Hospital", "PAEDIATRY", "8.00 a.m - 10.00 a.m")));
        doctorsList.put("SURGERY", surgeonsList);
        doctorsList.put("CARDIOLOGY", cardiologistsList);
        doctorsList.put("GYNAECOLOGY", gynaecologistsList);
        doctorsList.put("ENT", entList);
        doctorsList.put("PAEDIATRY", paediatriciansList);
    }

    @GET
    @Path("/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("category") String category) {
        List<Doctor> stock = doctorsList.get(category);
        return stock == null ?
                Response.status(Response.Status.NOT_FOUND).build() :
                Response.status(Response.Status.OK).entity(stock).build();
    }

    @POST
    @Path("/")
    public void post() {
        // TODO: Implementation for HTTP POST request
        System.out.println("POST invoked");
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
}
