package frc.robot.subsystems;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.DrivetrainCommand;

import static frc.robot.Constants.*;

public class Drivetrain extends SubsystemBase {
  private double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM, maxVel, minVel, maxAccel, allowedErr;

  //defines the motor that we're using for the subsystem
  public static CANSparkMax frontLeftMotor1;
  public static CANSparkMax backLeftMotor1;
  public static CANSparkMax frontRightMotor1;
  public static CANSparkMax backRightMotor1; 

  public static SparkMaxPIDController frontLeftMotorPID;
  public static SparkMaxPIDController backLeftMotorPID;
  public static SparkMaxPIDController frontRightMotorPID;
  public static SparkMaxPIDController backRightMotorPID;

  public static RelativeEncoder frontLeftMotorEncoder;
  public static RelativeEncoder backLeftMotorEncoder;
  public static RelativeEncoder frontRightMotorEncoder;
  public static RelativeEncoder backRightMotorEncoder;


  public static MecanumDrive drivetrainMecanum;

  // Creates a new Subsystem.
  public Drivetrain() {
    
    frontLeftMotor1 = new CANSparkMax(frontLeft1ID, MotorType.kBrushless);
    backLeftMotor1 = new CANSparkMax(backLeft1ID, MotorType.kBrushless);
    frontRightMotor1 = new CANSparkMax(frontRight1ID, MotorType.kBrushless);
    backRightMotor1 = new CANSparkMax(backRight1ID, MotorType.kBrushless);

    frontLeftMotorPID = frontLeftMotor1.getPIDController();
    backLeftMotorPID = backLeftMotor1.getPIDController();
    frontRightMotorPID = frontRightMotor1.getPIDController();
    backRightMotorPID = backRightMotor1.getPIDController();

    frontLeftMotorEncoder = frontLeftMotor1.getEncoder();
    backLeftMotorEncoder = backLeftMotor1.getEncoder();
    frontRightMotorEncoder = frontRightMotor1.getEncoder();
    backRightMotorEncoder = backRightMotor1.getEncoder();

    drivetrainMecanum = new MecanumDrive(frontLeftMotor1, backLeftMotor1, frontRightMotor1, backRightMotor1);
    drivetrainMecanum.setSafetyEnabled(false);

    
    // PID coefficients
    kP = 0.002; 
    kI = 0;
    kD = 0; 
    kIz = 0; 
    kFF = 0.000156; 
    kMaxOutput = 1; 
    kMinOutput = -1;
    maxRPM = 3000;

    //// Smart Motion Coefficients
    maxVel = 1000; // rpm
    maxAccel = 500;

    Drivetrain.frontLeftMotorPID.setP(kP);
    Drivetrain.backLeftMotorPID.setP(kP);
    Drivetrain.frontRightMotorPID.setP(kP);
    Drivetrain.backRightMotorPID.setP(kP);

    Drivetrain.frontLeftMotorPID.setI(kI);
    Drivetrain.backLeftMotorPID.setI(kI);
    Drivetrain.frontRightMotorPID.setI(kI);
    Drivetrain.backRightMotorPID.setI(kI);

    Drivetrain.frontLeftMotorPID.setD(kD);
    Drivetrain.backLeftMotorPID.setD(kD);
    Drivetrain.frontRightMotorPID.setD(kD);
    Drivetrain.backRightMotorPID.setD(kD);

    Drivetrain.frontLeftMotorPID.setIZone(kIz);
    Drivetrain.backLeftMotorPID.setIZone(kIz);
    Drivetrain.frontRightMotorPID.setIZone(kIz);
    Drivetrain.backRightMotorPID.setIZone(kIz);

    Drivetrain.frontLeftMotorPID.setFF(kFF);
    Drivetrain.backLeftMotorPID.setFF(kFF);
    Drivetrain.frontRightMotorPID.setFF(kFF);
    Drivetrain.backRightMotorPID.setFF(kFF);

    Drivetrain.frontLeftMotorPID.setOutputRange(kMinOutput, kMaxOutput);
    Drivetrain.backLeftMotorPID.setOutputRange(kMinOutput, kMaxOutput);
    Drivetrain.frontRightMotorPID.setOutputRange(kMinOutput, kMaxOutput);
    Drivetrain.backRightMotorPID.setOutputRange(kMinOutput, kMaxOutput);

    int slotID = 0;
    Drivetrain.frontLeftMotorPID.setSmartMotionMaxVelocity(maxVel, slotID);
    Drivetrain.backLeftMotorPID.setSmartMotionMaxVelocity(maxVel, slotID);
    Drivetrain.frontRightMotorPID.setSmartMotionMaxVelocity(maxVel, slotID);
    Drivetrain.backRightMotorPID.setSmartMotionMaxVelocity(maxVel, slotID);

    Drivetrain.frontLeftMotorPID.setSmartMotionMinOutputVelocity(minVel, slotID);
    Drivetrain.backLeftMotorPID.setSmartMotionMinOutputVelocity(minVel, slotID);
    Drivetrain.frontRightMotorPID.setSmartMotionMinOutputVelocity(minVel, slotID);
    Drivetrain.backRightMotorPID.setSmartMotionMinOutputVelocity(minVel, slotID);

    Drivetrain.frontLeftMotorPID.setSmartMotionMaxAccel(maxAccel, slotID);
    Drivetrain.backLeftMotorPID.setSmartMotionMaxAccel(maxAccel, slotID);
    Drivetrain.frontRightMotorPID.setSmartMotionMaxAccel(maxAccel, slotID);
    Drivetrain.backRightMotorPID.setSmartMotionMaxAccel(maxAccel, slotID);

    Drivetrain.frontLeftMotorPID.setSmartMotionAllowedClosedLoopError(allowedErr, slotID);
    Drivetrain.backLeftMotorPID.setSmartMotionAllowedClosedLoopError(allowedErr, slotID);
    Drivetrain.frontRightMotorPID.setSmartMotionAllowedClosedLoopError(allowedErr, slotID);
    Drivetrain.backRightMotorPID.setSmartMotionAllowedClosedLoopError(allowedErr, slotID);

    // display PID coefficients on SmartDashboard
    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("I Zone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", kMaxOutput);
    SmartDashboard.putNumber("Min Output", kMinOutput);

    // display Smart Motion coefficients
    SmartDashboard.putNumber("Max Velocity", maxVel);
    SmartDashboard.putNumber("Min Velocity", minVel);
    SmartDashboard.putNumber("Max Acceleration", maxAccel);
    SmartDashboard.putNumber("Allowed Closed Loop Error", allowedErr);
    SmartDashboard.putNumber("Set Position", 0);
    SmartDashboard.putNumber("Set Velocity", 0);
    }


