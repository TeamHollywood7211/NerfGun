//package frc.robot.ExampleFiles;

import edu.wpi.first.wpilibj2.command.CommandBase;


/** An example command that uses an example subsystem. */
public class ExampleCommand extends CommandBase {

  //private final Subsystem m_subsystem;

  /**
   * Creates a new RunConveyor Command.
   *
   * @param subsystem The subsystem used by this command.
   * 
   */

  public ExampleCommand(/*Subsystem subsystem*/) {

    //m_subsystem = subsystem;

    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(/*subsystem*/);

  }
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
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