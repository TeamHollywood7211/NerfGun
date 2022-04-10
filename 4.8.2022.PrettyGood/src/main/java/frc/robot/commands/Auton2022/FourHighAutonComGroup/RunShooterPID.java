package frc.robot.commands.Auton2022.FourHighAutonComGroup;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import static frc.robot.Constants.*;


/** An example command that uses an example subsystem. */
public class RunShooterPID extends CommandBase {
    private final Shooter m_shooter;
    private final Timer time;

    public RunShooterPID(Shooter shooter) {
        time = new Timer();
        m_shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        time.reset();
        time.start();
    }

  // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if(time.get()<6){
            m_shooter.SetShootersPID(targetShooterVelocityHigh);
        } else if(time.get()>6){
            m_shooter.SetShootersZero();
        }
    }

  // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_shooter.SetShootersZero();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}