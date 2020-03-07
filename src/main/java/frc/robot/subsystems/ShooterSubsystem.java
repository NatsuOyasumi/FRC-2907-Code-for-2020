/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class ShooterSubsystem extends SubsystemBase {
	/**
	 * Creates a new AimSubsystem.
	 */

	public WPI_TalonSRX shooterWheel0;
	public WPI_TalonSRX shooterWheel1;
	public WPI_TalonSRX shooterHood;

	private final int TICKS_PER_REVOLUTION = 4096;
	private final double GEAR_RATIO = 338d/36d;
	private final double DEGREES_PER_REV = 360d/GEAR_RATIO;

	double shooterSpeed = 0;
	
	final double portHeight = 6;// height of goal(the center of the reflect tape area NOT the goal itself) in compaison to camera in feet
	double portDistance;//distance horizantal to middle of goal

	public ShooterSubsystem() {

		shooterWheel0 = new WPI_TalonSRX(Constants.SHOOTERL);
		shooterWheel1 = new WPI_TalonSRX(Constants.SHOOTERR);
		shooterHood = new WPI_TalonSRX(Constants.HOOD);

		shooterHood.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);

	}

	@Override
	public void periodic() {
	}

	public int getSEncoderValue() {
		return shooterHood.getSelectedSensorPosition();
	}

	public void hoodHandler(boolean direction) {

		if (direction == true) {
			shooterHood.set(0.35);
		} else {
			shooterHood.set(-0.35);
		}

	}

	public void shooterHandler(double maxSpeed) {

		shooterSpeed = shooterRamping(shooterSpeed, maxSpeed);

		shooterWheel0.set(shooterSpeed);
		shooterWheel1.set(-shooterSpeed);

	}

	public double getHoodAngle(){
		double revolutions = shooterHood.getSelectedSensorPosition() * DEGREES_PER_REV;
		return revolutions;
	}

	public TalonSRX getShooterHood() {
		return shooterHood;
	}

	private double shooterRamping(double curSpeed, double maxSpeed) {

		final double speedInc = 1;
		final double speedMulti = (1/speedInc) / 50;
		double speed = curSpeed + speedMulti;
		speed = Math.max(0, Math.min(1, speed));

		return speed;

	}

}

