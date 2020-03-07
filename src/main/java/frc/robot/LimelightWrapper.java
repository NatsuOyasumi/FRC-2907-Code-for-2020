package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimelightWrapper {

	static NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");
	public static NetworkTableEntry tx = limelight.getEntry("tx");//target x position in camera
	public static NetworkTableEntry ty = limelight.getEntry("ty");//target y position in camera
	public static NetworkTableEntry ta = limelight.getEntry("ta");

	// if true is passed in it will turn on leds
	public void setLED(boolean on) {

		// 0 is on 1 is off
		if (on) {
			limelight.getEntry("ledMode").setNumber(0);
		} else {
			limelight.getEntry("ledMode").setNumber(1);
		}

	}

}
