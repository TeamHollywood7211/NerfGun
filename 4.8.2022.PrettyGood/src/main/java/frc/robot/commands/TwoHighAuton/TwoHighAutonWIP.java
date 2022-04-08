package frc.robot.commands.TwoHighAuton;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;

import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;


public class TwoHighAutonWIP extends CommandBase {
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
    m_drivetrain.setPosition(m_drivetrain.setPoint);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}