package frc.robot.commands.DemoCommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Solenoids;


/** Command that uses solenoid subsystem. */
public class RunDoubleSolenoids extends CommandBase {

  //private final Subsystem m_subsystem;


  /**
   * Creates a new RunConveyor Command.
   *
   * @param intake The subsystem used by this command.
   * 
   */

  public RunDoubleSolenoids(Solenoids solenoids) {

    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(solenoids);

  }
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotContainer.solenoidInOutButton.get()){
      if(Solenoids.intakeSolenoid.get() == Value.kOff){
        Solenoids.intakeSolenoid.set(Value.kForward);
      } else if(Solenoids.intakeSolenoid.get() == Value.kReverse){
        Solenoids.intakeSolenoid.set(Value.kForward);
      } else if(Solenoids.intakeSolenoid.get()== Value.kForward){
        Solenoids.intakeSolenoid.set(Value.kReverse);
      }
    }
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