package frc.robot.commands.MultiStepAuton;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;

/** An example command that uses an example subsystem. */
public class Step4 extends CommandBase {
  Shooter m_shooter;
  Conveyor m_conveyor;
  Drivetrain m_drivetrain;

  public Step4(Shooter shooter, Conveyor conveyor, Drivetrain drivetrain) {
    m_shooter = shooter;
    m_conveyor = conveyor;
    m_drivetrain = drivetrain;

    addRequirements(shooter, conveyor, drivetrain);
  }
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooter.SetShootersZero();
    m_conveyor.conveyorMotor.set(0);
    m_drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}