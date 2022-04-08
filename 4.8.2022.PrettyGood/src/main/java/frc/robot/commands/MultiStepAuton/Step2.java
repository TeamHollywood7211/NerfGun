package frc.robot.commands.MultiStepAuton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BreakBeams;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelights;

import static frc.robot.Constants.*;


/** An example command that uses an example subsystem. */
public class Step2 extends CommandBase {

  private Timer time;
  Drivetrain m_drivetrain;
  Intake m_intake;
  Conveyor m_conveyor;
  BreakBeams m_breakBeams;
  Limelights m_limelights;

  public Step2(Drivetrain drivetrain, Intake intake, Conveyor conveyor, BreakBeams breakBeams, Limelights limelights) {

    m_drivetrain = drivetrain;
    m_intake = intake;
    m_conveyor = conveyor;
    m_breakBeams = breakBeams;
    m_limelights = limelights;
    time = new Timer();

    addRequirements(drivetrain, intake, conveyor, breakBeams);

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
        m_drivetrain.drivetrainMecanum.driveCartesian(0.50, 0, m_limelights.horizontalAutoFront());
        m_intake.intakeMotor.set(IntakeConstants.intakeMotorPower);
    }
    if(time.get() > 1.2 && time.get() < 3){
        m_drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
        if(m_breakBeams.HopperBeamOutput() == true){
            if(m_breakBeams.MiddleBeamOutput() == false && m_breakBeams.TopBeamOutput() == false){ //if there isnt a ball in the middle or at the top then
                while(m_breakBeams.MiddleBeamOutput() == false){ //run conveyor until there is a ball in the middle
                m_conveyor.conveyorMotor.set(conveyorMotorPower);
                }
            }
            else if(m_breakBeams.MiddleBeamOutput() == true && m_breakBeams.TopBeamOutput() == false){ //if there is a ball in the middle or at the top, then
                while(m_breakBeams.TopBeamOutput()==false){ //run the conveyor until there is a ball at the top
                m_conveyor.conveyorMotor.set(conveyorMotorPower);
                }
            }
        m_conveyor.conveyorMotor.set(0);//duh
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