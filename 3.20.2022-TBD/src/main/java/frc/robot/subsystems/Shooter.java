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
    public static CANSparkMax shooterLeftMotor;
    public static CANSparkMax shooterRightMotor;

    public static RelativeEncoder shooterLeftEncoder;
    public static RelativeEncoder shooterRightEncoder;
    public static PIDController shooterLeftPID;
    public static PIDController shooterRightPID;

    public Shooter() {
      //these variables are the constants for the pid loop, kP is proportional, kI is integral, kD is derivative
      //look up "pid constant tuning effect chart" to find what changing these might do
      
      //pid constants
          double kP = 0.0018;
          double kI = 0.0004;//0.0006
          double kD = 0.00025;//0.0004
      
      shooterLeftMotor = new CANSparkMax(shooterLeftMotorID, MotorType.kBrushless);
      shooterRightMotor = new CANSparkMax(shooterRightMotorID, MotorType.kBrushless);

      //pid controllers for low shoot
      shooterLeftPID = new PIDController(kP, kI, kD);//order is kP, kI, kD
      shooterRightPID = new PIDController(kP, kI, kD);
      //pid controllers for the high shoot

      shooterLeftEncoder = shooterLeftMotor.getEncoder();
      shooterRightEncoder = shooterRightMotor.getEncoder();
    }

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
      SmartDashboard.putNumber("leftShooterMotorVelocity", shooterLeftEncoder.getVelocity());
      SmartDashboard.putNumber("rightShooterMotorVelocity", shooterRightEncoder.getVelocity());
    }

    //This method will set the shooter motors to zero
    public static void SetShootersZero(){
      shooterRightMotor.set(0);
      shooterLeftMotor.set(0);
    }
    //This method will set the shooter motors to normal speed
    public static void SetShootersHigh(){
      shooterRightMotor.set(shooterMotorPowerHigh);
      shooterLeftMotor.set(shooterMotorPowerHigh+.047);
    }
    //This method will set the shooter motors to slow speed
    public static void SetShootersSlow(){
      shooterRightMotor.set(shooterMotorPowerSlow);
      shooterLeftMotor.set(shooterMotorPowerSlow+.035);
    }
    public static void SetShootersPID(int targetVelocity){
      shooterLeftMotor.set(shooterLeftPID.calculate(shooterLeftEncoder.getVelocity(), targetVelocity));
      shooterRightMotor.set(shooterRightPID.calculate(shooterRightEncoder.getVelocity(), targetVelocity));
    }
}