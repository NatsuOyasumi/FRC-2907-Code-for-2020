/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.commands.DriveCommand;

public class AutoCommand0 extends CommandBase {
  /**
   * Creates a new AutoCommand0.
   */

  double speed;

//------------------------------------------------------------------------------------
  double move = 1;//max movement speed value
  double turn;//not set/changed anywhere

  double curSpeedT = 0;//turn
  final double speedInc = .2;//time to ramp
  final double speedMulti = (1/speedInc) / 50; //iteration step, 50 is cycles per second
  double curSpeedM = 0;
//-------------------------------------------------------------------------------------
  public AutoCommand0(double s) {
    speed = s;
    addRequirements(Robot.m_arcadeDrive);
  }

  // private double startTime;

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // startTime = Timer.getFPGATimestamp();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double time = Timer.getFPGATimestamp();
    time = 2;
    //-----------------------------------------------------------------------------
    if(time >= 0 && time <= 10){
      curSpeedM = moveCalculation(move, speedMulti, curSpeedM);
     // Robot.m_arcadeDrive.manualDrive(curSpeedM, curSpeedT);//MM used to be curSpeedT
     Robot.m_arcadeDrive.manualDrive(speed, 0);
     curSpeedT = turn;
    }
    //-------------------------------------------------------------------------------
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
//---------------------------------------------------------------------------------------------------------------------------
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
    if (controller < DriveCommand.stickDeadZoneThresh && controller > -DriveCommand.stickDeadZoneThresh) { finalSpeed = 0; }

    return finalSpeed;
  }

  //-----------------------------------------------------------------------------------------------------------------------------
}
