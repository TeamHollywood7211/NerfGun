// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
//=============================Drivetrain Constants=======================================//   
    public static int frontLeft1ID = 10;
    public static int frontRight1ID = 20;
    public static int backLeft1ID = 15;
    public static int backRight1ID = 25;
    public static double powerRegulator = .95;
    public static double deadzone = .13; //originally .20
//=============================Intake Constants==========================================//
    public static int intakeMotorID = 30;
    public static double intakeMotorPower = 0.40;
//=============================Conveyor Constants========================================//
    public static int conveyorMotorID = 35;
    public static double conveyorMotorPower = 0.70;
//=============================Shooter Constants=========================================//
    public static int shooterLeftMotorID = 40;
    public static int shooterRightMotorID = 41;
    public static double shooterMotorPower = 0.25;
    public static int targetShooterVelocity = 2500;
    public static double shooterMotorPowerSlow = .13;
    public static int targetShooterVelocitySlow = 500;
//=============================BreakBeam Constants=======================================//
    public static int TopBeamID = 2; 
    public static int MiddleBeamID = 1; 
    public static int HopperBeamID = 0;
//=============================Climber Constants=========================================//
    public static int ClimberL1ID = 50;
    //public static int ClimberL2ID = 52;
    public static int ClimberR1ID = 51;
    //public static int ClimberR2ID = 53;
    public static double climberSpeedStage1 = 1;
    public static double climberDesiredPosition = 200;
}
