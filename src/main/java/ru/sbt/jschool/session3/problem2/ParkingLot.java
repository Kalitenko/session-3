package ru.sbt.jschool.session3.problem2;

import java.util.HashMap;

/**
 * Created by 1 on 02.04.2018.
 */
public class ParkingLot implements ParkingService {

    // вместимость автостоянки
    private int numberOfParkingSpaces;
    // количество автомобилей на стоянке
    private int numberCarsOnParkingLot;
    // стоимость часа стоянки
    private double costPerHour;
    // автомобили на стоянке
    private HashMap<Long, Car> carsOnParkingLot = new HashMap<>();

    ParkingLot(int num, int cost){

        this.numberOfParkingSpaces = num;
        this.costPerHour = cost;
    }


    @Override
    public boolean driveIn(long carID, long timeOfDriveIn) {

        // проверка на наличие свободных мест на парковке
        if(!(numberCarsOnParkingLot < numberOfParkingSpaces))
            return false;

        // проверка на наличие автомобиля с таким же номером
        if(carsOnParkingLot.containsKey(carID))
            return false;

        // если автомобиля нет на парковке и есть свободные места
        // то автомобиль может въехать на парковку
        numberOfParkingSpaces++;
        Car car = new Car(carID, timeOfDriveIn);
        carsOnParkingLot.put(carID, car);
        return true;
    }

    @Override
    public double leave(long carID, long timeOfLeave) {

        // сумма к оплате
        double cost = 0;

        // возвращаем ноль за автомобиль, которого не было на парковке
        if(!carsOnParkingLot.containsKey(carID))
            return cost;

        // для автомобиля, который был на парковке
        Car car = carsOnParkingLot.get(carID);

        // если время выезда, меньше чем время въезда
        // ошибка? пока плату не берем
        if(timeOfLeave < car.getstartTime())
            return cost;

        long parkingTime = timeOfLeave - car.getstartTime();

        cost = parkingTime * costPerHour;

        return cost;
    }
}