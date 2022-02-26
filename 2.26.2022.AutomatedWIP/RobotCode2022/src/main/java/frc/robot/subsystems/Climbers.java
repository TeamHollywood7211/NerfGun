package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;


public class Climbers extends SubsystemBase {

    //defines the motors that we're using for the climbers
    public static CANSparkMax ClimberL1;
    public static CANSparkMax ClimberR1;
    public static RelativeEncoder ClimberL1Encoder;
    public static RelativeEncoder ClimberR1Encoder;

  // Creates a new Climber Subsystem.
  public Climbers() {
    ClimberL1 = new CANSparkMax(ClimberL1ID, MotorType.kBrushless);    
    ClimberR1 = new CANSparkMax(ClimberR1ID, MotorType.kBrushless);
    ClimberL1Encoder = ClimberL1.getEncoder();
    ClimberR1Encoder = ClimberR1.getEncoder();
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