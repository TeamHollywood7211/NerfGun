package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;
import frc.robot.RobotContainer;
import static frc.robot.Constants.*;

/** An example command that uses an example subsystem. */

public class RunConveyor extends CommandBase {
  Conveyor m_conveyor;

  public RunConveyor(Conveyor conveyor) {

    m_conveyor = conveyor;
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
        m_conveyor.conveyorMotor.set(conveyorMotorPower);
      }
      else if(RobotContainer.conveyorDownButton.get()){
        m_conveyor.conveyorMotor.set(-conveyorMotorPower);
      } 
      else{
        m_conveyor.conveyorMotor.set(0);
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