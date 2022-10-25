package frc.robot.commands.TwoHighAuton;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;

import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;


public class TwoHighAutonWIP extends CommandBase {
    //private double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM, maxVel, minVel, maxAccel, allowedErr;
  Drivetrain m_drivetrain;
  public TwoHighAutonWIP(Drivetrain drivetrain) {
    m_drivetrain = drivetrain;
    addRequirements(m_drivetrain);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    // Drivetrain.frontLeftMotorPID.setReference(setPoint, CANSparkMax.ControlType.kSmartMotion);
    // Drivetrain.backLeftMotorPID.setReference(setPoint, CANSparkMax.ControlType.kSmartMotion);
    // Drivetrain.frontRightMotorPID.setReference(setPoint, CANSparkMax.ControlType.kSmartMotion);
    // Drivetrain.backRightMotorPID.setReference(setPoint, CANSparkMax.ControlType.kSmartMotion);
    //Drivetrain.setPosition(m_drivetrain.setPoint);
    //processVariable = Drivetrain.frontLeftMotorEncoder.getPosition();
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}