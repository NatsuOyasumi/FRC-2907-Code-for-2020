/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class AimSubsystem extends SubsystemBase {
  /**
   * Creates a new AimCommand.
   */

  final double kp = 0.02560;
  final double ki = 0.08;
  final double kd = 0.006;
  final double iLimit = 12;

  double setPoint = 0;
  double errorSum;
  double lastTimeStamp;
  double lastError;

  public AimSubsystem() {

  }

  //Targeting code
  private double targetGoalCalc() {

    double currentPos = Robot.tx.getDouble(0.0);
    double error = setPoint - currentPos;
    double dt = Timer.getFPGATimestamp() - lastTimeStamp;

    if (Math.abs(error) < iLimit) {
      errorSum += error * dt; 
    }

    double errorRate = error - lastError; //dt;
    double motorOutput = kp * error  + kd * errorRate + ki * errorSum; //turn power calculation

    //Targeting dead zone
    if (error < .15 && error > -.15) {
      motorOutput = 0;
    }

    return motorOutput;
  }

  @Override
  public void periodic() {
    targetGoalCalc();
  }
}
