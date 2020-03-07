package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class LimelightCommand extends CommandBase {
	private boolean loopRan = false;


	@Override
	public void initialize() {
		loopRan = false;
	}

	@Override
	public boolean isFinished() {
		if(!loopRan){
			Robot.m_limelightWrapper.setLED(loopRan);
			loopRan = true;
			return false;
		} else {
			Robot.m_limelightWrapper.setLED(loopRan);
			return true;
		}
	}

	@Override
	public void end(boolean interrupted) {

	}
}
