// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants.EncoderConstants;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {
  private final WPI_TalonFX m_frontLeftMotor = new WPI_TalonFX(1); 
  private final WPI_TalonFX m_backLeftMotor= new WPI_TalonFX(2);
  private final WPI_TalonFX m_frontRightMotor = new WPI_TalonFX(3);
  private final WPI_TalonFX m_backRightMotor = new WPI_TalonFX(4);
  
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_frontLeftMotor, m_frontRightMotor);
  private final Joystick m_stick = new Joystick(0);

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
   
     //Set Masters and Followers
     m_backLeftMotor.follow(m_frontLeftMotor);
     m_backRightMotor.follow(m_frontRightMotor);

     m_frontLeftMotor.configFactoryDefault();
     m_backLeftMotor.configFactoryDefault();
     m_frontRightMotor.configFactoryDefault();
     m_backLeftMotor.configFactoryDefault();
     // Drive Train Encoder Setup
   m_frontLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, EncoderConstants.kPIDLoopIdx, EncoderConstants.kTimeoutMs);
   m_frontRightMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, EncoderConstants.kPIDLoopIdx, EncoderConstants.kTimeoutMs);
  }

  @Override
  
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    m_robotDrive.arcadeDrive(-m_stick.getX(), -m_stick.getY());
  }
}
