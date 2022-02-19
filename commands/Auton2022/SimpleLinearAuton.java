
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
    time.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    while(time.get() < 2){
        Shooter.shooterRightMotor.set(shooterMotorPowerSlow);
        Shooter.shooterLeftMotor.set(shooterMotorPowerSlow);
      if(Shooter.shooterLeftEncoder.getVelocity() >= targetShooterVelocitySlow && Shooter.shooterRightEncoder.getVelocity() >= targetShooterVelocitySlow){
        Conveyor.conveyorMotor.set(conveyorMotorPower);
      }
    }
    while(time.get()>2 && time.get()< 6){
        Intake.intakeMotor.set(intakeMotorPower);
        Drivetrain.drivetrainMecanum.driveCartesian(.25, 0, 0);
    }
    while(time.get()>6 && time.get()< 10){
      Drivetrain.drivetrainMecanum.driveCartesian(0, 0, DrivetrainCommand.horizontalAuto());
    }
    while(time.get()>10 && time.get()< 12){
      Conveyor.conveyorMotor.set(conveyorMotorPower);
    }
    BreakBeams.ammo = 0;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
