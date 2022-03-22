package frc.robot.subsystems;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class Drivetrain extends SubsystemBase {
  private double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM, maxVel, minVel, maxAccel, allowedErr;

  //defines the motor that we're using for the subsystem
  public CANSparkMax frontLeftMotor1;
  public CANSparkMax backLeftMotor1;
  public CANSparkMax frontRightMotor1;
  public CANSparkMax backRightMotor1; 

  public SparkMaxPIDController frontLeftMotorPID;
  public SparkMaxPIDController backLeftMotorPID;
  public SparkMaxPIDController frontRightMotorPID;
  public SparkMaxPIDController backRightMotorPID;

  public RelativeEncoder frontLeftMotorEncoder;
  public RelativeEncoder backLeftMotorEncoder;
  public RelativeEncoder frontRightMotorEncoder;
  public RelativeEncoder backRightMotorEncoder;


  public MecanumDrive drivetrainMecanum;

  public double setPoint;

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

    frontLeftMotorPID.setP(kP);
    backLeftMotorPID.setP(kP);
    frontRightMotorPID.setP(kP);
    backRightMotorPID.setP(kP);

    frontLeftMotorPID.setI(kI);
    backLeftMotorPID.setI(kI);
    frontRightMotorPID.setI(kI);
    backRightMotorPID.setI(kI);

    frontLeftMotorPID.setD(kD);
    backLeftMotorPID.setD(kD);
    frontRightMotorPID.setD(kD);
    backRightMotorPID.setD(kD);

    frontLeftMotorPID.setIZone(kIz);
    backLeftMotorPID.setIZone(kIz);
    frontRightMotorPID.setIZone(kIz);
    backRightMotorPID.setIZone(kIz);

    frontLeftMotorPID.setFF(kFF);
    backLeftMotorPID.setFF(kFF);
    frontRightMotorPID.setFF(kFF);
    backRightMotorPID.setFF(kFF);

    frontLeftMotorPID.setOutputRange(kMinOutput, kMaxOutput);
    backLeftMotorPID.setOutputRange(kMinOutput, kMaxOutput);
    frontRightMotorPID.setOutputRange(kMinOutput, kMaxOutput);
    backRightMotorPID.setOutputRange(kMinOutput, kMaxOutput);

    int slotID = 0;
    frontLeftMotorPID.setSmartMotionMaxVelocity(maxVel, slotID);
    backLeftMotorPID.setSmartMotionMaxVelocity(maxVel, slotID);
    frontRightMotorPID.setSmartMotionMaxVelocity(maxVel, slotID);
    backRightMotorPID.setSmartMotionMaxVelocity(maxVel, slotID);

    frontLeftMotorPID.setSmartMotionMinOutputVelocity(minVel, slotID);
    backLeftMotorPID.setSmartMotionMinOutputVelocity(minVel, slotID);
    frontRightMotorPID.setSmartMotionMinOutputVelocity(minVel, slotID);
    backRightMotorPID.setSmartMotionMinOutputVelocity(minVel, slotID);

    frontLeftMotorPID.setSmartMotionMaxAccel(maxAccel, slotID);
    backLeftMotorPID.setSmartMotionMaxAccel(maxAccel, slotID);
    frontRightMotorPID.setSmartMotionMaxAccel(maxAccel, slotID);
    backRightMotorPID.setSmartMotionMaxAccel(maxAccel, slotID);

    frontLeftMotorPID.setSmartMotionAllowedClosedLoopError(allowedErr, slotID);
    backLeftMotorPID.setSmartMotionAllowedClosedLoopError(allowedErr, slotID);
    frontRightMotorPID.setSmartMotionAllowedClosedLoopError(allowedErr, slotID);
    backRightMotorPID.setSmartMotionAllowedClosedLoopError(allowedErr, slotID);

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
    setPoint =  SmartDashboard.getNumber("Set Position", 0);
    // This method will be called once per scheduler run
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
            frontLeftMotorPID.setP(p); 
            backLeftMotorPID.setP(p); 
            frontRightMotorPID.setP(p);
            backRightMotorPID.setP(p); 
            kP = p; }
        if((i != kI)) { 
            frontLeftMotorPID.setI(i);
            backLeftMotorPID.setI(i);
            frontRightMotorPID.setI(i);
            backRightMotorPID.setI(i);
            kI = i;
        }
        if((d != kD)) {
            frontLeftMotorPID.setD(d);
            backLeftMotorPID.setD(d);
            frontRightMotorPID.setD(d);
            backRightMotorPID.setD(d);
            kD = d; 
        }
        if((iz != kIz)) {
            frontLeftMotorPID.setIZone(iz);
            backLeftMotorPID.setIZone(iz);
            frontRightMotorPID.setIZone(iz);
            backRightMotorPID.setIZone(iz);
            kIz = iz;
            }
        if((ff != kFF)) {
            frontLeftMotorPID.setFF(ff);
            backLeftMotorPID.setFF(ff);
            frontRightMotorPID.setFF(ff);
            backRightMotorPID.setFF(ff);
            kFF = ff; 
            }
        if((max != kMaxOutput) || (min != kMinOutput)) { 
          frontLeftMotorPID.setOutputRange(min, max);
          backLeftMotorPID.setOutputRange(min, max);
          frontRightMotorPID.setOutputRange(min, max);
          backRightMotorPID.setOutputRange(min, max);
          kMinOutput = min; kMaxOutput = max; 
        }
        if((maxV != maxVel)) { 
          frontLeftMotorPID.setSmartMotionMaxVelocity(maxV,0);
          backLeftMotorPID.setSmartMotionMaxVelocity(maxV,0);
          frontRightMotorPID.setSmartMotionMaxVelocity(maxV,0);
          backRightMotorPID.setSmartMotionMaxVelocity(maxV,0);
          maxVel = maxV; 
        }
        if((minV != minVel)) {
          frontLeftMotorPID.setSmartMotionMinOutputVelocity(minV,0);
          backLeftMotorPID.setSmartMotionMinOutputVelocity(minV,0);
          frontRightMotorPID.setSmartMotionMinOutputVelocity(minV,0);
          backRightMotorPID.setSmartMotionMinOutputVelocity(minV,0);
          minVel = minV; 
        }
        if((maxA != maxAccel)) { 
          frontLeftMotorPID.setSmartMotionMaxAccel(maxA,0);
          backLeftMotorPID.setSmartMotionMaxAccel(maxA,0);
          frontRightMotorPID.setSmartMotionMaxAccel(maxA,0);
          backRightMotorPID.setSmartMotionMaxAccel(maxA,0);
          maxAccel = maxA; 
        }
        if((allE != allowedErr)) { 
          frontLeftMotorPID.setSmartMotionAllowedClosedLoopError(allE,0);
          backLeftMotorPID.setSmartMotionAllowedClosedLoopError(allE,0);
          frontRightMotorPID.setSmartMotionAllowedClosedLoopError(allE,0);
          backRightMotorPID.setSmartMotionAllowedClosedLoopError(allE,0);
          allowedErr = allE; 
        }
      }

  public void setPosition(double position){
    frontLeftMotorPID.setReference(position, ControlType.kPosition);
    backLeftMotorPID.setReference(position, ControlType.kPosition);
    frontRightMotorPID.setReference(position, ControlType.kPosition);
    backRightMotorPID.setReference(position, ControlType.kPosition);
  }
}