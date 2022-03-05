package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.kauailabs.navx.frc.AHRS;

//accelerometer imports
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroAccelerometer extends SubsystemBase {

  public static AHRS ahrs;
  public static BuiltInAccelerometer accel;


  public GyroAccelerometer() {
    //gyro and accelerometer definition
    ahrs = new AHRS(SPI.Port.kMXP);
    accel = new BuiltInAccelerometer();
  }


  @Override

  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Gyro Info", ahrs.getAngle());
    /*if(RobotContainer.calibrateButton.get()){
      ahrs.calibrate();
    }*/
  }

}