package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.RobotContainer;
import static frc.robot.Constants.*;
//import frc.robot.commands.RunShooter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shooter extends SubsystemBase {
    /** Creates a new Shooter. */
    public static CANSparkMax shooterLeftMotor;
    public static CANSparkMax shooterRightMotor;

    public static CANEncoder shooterLeftEncoder;
    //public static double leftMotorVelocity = Shooter.shooterLeftEncoder.getVelocity();
    public static CANEncoder shooterRightEncoder;
    //public static double rightMotorVelocity = Shooter.shooterRightEncoder.getVelocity();

    public static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    public static NetworkTableEntry tv = table.getEntry("tv");
    public static NetworkTableEntry ta = table.getEntry("ta");

    

    public Shooter() {
      shooterLeftMotor = new CANSparkMax(shooterLeftMotorID, MotorType.kBrushless);
      shooterRightMotor = new CANSparkMax(shooterRightMotorID, MotorType.kBrushless);
      shooterLeftEncoder = shooterLeftMotor.getEncoder();
      shooterRightEncoder = shooterRightMotor.getEncoder();
    }
    //This method will set the shooter motors to zero
    public static void SetShootersZero(){
      shooterRightMotor.set(0);
      shooterLeftMotor.set(0);
    }
    //This method will set the shooter motors to normal speed
    public static void SetShootersNormal(){
      shooterRightMotor.set(shooterMotorPower);
      shooterLeftMotor.set(shooterMotorPower);
    }
    //This method will set the shooter motors to slow speed
    public static void SetShootersSlow(){
      shooterRightMotor.set(shooterMotorPowerSlow);
      shooterLeftMotor.set(shooterMotorPowerSlow);
    }

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
}