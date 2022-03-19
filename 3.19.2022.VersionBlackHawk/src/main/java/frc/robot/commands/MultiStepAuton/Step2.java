package frc.robot.commands.MultiStepAuton;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.DrivetrainCommand;
import frc.robot.subsystems.BreakBeams;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Solenoids;

import static frc.robot.Constants.*;


/** An example command that uses an example subsystem. */
public class Step2 extends CommandBase {

  //private final Subsystem m_subsystem;
    private Timer time;
    public static NetworkTableEntry ta = DrivetrainCommand.frontTable.getEntry("ta");
    public static NetworkTableEntry tx = DrivetrainCommand.frontTable.getEntry("tx");
    public static NetworkTableEntry ty = DrivetrainCommand.frontTable.getEntry("ty");
    public static NetworkTableEntry tv = DrivetrainCommand.frontTable.getEntry("tv");
  /**
   * Creates a new RunConveyor Command.
   *
   * @param drivetrain The subsystem used by this command.
   * 
   */

  public Step2(Drivetrain drivetrain, Intake intake, Conveyor conveyor) {
    time = new Timer();
    //m_subsystem = subsystem;

    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(drivetrain, intake, conveyor);

  }
  @Override
  public void initialize() {
    System.out.print("step2Init");
    time.reset();    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
public void execute() {
    System.out.print("step2");
    time.start();
    if(time.get() < 1.2){
        Drivetrain.drivetrainMecanum.driveCartesian(0.50, 0, horizontalAuto());
        Intake.intakeMotor.set(intakeMotorPower);
    }
    if(time.get() > 1.2 && time.get() < 3){
        Drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
        if(BreakBeams.HopperBeamOutput() == true){
            if(BreakBeams.MiddleBeamOutput() == false && BreakBeams.TopBeamOutput() == false){ //if there isnt a ball in the middle or at the top then
                while(BreakBeams.MiddleBeamOutput() == false){ //run conveyor until there is a ball in the middle
                Conveyor.conveyorMotor.set(conveyorMotorPower);
                }
            }
            else if(BreakBeams.MiddleBeamOutput() == true && BreakBeams.TopBeamOutput() == false){ //if there is a ball in the middle or at the top, then
                while(BreakBeams.TopBeamOutput()==false){ //run the conveyor until there is a ball at the top
                Conveyor.conveyorMotor.set(conveyorMotorPower);
                }
            }
        Conveyor.conveyorMotor.set(0);//duh
        //BreakBeams.ammo++; //increments the ammo by one
        }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(time.get()>3){
        return true;
    } else{
        return false;
    }
  }

  private double horizontalAuto(){
    double Kp = 0.02;//.0225 og set // Proportional control constant
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
}