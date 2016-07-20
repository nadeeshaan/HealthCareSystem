package org.wso2.sample.healthcare;

/**
 * Created by nadeeshaan on 7/20/16.
 */
public class PaymentSettlement {
    int appointmentID;
    double paymentAmount;
    String card_number;

    public PaymentSettlement(int appointmentID, double paymentAmount, String card_number) {
        this.appointmentID = appointmentID;
        this.paymentAmount = paymentAmount;
        this.card_number = card_number;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }
}
