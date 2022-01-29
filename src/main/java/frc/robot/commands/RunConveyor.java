package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;
import frc.robot.RobotContainer;
import static frc.robot.Constants.*;

/** An example command that uses an example subsystem. */

public class RunConveyor extends CommandBase {

  private final Conveyor m_conveyor;


  /**
   * Creates a new RunConveyor Command.
   *
   * @param m_conveyor The subsystem used by this command.
   */

  public RunConveyor(Conveyor conveyor) {

    m_conveyor = conveyor;

    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(conveyor);

  }
  @Override
  public void initialize() {
      //runs once before everything else
  }

  // Called every time the scheduler runs while the command is scheduled.

  @Override
  public void execute() {
      if(RobotContainer.conveyorUpButton.get()){
        Conveyor.conveyorMotor.set(conveyorMotorPower);
      } else{
        Conveyor.conveyorMotor.set(0);
      } if(RobotContainer.conveyorDownButton.get()){
        Conveyor.conveyorMotor.set(-conveyorMotorPower);
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