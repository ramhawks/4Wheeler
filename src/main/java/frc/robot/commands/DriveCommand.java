// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSubsystem;


public class DriveCommand extends Command {
  
  private final DriveTrainSubsystem driveTrain;
  private double throttle;
  private double steer;
  private double frontBrake;
  private boolean rearBrake;
  private boolean springBrakeEnbable;
  private boolean reverse;

  /** Creates a new DriveCommand. */
  public DriveCommand(DriveTrainSubsystem driveTrain, 
                          double throttle, 
                          double steer,
                          double frontBrake, 
                          boolean rearBrake, 
                          boolean springBrakeEnbable, 
                          boolean reverse) {
    // Use addRequirements() here to declare subsystem dependencies.

    this.driveTrain = driveTrain;
    this.throttle = throttle;
    this.steer = steer;
    this.frontBrake = frontBrake;
    this.rearBrake = rearBrake;
    this.springBrakeEnbable = springBrakeEnbable;
    this.reverse = reverse;

    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.drive(throttle, steer, frontBrake, rearBrake, springBrakeEnbable, reverse);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
