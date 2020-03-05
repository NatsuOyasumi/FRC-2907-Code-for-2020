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

public class AutoCommand_MoveOffLine extends CommandBase {
  /**
   * Creates a new AutoCommand_MoveOffLine.
   */

    double speed;//how fast it should move, passed in to constructor
    double startTimeStamp;//clock time when it is first used --> run for ~1 second
    final double runForNumofSecs = 1.5;//1 second is a long time...

  public AutoCommand_MoveOffLine(double s) {
    // Use addRequirements() here to declare subsystem dependencies.
    speed = s;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(startTimeStamp == 0) {
      startTimeStamp = Timer.getFPGATimestamp();
      //if first time, then set the start time
    }

    //if within one second (or so), move forward
    if(Timer.getFPGATimestamp() - startTimeStamp <= runForNumofSecs) {
      //Just move forward
      Robot.m_arcadeDrive.manualDrive(speed, 0);//doesn't need full power..?
      //Not going to put ramping in here, assuming it will be handled elsewhere
    }
    else {
      startTimeStamp = Timer.getFPGATimestamp();
    }
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
}
