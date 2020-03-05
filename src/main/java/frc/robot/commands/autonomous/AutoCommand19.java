/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Autonomous;
import frc.robot.Robot;

public class AutoCommand19 extends CommandBase {

  double speed;

  public AutoCommand19(double s) {
    speed = s-0.1;
    addRequirements(Robot.m_arcadeDrive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void execute() {
    AutoCommand_MoveOffLine.inUse = false;
    
    //0.2 is too low, does nothing
    //0.3 is slow, but it turns the wheels
    //0.25 is too low, makes noise but wheels don't turn
    //0.27 is too low, makes noise but wheels don't turn
    //0.28 is too low, makes noise but wheels don't turn
    //0.29 is slow, makes noise but wheels turn
    //On a full battery, 0.26 is basically the absolute
      //lowest setting, but only one side turns
    //0.9 is totally fine. . .
    Robot.m_arcadeDrive.manualDrive(speed, 0);
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

  
}//end class