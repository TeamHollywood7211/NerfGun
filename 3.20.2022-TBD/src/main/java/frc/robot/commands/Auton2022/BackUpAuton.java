package frc.robot.commands.Auton2022;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.DrivetrainCommand;
import frc.robot.subsystems.Drivetrain;


/** An example command that uses an example subsystem. */
public class BackUpAuton extends CommandBase {

  //private final Subsystem m_subsystem;
    private Timer time;
  /**
   * Creates a new RunConveyor Command.
   *
   * @param drivetrain The subsystem used by this command.
   * 
   */

  public BackUpAuton(Drivetrain drivetrain) {
    time = new Timer();
    //m_subsystem = subsystem;

    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(drivetrain);

  }
  @Override
  public void initialize() {
      time.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      while(time.get()<1.5){
          Drivetrain.drivetrainMecanum.driveCartesian(0.2, 0, 0);
      }
      Drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
      time.stop();
      time.reset();
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