// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new Drive. */
  public TalonFX frontLeftMotor;
  public TalonFX backLeftMotor;
  public TalonFX frontRightMotor;
  public TalonFX backRightMotor;

  public DriveSubsystem() {
    frontLeftMotor = new TalonFX(Constants.FrontLeftMotor);
    backLeftMotor = new TalonFX(Constants.BackLeftMotor);
    frontRightMotor = new TalonFX(Constants.FrontRightMotor);
    backRightMotor = new TalonFX(Constants.BackRightMotor);
  }

  public void drive(double speed){
    frontLeftMotor.set(-speed);
    frontRightMotor.set(-speed);
    backLeftMotor.set(-speed);
    backRightMotor.set(-speed);
    System.out.println("Driving");
  }
}
