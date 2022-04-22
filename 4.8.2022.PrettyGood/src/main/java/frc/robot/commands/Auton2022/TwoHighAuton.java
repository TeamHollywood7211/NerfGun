package frc.robot.commands.Auton2022;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BreakBeams;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelights;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Solenoids;

import static frc.robot.Constants.*;

/** An example command that uses an example subsystem. */
public class TwoHighAuton extends CommandBase {

  Drivetrain m_drivetrain;
  Intake m_intake;
  Solenoids m_solenoids;
  Shooter m_shooter;
  Limelights m_limeLights;
  Conveyor m_conveyor;
  BreakBeams m_breakBeams;

  public Timer time;
  private int autonTargetVelocity = 780;

  public TwoHighAuton(Drivetrain drivetrain, Intake intake, Solenoids solenoids, Shooter shooter, Limelights limeLights, Conveyor conveyor, BreakBeams breakBeams) {
    time = new Timer();

    m_drivetrain = drivetrain;
    m_intake = intake;
    m_solenoids = solenoids;
    m_shooter = shooter;
    m_limeLights = limeLights;
    m_conveyor = conveyor;
    m_breakBeams = breakBeams;

    addRequirements(drivetrain, intake, solenoids, shooter);

  }
  @Override
  public void initialize() {
    time.reset();    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    time.start();
    SmartDashboard.putNumber("time", time.get());
    if(time.get()<1){
      m_solenoids.intakeSolenoid.set(Value.kForward);
    }
    if(time.get()>1 && time.get()<2.2){
      m_drivetrain.drivetrainMecanum.driveCartesian(0.48, 0, 0);
      m_intake.intakeMotor.set(intakeMotorPower);
    }
    if(time.get()>2.2 && time.get()<5){
      m_drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
      // if(m_breakBeams.HopperBeamOutput() == true){
      //   if(m_breakBeams.MiddleBeamOutput() == false){ //if there isnt a ball in the middle or at the top then
      //     if(m_breakBeams.MiddleBeamOutput() != true){ //run conveyor until there is a ball in the middle
      //       m_conveyor.conveyorMotor.set(conveyorMotorPower);
      //     }
      //   }
      //   // else if(m_breakBeams.MiddleBeamOutput() == true){ //if there is a ball in the middle or at the top, then
      //   //   if(m_breakBeams.MiddleBeamOutput()!=false){ //run the conveyor until there is a ball at the top
      //   //     m_conveyor.conveyorMotor.set(conveyorMotorPower);
      //   //   }
      //   // }
      //   else{
      //     m_conveyor.conveyorMotor.set(0);//duh
      //   }
      // }
    } 
    if(time.get() > 5 && time.get() < 7){
      m_limeLights.backTable.getEntry("ledMode").setNumber(3);
      m_drivetrain.drivetrainMecanum.driveCartesian(0, 0, m_limeLights.horizontalAutoBack());
      m_shooter.SetShootersPID(autonTargetVelocity);
    }
    if(time.get() > 7 && time.get() < 8){
      m_drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
      m_shooter.SetShootersPID(autonTargetVelocity);
    }
    if(time.get() > 8 && time.get() < 10){
      m_shooter.SetShootersPID(autonTargetVelocity);
      m_conveyor.conveyorMotor.set(conveyorMotorPower);
    }
    if(time.get() > 10 && time.get() < 15){
      m_conveyor.conveyorMotor.set(0);
      m_intake.intakeMotor.set(0);
      m_shooter.SetShootersZero();
      m_drivetrain.drivetrainMecanum.driveCartesian(0, 0, 0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // time.stop();
    // time.reset();
    return false;
  }
}