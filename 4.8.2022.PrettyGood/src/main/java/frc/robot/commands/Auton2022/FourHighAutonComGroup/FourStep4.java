package frc.robot.commands.Auton2022.FourHighAutonComGroup;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

/** An example command that uses an example subsystem. */
public class FourStep4 extends CommandBase {
    private final Drivetrain m_drivetrain;

    public FourStep4(Drivetrain drivetrain) {
        m_drivetrain = drivetrain;
        addRequirements(drivetrain);
    }
    @Override
    public void initialize() {
        m_drivetrain.resetDrivetrainEncoders();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_drivetrain.setPositionDrivetrain(84);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
    if(m_drivetrain.getFrontMotorPositions()>82){
        return true;
    }
    return false;
    }
}