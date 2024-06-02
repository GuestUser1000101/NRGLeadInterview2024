// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MotorSubsystem extends SubsystemBase {

  private final CANSparkMax motor = new CANSparkMax(Constants.MOTOR_PORT, MotorType.kBrushless);
  private final double MOTOR_VOLTAGE = 1.0;

  private boolean isEnabled = false;

  private final Timer timer = new Timer();

  private double runTimeSeconds = 10;

  /** Creates a new MotorSubsystem. */
  public MotorSubsystem() {
    motor.setIdleMode(IdleMode.kBrake);
    timer.reset();
  }

  @Override
  public void periodic() {    
    if (timer.hasElapsed(runTimeSeconds)) {
      stopMotorTimer();
    }

    if (isEnabled) {
      runMotor();
    }
  }

  /**
   * Run motor at set voltage.
   */
  public void runMotor() {
    isEnabled = true;
    motor.setVoltage(MOTOR_VOLTAGE);
  }

  /**
   * Stop motor.
   */
  public void disableMotor() {
    isEnabled = false;
    motor.stopMotor();
  }

  /**
   * Start motor to run for set amount of time.
   */
  public void startMotorTimer() {
    timer.restart();
    runMotor();
  }

  /**
   * Stop motor and reset timer.
   */
  public void stopMotorTimer() {
    timer.stop();
    timer.reset();
    disableMotor();
  }
}
