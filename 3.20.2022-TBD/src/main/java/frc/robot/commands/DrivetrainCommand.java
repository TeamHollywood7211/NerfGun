package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GyroAccelerometer;
import frc.robot.subsystems.Limelights;

import static frc.robot.Constants.*;

/** An example command that uses an example subsystem. */
public class DrivetrainCommand extends CommandBase {


  public DrivetrainCommand(Drivetrain drivetrain, GyroAccelerometer gyroAccel) {
    addRequirements(drivetrain, gyroAccel);
  }
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotContainer.calibrateButton.get()){
        GyroAccelerometer.ahrs.reset();
    }

    if(RobotContainer.autoAimButton.get()){
        Drivetrain.drivetrainMecanum.driveCartesian(-returnLeftAxis(1), returnLeftAxis(0), Limelights.horizontalAutoBack(), GyroAccelerometer.ahrs.getAngle());
        Limelights.backTable.getEntry("ledMode").setNumber(3);
    }
    else{//drive carte first input is negative so we can change what direction push goes
        Drivetrain.drivetrainMecanum.driveCartesian(-returnLeftAxis(1), returnLeftAxis(0), returnRightAxis(0), GyroAccelerometer.ahrs.getAngle());
        Limelights.backTable.getEntry("ledMode").setNumber(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      Drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
public static double returnLeftAxis(int leftAxis){
    double leftStick = RobotContainer.leftJoystick.getRawAxis(leftAxis);
        
    if(leftStick > deadzone || leftStick < -deadzone){
        return leftStick * powerRegulator;
    }
    else{
        return 0;
    }
}
public static double returnRightAxis(int rightAxis){
    double rightStick = RobotContainer.rightJoystick.getRawAxis(rightAxis);
    if(rightStick > deadzone || rightStick < -deadzone){
        return rightStick * powerRegulator;
   }
   else{
        return 0;
   }
  
}
}