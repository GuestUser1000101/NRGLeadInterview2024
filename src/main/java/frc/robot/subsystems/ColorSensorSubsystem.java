// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorSensorSubsystem extends SubsystemBase {

  private final ColorSensorV3 camera = new ColorSensorV3(I2C.Port.kOnboard);
  private final ColorMatch colorMatch = new ColorMatch();

  private Color rawColor = camera.getColor();
  private final Color RED_TARGET = new Color(1.0, 0.0, 0.0);
  private final Color BLUE_TARGET = new Color(0.0, 0.5, 1.0);

  private final double RED_CONFIDENCE = 0.4;
  private final double BLUE_CONFIDENCE = 0.5;

  public enum ColorMode {
    RED, BLUE, NONE
  }

  private ColorMode currentColor;

  /** Creates a new CameraSubsystem. */
  public ColorSensorSubsystem() {
    colorMatch.addColorMatch(RED_TARGET);
    colorMatch.addColorMatch(BLUE_TARGET);
  }

  @Override
  public void periodic() {
    rawColor = camera.getColor(); // Optional, for Shuffleboard later.
    currentColor = isRed() ? ColorMode.RED : (isBlue() ? ColorMode.BLUE : ColorMode.NONE);
  }

  /**
   * Get the color detected by the REV Color Sensor V3.
   * @return The color detected.
   */
  public Color getRawColor() {
    return rawColor;
  }

  /**
   * Get whether the detected color is red, blue, or none.
   * @return The closest color.
   */
  public ColorMode getColor() {
    return currentColor;
  }

  /**
   * Check if the detected color is within range of (1.0, 0.0, 0.0).
   * @return Whether the color is within range.
   */
  public boolean isRed() {
    colorMatch.setConfidenceThreshold(RED_CONFIDENCE);
    return colorMatch.matchColor(getRawColor()).color.equals(RED_TARGET);
  }

  /**
   * Check if the detected color is within range of (0.0, 0.5, 1.0).
   * @return Whether the color is within range.
   */
  public boolean isBlue() {
    colorMatch.setConfidenceThreshold(BLUE_CONFIDENCE);
    return colorMatch.matchColor(getRawColor()).color.equals(BLUE_TARGET);
  }
}
