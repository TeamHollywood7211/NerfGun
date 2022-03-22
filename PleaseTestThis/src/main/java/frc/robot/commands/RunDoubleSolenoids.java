package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Solenoids;


/** Command that uses solenoid subsystem. */
public class RunDoubleSolenoids extends CommandBase {

  //private final Subsystem m_subsystem;
  boolean toggleIntake = true;
  boolean solenoidStateIntake = false;
  boolean toggleClimb = true;
  boolean solenoidStateClimb = false;

  Solenoids m_solenoids;

  public RunDoubleSolenoids(Solenoids solenoids) {
    m_solenoids = solenoids;

    addRequirements(solenoids);
  }
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  SmartDashboard.putBoolean("intakebuttonpressed", RobotContainer.solenoidIntakeButton.get());
  if(toggleIntake && RobotContainer.solenoidIntakeButton.get()){
    toggleIntake = false;
    if(solenoidStateIntake){
      solenoidStateIntake = false;
      m_solenoids.intakeSolenoid.set(Value.kReverse);
    } else{
      solenoidStateIntake = true;
      m_solenoids.intakeSolenoid.set(Value.kForward);
    }
  } else if(RobotContainer.solenoidIntakeButton.get() == false){
    toggleIntake = true;
  }

  SmartDashboard.putBoolean("climbsolenoid", (RobotContainer.solenoidClimbButton.get() || RobotContainer.solenoidClimbButtonOp.get()));
    if(toggleClimb && (RobotContainer.solenoidClimbButton.get() || RobotContainer.solenoidClimbButtonOp.get())){
        toggleClimb = false;
        if(solenoidStateClimb){
          solenoidStateClimb = false;
          m_solenoids.climberSolenoid2.set(Value.kReverse);
        } else{
          solenoidStateClimb = true;
          m_solenoids.climberSolenoid2.set(Value.kForward);
        }
      } else if(RobotContainer.solenoidClimbButton.get() == false && RobotContainer.solenoidClimbButtonOp.get() == false){
        toggleClimb = true;
      }
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