// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new Drive. */
  private TalonFX frontLeftMotor;
  private TalonFX backLeftMotor;
  private TalonFX frontRightMotor;
  private TalonFX backRightMotor;

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
    // System.out.println("Driving");   // Debug
  }

  public void setNeutralMode(NeutralModeValue value){
    frontLeftMotor.setNeutralMode(value);
    frontRightMotor.setNeutralMode(value);
    backLeftMotor.setNeutralMode(value);
    backRightMotor.setNeutralMode(value);
  }

  public double getLowestAbsoluteSpeed(){
    double currentLowestSpeed = frontLeftMotor.get();
    for (TalonFX iterable_element : new TalonFX[] {backLeftMotor, frontRightMotor, backRightMotor}) {
      if(Math.abs(iterable_element.get()) < currentLowestSpeed)
        currentLowestSpeed = iterable_element.get();
    }
    return currentLowestSpeed;
  }
  public TalonFX getLowestAbsoluteSpeedMotor(){
    TalonFX currentLowestSpeedMotor = frontLeftMotor;
    for (TalonFX iterable_element : new TalonFX[] {backLeftMotor, frontRightMotor, backRightMotor}) {
      if(Math.abs(iterable_element.get()) < currentLowestSpeedMotor.get())
        currentLowestSpeedMotor = iterable_element;
    }
    return currentLowestSpeedMotor;
  }
  
  public double getSpeed(){
    double rotationalVelocity = getLowestAbsoluteSpeedMotor().getVelocity().getValueAsDouble();  // RPM
    double linearVelocity = rotationalVelocity * 8 * Math.PI;  // Inches per Minute
    double convertedVelocity = (linearVelocity * 60) / 63360;  // Miles per Hour
    return convertedVelocity;
  }

  private void putSmartDashboard(){
    SmartDashboard.putNumber("Speed", getSpeed());
  }

  @Override
  public void periodic(){
    putSmartDashboard();
  }
}
