package ru.sbt.jschool.session3.problem2;

/**
 * Created by 1 on 02.04.2018.
 */
public interface ParkingService {
    // если можно вьехать, то возвращает true.
    boolean driveIn(long carID, long timeOfDriveIn);
    // оплата стоянки при выезде
    double leave(long carID, long timeOfLeave);
}
