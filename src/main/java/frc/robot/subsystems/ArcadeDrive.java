/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Autonomous;
import frc.robot.Constants;
import frc.robot.commands.DriveCommand;

public class ArcadeDrive extends SubsystemBase {

  //Creating Motors
  public static WPI_TalonSRX leftMaster;
  public static WPI_TalonSRX leftSlave1;
  public static WPI_TalonSRX leftSlave2;

  public static WPI_TalonSRX rightMaster;
  public static WPI_TalonSRX rightSlave1;
  public static WPI_TalonSRX rightSLave2;

  public DifferentialDrive drive;

  public void manualDrive(double move, double turn) {
    System.out.println("manualDrive move " + move + " and turn " + turn);
    drive.arcadeDrive(move * Constants.GSPEED, turn * Constants.GSPEED);
  }

  public ArcadeDrive() {

    //Motor Names
    leftMaster = new WPI_TalonSRX(Constants.MOTORLEFT0);
    leftSlave1 = new WPI_TalonSRX(Constants.MOTORLEFT1);
    leftSlave2 = new WPI_TalonSRX(Constants.MOTORLEFT2);

    rightMaster = new WPI_TalonSRX(Constants.MOTORRIGHT0);
    rightSlave1 = new WPI_TalonSRX(Constants.MOTORRIGHT1);
    rightSLave2 = new WPI_TalonSRX(Constants.MOTORRIGHT2);

    //Declaring Masters
    leftSlave1.follow(leftMaster);
    leftSlave2.follow(leftMaster);

    rightSlave1.follow(rightMaster);
    rightSLave2.follow(rightMaster);

    drive = new DifferentialDrive(leftMaster, rightMaster);
  }

  public void initDefaultCommand(){
    setDefaultCommand(new DriveCommand());
  }

  @Override
  public void periodic() { 
  }
}