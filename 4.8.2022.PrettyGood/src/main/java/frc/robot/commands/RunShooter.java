package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Shooter;
import static frc.robot.Constants.*;

/*import edu.wpi.first.wpilibj.smartdashboard.*;*/


public class RunShooter extends CommandBase {
  Shooter m_shooter;

  public RunShooter(Shooter shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = shooter;
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
    SmartDashboard.putBoolean("ShooterStatus", isShooterAtSpeed());
    if(RobotContainer.shootSlowTrigger()){
      m_shooter.SetShootersPID(targetShooterVelocitySlow);
    } else if(RobotContainer.shootHighTrigger.get()){
      m_shooter.SetShootersPID(targetShooterVelocityHigh);
    // } else if(RobotContainer.shootSafeWall()){
    //   m_shooter.SetShootersPID(835);
    } else{
      m_shooter.SetShootersZero();
      m_shooter.shooterLeftPID.reset();
      m_shooter.shooterRightPID.reset();
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

  private boolean isShooterAtSpeed(){
    if(RobotContainer.shootSlowTrigger() && (m_shooter.shooterRightEncoder.getVelocity() >= targetShooterVelocitySlow-50 && m_shooter.shooterLeftEncoder.getVelocity() >= targetShooterVelocitySlow-50)){
      return true;
    } else if(RobotContainer.shootHighTrigger.get() && (m_shooter.shooterRightEncoder.getVelocity() >= targetShooterVelocityHigh-50 && m_shooter.shooterLeftEncoder.getVelocity() >= targetShooterVelocityHigh-50)){
      return true;
    } else{
      return false;
    }
  }
}