package frc.robot.subsystems;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class Limelights extends SubsystemBase {
    public static NetworkTable frontTable = NetworkTableInstance.getDefault().getTable("limelight-front");
    public static NetworkTableEntry frontta = frontTable.getEntry("ta");;
    public static NetworkTableEntry fronttx = frontTable.getEntry("tx");;
    public static NetworkTableEntry frontty = frontTable.getEntry("ty");;
    public static NetworkTableEntry fronttv = frontTable.getEntry("tv");;

    public static NetworkTable backTable = NetworkTableInstance.getDefault().getTable("limelight-back");
    public static NetworkTableEntry ledMode = backTable.getEntry("ledMode");
    public static NetworkTableEntry backta = backTable.getEntry("ta");
    public static NetworkTableEntry backtx = backTable.getEntry("tx");
    public static NetworkTableEntry backty = backTable.getEntry("ty");
    public static NetworkTableEntry backtv = backTable.getEntry("tv");

  public Limelights() {
  }


  @Override

  public void periodic() {
    // This method will be called once per scheduler run

  }

  public static double horizontalAutoBack(){
    double Kp = 0.0225;//.0225 og set // Proportional control constant
    double x = backtx.getDouble(0.0); //this is the error from target horizontally, default value is in the parenthesis
    double v = backtv.getDouble(0); // this is whether or not there is a target seen.
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

  public static double horizontalAutoFront(){
    double Kp = 0.018;//.0225 og set // Proportional control constant
    double x = fronttx.getDouble(0.0); //this is the error from target horizontally, default value is in the parenthesis
    double v = fronttv.getDouble(0); // this is whether or not there is a target seen.
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
}