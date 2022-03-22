package frc.robot.commands.Auton2022;

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
public class SimpleAuton extends CommandBase {

  public Timer time;
  Drivetrain m_drivetrain;
  Intake m_intake;
  Solenoids m_solenoids;
  Shooter m_shooter;
  Conveyor m_conveyor;
  BreakBeams m_breakBeams;

  public SimpleAuton(Drivetrain drivetrain, Intake intake, Solenoids solenoids, Shooter shooter, Conveyor conveyor, BreakBeams breakBeams) {
    time = new Timer();
    m_drivetrain = drivetrain;
    m_intake = intake;
    m_solenoids = solenoids;
    m_shooter = shooter;
    m_conveyor = conveyor;
    m_breakBeams = breakBeams;

    addRequirements(m_drivetrain, intake, solenoids, shooter);
  }
  @Override
  public void initialize() {
    time.reset();    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    time.start();
    while(time.get()<2){
      m_solenoids.intakeSolenoid.set(Value.kForward);
      m_shooter.SetShootersSlow();
    }
    while(time.get()<3 && time.get() > 2){
      m_conveyor.conveyorMotor.set(conveyorMotorPower);
    }
    while(time.get()>3 && time.get()<4.2){
      m_conveyor.conveyorMotor.set(0);
      m_shooter.SetShootersZero();
      m_drivetrain.drivetrainMecanum.driveCartesian(0.50, 0, 0);
      m_intake.intakeMotor.set(IntakeConstants.intakeMotorPower);
    }
    while(time.get()>4.2 && time.get()<6){
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
    while(time.get() > 6 && time.get() < 8){
      m_drivetrain.drivetrainMecanum.driveCartesian(-0.31, 0, 0);
      m_intake.intakeMotor.set(0);
    }
    while(time.get() > 8 && time.get() < 9){
      m_drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
      m_shooter.SetShootersSlow();
    }
    while(time.get() > 9 && time.get() < 11){
      m_conveyor.conveyorMotor.set(conveyorMotorPower);
    }
    while(time.get() > 11 && time.get() < 13){
      m_conveyor.conveyorMotor.set(0);
      m_shooter.SetShootersZero();
      m_drivetrain.drivetrainMecanum.driveCartesian(0.35, 0, 0);
    }
    while(time.get()>13 && time.get()<15){
      m_drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // time.stop();
    // time.reset();
    return false;
  }
}