  @Override

  public void periodic() {
    double setPoint = SmartDashboard.getNumber("Set Position", 0);
    // This method will be called once per scheduler run
    setDefaultCommand(new DrivetrainCommand(RobotContainer.m_drivetrain, RobotContainer.m_gyroAccel));
        // read PID coefficients from SmartDashboard
        double p = SmartDashboard.getNumber("P Gain", 0);
        double i = SmartDashboard.getNumber("I Gain", 0);
        double d = SmartDashboard.getNumber("D Gain", 0);
        double iz = SmartDashboard.getNumber("I Zone", 0);
        double ff = SmartDashboard.getNumber("Feed Forward", 0);
        double max = SmartDashboard.getNumber("Max Output", 0);
        double min = SmartDashboard.getNumber("Min Output", 0);
        double maxV = SmartDashboard.getNumber("Max Velocity", 0);
        double minV = SmartDashboard.getNumber("Min Velocity", 0);
        double maxA = SmartDashboard.getNumber("Max Acceleration", 0);
        double allE = SmartDashboard.getNumber("Allowed Closed Loop Error", 0);
    
        // if PID coefficients on SmartDashboard have changed, write new values to controller
        if((p != kP)) { 
            Drivetrain.frontLeftMotorPID.setP(p); 
            Drivetrain.backLeftMotorPID.setP(p); 
            Drivetrain.frontRightMotorPID.setP(p);
            Drivetrain.backRightMotorPID.setP(p); 
            kP = p; }
        if((i != kI)) { 
            Drivetrain.frontLeftMotorPID.setI(i);
            Drivetrain.backLeftMotorPID.setI(i);
            Drivetrain.frontRightMotorPID.setI(i);
            Drivetrain.backRightMotorPID.setI(i);
            kI = i;
        }
        if((d != kD)) {
            Drivetrain.frontLeftMotorPID.setD(d);
            Drivetrain.backLeftMotorPID.setD(d);
            Drivetrain.frontRightMotorPID.setD(d);
            Drivetrain.backRightMotorPID.setD(d);
            kD = d; 
        }
        if((iz != kIz)) {
            Drivetrain.frontLeftMotorPID.setIZone(iz);
            Drivetrain.backLeftMotorPID.setIZone(iz);
            Drivetrain.frontRightMotorPID.setIZone(iz);
            Drivetrain.backRightMotorPID.setIZone(iz);
            kIz = iz;
            }
        if((ff != kFF)) {
            Drivetrain.frontLeftMotorPID.setFF(ff);
            Drivetrain.backLeftMotorPID.setFF(ff);
            Drivetrain.frontRightMotorPID.setFF(ff);
            Drivetrain.backRightMotorPID.setFF(ff);
            kFF = ff; 
            }
        if((max != kMaxOutput) || (min != kMinOutput)) { 
          Drivetrain.frontLeftMotorPID.setOutputRange(min, max);
          Drivetrain.backLeftMotorPID.setOutputRange(min, max);
          Drivetrain.frontRightMotorPID.setOutputRange(min, max);
          Drivetrain.backRightMotorPID.setOutputRange(min, max);
          kMinOutput = min; kMaxOutput = max; 
        }
        if((maxV != maxVel)) { 
          Drivetrain.frontLeftMotorPID.setSmartMotionMaxVelocity(maxV,0);
          Drivetrain.backLeftMotorPID.setSmartMotionMaxVelocity(maxV,0);
          Drivetrain.frontRightMotorPID.setSmartMotionMaxVelocity(maxV,0);
          Drivetrain.backRightMotorPID.setSmartMotionMaxVelocity(maxV,0);
          maxVel = maxV; 
        }
        if((minV != minVel)) {
          Drivetrain.frontLeftMotorPID.setSmartMotionMinOutputVelocity(minV,0);
          Drivetrain.backLeftMotorPID.setSmartMotionMinOutputVelocity(minV,0);
          Drivetrain.frontRightMotorPID.setSmartMotionMinOutputVelocity(minV,0);
          Drivetrain.backRightMotorPID.setSmartMotionMinOutputVelocity(minV,0);
          minVel = minV; 
        }
        if((maxA != maxAccel)) { 
          Drivetrain.frontLeftMotorPID.setSmartMotionMaxAccel(maxA,0);
          Drivetrain.backLeftMotorPID.setSmartMotionMaxAccel(maxA,0);
          Drivetrain.frontRightMotorPID.setSmartMotionMaxAccel(maxA,0);
          Drivetrain.backRightMotorPID.setSmartMotionMaxAccel(maxA,0);
          maxAccel = maxA; 
        }
        if((allE != allowedErr)) { 
          Drivetrain.frontLeftMotorPID.setSmartMotionAllowedClosedLoopError(allE,0);
          Drivetrain.backLeftMotorPID.setSmartMotionAllowedClosedLoopError(allE,0);
          Drivetrain.frontRightMotorPID.setSmartMotionAllowedClosedLoopError(allE,0);
          Drivetrain.backRightMotorPID.setSmartMotionAllowedClosedLoopError(allE,0);
          allowedErr = allE; 
        }
      }

  public static void setPosition(double position){
    frontLeftMotorPID.setReference(position, ControlType.kPosition);
    backLeftMotorPID.setReference(position, ControlType.kPosition);
    frontRightMotorPID.setReference(position, ControlType.kPosition);
    backRightMotorPID.setReference(position, ControlType.kPosition);
  }

}