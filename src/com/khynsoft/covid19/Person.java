package com.khynsoft.covid19;

public class Person {
    private String name, address, date, time, contactNumber;
    private float bodyTemperature;

    public Person(String name,
                  String address,
                  String date,
                  String time,
                  String contactNumber,
                  float bodyTemperature) {
        this.name = name;
        this.address = address;
        this.date = date;
        this.time = time;
        this.contactNumber = contactNumber;
        this.bodyTemperature = bodyTemperature;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public float getBodyTemperature() {
        return bodyTemperature;
    }

    @Override
    public String toString() {
        return String.format(
                "%s\t|\t%s\t\t|\t%s\t|\t%s\t|\t%s\t\t|\t%.2f°C",
                name,
                address,
                contactNumber,
                date,
                time,
                bodyTemperature);
    }
}
