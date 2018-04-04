package ru.sbt.jschool.session3.problem2;

/**
 * Created by 1 on 04.04.2018.
 */
public class SimpleParkingLotTest {

    public static void main(String[] args) {
        ParkingLot parking1 = new ParkingLot(3, 4.5);

        System.out.println("Результат въезда:");
        System.out.println(parking1.driveIn(1, 0));
        System.out.println(parking1.driveIn(1, 3));
        System.out.println(parking1.driveIn(2, 6));
        System.out.println(parking1.driveIn(3, 12));
        System.out.println(parking1.driveIn(4, 16));

        System.out.println("Стоимость парковки:");
        System.out.println(parking1.leave(1, 0) + "$");
        System.out.println(parking1.leave(1, 15) + "$");
        System.out.println(parking1.leave(2, 38) + "$");
        System.out.println(parking1.leave(3, 155) + "$");
        System.out.println(parking1.leave(4, 142) + "$");



    }

}
