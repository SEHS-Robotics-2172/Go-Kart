// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.DriveSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class DriveCommand extends Command {
  /** Creates a new DriveCommand. */
  DriveSubsystem driveSubsystem;
  DoubleSupplier speedForward;
  DoubleSupplier speedBackwards;
  
  public DriveCommand(DriveSubsystem driveSubsystem, DoubleSupplier speedForward, DoubleSupplier speedBackwards) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveSubsystem = driveSubsystem;
    this.speedBackwards = speedBackwards;
    this.speedForward = speedForward;
    addRequirements(driveSubsystem);
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveSubsystem.drive(speedForward.getAsDouble() - speedBackwards.getAsDouble());
  }
}
