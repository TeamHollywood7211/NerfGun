package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.controller.PIDController;
//accelerometer imports
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroAccelerometer extends SubsystemBase {

  public AHRS ahrs;
  public BuiltInAccelerometer accel;
  public PIDController navController;

  double kP = 0.014;
  double kI;
  double kD;
  double kF;

  double kToleranceDegrees = 1.0f;

  public GyroAccelerometer() {
    //gyro and accelerometer definition
    ahrs = new AHRS(SPI.Port.kMXP);
    accel = new BuiltInAccelerometer();
    navController = new PIDController(kP, kI, kD);
  }

  public double turnToAngle(double angle){
    return navController.calculate(ahrs.getAngle(), angle);
  }
  @Override

  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Gyro Info", ahrs.getAngle());
  }

}