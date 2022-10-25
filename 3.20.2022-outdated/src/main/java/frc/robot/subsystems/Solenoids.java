package frc.robot.subsystems;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//import static frc.robot.Constants.*;


public class Solenoids extends SubsystemBase {

  //defines the motor that we're using for the subsystem
  public static PneumaticHub revPneumaticHub;
  public static Compressor compressor;
  public static DoubleSolenoid intakeSolenoid;
  public static DoubleSolenoid climberSolenoid2;

  // Creates a new Subsystem.
  public Solenoids() {
    revPneumaticHub = new PneumaticHub();
    compressor = new Compressor(PneumaticsModuleType.REVPH);
    intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, 13, 15); //solenoid is triggered on autonomous initialization in Robot
    climberSolenoid2 = new DoubleSolenoid(PneumaticsModuleType.REVPH, 2, 0);
  }


  @Override

  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("compressor status", compressor.enabled());
  }
}