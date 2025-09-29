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
    frontLeftMotor.set(speed);
    frontRightMotor.set(speed);
    backLeftMotor.set(speed);
    backRightMotor.set(speed);
    // System.out.println("Driving");   // Debug
  }

  public double getLowestAbsoluteSpeed(){
    return getLowestAbsoluteSpeedMotor().get();
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
    double bufferVelocity = getLowestAbsoluteSpeedMotor().getVelocity().getValueAsDouble() * Constants.gearRation;  // RPM
    bufferVelocity = bufferVelocity * 8 * Math.PI;  // Inches per Minute
    bufferVelocity = (bufferVelocity * 60) / 63360;  // Miles per Hour
    return bufferVelocity;
  }
  
  private void putSmartDashboard(){
    SmartDashboard.putNumber("Speed", Math.round(getSpeed() / 10) * 10);
  }
  
  @Override
  public void periodic(){
    putSmartDashboard();
  }
}
