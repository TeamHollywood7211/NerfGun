package frc.robot.commands.MultiStepAuton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Solenoids;

import static frc.robot.Constants.*;


/** An example command that uses an example subsystem. */
public class Step1 extends CommandBase {

  //private final Subsystem m_subsystem;
    private Timer time;

  /**
   * Creates a new RunConveyor Command.
   *
   * @param drivetrain The subsystem used by this command.
   * 
   */

  public Step1(Shooter shooter, Conveyor conveyor, Solenoids solenoids) {
    time = new Timer();
    //m_subsystem = subsystem;

    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(shooter, conveyor, solenoids);

  }
  @Override
  public void initialize() {
    time.reset();    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.print("step1");
    time.start();
    if(time.get() < 2){
      Shooter.SetShootersSlow();
      Solenoids.intakeSolenoid.set(Value.kForward);
    }
    if(time.get() > 2 && time.get() < 3){
      Conveyor.conveyorMotor.set(conveyorMotorPower);
    }
    if(time.get() > 3 && time.get() < 3.2){
      Shooter.SetShootersZero();
      Conveyor.conveyorMotor.set(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(time.get()>3.2){
      return true;
    } else{
    return false;
    }
  }
}