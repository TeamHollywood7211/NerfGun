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

  //private final Subsystem m_subsystem;
    public Timer time;

  /**
   * Creates a new RunConveyor Command.
   *
   * @param drivetrain The subsystem used by this command.
   * 
   */

  public SimpleAuton(Drivetrain drivetrain, Intake intake, Solenoids solenoids, Shooter shooter) {
    time = new Timer();
    //m_subsystem = subsystem;

    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(drivetrain, intake, solenoids, shooter);

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
      Solenoids.intakeSolenoid.set(Value.kForward);
      Shooter.SetShootersSlow();
    }
    while(time.get()<3 && time.get() > 2){
      Conveyor.conveyorMotor.set(conveyorMotorPower);
    }
    while(time.get()>3 && time.get()<4.2){
      Conveyor.conveyorMotor.set(0);
      Shooter.SetShootersZero();
      Drivetrain.drivetrainMecanum.driveCartesian(0.50, 0, 0);
      Intake.intakeMotor.set(intakeMotorPower);
    }
    while(time.get()>4.2 && time.get()<6){
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
    while(time.get() > 6 && time.get() < 8){
      Drivetrain.drivetrainMecanum.driveCartesian(-0.31, 0, 0);
      Intake.intakeMotor.set(0);
    }
    while(time.get() > 8 && time.get() < 9){
      Drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
      Shooter.SetShootersSlow();
    }
    while(time.get() > 9 && time.get() < 11){
      Conveyor.conveyorMotor.set(conveyorMotorPower);
    }
    while(time.get() > 11 && time.get() < 13){
      Conveyor.conveyorMotor.set(0);
      Shooter.SetShootersZero();
      Drivetrain.drivetrainMecanum.driveCartesian(0.35, 0, 0);
    }
    while(time.get()>13 && time.get()<15){
      Drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // time.stop();
    // time.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // time.stop();
    // time.reset();
    return false;
  }
}