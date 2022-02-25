package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;


public class Climbers extends SubsystemBase {

    //defines the motors that we're using for the climbers
    public static CANSparkMax ClimberL1;
    //public static CANSparkMax ClimberL2;
    public static CANSparkMax ClimberR1;
    //public static CANSparkMax ClimberR2;
  // Creates a new Climber Subsystem.
  public Climbers() {
    ClimberL1 = new CANSparkMax(ClimberL1ID, MotorType.kBrushless);
    //ClimberL2 = new CANSparkMax(ClimberL2ID, MotorType.kBrushless);
    ClimberR1 = new CANSparkMax(ClimberR1ID, MotorType.kBrushless);
    //ClimberR2 = new CANSparkMax(ClimberR2ID, MotorType.kBrushless);
  }


  @Override

  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("climberRightSpeed", ClimberR1.getEncoder().getVelocity());
    SmartDashboard.putNumber("climberLeftSpeed", ClimberL1.getEncoder().getVelocity());
    SmartDashboard.putNumber("climberRightSetPoint", ClimberR1.getEncoder().getPosition());
    SmartDashboard.putNumber("climberLeftSetPoint", ClimberL1.getEncoder().getPosition());
  }
}