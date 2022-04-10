package frc.robot.commands.Auton2022.FourHighAutonComGroup;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelights;
import static frc.robot.Constants.*;


/** An example command that uses an example subsystem. */
public class FourStep5 extends CommandBase {
    private final Drivetrain m_drivetrain;
    private final Limelights m_limelights;
    private final Intake m_intake;
    private final Conveyor m_conveyor;
    private Timer time;

    public FourStep5(Drivetrain drivetrain, Limelights limelights, Intake intake, Conveyor conveyor) {
        m_drivetrain = drivetrain;
        m_limelights = limelights;
        m_intake = intake;
        m_conveyor = conveyor;
        time = new Timer();
        addRequirements(drivetrain);
    }
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
    if(m_drivetrain.getFrontMotorPositions()>24){
        return true;
    }
    return false;
    }
}