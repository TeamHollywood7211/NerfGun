package frc.robot.commands.Auton2022.FourHighAutonComGroup;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelights;

/** An example command that uses an example subsystem. */
public class FourStep5 extends CommandBase {
    private final Drivetrain m_drivetrain;
    private final Limelights m_limelights;
    private final Conveyor m_conveyor;

    public FourStep5(Drivetrain drivetrain, Limelights limelights, Conveyor conveyor) {
        m_conveyor = conveyor;
        m_limelights = limelights;
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

        m_limelights.backTable.getEntry("ledMode").setNumber(3);
        m_drivetrain.setPositionDrivetrain(-75);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
    if(m_drivetrain.getFrontMotorPositions()<-73){
        return true;
    }
    return false;
    }
}