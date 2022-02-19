package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class Conveyor extends SubsystemBase {

    //defines the motor that we're using for the conveyor
        public static CANSparkMax conveyorMotor;
        //Creates a new Conveyor Subsystem.
        public Conveyor() {
        conveyorMotor = new CANSparkMax(conveyorMotorID, MotorType.kBrushless);
        }


  @Override

  public void periodic() {
    // This method will be called once per scheduler run

  }
}