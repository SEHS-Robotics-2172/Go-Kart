// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Commands.DriveCommand;
import frc.robot.Subsystems.DriveSubsystem;

public class RobotContainer {
  private Joystick controller;
  private DriveSubsystem driveSubsystem;
  public RobotContainer() {
    controller = new Joystick(0);
    driveSubsystem = new DriveSubsystem();

    driveSubsystem.setDefaultCommand(
      new DriveCommand(
        driveSubsystem, 
        () -> {return (controller.getRawAxis(3) + 1) / 2;}, 
        () -> controller.getRawAxis(1),
        () -> {
          if (controller.getRawAxis(2) <= -0.9)
            return true;
          else
            return false;
          }
      )
    );

    configureBindings();
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
