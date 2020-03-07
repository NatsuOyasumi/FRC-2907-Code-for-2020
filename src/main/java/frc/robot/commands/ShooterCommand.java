/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ShooterCommand extends CommandBase {
  /**
   * Creates a new ShooterCommand.
   */
  public ShooterCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (Robot.m_robotContainer.manipGamepad.getRawButton(3) == true) {
      Robot.m_shooterSubsystem.shooterHandler(1);
    } else {
      Robot.m_shooterSubsystem.shooterWheel0.set(0);
      Robot.m_shooterSubsystem.shooterWheel1.set(0);
    }

    //Robot.m_shooterSubsystem.shooterHood.set(.5);
    if (Robot.m_robotContainer.manipGamepad.getRawAxis(1) < -.8) {
      Robot.m_shooterSubsystem.hoodHandler(true);
    } else if (Robot.m_robotContainer.manipGamepad.getRawAxis(1) > .8) {
      Robot.m_shooterSubsystem.hoodHandler(false);
    } else {
      Robot.m_shooterSubsystem.shooterHood.set(0);
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
