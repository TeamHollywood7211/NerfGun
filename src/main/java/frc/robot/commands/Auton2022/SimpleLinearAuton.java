package frc.robot.commands.Auton2022;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BreakBeams;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.MecanumDrivetrain;
import frc.robot.subsystems.Shooter;
import static frc.robot.Constants.*;

/** An example command that uses an example subsystem. */
public class SimpleLinearAuton extends CommandBase {

  //private final Subsystem m_subsystem;
    private Timer time;

  /**
   * Creates a new RunConveyor Command.
   * @param shooter
   * @param intake
   * @param mecanumdrivetrain The subsystem used by this command.
   * @param conveyor
   * 
   */

  public SimpleLinearAuton(MecanumDrivetrain mecanumdrivetrain, Intake intake, Shooter shooter, Conveyor conveyor) {
    time = new Timer();
    //m_subsystem = subsystem;

    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(mecanumdrivetrain, intake, shooter);

  }
  @Override
  public void initialize() {
    time.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    while(time.get() < 2){
        Shooter.shooterRightMotor.set(shooterMotorPowerSlow);
        Shooter.shooterLeftMotor.set(shooterMotorPowerSlow);
      if(Shooter.shooterLeftEncoder.getVelocity() >= targetShooterVelocitySlow && Shooter.shooterRightEncoder.getVelocity() >= targetShooterVelocitySlow){
        Conveyor.conveyorMotor.set(conveyorMotorPower);
      }
    }
    while(time.get()>2 && time.get()< 6){
        MecanumDrivetrain.driveCarte(.25, 0, 0);
    }
    /*future eighdin, or any other programming monkey, 
    please put code here to aim for roughly 2 seconds and then spin up shooters 
    and then run conveyor and then turn the shooters off.*/
    BreakBeams.ammo = 0;
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