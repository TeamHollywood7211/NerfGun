package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;

/** An example command that uses an example subsystem. */
public class RunConveyorAutomated extends CommandBase {

  /**
   * Creates a new RunConveyor Command.
   *
   * The subsystem used by this command.
   * 
   */

  public RunConveyorAutomated(Conveyor conveyor) {

    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(conveyor);

  }
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  //   SmartDashboard.putBoolean("conveyorgobutton", RobotContainer.conveyorGoTrigger());
  //   if(BreakBeams.ammo>0 && RobotContainer.conveyorGoTrigger()){ // Got bullets? Button mashed?      
  //     if(Shooter.shooterRightEncoder.getVelocity() >= targetShooterVelocitySlow-20 && Shooter.shooterLeftEncoder.getVelocity() >= targetShooterVelocitySlow-20){
  //       if(BreakBeams.ammo == 2){
  //         if(BreakBeams.TopBeamOutput() == true){ //while there is a ball at the top
  //         Conveyor.conveyorMotor.set(conveyorMotorPower); //run conveyor until there isnt
  //         }
  //         BreakBeams.ammo--; // subtracts an ammo
  //       }
  //       if(BreakBeams.ammo == 1){
  //         if(BreakBeams.TopBeamOutput() == false){ //if there isnt a ball at the top
  //           Conveyor.conveyorMotor.set(conveyorMotorPower);//make sure there is
  //         }
  //         if(BreakBeams.TopBeamOutput() == true){//once there is a ball at the top
  //           Conveyor.conveyorMotor.set(conveyorMotorPower);//shoot
  //         }
  //         BreakBeams.ammo--;//subtract an ammo after all of the logic-ing has happened
  //       }
  //       if(BreakBeams.ammo == 0){
  //         Conveyor.conveyorMotor.set(0);
  //       }
  //      } //else{
  //     // Conveyor.conveyorMotor.set(0);//duh
  //     // }
  //   }
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