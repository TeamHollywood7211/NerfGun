package frc.robot.subsystems;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class Limelights extends SubsystemBase {
  public NetworkTable frontTable;
  public NetworkTableEntry frontta;
  public NetworkTableEntry fronttx;
  public NetworkTableEntry frontty;
  public NetworkTableEntry fronttv;

  public NetworkTable backTable;
  public NetworkTableEntry ledMode;
  public NetworkTableEntry backta;
  public NetworkTableEntry backtx;
  public NetworkTableEntry backty;
  public NetworkTableEntry backtv;

  private PIDController backController;
  double backP = 0.02;
  double backI = 0;
  double backD = 0;


  public Limelights() {
    backController = new PIDController(backP, backI, backD);
    frontTable = NetworkTableInstance.getDefault().getTable("limelight-front");
    frontta = frontTable.getEntry("ta");;
    fronttx = frontTable.getEntry("tx");;
    frontty = frontTable.getEntry("ty");;
    fronttv = frontTable.getEntry("tv");;
  
    backTable = NetworkTableInstance.getDefault().getTable("limelight-back");
    ledMode = backTable.getEntry("ledMode");
    backta = backTable.getEntry("ta");
    backtx = backTable.getEntry("tx");
    backty = backTable.getEntry("ty");
    backtv = backTable.getEntry("tv");
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