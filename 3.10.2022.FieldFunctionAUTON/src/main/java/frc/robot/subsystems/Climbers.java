package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

import static frc.robot.Constants.*;


public class Climbers extends SubsystemBase {

    //defines the motors that we're using for the climbers
    public static CANSparkMax ClimberL1;
    public static CANSparkMax ClimberL2;
    public static CANSparkMax ClimberR1;
    public static CANSparkMax ClimberR2;
    public static RelativeEncoder ClimberL1Encoder;
    public static RelativeEncoder ClimberL2Encoder;
    public static RelativeEncoder ClimberR1Encoder;
    public static RelativeEncoder ClimberR2Encoder;

  // Creates a new Climber Subsystem.
  public Climbers() {
    ClimberL1 = new CANSparkMax(ClimberL1ID, MotorType.kBrushless);
    ClimberL2 = new CANSparkMax(ClimberL2ID, MotorType.kBrushless);
    ClimberR1 = new CANSparkMax(ClimberR1ID, MotorType.kBrushless);
    ClimberR2 = new CANSparkMax(ClimberR2ID, MotorType.kBrushless);

    ClimberL1Encoder = ClimberL1.getEncoder();
    ClimberL2Encoder = ClimberL2.getEncoder();
    ClimberR1Encoder = ClimberR1.getEncoder();
    ClimberR2Encoder = ClimberR2.getEncoder();
  }


  @Override

  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("climberLeft2Position", ClimberR2Encoder.getPosition());
    SmartDashboard.putNumber("climberRight2Position", ClimberL2Encoder.getPosition());
  }
}