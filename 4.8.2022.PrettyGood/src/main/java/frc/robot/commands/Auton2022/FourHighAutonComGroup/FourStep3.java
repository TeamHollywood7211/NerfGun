package frc.robot.commands.Auton2022.FourHighAutonComGroup;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GyroAccelerometer;

/** An example command that uses an example subsystem. */
public class FourStep3 extends CommandBase {
    private final Drivetrain m_drivetrain;
    private final GyroAccelerometer m_gyroAccel;
    double setpoint;

    public FourStep3(Drivetrain drivetrain, GyroAccelerometer gyroAccel) {
        m_gyroAccel = gyroAccel;
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
        System.out.print("theres too many mf snakes on this mf plane");
        m_drivetrain.drivetrainMecanum.driveCartesian(0, 0, m_gyroAccel.turnToAngle(170));
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
    if(m_gyroAccel.ahrs.getAngle()>173 && m_gyroAccel.ahrs.getAngle()<177){
        return true;
    }
    return false;
    }
}