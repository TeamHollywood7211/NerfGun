package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GyroAccelerometer;

import static frc.robot.Constants.*;

/** An example command that uses an example subsystem. */
public class DrivetrainCommand extends CommandBase {
    public static NetworkTable frontTable = NetworkTableInstance.getDefault().getTable("limelight-front");
    public static NetworkTable backTable = NetworkTableInstance.getDefault().getTable("limelight-back");
    public static NetworkTableEntry ledMode = backTable.getEntry("ledMode");
    public static NetworkTableEntry ta = backTable.getEntry("ta");
    public static NetworkTableEntry tx = backTable.getEntry("tx");
    public static NetworkTableEntry ty = backTable.getEntry("ty");
    public static NetworkTableEntry tv = backTable.getEntry("tv");

  /**
   * Creates a new RunConveyor Command.
   *
   * @param drivetrain The subsystem used by this command.
   * 
   */

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

public static double horizontalAuto(){
    double Kp = 0.023;//.0225 og set // Proportional control constant
    double x = tx.getDouble(0.0); //this is the error from target horizontally, default value is in the parenthesis
    double v = tv.getDouble(0); // this is whether or not there is a target seen.
    double chassisAdjust = 0;
    double targetX = 0;//ideal target distance from crosshair to target horizontally

    // If A is held down, run a PID loop to center the turret.
    if(v == 1){
        if (x > targetX || x < targetX) {
            chassisAdjust = Kp * x;
        } else{
            chassisAdjust = 0;
        }
    }
    return chassisAdjust;
}

  public DrivetrainCommand(Drivetrain drivetrain, GyroAccelerometer gyroAccel) {

    //m_subsystem = subsystem;

    // Use addRequirements() here to declare subsystem dependencies.

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
        Drivetrain.drivetrainMecanum.driveCartesian(-returnLeftAxis(1), returnLeftAxis(0), horizontalAuto(), GyroAccelerometer.ahrs.getAngle());
        backTable.getEntry("ledMode").setNumber(3);
    }
    else{//drive carte first input is negative so we can change what direction push goes
        Drivetrain.drivetrainMecanum.driveCartesian(-returnLeftAxis(1), returnLeftAxis(0), returnRightAxis(0), GyroAccelerometer.ahrs.getAngle());
        backTable.getEntry("ledMode").setNumber(0);
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
}