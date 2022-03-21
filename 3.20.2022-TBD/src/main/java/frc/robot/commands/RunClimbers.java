package frc.robot.commands;

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
    //code for moving stage one climbers up to zero or max points
    if(RobotContainer.climbUp1Button.get() && (Climbers.ClimberL1Encoder.getPosition() < 280 || Climbers.ClimberR1Encoder.getPosition() < 280)){
        Climbers.ClimberL1.set(climberSpeedStage1);
        Climbers.ClimberR1.set(climberSpeedStage1);
    } else if(RobotContainer.climbDown1Button.get() && (Climbers.ClimberL1Encoder.getPosition() > 6 || Climbers.ClimberR1Encoder.getPosition() > 6)){
        Climbers.ClimberL1.set(-climberSpeedStage1);
        Climbers.ClimberR1.set(-climberSpeedStage1);
    } else{
      Climbers.ClimberL1.set(0);
      Climbers.ClimberR1.set(0);
    }
    //code for moving stage two climb arms in or out, to min or max points.
    if((RobotContainer.climbUp2Button.get() || RobotContainer.climbUp2ButtonOperator()) && (Climbers.ClimberL2Encoder.getPosition() < 360 || Climbers.ClimberR2Encoder.getPosition() < 360)){
      Climbers.ClimberL2.set(climberSpeedStage2);
      Climbers.ClimberR2.set(climberSpeedStage2+.01);
    } else if((RobotContainer.climbDown2Button.get() || RobotContainer.climbDown2ButtonOperator()) && (Climbers.ClimberL2Encoder.getPosition() > 0 || Climbers.ClimberR2Encoder.getPosition() > 0)){
      Climbers.ClimberL2.set(-climberSpeedStage2);
      Climbers.ClimberR2.set(-climberSpeedStage2);
    } else{
      Climbers.ClimberL2.set(0);
      Climbers.ClimberR2.set(0);      
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