package frc.robot.commands.Auton2022.FourHighAutonComGroup;


import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelights;
import frc.robot.subsystems.Solenoids;


/** An example command that uses an example subsystem. */
public class FourStep1 extends CommandBase {
  private final Drivetrain m_drivetrain;
  private final Solenoids m_solenoids;
  private final Limelights m_limelights;

  public FourStep1(Drivetrain drivetrain, Solenoids solenoids, Limelights limelights) {
    m_drivetrain = drivetrain;
    m_solenoids = solenoids;
    m_limelights = limelights;
    addRequirements(drivetrain, solenoids, limelights);
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
    m_drivetrain.setPositionDrivetrain(33);//25 for wall
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(m_drivetrain.getFrontMotorPositions()>30){
      return true;
    }
    return false;
  }
}