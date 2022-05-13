package frc.robot.subsystems;
import edu.wpi.first.math.controller.PIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  public CANSparkMax shooterLeftMotor;
  public CANSparkMax shooterRightMotor;

  public RelativeEncoder shooterLeftEncoder;
  public RelativeEncoder shooterRightEncoder;
  public PIDController shooterLeftPID;
  public PIDController shooterRightPID;
  
  // public double shooterSpeed = 730;

  public Shooter() {
    //these variables are the constants for the pid loop, kP is proportional, kI is integral, kD is derivative
    //look up "pid constant tuning effect chart" to find what changing these might do
    //pid constants
    double kP = 0.0018;
    double kI = 0.00073;
    double kD = 0.00028;

    
    shooterLeftMotor = new CANSparkMax(shooterLeftMotorID, MotorType.kBrushless);
    shooterRightMotor = new CANSparkMax(shooterRightMotorID, MotorType.kBrushless);

    //pid controllers for low shoot
    shooterLeftPID = new PIDController(kP, kI, kD);//order is kP, kI, kD
    shooterRightPID = new PIDController(kP, kI, kD);
    //pid controllers for the high shoot

    shooterLeftEncoder = shooterLeftMotor.getEncoder();
    shooterRightEncoder = shooterRightMotor.getEncoder();

    // SmartDashboard.putNumber("Shooter Speed", shooterSpeed);
  }

  @Override
  public void periodic() {
    // double finalShooterSpeed = SmartDashboard.getNumber("Shooter Speed", 0);
    // if(shooterSpeed != finalShooterSpeed){
    //   shooterSpeed = finalShooterSpeed;
    // }
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("leftShooterMotorVelocity", shooterLeftEncoder.getVelocity());
    SmartDashboard.putNumber("rightShooterMotorVelocity", shooterRightEncoder.getVelocity());
  }

  //This method will set the shooter motors to zero
  public void SetShootersZero(){
    shooterRightMotor.set(0);
    shooterLeftMotor.set(0);
  }
  //This method will set the shooter motors to normal speed
  public void SetShootersHigh(){
    shooterRightMotor.set(shooterMotorPowerHigh);
    shooterLeftMotor.set(shooterMotorPowerHigh+.047);
  }
  //This method will set the shooter motors to slow speed
  public void SetShootersSlow(){
    shooterRightMotor.set(shooterMotorPowerSlow);
    shooterLeftMotor.set(shooterMotorPowerSlow+.035);
  }
  public void SetShootersPID(double targetVelocity){
    shooterLeftMotor.set(shooterLeftPID.calculate(shooterLeftEncoder.getVelocity(), targetVelocity));
    shooterRightMotor.set(shooterRightPID.calculate(shooterRightEncoder.getVelocity(), targetVelocity));
  }
  public double returnShooterVelAverage(){
    double leftShooterMotorVelocity = shooterLeftEncoder.getVelocity();
    double rightShooterMotorVelocity = shooterRightEncoder.getVelocity();
    return (leftShooterMotorVelocity+rightShooterMotorVelocity)/2;
  }

  public void clearShooterErrors(){
      shooterLeftMotor.clearFaults();
      shooterRightMotor.clearFaults();
  }

}