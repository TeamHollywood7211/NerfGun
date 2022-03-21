package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;
import static frc.robot.Constants.*;

/** An example command that uses an example subsystem. */
public class DrivetrainCommand extends CommandBase {

    public static NetworkTable backTable = NetworkTableInstance.getDefault().getTable("limelight-back");
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
    double Kp = 0.0225; // Proportional control constant
    double x = tx.getDouble(0.0);
    double v = tv.getDouble(0);
    double horizontalError = x;
    double chassisAdjust = 0;
    double targetX = 0;

    // If A is held down, run a PID loop to center the turret.
    if(v == 1){
        if (x > targetX) {
            chassisAdjust = Kp * horizontalError;
        } 
        else if (x < targetX) {
            chassisAdjust = Kp * horizontalError;
        }
    }
    return chassisAdjust;
}

  public DrivetrainCommand(Drivetrain drivetrain) {

    //m_subsystem = subsystem;

    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(drivetrain);

  }
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotContainer.autoAimButton.get()){
            
        Drivetrain.drivetrainMecanum.driveCartesian(0, 0, horizontalAuto());
    }
    else{//drive carte first input is negative so we can change what direction push goes
        Drivetrain.drivetrainMecanum.driveCartesian(-returnLeftAxis(1), returnLeftAxis(0), returnRightAxis(0));
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