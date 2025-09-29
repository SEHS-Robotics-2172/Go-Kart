// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.DriveSubsystem;

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

    if(speed < driveSubsystem.getLowestAbsoluteSpeed()){
      speed = 0;
    }
    
    if(brakeMode.getAsBoolean()){
      speed = 0.01;
    }

    driveSubsystem.drive(speed);
  }
}
