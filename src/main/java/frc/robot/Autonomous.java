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


public class Autonomous {
    public static double speed = 0.5;

    // Initialize and Declare auto classes.
    private Command m_autoCommand0 = new AutoCommand0(speed);
    private Command m_autoCommand1 = new AutoCommand1(speed);
    private Command m_autoCommand2 = new AutoCommand2(speed);
    private Command m_autoCommand3 = new AutoCommand3(speed);//goal targeting
    private Command m_autoCommand4 = new AutoCommand4(speed);
    private Command m_autoCommand19 = new AutoCommand19(speed);

    SendableChooser<Command> chooser = new SendableChooser<Command>();

    // Really don't know if this is needed but it worked so yeah.
    public void scheduleAuto() {

        m_autoCommand0.schedule();
        m_autoCommand1.schedule();
        m_autoCommand2.schedule();
        m_autoCommand3.schedule();
        m_autoCommand4.schedule();
        m_autoCommand19.schedule();

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
        SmartDashboard.putData("AutoMode", chooser);

    }

    // On autonomous init start picked.
    public void choosePicked() {

        chooser.getSelected().schedule();

    }

}
