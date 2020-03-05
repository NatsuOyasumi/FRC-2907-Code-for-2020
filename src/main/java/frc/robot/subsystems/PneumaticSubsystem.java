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

  private static Solenoid leftSolenoid_extend;
  private static Solenoid leftSolenoid_retract;
  private static Solenoid rightSolenoid_extend;
  private static Solenoid rightSolenoid_retract;
  private Compressor compressor;
  private boolean compressorIsRunning;
  

  public PneumaticSubsystem() {
    leftSolenoid_extend = new Solenoid(Constants.SOLENOIDL_EXTEND);
    leftSolenoid_retract = new Solenoid(Constants.SOLENOIDL_RETRACT);
    rightSolenoid_extend = new Solenoid(Constants.SOLENOIDR_EXTEND);
    rightSolenoid_retract = new Solenoid(Constants.SOLENOIDR_RETRACT);
    compressor = new Compressor(Constants.COMPRESSOR);

    /*
    leftSolenoid_retract.set(true);
    rightSolenoid_retract.set(true);
    leftSolenoid_extend.set(false);
    rightSolenoid_extend.set(false);
    */
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void pneumaticHandler(boolean position) {

    leftSolenoid_extend.set(position);
    rightSolenoid_extend.set(position);

    leftSolenoid_retract.set(!position);
    rightSolenoid_retract.set(!position);
    //System.out.println("Handler: " + position);

  }

  public void compressorHandler(boolean compOn) { //whether to turn compressor on or not
    
    if (compOn == true) {
      compressor.start();
      compressorIsRunning = true;
      if (compressor.getPressureSwitchValue() == false) { //true if low pressure, false if high pressure
        compressor.stop();
        compressorIsRunning = false;
      }
    } else {
      compressor.stop();
      compressorIsRunning = false;
    }
    //Need to update sensors
    //System.out.println("compressor is active: " + compressorIsRunning);
    System.out.println("compressor pressure is low: " + compressor.getPressureSwitchValue());

  }

  public boolean getCompressorPowerOn() {
    return compressorIsRunning;
  }



}
