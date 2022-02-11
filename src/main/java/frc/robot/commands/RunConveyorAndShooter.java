package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BreakBeams;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Shooter;
import static frc.robot.Constants.*;

import frc.robot.RobotContainer;

/** An example command that uses an example subsystem. */
public class RunConveyorAndShooter extends CommandBase {

  /**
   * Creates a new RunConveyor Command.
   *
   * The subsystem used by this command.
   * 
   */

  public RunConveyorAndShooter(Conveyor conveyor, Shooter shooter) {

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
        Shooter.SetShootersNormal();
        while(Shooter.shooterLeftEncoder.getVelocity() <= targetShooterVelocity && Shooter.shooterRightEncoder.getVelocity() <= targetShooterVelocity){}//waits to do everything else until the motors reach a targeted rpm
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
    Shooter.SetShootersZero();
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