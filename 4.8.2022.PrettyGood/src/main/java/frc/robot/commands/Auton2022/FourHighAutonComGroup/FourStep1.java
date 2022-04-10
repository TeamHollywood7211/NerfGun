package frc.robot.commands.Auton2022.FourHighAutonComGroup;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelights;
import frc.robot.subsystems.Solenoids;


/** An example command that uses an example subsystem. */
public class FourStep1 extends CommandBase {
  private final Intake m_intake;
  private final Drivetrain m_drivetrain;
  private final Solenoids m_solenoids;
  private final Limelights m_limelights;

  public FourStep1(Intake intake, Drivetrain drivetrain, Solenoids solenoids, Limelights limelights) {
    m_intake = intake;
    m_drivetrain = drivetrain;
    m_solenoids = solenoids;
    m_limelights = limelights;
    addRequirements(intake, drivetrain, solenoids, limelights);
  }
  @Override
  public void initialize() {
    m_drivetrain.resetDrivetrainEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_limelights.backTable.getEntry("ledMode").setNumber(3);
    m_solenoids.intakeSolenoid.set(Value.kForward);
    m_intake.intakeMotor.set(intakeMotorPower);
    m_drivetrain.setPositionDrivetrain(30);//25 for wall
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(m_drivetrain.getFrontMotorPositions()>28){
      return true;
    }
    return false;
  }
}