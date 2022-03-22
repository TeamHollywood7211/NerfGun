package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GyroAccelerometer;
import frc.robot.subsystems.Limelights;

import static frc.robot.Constants.*;

/** An example command that uses an example subsystem. */
public class DrivetrainCommand extends CommandBase {
  GyroAccelerometer m_gyroAccel;
  Drivetrain m_drivetrain;
  Limelights m_limeLights;

  public DrivetrainCommand(Drivetrain drivetrain, GyroAccelerometer gyroAccel, Limelights limeLights) {
    m_gyroAccel = gyroAccel;
    m_drivetrain = drivetrain;
    m_limeLights = limeLights;
    addRequirements(m_drivetrain, m_gyroAccel, m_limeLights);
  }
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotContainer.calibrateButton.get()){
        m_gyroAccel.ahrs.reset();
    }
    if(RobotContainer.autoAimButton.get()){
        m_drivetrain.drivetrainMecanum.driveCartesian(-RobotContainer.returnLeftAxis(1), RobotContainer.returnLeftAxis(0), m_limeLights.horizontalAutoBack(), m_gyroAccel.ahrs.getAngle());
        m_limeLights.backTable.getEntry("ledMode").setNumber(3);
    }
    else{//drive carte first input is negative so we can change what direction push goes
        m_drivetrain.drivetrainMecanum.driveCartesian(-RobotContainer.returnLeftAxis(1), RobotContainer.returnLeftAxis(0), RobotContainer.returnRightAxis(0), m_gyroAccel.ahrs.getAngle());
        m_limeLights.backTable.getEntry("ledMode").setNumber(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      m_drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}