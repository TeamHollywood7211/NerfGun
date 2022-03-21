
package frc.robot.commands.Auton2022;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.DrivetrainCommand;
import frc.robot.subsystems.BreakBeams;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import static frc.robot.Constants.*;

// An example command that uses an example subsystem.
public class SimpleLinearAuton extends CommandBase {

  //private final Subsystem m_subsystem;
    private Timer time;
    public static NetworkTable frontTable = NetworkTableInstance.getDefault().getTable("limelight-front");
    public static NetworkTableEntry ta = frontTable.getEntry("ta");
    public static NetworkTableEntry tx = frontTable.getEntry("tx");
    public static NetworkTableEntry ty = frontTable.getEntry("ty");
    public static NetworkTableEntry tv = frontTable.getEntry("tv");
    /**
   * Creates a new RunConveyor Command.
   * @param shooter
   * @param intake
   * @param drivetrain The subsystem used by this command.
   * @param conveyor
   * 
   */

  public SimpleLinearAuton(Drivetrain drivetrain, Intake intake, Shooter shooter, Conveyor conveyor) {
    time = new Timer();
    //m_subsystem = subsystem;

    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(drivetrain, intake, shooter);

  }
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    time.reset();
    time.start();
    while(time.get() < 2.5){
        Shooter.shooterRightMotor.set(.15);
        Shooter.shooterLeftMotor.set(.15);
      if(Shooter.shooterLeftEncoder.getVelocity() >= targetShooterVelocitySlow-200 /*&& Shooter.shooterRightEncoder.getVelocity() >= targetShooterVelocitySlow*/){
        Conveyor.conveyorMotor.set(conveyorMotorPower);
      }
    }
    while(time.get()>2 && time.get()< 6){
        Shooter.SetShootersZero();
        Conveyor.conveyorMotor.set(0);
        Intake.intakeMotor.set(intakeMotorPower);
        Drivetrain.drivetrainMecanum.driveCartesian(.2, 0, 0);
    }
    while(time.get()>6 && time.get()< 8){
      Drivetrain.drivetrainMecanum.driveCartesian(0.2, 0, 0);
      Drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
      Intake.intakeMotor.set(0);
      while(BreakBeams.TopBeamOutput()==false){
      Conveyor.conveyorMotor.set(conveyorMotorPower);
      }
      Conveyor.conveyorMotor.set(0);
      Drivetrain.drivetrainMecanum.driveCartesian(0, 0, DrivetrainCommand.horizontalAuto());
    }
    while(time.get()>8 && time.get()< 10){
      Shooter.SetShootersHigh();
      Conveyor.conveyorMotor.set(conveyorMotorPower);
    }
    Drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
    BreakBeams.ammo = 0;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
    time.stop();
    time.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    Drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
    time.stop();
    time.reset();
    return false;
  }
}
