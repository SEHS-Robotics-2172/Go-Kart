// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/** Every value that never changes goes into this class as a static variable. */
public class Constants {
    public static final int FrontLeftMotor = 3;
    public static final int BackLeftMotor = 2;
    public static final int FrontRightMotor = 1;
    public static final int BackRightMotor = 4;

    public static final double gearRatio = 7.5;
    public static final double wheelRadius = 4;

    public static final double maxSpeed = 10;   // MPH,

    public static final double driveMotorkP = 1; // TODO: Should be a small value to make up for resistance after kV is applied
    public static final double driveMotorkV = 1; // TODO: TUNE THIS BEFORE ANYTHING ELSE HAPPENS 
}
