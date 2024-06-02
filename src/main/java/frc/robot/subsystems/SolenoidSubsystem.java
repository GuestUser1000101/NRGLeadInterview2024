// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SolenoidConstants;

public class SolenoidSubsystem extends SubsystemBase {

  private final DoubleSolenoid solenoid = new DoubleSolenoid(
    PneumaticsModuleType.CTREPCM,
    SolenoidConstants.SOLENOID_PORT_FOWARD,
    SolenoidConstants.SOLENOID_PORT_REVERSE
    );

  /** Creates a new SolenoidSubsystem. */
  public SolenoidSubsystem() {
    solenoid.set(DoubleSolenoid.Value.kOff);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Extend the double solenoid.
   */
  public void extend() {
    solenoid.set(DoubleSolenoid.Value.kForward);
  }

  /**
   * Reverse the double solenoid.
   */
  public void reverse() {
    solenoid.set(DoubleSolenoid.Value.kReverse);
  }
}
