package org.wso2.sample.healthcare;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

/**
 * Created by nadeeshaan on 7/20/16.
 */

@XmlRootElement
public class Payment {
    private String patient;
    private double actualFee;
    private int discount;
    private double discounted;
    private String paymentID;

    private String status;

    public Payment() {
        this.paymentID = UUID.randomUUID().toString();
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public double getActualFee() {
        return actualFee;
    }

    public void setActualFee(double actualFee) {
        this.actualFee = actualFee;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getDiscounted() {
        return discounted;
    }

    public void setDiscounted(double discounted) {
        this.discounted = discounted;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
