// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Commands.DriveCommand;
import frc.robot.Subsystems.DriveSubsystem;

public class RobotContainer {
  XboxController controller;
  DriveSubsystem driveSubsystem;
  public RobotContainer() {
    controller = new XboxController(0);
    driveSubsystem = new DriveSubsystem();

    driveSubsystem.setDefaultCommand(
      new DriveCommand(
        driveSubsystem, 
        controller::getRightTriggerAxis, 
        controller::getLeftTriggerAxis,
        controller::getBButton
      )
    );

    configureBindings();
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
