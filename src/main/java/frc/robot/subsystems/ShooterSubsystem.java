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

public class ShooterSubsystem extends SubsystemBase {
  /**
   * Creates a new AimSubsystem.
   */

  private WPI_TalonSRX shooterWheel0;
  private WPI_TalonSRX shooterWheel1;
  private WPI_TalonSRX shooterTower;
  private WPI_TalonSRX shooterHood;

  public ShooterSubsystem() {

      shooterWheel0 = new WPI_TalonSRX(Constants.SHOOTER1);
      shooterWheel1 = new WPI_TalonSRX(Constants.SHOOTER2);
      shooterTower = new WPI_TalonSRX(Constants.TOWER);
      shooterHood = new WPI_TalonSRX(Constants.HOOD);

  }

  final double portHeight = 6;// height of goal(the center of the reflect tape area NOT the goal itself) in compaison to camera in feet
  double portDistance;//distance horizantal to middle of goal

  @Override
  public void periodic() {
  
    

  }

  private double hoodCalc() {
    double currentPosY = Robot.ty.getDouble(0.0);
    //Shooter calculations 'William'
    portDistance = portHeight/(Math.tan((currentPosY + 28)*Math.PI/180));
    double shootSpeed = Math.sqrt((2 * 32.1522 * portHeight) + Math.pow(32.1522 * portDistance/Math.sqrt(2 * 32.1522 * portHeight), 2));
    double shootAngle = Math.toDegrees(Math.asin(Math.sqrt(2 * 32.1522 * portHeight)/shootSpeed));
    //SmartDashboard.putNumber("Motot Output", portDistance);

    return shootAngle;
  }
}
}
