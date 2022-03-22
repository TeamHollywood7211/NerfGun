package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;


public class Climbers extends SubsystemBase {

    //defines the motors that we're using for the climbers
    public CANSparkMax ClimberL1;
    public CANSparkMax ClimberL2;
    public CANSparkMax ClimberR1;
    public CANSparkMax ClimberR2;
    public RelativeEncoder ClimberL1Encoder;
    public RelativeEncoder ClimberL2Encoder;
    public RelativeEncoder ClimberR1Encoder;
    public RelativeEncoder ClimberR2Encoder;

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