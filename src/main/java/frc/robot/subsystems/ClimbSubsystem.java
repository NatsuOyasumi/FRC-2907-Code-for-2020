/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class ClimbSubsystem extends SubsystemBase {
  /**
   * Creates a new ClimbSubsystem.
   */

  private WPI_TalonSRX climb;//9

  public ClimbSubsystem() {

      climb = new WPI_TalonSRX(Constants.CLIMB);

  }


  @Override
  public void periodic() {
  
  }


}

