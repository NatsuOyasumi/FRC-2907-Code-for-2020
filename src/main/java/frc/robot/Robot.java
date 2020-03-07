
package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.AimCommand;
//import edu.wpi.first.wpilibj.TalonSRX;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.HopperCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.PneumaticCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.subsystems.AimSubsystem;
import frc.robot.subsystems.ArcadeDrive;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PneumaticSubsystem;
import frc.robot.subsystems.SRXMagEncoder_Relative;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.commands.autonomous.AutoCalibrate;

import java.lang.Object;

public class Robot extends TimedRobot {

	private WPI_TalonSRX climbMaster;//9

	static NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");
	public static NetworkTableEntry tx = limelight.getEntry("tx");//target x position in camera
	public static NetworkTableEntry ty = limelight.getEntry("ty");//target y position in camera
	public static NetworkTableEntry ta = limelight.getEntry("ta");

	private Command m_driveCommand = new DriveCommand();
	private Command m_intakeCommand = new IntakeCommand();
	private Command m_hopperCommand = new HopperCommand();
	private Command m_pneumaticCommand = new PneumaticCommand();
	private Command m_shooterCommand = new ShooterCommand();
	private Command m_aimCommand = new AimCommand();

	public static ArcadeDrive m_arcadeDrive = new ArcadeDrive();
	public static IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
	public static HopperSubsystem m_hopperSubsystem = new HopperSubsystem();
	public static ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
	public static PneumaticSubsystem m_pneumaticSubsystem = new PneumaticSubsystem();
	public static AimSubsystem m_aimSubsystem = new AimSubsystem();

	public static Autonomous m_autonomous = new Autonomous();
	public static RobotContainer m_robotContainer = new RobotContainer();
	public static AHRS gyro;

	public static int counter = 0;

	//for encoder stuff, dont touch pls
	//we also made the talon motors public static to make this works
  
  /*  //These are actually FX's not SRX's so we need a different encoder class
  public static SRXMagEncoder_Relative encoderLeft = new SRXMagEncoder_Relative(ArcadeDrive.leftMaster);
  public static SRXMagEncoder_Relative encoderRight = new SRXMagEncoder_Relative(ArcadeDrive.rightMaster);
  */

	public static SRXMagEncoder_Relative encoderHood = new SRXMagEncoder_Relative(m_shooterSubsystem.getShooterHood());

	@Override
	public void robotInit() {

		m_robotContainer = new RobotContainer();
		m_driveCommand.schedule();
		m_autonomous.scheduleAuto();
		m_autonomous.getAutonomous();
		try {
			gyro = new AHRS();
		} catch(RuntimeException ex ) {
			ex.printStackTrace();
		}

	}

	@Override
	public void robotPeriodic() {
		//System.out.println("robot periodic");
		//At the moment these don't exist...
		//SmartDashboard.putNumber("Encoder for RightMaster", encoderRight.getPosition());
		//SmartDashboard.putNumber("Encoder for LeftMaster ", encoderLeft.getPosition());
		SmartDashboard.putNumber("Encoder for ShooterHood", encoderHood.getPosition());
		SmartDashboard.updateValues();
		//if(counter % 50 == 0)
		//System.out.println("EncoderRight position: " + encoderRight.getPosition());
		//counter++;
		CommandScheduler.getInstance().run();
		SmartDashboard.putNumber("LimelightX", tx.getDouble(0.0));
		SmartDashboard.putNumber("LimelightY", ty.getDouble(0.0));
		m_autonomous.getAutonomous();
	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Autonomous.currentlyUsing = false;
	}

	@Override
	public void autonomousInit() {
		m_autonomous.choosePicked();
	}

	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void teleopInit() {
		Autonomous.currentlyUsing = false;
		m_driveCommand.schedule();
		m_intakeCommand.schedule();
		m_hopperCommand.schedule();
		m_pneumaticCommand.schedule();
		m_shooterCommand.schedule();
		m_aimCommand.schedule();
	}

	@Override
	public void teleopPeriodic() {
		SmartDashboard.putNumber("Shooter Encoder", Robot.m_shooterSubsystem.getSEncoderValue());
	}

	@Override
	public void testInit() {
		CommandScheduler.getInstance().cancelAll();
		AutoCalibrate.calibrate();
	}

	@Override
	public void testPeriodic() {
  }
  
  public static double Ramping(double currentValue, double rampMult, double targetValue) {
    //currentValue is the speed currently being used by the motor.
    //rampMult is the multiplier value for ramping - such as increasing by 10% each time
    //maxValue is the maxValue it can get up or down to - so whatever the joystick is at (usually)
    //speedingUp is whether it should be speeding up or slowing down
    boolean speedingUp = true;
    if(currentValue > targetValue) 
      speedingUp = false;

    double speed = currentValue;

    if(speedingUp) {
      //if speeding up, then increment speed by % until at or above targetVal, then set to targetValue
      speed += (targetValue*rampMult);
      if(speed > targetValue) {
        speed = targetValue;
      }
    }
    else {
      //if slowing down, then decrement speed by % until at or below targetValue, then set to targetValue
      //So if slowing down to halfway instead of 0, stop at speed instead of just going to 0.
      speed -= (targetValue*rampMult);
      if(speed < targetValue) {
        speed = targetValue;
      }
    }

    //if joystick is almost but not quite 0, so isn't actually being used
    if(targetValue < DriveCommand.stickDeadZoneThresh && targetValue > -(DriveCommand.stickDeadZoneThresh)) {
      speed = 0;
    }

    return speed;
  }//end ramping method
  
}