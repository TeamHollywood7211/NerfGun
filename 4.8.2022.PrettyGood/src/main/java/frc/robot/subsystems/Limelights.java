package frc.robot.subsystems;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class Limelights extends SubsystemBase {
    public NetworkTable frontTable = NetworkTableInstance.getDefault().getTable("limelight-front");
    public NetworkTableEntry frontta = frontTable.getEntry("ta");;
    public NetworkTableEntry fronttx = frontTable.getEntry("tx");;
    public NetworkTableEntry frontty = frontTable.getEntry("ty");;
    public NetworkTableEntry fronttv = frontTable.getEntry("tv");;

    public NetworkTable backTable = NetworkTableInstance.getDefault().getTable("limelight-back");
    public NetworkTableEntry ledMode = backTable.getEntry("ledMode");
    public NetworkTableEntry backta = backTable.getEntry("ta");
    public NetworkTableEntry backtx = backTable.getEntry("tx");
    public NetworkTableEntry backty = backTable.getEntry("ty");
    public NetworkTableEntry backtv = backTable.getEntry("tv");

  public Limelights() {
  }


  @Override

  public void periodic() {
    // This method will be called once per scheduler run

  }

  public double horizontalAutoBack(){
    double Kp = 0.023;//.0225 og set // Proportional control constant
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

  public double horizontalAutoFront(){
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