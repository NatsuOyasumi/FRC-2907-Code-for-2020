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

public class AutoCommand1 extends CommandBase {
  /**
   * Creates a new AutoCommand1.
   */
  
  double speed;

  public AutoCommand1(double s) {
    speed = s;
    addRequirements(Robot.m_arcadeDrive);
  }

  private double startTime;

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startTime = Timer.getFPGATimestamp();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    AutoCommand_MoveOffLine.inUse = false;
    double time = Timer.getFPGATimestamp();

    if (time - startTime <= 1) {
      moveEasy(-speed, 0);
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

  public void moveEasy(double move, double turn) {
    Robot.m_arcadeDrive.manualDrive(-move, turn);
  }
}
