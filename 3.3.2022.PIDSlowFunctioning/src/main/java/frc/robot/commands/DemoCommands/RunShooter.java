package frc.robot.commands.DemoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Shooter;
import static frc.robot.Constants.*;

/*import edu.wpi.first.wpilibj.smartdashboard.*;*/


public class RunShooter extends CommandBase {

  
    /**
   * 
   * Creates a new RunShooter.
   */

  public RunShooter(Shooter shooter) {

    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(shooter);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      //runs once before everything else
  }

  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotContainer.shootSlowTrigger()){
      //Shooter.SetShootersSlow();
      Shooter.SetShootersSlowPID();
    } else if(RobotContainer.shootHighTrigger.get()){
      //Shooter.SetShootersHigh();
      Shooter.shooterLeftMotor.set(Shooter.shooterLeftPIDSlow.calculate(Shooter.shooterLeftEncoder.getVelocity(), targetShooterVelocitySlow));
    } else{
      Shooter.SetShootersZero();
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