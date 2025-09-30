// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
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
  private TalonFXConfiguration leftConfig;
  private TalonFXConfiguration rightConfig;
  private VelocityVoltage velocityVoltage;

  public DriveSubsystem() {
    frontLeftMotor = new TalonFX(Constants.FrontLeftMotor);
    backLeftMotor = new TalonFX(Constants.BackLeftMotor);
    frontRightMotor = new TalonFX(Constants.FrontRightMotor);
    backRightMotor = new TalonFX(Constants.BackRightMotor);

    leftConfig = new TalonFXConfiguration();
    rightConfig = new TalonFXConfiguration();

    leftConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    leftConfig.Feedback.SensorToMechanismRatio = Constants.gearRatio;
    leftConfig.Slot0.kP = 0.1;

    rightConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    rightConfig.Feedback.SensorToMechanismRatio = Constants.gearRatio;


    velocityVoltage = new VelocityVoltage(0);
  }

  public void drive(double speed){
    if (speed != 0){
      velocityVoltage.withVelocity(MPHtoRPS(speed * Constants.maxSpeed));
      frontLeftMotor.setControl(velocityVoltage);
      frontRightMotor.setControl(velocityVoltage);
      backLeftMotor.setControl(velocityVoltage);
      backRightMotor.setControl(velocityVoltage);
    }
    else {
      frontLeftMotor.set(0);
      frontRightMotor.set(0);
      backLeftMotor.set(0);
      backRightMotor.set(0);
    }
    // System.out.println("Driving");   // Debug
  }

  public double getLowestAbsoluteSpeed(){
    return getSpeed(getLowestAbsoluteSpeedMotor());
  }
  public TalonFX getLowestAbsoluteSpeedMotor(){
    TalonFX currentLowestSpeedMotor = frontLeftMotor;
    for (TalonFX iterable_element : new TalonFX[] {backLeftMotor, frontRightMotor, backRightMotor}) {
      if(Math.abs(iterable_element.getVelocity().getValueAsDouble()) < currentLowestSpeedMotor.getVelocity().getValueAsDouble())
        currentLowestSpeedMotor = iterable_element;
    }
    return currentLowestSpeedMotor;
  }

  public static double getSpeed(TalonFX motor){
    return RPStoMPH(motor.getVelocity().getValueAsDouble());
  }

  /*Velocity to Motor Speed */
  public static double MPHtoRPS(double mph){
    return ((mph / 60) * (1 / (Math.PI * Constants.wheelRadius * 2)) * (63360) / 60) * Constants.gearRatio;
  }

  /*Motor Speed to Velocity */
  public static double RPStoMPH(double rps){
    return ((rps / Constants.gearRatio) * 60) * (Constants.wheelRadius * 2 * Math.PI) * (1/63360);
  }

  private void putSmartDashboard(){
    SmartDashboard.putNumber("Speed", Math.round(getSpeed(getLowestAbsoluteSpeedMotor()) * 10) / 10);
  }

  @Override
  public void periodic(){
    putSmartDashboard();
  }
}
