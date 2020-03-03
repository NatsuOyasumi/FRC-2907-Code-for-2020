/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class DriveCommand extends CommandBase {

  double move = Robot.m_robotContainer.driverGamepad.getRawAxis(1);
  double turn = Robot.m_robotContainer.driverGamepad.getRawAxis(2);

  double curSpeedT = 0;//turn
  final double speedInc = .2;//time to ramp
  final double speedMulti = (1/speedInc) / 50; //iteration step, 50 is cycles per second
  double curSpeedM = 0;
  

  final double kp = 0.02560;
  final double ki = 0.08;
  final double kd = 0.006;
  final double iLimit = 12;//maximum error the targeting code is allowed to target

  // 'William'
  final double portHeight = 6;// height of goal(the center of the reflect tape area NOT the goal itself) in compaison to camera in feet
  double portDistance;//distance horizantal to middle of goal

  double setPoint = 0;
  double errorSum;
  double lastTimeStamp;
  double lastError;

  final double stickDeadZoneThresh = .01;//the dead zone for the controller stick
  final double targingDeadZoneThresh = .15;//the dead zone threash hold to end the PID targeting loop
  final double magicMotionDeadZoneThresh = 1;// the dead zone for when the magic motion code is allowed to take over and adjust unintentional turning

  // get relative x position
  double currentPos = Robot.tx.getDouble(0.0);

  // get relative y position 'William'
  double currentPosY = Robot.ty.getDouble(0.0);
  
  // calculations
  double error = setPoint - currentPos;
  double dt = Timer.getFPGATimestamp() - lastTimeStamp;
  

  public DriveCommand() {
    addRequirements(Robot.m_arcadeDrive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //No matter what, driver controls speed.
    //But if holding x, robot controls turn
    move = Robot.m_robotContainer.driverGamepad.getRawAxis(1) * -1;//assuming -1 is backwards and +1 is forwards, it's inverted.
    
    //if X (theoretically) is held it should take away driver control and point the robot at the goal
    if(Robot.m_robotContainer.driverGamepad.getRawButton(2)) {
      //goal targeting
      curSpeedM = moveCalculation(move, speedMulti, curSpeedM);
      Robot.m_arcadeDrive.manualDrive(curSpeedM, targetGoalCalc());
    } else {
      turn = Robot.m_robotContainer.driverGamepad.getRawAxis(2); //* -1;//is also inverted

      //normal movement
      curSpeedM = moveCalculation(move, speedMulti, curSpeedM);
      Robot.m_arcadeDrive.manualDrive(curSpeedM, magicMotion());//MM used to be curSpeedT
      //curSpeedT = moveCalculation(turn, speedMulti, curSpeedT);
      curSpeedT = turn;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  private double moveCalculation(final double controller, final double multiplication, final double currentValue) {

    double finalSpeed = currentValue;

    //final speed iteration
    finalSpeed += controller * multiplication;

    //final speed limiting for ramp
    if (finalSpeed > controller && controller > 0) { 
      finalSpeed = controller; 
    } else if(finalSpeed < controller && controller < 0) {
      finalSpeed = controller;
    }

    //controller dead zone
    if (controller < stickDeadZoneThresh && controller > -stickDeadZoneThresh) { finalSpeed = 0; }

    return finalSpeed;
  }

  //Targeting code
  private double targetGoalCalc() {

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

  private double magicMotion() {
    double motorOutput = 0.0;

    if(curSpeedT != 0) {
        //if driver controlling, reset gryo/forwards
        //currentZero = Robot.gyro.getYaw();
        Robot.gyro.reset();
        motorOutput = curSpeedT;
    } else {
      if(Math.abs(Robot.gyro.getYaw()) > magicMotionDeadZoneThresh) {
        //motorOutput is proportional to how far off the yaw
        //is. If it turns out that it is too strong or not
        //strong enough, change the 180.
        //Yaw should never be greater than the denominator though
        //because motorOutput is -1 to 1, so it would set to max.
        //hi - Charles
       motorOutput = (-1) * (Robot.gyro.getYaw() / 180);;;;
      }
    }
    return motorOutput;
  }//end magicMotion

  private void hopperManager() {
    //Robot.getHopperLeft()
  }
}