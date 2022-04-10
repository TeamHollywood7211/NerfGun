package frc.robot.commands.Auton2022.FourHighAutonComGroup;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GyroAccelerometer;

/** An example command that uses an example subsystem. */
public class FourStep3 extends CommandBase {
    private final Drivetrain m_drivetrain;
    private final GyroAccelerometer m_gyroAccel;
    double setAngle = 163;
    private Timer time;

    public FourStep3(Drivetrain drivetrain, GyroAccelerometer gyroAccel) {
        time = new Timer();
        m_gyroAccel = gyroAccel;
        m_drivetrain = drivetrain;
        addRequirements(drivetrain);
    }
    @Override
    public void initialize() {
        time.stop();
        time.reset();
        m_drivetrain.resetDrivetrainEncoders();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        time.start();
        m_drivetrain.drivetrainMecanum.driveCartesian(0, 0, m_gyroAccel.turnToAngle(setAngle));
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
    if(m_gyroAccel.ahrs.getAngle()>setAngle-1.7 && m_gyroAccel.ahrs.getAngle()<setAngle+1.7 && time.get() > 0.5){
        return true;
    }
    return false;
    }
}