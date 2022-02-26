package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;


public class BreakBeams extends SubsystemBase {
  public static DigitalInput HopperBeam;
  public static DigitalInput MiddleBeam;
  public static DigitalInput TopBeam;

  public static int ammo = 0;

  // Creates a new BreakBeam Subsystem.
  public BreakBeams(){
    HopperBeam = new DigitalInput(HopperBeamID);
    MiddleBeam = new DigitalInput(MiddleBeamID);
    TopBeam = new DigitalInput(TopBeamID);
  }

  /*Beam output functions inverse the value that we get from lasers to reduce confusion later.
  When the beams detect a ball, they return a value of false, this is confusing so we did this.*/
  public static boolean HopperBeamOutput(){
    boolean ballDetected;
    if(HopperBeam.get() == false){
      ballDetected = true;
    } else{
      ballDetected = false;
    }
    return ballDetected;
  }

  public static boolean MiddleBeamOutput(){
    boolean ballDetected;
    if(MiddleBeam.get() == false){
      ballDetected = true;
    } else{
      ballDetected = false;
    }
    return ballDetected;
  }

  public static boolean TopBeamOutput(){
    boolean ballDetected;
    if(TopBeam.get() == false){
      ballDetected = true;
    } else{
      ballDetected = false;
    }
    return ballDetected;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Ammo", ammo);
  }
}