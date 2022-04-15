package frc.robot.commands.Auton2022.FourHighAutonComGroup;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BreakBeams;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import static frc.robot.Constants.*;

/** An example command that uses an example subsystem. */
public class FourStep4 extends CommandBase {
    private final Drivetrain m_drivetrain;
    private final BreakBeams m_breakBeams;
    private final Conveyor m_conveyor;
    private final Timer time;

    public FourStep4(Drivetrain drivetrain, BreakBeams breakBeams, Conveyor conveyor) {
        time = new Timer();
        m_conveyor = conveyor;
        m_breakBeams = breakBeams;
        m_drivetrain = drivetrain;
        addRequirements(drivetrain);
    }
    @Override
    public void initialize() {
        m_drivetrain.resetDrivetrainEncoders();
        time.reset();
        time.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_drivetrain.setPositionDrivetrain(80);
        if(m_breakBeams.HopperBeamOutput() == true){
            if(m_breakBeams.MiddleBeamOutput() == false){ //if there isnt a ball in the middle or at the top then
                if(m_breakBeams.MiddleBeamOutput() != true){ //run conveyor until there is a ball in the middle
                    m_conveyor.conveyorMotor.set(conveyorMotorPower);
                }
            }
            else if(m_breakBeams.MiddleBeamOutput() == true){ //if there is a ball in the middle or at the top, then
                if(m_breakBeams.MiddleBeamOutput() != false){ //run the conveyor until there is a ball at the top
                    m_conveyor.conveyorMotor.set(conveyorMotorPower);
                }
            }
        } else{
            m_conveyor.conveyorMotor.set(0);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
    if(m_drivetrain.getFrontMotorPositions() > 78 && time.get() > 4){
        return true;
    }
    return false;
    }
}