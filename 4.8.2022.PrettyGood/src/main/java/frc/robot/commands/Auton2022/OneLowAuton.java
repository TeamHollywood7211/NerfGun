package frc.robot.commands.Auton2022;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;

import static frc.robot.Constants.*;


/** An example command that uses an example subsystem. */
public class OneLowAuton extends CommandBase {

  public Timer time;
  Drivetrain m_drivetrain;
  Shooter m_shooter;
  Conveyor m_conveyor;

  public OneLowAuton(Drivetrain drivetrain, Shooter shooter, Conveyor conveyor) {
    time = new Timer();
    m_drivetrain = drivetrain;
    m_shooter = shooter;
    m_conveyor = conveyor;

    addRequirements(drivetrain, shooter, conveyor);

  }
  @Override
  public void initialize() {
    time.reset();    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    time.start();
    while(time.get()<2){
      m_shooter.SetShootersSlow();
    }
    while(time.get()<3 && time.get() > 2){
      m_conveyor.conveyorMotor.set(conveyorMotorPower);
    }
    while(time.get()>3 && time.get()<4.2){
      m_conveyor.conveyorMotor.set(0);
      m_shooter.SetShootersZero();
      m_drivetrain.drivetrainMecanum.driveCartesian(0.50, 0, 0);
    }
    while(time.get()>4.2 && time.get()<15){
      m_drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
    }
     
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // time.stop();
    // time.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // time.stop();
    // time.reset();
    return false;
  }
}