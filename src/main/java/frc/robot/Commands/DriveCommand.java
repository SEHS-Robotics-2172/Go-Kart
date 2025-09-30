// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.DriveSubsystem;

import frc.robot.Constants;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class DriveCommand extends Command {
  /** Creates a new DriveCommand. */
  DriveSubsystem driveSubsystem;
  DoubleSupplier speedForward;
  DoubleSupplier speedBackwards;
  BooleanSupplier brakeMode;
  double speed;
  
  public DriveCommand(DriveSubsystem driveSubsystem, DoubleSupplier speedForward, DoubleSupplier speedBackwards, BooleanSupplier brakeMode) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveSubsystem = driveSubsystem;
    this.speedBackwards = speedBackwards;
    this.speedForward = speedForward;
    this.brakeMode = brakeMode;
    addRequirements(driveSubsystem);
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    speed = speedForward.getAsDouble() - speedBackwards.getAsDouble();

    if(DriveSubsystem.MPHtoRPS(speed * Constants.maxSpeed) < Math.abs(driveSubsystem.getLowestAbsoluteSpeed())){
      speed = 0;
    }
    SmartDashboard.putNumber("RPS", driveSubsystem.getLowestAbsoluteSpeed());
    SmartDashboard.putNumber("Wanted RPS", DriveSubsystem.MPHtoRPS(speed * Constants.maxSpeed));
    if(brakeMode.getAsBoolean())
      speed = 0.01;

    driveSubsystem.drive(speed);
  }
}
