/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class AutoCommand3 extends CommandBase {

  double speed;

  final double kp = 0.02560;
  final double ki = 0.08;
  final double kd = 0.006;
  final double iLimit = 12;

  // 'William'
  double portHeight = 6;
  double portDistance;

  double setPoint = 0;
  double errorSum;
  double lastTimeStamp;
  double lastError;

  public AutoCommand3(double s) {
    speed = s;
    addRequirements(Robot.m_arcadeDrive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    errorSum = 0;
    lastTimeStamp =  Timer.getFPGATimestamp();
    lastError = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    // get relative x position
    double currentPos = Robot.tx.getDouble(0.0);

    // get relative y position 'William'
    double currentPosY = Robot.ty.getDouble(0.0);
    
    // calculations
    double error = setPoint - currentPos;
    double dt = Timer.getFPGATimestamp() - lastTimeStamp;

    //Shooter calculations 'William'
    portDistance = portHeight/(Math.tan((currentPosY + 28)*Math.PI/180));
    double shootSpeed = Math.sqrt((2 * 32.1522 * portHeight) + Math.pow(32.1522 * portDistance/Math.sqrt(2 * 32.1522 * portHeight), 2));
    double shootAngle = Math.toDegrees(Math.asin(Math.sqrt(2 * 32.1522 * portHeight)/shootSpeed));
    SmartDashboard.putNumber("Motot Output", portDistance);

    if (Math.abs(error) < iLimit) {
      errorSum += error * dt; 
    }

    double errorRate = error - lastError; // dt;
    double motorOutput = kp * error  + kd * errorRate + ki * errorSum;

    if (error < .15 && error > -.15) {
      motorOutput = 0;
    }

    // move motors
    moveEasy(0, -motorOutput);

    // update variables
    lastTimeStamp = Timer.getFPGATimestamp();
    lastError = error;

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public void moveEasy(double move, double turn) {
    Robot.m_arcadeDrive.manualDrive(-move, turn);
  }
}
