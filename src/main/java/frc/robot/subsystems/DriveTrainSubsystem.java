// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.CANSparkBase.SoftLimitDirection;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;

public class DriveTrainSubsystem extends SubsystemBase {

  private final DigitalOutput throttle;
  private final Relay rearBrakeRelay;

  private final CANSparkMax brakeMotor;
  private final RelativeEncoder brakeEncoder;

  private final CANSparkMax steeringMotor;
  private final SparkAbsoluteEncoder steeringEncoder;

  private final Compressor airCompressor;
  private final Relay springBrake;

  private static boolean brakeHomed = false;

  /** Creates a new DriveTrainSubsystem. */
  public DriveTrainSubsystem() {

    //Steering Motor Config
    steeringMotor = new CANSparkMax(DriveTrainConstants.STEERINGMOTOR_ID, MotorType.kBrushed);
    steeringMotor.restoreFactoryDefaults();
    steeringMotor.setSmartCurrentLimit(DriveTrainConstants.STEERINGMOTOR_CURRENT_LIMIT);

    steeringEncoder = steeringMotor.getAbsoluteEncoder();
    steeringMotor.enableSoftLimit(SoftLimitDirection.kForward, true);
    steeringMotor.enableSoftLimit(SoftLimitDirection.kReverse, true);
    steeringMotor.setSoftLimit(SoftLimitDirection.kForward, DriveTrainConstants.STEERINGMOTORFORWARDLIMIT);
    steeringMotor.setSoftLimit(SoftLimitDirection.kReverse, DriveTrainConstants.STEERINGMOTORREVERSELIMIT);
    
    //Brake Motor Config
    brakeMotor = new CANSparkMax(DriveTrainConstants.BRAKEMOTOR_ID, MotorType.kBrushed);
    brakeMotor.restoreFactoryDefaults();
    brakeMotor.setSmartCurrentLimit(DriveTrainConstants.BRAKEMOTOR_CURRENT_LIMIT);

    brakeEncoder = brakeMotor.getEncoder(); 


  
    throttle = new DigitalOutput(DriveTrainConstants.THROTTLE_PORT);
    rearBrakeRelay = new Relay(DriveTrainConstants.REARBRAKE_PORT);
    airCompressor = new Compressor(PneumaticsModuleType.CTREPCM);
    springBrake = new Relay(DriveTrainConstants.AIRBRAKERELAY_PORT);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Brake Homed", brakeHomed);
  }

  public void drive(double throttle, double frontBrake, boolean rearBrake, boolean springBrakeEnbable, boolean reverse){
    if(!brakeHomed || springBrakeEnbable) {
      airCompressor.disable();
      enableSpringBrake();
      return;
    }
    else{
      disableSpringBrake();
      if(rearBrake){
        rearBrakeRelay.setDirection(Relay.Direction.kForward);
        return;
      }
      rearBrakeRelay.setDirection(Relay.Direction.kReverse);
    }
  }

  public void homeBrakeMotor(){
  }

  public void enableSpringBrake(){
    springBrake.setDirection(Relay.Direction.kReverse);
  }

  public void disableSpringBrake(){
    if(!brakeHomed) return;
    else{
      springBrake.setDirection(Relay.Direction.kForward);
    }
  }
}
