package frc.robot.subsystems;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.DrivetrainCommand;

import static frc.robot.Constants.*;


public class Drivetrain extends SubsystemBase {

  //defines the motor that we're using for the subsystem
  public static CANSparkMax frontLeftMotor1;
  public static CANSparkMax backLeftMotor1;
  public static CANSparkMax frontRightMotor1;
  public static CANSparkMax backRightMotor1;

  public static MecanumDrive drivetrainMecanum;

  // Creates a new Subsystem.
  public Drivetrain() {
    frontLeftMotor1 = new CANSparkMax(frontLeft1ID, MotorType.kBrushless);
    backLeftMotor1 = new CANSparkMax(backLeft1ID, MotorType.kBrushless);
    frontRightMotor1 = new CANSparkMax(frontRight1ID, MotorType.kBrushless);
    backRightMotor1 = new CANSparkMax(backRight1ID, MotorType.kBrushless);

    drivetrainMecanum = new MecanumDrive(frontLeftMotor1, backLeftMotor1, frontRightMotor1, backRightMotor1);
    drivetrainMecanum.setSafetyEnabled(false);
    }


  @Override

  public void periodic() {
    // This method will be called once per scheduler run
     setDefaultCommand(new DrivetrainCommand(RobotContainer.m_drivetrain, RobotContainer.m_gyroAccel));
  }
}