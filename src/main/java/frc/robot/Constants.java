/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static double GSPEED = 1; //global speed

    // public static final int MOTORLEFT0 = 0;
    // public static final int MOTORLEFT1 = 1;
    // public static final int MOTORRIGHT0 = 14;
    // public static final int MOTORRIGHT1 = 15;
    
    public static final int MOTORRIGHT0 = 1;//Master right
    public static final int MOTORRIGHT1 = 2;//Slave right
    public static final int MOTORRIGHT2 = 3;//Slave right

    public static final int MOTORLEFT0 = 4;//Master left
    public static final int MOTORLEFT1 = 5;//Slave left
    public static final int MOTORLEFT2 = 6;//Slave left

    // public static final int HOPPER1 = 0;//left hopper 
    // public static final int HOPPER2 = 0;//right hopper

    public static final int SHOOTER1 = 0;
    public static final int SHOOTER2 = 0;
    public static final int TOWER = 0;
    public static final int HOOD = 0;

    // public static final int INTAKE = 0;

    // public static final int CLIMB = 0;

    public static final int DRIVER_GAMEPAD = 0;
    public static final int MANIPJOI = 1;
}
