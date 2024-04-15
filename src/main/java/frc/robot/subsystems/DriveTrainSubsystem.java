// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;

public class DriveTrainSubsystem extends SubsystemBase {

  private final CANSparkMax brakeMotor;
  private final RelativeEncoder brakeEncoder;

  private final CANSparkMax steeringMotor;
  private final AbsoluteEncoder steeringEncoder;

  private static boolean brakeHomed = false;

  /** Creates a new DriveTrainSubsystem. */
  public DriveTrainSubsystem() {

    //Steering Motor Config
    steeringMotor = new CANSparkMax(DriveTrainConstants.STEERINGMOTOR_ID, MotorType.kBrushed);
    steeringMotor.restoreFactoryDefaults();
    steeringMotor.setSmartCurrentLimit(DriveTrainConstants.STEERINGMOTOR_CURRENT_LIMIT);

    steeringEncoder = steeringMotor.getAbsoluteEncoder();

    
    //Brake Motor Config
    brakeMotor = new CANSparkMax(DriveTrainConstants.BRAKEMOTOR_ID, MotorType.kBrushed);
    brakeMotor.restoreFactoryDefaults();
    brakeMotor.setSmartCurrentLimit(DriveTrainConstants.BRAKEMOTOR_CURRENT_LIMIT);

    brakeEncoder = brakeMotor.getEncoder(); 
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void homeBrakeMotor(){

  }
}
