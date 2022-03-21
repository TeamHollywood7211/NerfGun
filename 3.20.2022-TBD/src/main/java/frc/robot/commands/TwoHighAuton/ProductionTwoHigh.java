package frc.robot.commands.TwoHighAuton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BreakBeams;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelights;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Solenoids;

import static frc.robot.Constants.*;

import javax.security.auth.AuthPermission;


/** An example command that uses an example subsystem. */
public class ProductionTwoHigh extends CommandBase {

  //private final Subsystem m_subsystem;
    public Timer time;
    private int autonTargetVelocity = 780;
  /**
   * Creates a new RunConveyor Command.
   *
   * @param drivetrain The subsystem used by this command.
   * 
   */

  public ProductionTwoHigh(Drivetrain drivetrain, Intake intake, Solenoids solenoids, Shooter shooter) {
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
    SmartDashboard.putNumber("time", time.get());
    if(time.get()<1){
      Solenoids.intakeSolenoid.set(Value.kForward);
    }
    if(time.get()>1 && time.get()<2.2){
      Drivetrain.drivetrainMecanum.driveCartesian(0.48, 0, Limelights.horizontalAutoFront());
      Intake.intakeMotor.set(intakeMotorPower);
    }
    if(time.get()>2.2 && time.get()<5){
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
        Intake.intakeMotor.set(0);
      }
    } 
    if(time.get() > 5 && time.get() < 7){
      Limelights.backTable.getEntry("ledMode").setNumber(3);
      Drivetrain.drivetrainMecanum.driveCartesian(0, 0, Limelights.horizontalAutoBack());
      Shooter.SetShootersPID(autonTargetVelocity);
    }
    if(time.get() > 7 && time.get() < 8){
      Drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
      Shooter.SetShootersPID(autonTargetVelocity);
    }
    if(time.get() > 8 && time.get() < 10){
      Shooter.SetShootersPID(autonTargetVelocity);
      Conveyor.conveyorMotor.set(conveyorMotorPower);
    }
    if(time.get() > 10 && time.get() < 15){
      Conveyor.conveyorMotor.set(0);
      Shooter.SetShootersZero();
      Drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
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