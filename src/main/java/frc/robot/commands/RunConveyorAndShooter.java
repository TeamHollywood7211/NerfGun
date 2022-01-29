package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BreakBeams;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Shooter;
import static frc.robot.Constants.*;
import frc.robot.RobotContainer;

/** An example command that uses an example subsystem. */
public class RunConveyorAndShooter extends CommandBase {

  private final Conveyor m_conveyor;
  private final Shooter m_shooter;
  /**
   * Creates a new RunConveyor Command.
   *
   * @param conveyor The subsystem used by this command.
   * 
   */

  public RunConveyorAndShooter(Conveyor conveyor, Shooter shooter) {

    m_conveyor = conveyor;
    m_shooter = shooter;

    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(conveyor, shooter);

  }
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    while(BreakBeams.ammo>0 && RobotContainer.shootButton.get() ){ // Got bullets? Button mashed?
        Shooter.shooterLeftMotor.set(shooterMotorPower); //spin up shootermotors
        Shooter.shooterRightMotor.set(shooterMotorPower);
        
        if(BreakBeams.ammo == 2){
          while(BreakBeams.TopBeamOutput() == true){ //while there is a ball at the top
          Conveyor.conveyorMotor.set(conveyorMotorPower); //run conveyor until there isnt
          }
          BreakBeams.ammo--; // subtracts an ammo
        }
        else if(BreakBeams.ammo == 1){ 
          while(BreakBeams.TopBeamOutput() == false){ //if there isnt a ball at the top
            Conveyor.conveyorMotor.set(conveyorMotorPower);//make sure there is
          }
          while(BreakBeams.TopBeamOutput() == true){//once there is a ball at the top
            Conveyor.conveyorMotor.set(conveyorMotorPower);//shoot
          }
          BreakBeams.ammo--;//subtract an ammo after all of the logic-ing has happened
        }
      }
    Shooter.shooterLeftMotor.set(0);//duh
    Shooter.shooterRightMotor.set(0);//duh
    Conveyor.conveyorMotor.set(0);//duh

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