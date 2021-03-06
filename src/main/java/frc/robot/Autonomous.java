/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights  are totally not Reserved.lolol                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.autonomous.AutoCommand0;
import frc.robot.commands.autonomous.AutoCommand1;
import frc.robot.commands.autonomous.AutoCommand19;
import frc.robot.commands.autonomous.AutoCommand2;
import frc.robot.commands.autonomous.AutoCommand3;
import frc.robot.commands.autonomous.AutoCommand4;
import frc.robot.commands.autonomous.AutoCommand_AimTowardsGoal;
import frc.robot.commands.autonomous.AutoCommand_MoveOffLine;
import frc.robot.subsystems.ArcadeDrive;
//example change

public class Autonomous {
    public static double speed = 0.5;
    public static boolean currentlyUsing = false;

    // Initialize and Declare auto classes.
    private Command m_autoCommand0 = new AutoCommand0(speed);
    private Command m_autoCommand1 = new AutoCommand1(speed);
    private Command m_autoCommand2 = new AutoCommand2(speed);
    private Command m_autoCommand3 = new AutoCommand3(speed);//goal targeting
    private Command m_autoCommand4 = new AutoCommand4(speed);
    private Command m_autoCommand19 = new AutoCommand19(speed);

    private Command m_autoCommand_MoveOffLine = new AutoCommand_MoveOffLine(speed);
    private Command m_autoCommand_AimTowardsGoal = new AutoCommand_AimTowardsGoal(speed);

    SendableChooser<Command> chooser = new SendableChooser<Command>();

    // Really don't know if this is needed but it worked so yeah.
    public void scheduleAuto() {

        m_autoCommand0.schedule();
        m_autoCommand1.schedule();
        m_autoCommand2.schedule();
        m_autoCommand3.schedule();
        m_autoCommand4.schedule();
        m_autoCommand19.schedule();
        m_autoCommand_MoveOffLine.schedule();//does the order matter here..?
        m_autoCommand_AimTowardsGoal.schedule();

    }

    // Chooser in smart dashboard for autonomous.
    public void getAutonomous() {
        chooser.setDefaultOption("Defaut Auto", m_autoCommand3);
        chooser.addOption("Forward 0.5", m_autoCommand0);
        chooser.addOption("Backward 0.5", m_autoCommand1);
        chooser.addOption("Crazy", m_autoCommand2);
        chooser.addOption("TEST", m_autoCommand3);//goal targeting
        chooser.addOption("Spin", m_autoCommand4);
        chooser.addOption("Run slow", m_autoCommand19);
        chooser.addOption("Move Straight Off Line", m_autoCommand_MoveOffLine);
        chooser.addOption("Aim Towards Goal, Shoot", m_autoCommand_AimTowardsGoal);
        SmartDashboard.putData("AutoMode", chooser);
    }

    // On autonomous init start picked.
    public void choosePicked() {

        currentlyUsing = true;
        chooser.getSelected().schedule();

    }

}
