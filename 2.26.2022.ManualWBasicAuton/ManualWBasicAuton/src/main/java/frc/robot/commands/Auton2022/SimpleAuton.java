package frc.robot.commands.Auton2022;

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
public class BackUpBallAuton extends CommandBase {

  //private final Subsystem m_subsystem;
    private Timer time;
  /**
   * Creates a new RunConveyor Command.
   *
   * @param drivetrain The subsystem used by this command.
   * 
   */

  public BackUpBallAuton(Drivetrain drivetrain, Intake intake, Shooter shooter, Solenoids solenoids) {
    time = new Timer();
    //m_subsystem = subsystem;

    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(drivetrain, intake, shooter, solenoids);

  }
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    time.start();
      while(time.get()<3){
        Shooter.SetShootersSlow();
        Solenoids.intakeSolenoid.set(Value.kForward);
        Intake.intakeMotor.set(intakeMotorPower);
      }
      while(time.get()<5 && time.get() > 3){
        Conveyor.conveyorMotor.set(conveyorMotorPower);
      }
      while(time.get()>5 && time.get()<8){
        Drivetrain.drivetrainMecanum.driveCartesian(0.3, 0, 0);
      }
      while(time.get()>8 && time.get()<15){
        Drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
        Shooter.SetShootersZero();
        Conveyor.conveyorMotor.set(0);
        Intake.intakeMotor.set(0);
      }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    time.stop();
    time.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    time.stop();
    time.reset();
    return false;
  }
}