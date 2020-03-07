/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class HopperSubsystem extends SubsystemBase {
  /**
   * Creates a new HopperSubsystem.
   */

  private WPI_TalonSRX hopperLeft;//11
  private WPI_TalonSRX hopperRight;//12
  private WPI_TalonSRX shooterTower;

  // private  topTowerSensor;
  // private  bottomTowerSensor;

  public HopperSubsystem() {

    hopperLeft = new WPI_TalonSRX(Constants.HOPPERL);
    hopperRight = new WPI_TalonSRX(Constants.HOPPERR);
    shooterTower = new WPI_TalonSRX(Constants.TOWER);

    // topTowerSensor = new (Constants.TOP_TOWER_SENSOR);
    // bottomTowerSensor = new (Constants.BOTTOM_TOWER_SENSOR);
    
  }

  @Override
  public void periodic() {
  }

  public void hopperManager(double speed) {

    hopperLeft.set(speed/1.5);
    hopperRight.set(speed);

  }

  public void towerManager(double speed) {
    shooterTower.set(-speed);
  }

}
