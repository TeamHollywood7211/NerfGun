package frc.robot.commands.Auton2022;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;

import static frc.robot.Constants.*;


/** An example command that uses an example subsystem. */
public class ShootBackUpAuton extends CommandBase {

  //private final Subsystem m_subsystem;
    public Timer time;

  /**
   * Creates a new RunConveyor Command.
   *
   * @param drivetrain The subsystem used by this command.
   * 
   */

  public ShootBackUpAuton(Drivetrain drivetrain, Shooter shooter) {
    time = new Timer();
    //m_subsystem = subsystem;

    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(drivetrain, shooter);

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
      Shooter.SetShootersSlow();
    }
    while(time.get()<3 && time.get() > 2){
      Conveyor.conveyorMotor.set(conveyorMotorPower);
    }
    while(time.get()>3 && time.get()<4.2){
      Conveyor.conveyorMotor.set(0);
      Shooter.SetShootersZero();
      Drivetrain.drivetrainMecanum.driveCartesian(0.50, 0, 0);
    }
    while(time.get()>4.2 && time.get()<15){
        Drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
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