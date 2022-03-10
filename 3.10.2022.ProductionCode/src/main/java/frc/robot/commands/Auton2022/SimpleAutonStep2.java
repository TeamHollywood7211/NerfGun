package frc.robot.commands.Auton2022;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BreakBeams;
import frc.robot.subsystems.Conveyor;
import static frc.robot.Constants.*;


/** An example command that uses an example subsystem. */
public class SimpleAutonStep2 extends CommandBase {

  //private final Subsystem m_subsystem;


  /**
   * Creates a new RunConveyor Command.
   *
   * @param subsystem The subsystem used by this command.
   * 
   */

  public SimpleAutonStep2(Conveyor conveyor, BreakBeams breakBeams) {

    //m_subsystem = subsystem;

    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(conveyor, breakBeams);

  }
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(BreakBeams.HopperBeamOutput() == true){
        if(BreakBeams.MiddleBeamOutput() == false && BreakBeams.TopBeamOutput() == false){ //if there isnt a ball in the middle or at the top then
          while(BreakBeams.MiddleBeamOutput() == false){ //run conveyor until there is a ball in the middle
            Conveyor.conveyorMotor.set(conveyorMotorPower);
          }
        }
        else if(BreakBeams.MiddleBeamOutput() == true && BreakBeams.TopBeamOutput() == false){ //if there is a ball in the middle or at the top, then
          while(BreakBeams.TopBeamOutput()==false){ //run the conveyor until there is a ball at the top
            Conveyor.conveyorMotor.set(conveyorMotorPower);
          }
        }
        else if(BreakBeams.TopBeamOutput() == true){//If there's a ball at the top, but not the middle, run the conveyor
          while(BreakBeams.MiddleBeamOutput()== false){//backwards until it hits the middle.
            Conveyor.conveyorMotor.set(-conveyorMotorPower);
          }
          while(BreakBeams.MiddleBeamOutput()==true){
            Conveyor.conveyorMotor.set(-conveyorMotorPower);
          }
          while(BreakBeams.TopBeamOutput() == false){//then, run the conveyor until there's a ball in the top
            Conveyor.conveyorMotor.set(conveyorMotorPower);
          }
        }
        Conveyor.conveyorMotor.set(0);//duh
        //BreakBeams.ammo++; //increments the ammo by one
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