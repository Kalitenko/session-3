package ru.sbt.jschool.session3.problem2;

import java.util.HashMap;

/**
 * Created by 1 on 02.04.2018.
 */
public class ParkingLot implements ParkingService {

    // вместимость автостоянки
    private int numberOfParkingSpaces;
    // стоимость часа стоянки
    private double costPerHour;
    // автомобили на стоянке
    private HashMap<Long, Car> carsOnParkingLot = new HashMap<>();

    ParkingLot(int num, double cost){

        this.numberOfParkingSpaces = num;
        this.costPerHour = cost;
    }


    @Override
    public boolean driveIn(long carID, long timeOfDriveIn) {

        // проверка на наличие свободных мест на парковке
        if(numberOfParkingSpaces <= 0)
            return false;

        // проверка на наличие автомобиля с таким же номером
        if(carsOnParkingLot.containsKey(carID))
            return false;

        // если автомобиля нет на парковке и есть свободные места
        // то автомобиль может въехать на парковку
        numberOfParkingSpaces--;
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
        // место освободилось
        numberOfParkingSpaces++;
        // удаляем с парковки
        carsOnParkingLot.remove(carID, car);

        // если время выезда, меньше чем время въезда
        // ошибка? пока плату не берем
        if(timeOfLeave < car.getstartTime())
            return cost;

        cost = calculateCost(car.getstartTime(), timeOfLeave);

        return cost;
    }

    // расчет стоимости парковки
    private double calculateCost(long startTime, long finalTime){

        double cost = 0;

        // общее время парковки
        long timeOnParking = finalTime - startTime;

        long tmp = startTime % 24;
        startTime = (tmp == 0) ? 24 : tmp;

        tmp = finalTime % 24;
        finalTime = (tmp == 0) ? 24 : tmp;

        long nightFactor = 2;
        long startNight = 23;
        long startDay = 6;
        long lengthOfNight = 24 - startNight + startDay;

        double costPerDay = (lengthOfNight * nightFactor + 24 - lengthOfNight) * costPerHour;
        cost = timeOnParking / 24 * costPerDay;
        timeOnParking %= 24;


        // если осталось время
        if(timeOnParking > 0) {

            if (startTime >= startNight || startTime < startDay)
                if (finalTime%24 <= startDay)
                    cost += timeOnParking * nightFactor * costPerHour;
                else{
                    tmp = startTime >= startNight ? (startTime - startNight) : 0;
                    cost += ((tmp + startDay) * nightFactor
                            + timeOnParking - (tmp + startDay + 1)) * costPerHour;
                }
            else {
                if (finalTime < startNight)
                    cost += timeOnParking * costPerHour;
                else
                    cost += ((finalTime - startNight) * nightFactor
                            + (timeOnParking - (finalTime - startNight))) * costPerHour;
            }
        }

        return cost;
    }
}