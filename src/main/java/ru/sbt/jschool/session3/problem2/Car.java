package ru.sbt.jschool.session3.problem2;

/**
 * Created by 1 on 02.04.2018.
 */
public class Car {
    private long carID;         // ID автомобиля

    private long startTime;     // время начала стоянки

    public Car(long carID, long startTime){
        this.carID = carID;
        this.startTime = startTime;
    }

    public long getCarID() {
        return carID;
    }

    public void setCarID(long carID) {
        this.carID = carID;
    }

    public long getstartTime() {
        return startTime;
    }

    public void setstartTime(long startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}