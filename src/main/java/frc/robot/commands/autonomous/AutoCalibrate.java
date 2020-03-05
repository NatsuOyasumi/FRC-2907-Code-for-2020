package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.ShooterSubsystem;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.subsystems.PneumaticSubsystem;

public class AutoCalibrate extends CommandBase{
    private boolean HoodDown = false;

    public static void callibrateHood() {

        /*int currentTicks = 0;
        int pastTicks = -1;

        Robot.m_shooterSubsystem.shooterHood.set(0.1); //negative = up, positive = down
        while (!HoodDown) {
            currentTicks = enumerator.getTicks(); //enumerator class doesn't exist yet
            if (Math.abs(pastTicks - currentTicks) < 10) {
                HoodDown = true;
            }                                                //un-comment when we can access the encoder
            pastTicks = currentTicks;
        }
        Robot.m_shooterSubsystem.shooterHood.set(0);
    */}
    public static void callibrateIntake() {
        Robot.m_pneumaticSubsystem.setRightLeft(false); //moves the intake up (false)
    }
    public static void calibrate(){
        callibrateHood();
        callibrateIntake();
    }

}


/*
 X   1. hood motor
 X   3. move intake up
 X   5. calibrateall 
*/