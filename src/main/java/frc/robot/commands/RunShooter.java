package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
/*import frc.robot.RobotContainer;*/
import frc.robot.subsystems.Shooter;
import static frc.robot.Constants.*;

/*import edu.wpi.first.wpilibj.smartdashboard.*;*/


public class RunShooter extends CommandBase {

    private final Shooter m_shooter;

    /**
   * Creates a new RunShooter.
   */

  public RunShooter(Shooter shooter) {

    m_shooter = shooter;

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
    if(RobotContainer.shootButton.get()){
      Shooter.shooterLeftMotor.set(shooterMotorPower);
      Shooter.shooterRightMotor.set(shooterMotorPower);
    } else{
      Shooter.shooterLeftMotor.set(0);
      Shooter.shooterRightMotor.set(0);
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