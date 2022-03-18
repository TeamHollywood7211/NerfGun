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
    public static PIDController shooterLeftPIDSlow;
    public static PIDController shooterRightPIDSlow;
    public static PIDController shooterLeftPIDHigh;
    public static PIDController shooterRightPIDHigh;

    public Shooter() {
      //these variables are the constants for the pid loop, kP is proportional, kI is integral, kD is derivative
      //look up "pid constant tuning effect chart" to find what changing these might do
      
      //right pid constants
        //slow right
          double SlowRkP = 0.0033; 
          double SlowRkI = 0.000732; 
          double SlowRkD = 0.000373;
        //high right
          double HighRkP = 0.004;
          double HighRkI = 0.000523;
          double HighRkD = 0.0005;
      //left pid constants
        //slow left
          double SlowLkP = 0.0033;
          double SlowLkI = 0.000732;
          double SlowLkD = 0.000373;
        //high left
          double HighLkP = 0.004;
          double HighLkI = 0.000523;
          double HighLkD = 0.0005;

      shooterLeftMotor = new CANSparkMax(shooterLeftMotorID, MotorType.kBrushless);
      shooterRightMotor = new CANSparkMax(shooterRightMotorID, MotorType.kBrushless);

      //pid controllers for low shoot
      shooterLeftPIDSlow = new PIDController(SlowLkP, SlowLkI, SlowLkD);//order is kP, kI, kD
      shooterRightPIDSlow = new PIDController(SlowRkP, SlowRkI, SlowRkD);
      //pid controllers for the high shoot
      shooterLeftPIDHigh = new PIDController(HighLkP, HighLkI, HighLkD);
      shooterRightPIDHigh = new PIDController(HighRkP, HighRkI, HighRkD);


      shooterLeftEncoder = shooterLeftMotor.getEncoder();
      shooterRightEncoder = shooterRightMotor.getEncoder();
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
    public static void SetShootersHighPID(){
      shooterLeftMotor.set(shooterLeftPIDHigh.calculate(shooterLeftEncoder.getVelocity(), targetShooterVelocityHigh));
      shooterRightMotor.set(shooterRightPIDHigh.calculate(shooterRightEncoder.getVelocity(), targetShooterVelocityHigh));
    }
    public static void SetShootersSlowPID(){
      shooterLeftMotor.set(shooterLeftPIDSlow.calculate(shooterLeftEncoder.getVelocity(), targetShooterVelocitySlow));
      shooterRightMotor.set(shooterRightPIDSlow.calculate(shooterRightEncoder.getVelocity(), targetShooterVelocitySlow));
    }

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
      SmartDashboard.putNumber("leftShooterMotorVelocity", shooterLeftEncoder.getVelocity());
      SmartDashboard.putNumber("rightShooterMotorVelocity", shooterRightEncoder.getVelocity());

    }
}