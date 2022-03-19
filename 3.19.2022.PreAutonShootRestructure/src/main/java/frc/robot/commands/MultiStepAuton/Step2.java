package frc.robot.commands.MultiStepAuton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BreakBeams;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Solenoids;

import static frc.robot.Constants.*;


/** An example command that uses an example subsystem. */
public class Step2 extends CommandBase {

  //private final Subsystem m_subsystem;
    private Timer time;

  /**
   * Creates a new RunConveyor Command.
   *
   * @param drivetrain The subsystem used by this command.
   * 
   */

  public Step2(Drivetrain drivetrain, Intake intake, Conveyor conveyor) {
    time = new Timer();
    //m_subsystem = subsystem;

    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(drivetrain, intake, conveyor);

  }
  @Override
  public void initialize() {
    System.out.print("step2Init");
    time.reset();    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
public void execute() {
    System.out.print("step2");
    time.start();
    if(time.get() < 1.2){
        Drivetrain.drivetrainMecanum.driveCartesian(0.50, 0, 0);
        Intake.intakeMotor.set(intakeMotorPower);
    }
    if(time.get() > 1.2 && time.get() < 3){
        Drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
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
        Conveyor.conveyorMotor.set(0);//duh
        //BreakBeams.ammo++; //increments the ammo by one
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
    if(time.get()>3){
        return true;
    } else{
        return false;
    }
  }
}