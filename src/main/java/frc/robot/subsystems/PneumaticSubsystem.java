/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PneumaticSubsystem extends SubsystemBase {
  /**
   * Creates a new PneumaticSubsystem.
   */

  private Solenoid leftSolenoid;
  private Solenoid rightSolenoid;
  private Compressor compressor;

  public PneumaticSubsystem() {
    leftSolenoid = new Solenoid(Constants.SOLENOIDL);
    rightSolenoid = new Solenoid(Constants.SOLENOIDR);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
