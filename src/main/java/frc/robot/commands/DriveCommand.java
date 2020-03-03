/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class DriveCommand extends CommandBase {

  double move = Robot.m_robotContainer.driverGamepad.getRawAxis(1);
  double turn = Robot.m_robotContainer.driverGamepad.getRawAxis(2);

  double curSpeedT = 0;
  final double speedInc = .2;
  final double speedMulti = (1/speedInc) / 50; 
  double curSpeedM = 0;

  public static final double stickDeadZoneThresh = .01;
  public static final double targingDeadZoneThresh = .15;
  // public static final double magicMotionDeadZoneThresh = 1;

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

    move = Robot.m_robotContainer.driverGamepad.getRawAxis(1);
    turn = Robot.m_robotContainer.driverGamepad.getRawAxis(2);

    curSpeedM = moveCalculation(move, speedMulti, curSpeedM);
    curSpeedT = turn;
    Robot.m_arcadeDrive.manualDrive(curSpeedM, curSpeedT);
    
    //curSpeedT = moveCalculation(turn, speedMulti, curSpeedT);

  }

  // private double magicMotion() {
  //   double motorOutput = 0.0;

  //   if(curSpeedT != 0) {
  //       //if driver controlling, reset gryo/forwards
  //       //currentZero = Robot.gyro.getYaw();
  //       Robot.gyro.reset();
  //       motorOutput = curSpeedT;
  //   } else {
  //     if(Math.abs(Robot.gyro.getYaw()) > magicMotionDeadZoneThresh) {
  //       //motorOutput is proportional to how far off the yaw
  //       //is. If it turns out that it is too strong or not
  //       //strong enough, change the 180.
  //       //Yaw should never be greater than the denominator though
  //       //because motorOutput is -1 to 1, so it would set to max.
  //      motorOutput = (-1) * (Robot.gyro.getYaw() / 180);;;;
  //     }
  //   }
  //   return motorOutput;
  // }

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

  

  

}

  