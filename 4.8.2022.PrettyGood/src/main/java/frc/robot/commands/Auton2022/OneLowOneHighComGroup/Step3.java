package frc.robot.commands.Auton2022.OneLowOneHighComGroup;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelights;
import frc.robot.subsystems.Shooter;

import static frc.robot.Constants.*;


/** An example command that uses an example subsystem. */
public class Step3 extends CommandBase {

  private Timer time;
  Shooter m_shooter;
  Conveyor m_conveyor;
  Drivetrain m_drivetrain;
  Limelights m_limelights;
  Intake m_intake;

  public Step3(Shooter shooter, Conveyor conveyor, Drivetrain drivetrain, Limelights limelights, Intake intake) {
    time = new Timer();

    m_shooter = shooter;
    m_conveyor = conveyor;
    m_drivetrain = drivetrain;
    m_limelights = limelights;
    m_intake = intake;

    addRequirements(shooter, conveyor, drivetrain);

  }
  @Override
  public void initialize() {
    time.reset();    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    time.start();
    if(time.get() < 2){
      m_limelights.backTable.getEntry("ledMode").setNumber(3);
      m_drivetrain.drivetrainMecanum.driveCartesian(0, 0, m_limelights.horizontalAutoBack());
    }
    if(time.get() > 2 && time.get() < 4){
      m_drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
      m_shooter.SetShootersPID(targetShooterVelocityHigh);
      m_intake.intakeMotor.set(0);
    }
    if(time.get() > 4 && time.get() < 6){
      m_shooter.SetShootersPID(targetShooterVelocityHigh);
      m_conveyor.conveyorMotor.set(conveyorMotorPower);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(time.get() > 6){
        return true;
    } else{
    return false;
    }
  }
}