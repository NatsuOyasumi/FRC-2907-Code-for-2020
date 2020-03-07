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

public class AutoCommand_MoveOffLine extends CommandBase {
  /**
   * Creates a new AutoCommand_MoveOffLine.
   */

    double speed;//how fast it should move, passed in to constructor
    double startTimeStamp;//clock time when it is first used --> run for ~1 second
    final double runForNumofSecs = 2.5;//1 second is a long time, but it's going slow
    public static boolean inUse = false;//so other autocommands can access - for checking purposes sort of

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
    //***Can't send in boolean, messes up override, so global variable in Autonomous.
    //Want to confirm it stopped and then is being told to start again. So sending in
    //boolean to say whether in use, and it shouldn't reactivate until it's stopped and restarted.
    if(Autonomous.currentlyUsing == false) {
      inUse = false;
      //if in Teleop, inUse is set to false. During disabled as well, I think.
      //if other AutoCommands are called, they set inUse to false as well.
    }
                              //otherwise will set the first time called even if not in use...
    if(startTimeStamp == 0 && Autonomous.currentlyUsing == true) { 
      startTimeStamp = Timer.getFPGATimestamp();
      //if first time, then set the start time
    }

    //if within one second (or so), move forward
    if(Timer.getFPGATimestamp() - startTimeStamp <= runForNumofSecs) {
      //Just move forward
      Robot.m_arcadeDrive.manualDrive(speed, 0);//doesn't need full power..?
      //Not going to put ramping in here, assuming it will be handled elsewhere

      inUse = true;
    }
    else {
      if(Autonomous.currentlyUsing && !inUse) {
        startTimeStamp = 0;//will auto-set next time is called
      }
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
