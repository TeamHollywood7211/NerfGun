package frc.robot.commands.MultiStepAuton;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.DrivetrainCommand;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Solenoids;

import static frc.robot.Constants.*;


/** An example command that uses an example subsystem. */
public class Step3 extends CommandBase {

  //private final Subsystem m_subsystem;
    private Timer time;

  /**
   * Creates a new RunConveyor Command.
   *
   * @param drivetrain The subsystem used by this command.
   * 
   */

  public Step3(Shooter shooter, Conveyor conveyor, Drivetrain drivetrain) {
    time = new Timer();
    //m_subsystem = subsystem;

    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(shooter, conveyor, drivetrain);

  }
  @Override
  public void initialize() {
    time.reset();    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    time.start();
    while(time.get() < 1){
        DrivetrainCommand.backTable.getEntry("ledMode").setNumber(3);
        Drivetrain.drivetrainMecanum.driveCartesian(0, 0, DrivetrainCommand.horizontalAuto());
    }
    while(time.get() > 1 && time.get() < 5){
        DrivetrainCommand.backTable.getEntry("ledMode").setNumber(0);
        Shooter.SetShootersHigh();
        Intake.intakeMotor.set(0);
    }
    while(time.get() > 5 && time.get() < 6.5){
        Conveyor.conveyorMotor.set(conveyorMotorPower);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(time.get() > 6.5){
        return true;
    } else{
    return false;
    }
  }
}