package frc.robot.commands.Auton2022.FourHighAutonComGroup;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import static frc.robot.Constants.*;


/** An example command that uses an example subsystem. */
public class RunShooterPID extends CommandBase {
    private final Shooter m_shooter;
    private final Intake m_intake;
    private final Timer time;

    public RunShooterPID(Shooter shooter, Intake intake) {
        time = new Timer();
        m_intake = intake;
        m_shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        time.stop();
        time.reset();
        time.start();
    }

  // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if(time.get() > .5){
            m_intake.intakeMotor.set(intakeMotorPower);
        }
        if(time.get() < 5){
            m_shooter.SetShootersPID(targetShooterVelocityHigh);
        } else if(time.get() > 5 && time.get() < 8){
            m_shooter.SetShootersZero();
            m_shooter.shooterLeftPID.reset();
            m_shooter.shooterRightPID.reset();
        } else if(time.get() > 9.5){
            m_shooter.SetShootersPID(targetShooterVelocityHigh+8);
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