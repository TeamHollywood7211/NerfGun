package frc.robot.commands.DemoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Climbers;
import static frc.robot.Constants.*;


/** An example command that uses an example subsystem. */
public class RunClimbers extends CommandBase {

  /**
   * Creates a new RunConveyor Command.
   *
   * @param subsystem The subsystem used by this command.
   * 
   */

  public RunClimbers(Climbers climbers) {

    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(climbers);

  }
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotContainer.climbUpButton.get()){
        Climbers.ClimberL1.set(climberSpeedStage1);
        Climbers.ClimberR1.set(climberSpeedStage1);
    } else if(RobotContainer.climbDownButton.get()){
        Climbers.ClimberL1.set(-climberSpeedStage1);
        Climbers.ClimberR1.set(-climberSpeedStage1);
    } else{
      Climbers.ClimberL1.set(0);
      Climbers.ClimberR1.set(0);
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