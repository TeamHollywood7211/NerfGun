package frc.robot.commands.Auton2022.FourHighAutonComGroup;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelights;
import frc.robot.subsystems.Shooter;

/** An example command that uses an example subsystem. */
public class FourStep2 extends CommandBase {
    private final Drivetrain m_drivetrain;
    private final Conveyor m_conveyor;
    private final Limelights m_limelights;
    private final Shooter m_shooter;
    private Timer time;
    private boolean doneAiming;
    private boolean doneShooting;

    public FourStep2(Drivetrain drivetrain, Conveyor conveyor, Limelights limelights, Shooter shooter) {
        time = new Timer();
        m_shooter = shooter;
        m_conveyor = conveyor;
        m_drivetrain = drivetrain;
        m_limelights = limelights;
        addRequirements(drivetrain);
    }
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_drivetrain.drivetrainMecanum.driveCartesian(0, 0, -.08);
        if(m_limelights.backtx.getDouble(0) > 1|| m_limelights.backtx.getDouble(0) < -1){
            m_drivetrain.drivetrainMecanum.driveCartesian(0, 0, m_limelights.horizontalAutoBack());
            doneAiming = false;
        } else{
            m_drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
            m_limelights.backTable.getEntry("ledMode").setNumber(0);
            doneAiming = true;
        }
        if(doneAiming == true){
            time.start();
            if(time.get()<3){
                doneShooting = false;
                if(m_shooter.shooterLeftEncoder.getVelocity()>targetShooterVelocityHigh-13){
                    m_conveyor.conveyorMotor.set(conveyorMotorPower);
                }else if(m_shooter.shooterLeftEncoder.getVelocity()<targetShooterVelocityHigh-13){
                    m_conveyor.conveyorMotor.set(0);
                }
            }
            else{
                doneShooting = true;
                m_conveyor.conveyorMotor.set(0);
            }
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
    if(doneShooting == true){
        return true;
    }
    return false;
    }
}