package ru.sbt.jschool.session3.problem2;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 * Created by 1 on 07.04.2018.
 */
public class ParkingLotTest {
    @Test public void driveInTest1(){

        long carID = 1;
        long timeOfDriveIn = 0;

        int numberOfParkingSpaces = 1;
        double costPerHour = 1;

        ParkingLot parkingLot = new ParkingLot(numberOfParkingSpaces,costPerHour);

        assertEquals(true, parkingLot.driveIn(1,timeOfDriveIn));

    }

    @Test public void driveInTest2(){

        long carID = 1;
        long timeOfDriveIn = 0;

        int numberOfParkingSpaces = 1;
        double costPerHour = 1;

        ParkingLot parkingLot = new ParkingLot(numberOfParkingSpaces,costPerHour);
        parkingLot.driveIn(1,timeOfDriveIn);

        assertEquals(false, parkingLot.driveIn(1,timeOfDriveIn));

    }

    @Test public void driveInAndLeaveTest(){

        long carID1 = 1;
        long carID2 = 1;
        long carID3 = 1;

        long timeOfDriveIn = 0;

        int numberOfParkingSpaces = 2;
        double costPerHour = 1;

        ParkingLot parkingLot = new ParkingLot(numberOfParkingSpaces,costPerHour);
        parkingLot.driveIn(1,timeOfDriveIn);
        parkingLot.driveIn(2,timeOfDriveIn);
        parkingLot.leave(2,timeOfDriveIn);

        assertEquals(true, parkingLot.driveIn(3,timeOfDriveIn));
    }

    @Test public void LeaveTest1(){

        long carID = 1;

        long timeOfDriveIn = 0;
        long timeOfLeave = 0;

        int numberOfParkingSpaces = 1;
        double costPerHour = 1;

        ParkingLot parkingLot = new ParkingLot(numberOfParkingSpaces,costPerHour);
        parkingLot.driveIn(1,timeOfDriveIn);

        assertEquals(0, parkingLot.leave(1,timeOfLeave), 0);
    }

    @Test public void LeaveTest2(){

        long carID = 1;

        long timeOfDriveIn = 0;
        long timeOfLeave = 24;

        int numberOfParkingSpaces = 1;
        double costPerHour = 1;

        ParkingLot parkingLot = new ParkingLot(numberOfParkingSpaces,costPerHour);
        parkingLot.driveIn(1,timeOfDriveIn);

        assertEquals(31, parkingLot.leave(1,timeOfLeave), 0);
    }

    @Test public void LeaveTest3(){

        long carID = 1;

        long timeOfDriveIn = 12;
        long timeOfLeave = 48;

        int numberOfParkingSpaces = 1;
        double costPerHour = 1;

        ParkingLot parkingLot = new ParkingLot(numberOfParkingSpaces,costPerHour);
        parkingLot.driveIn(1,timeOfDriveIn);

        assertEquals(44, parkingLot.leave(1,timeOfLeave), 0);
    }
}
