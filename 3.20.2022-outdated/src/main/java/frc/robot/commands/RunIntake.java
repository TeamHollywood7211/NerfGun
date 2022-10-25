/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BreakBeams;
import frc.robot.subsystems.Intake;
import static frc.robot.Constants.*;

public class RunIntake extends CommandBase {
  //private final Harvester m_harvester;
  int beamB;
  int enterLoop;
  public RunIntake(Intake intake, BreakBeams breakBeams) {
    beamB= 1;
    enterLoop = 1;
    SmartDashboard.putNumber("beamB", beamB);
    SmartDashboard.putNumber("enterLoop", enterLoop);
    //Use addRequirements() here to declare subsystem dependencies
    addRequirements(intake, breakBeams);
  }

    //Runs when the command is initially run by the scheduler
    @Override
    public void initialize() {
    }

    //Runs consistently while the command is being scheduled
    @Override
    public void execute() {
      //maths for if there is something detected in the hopper.
      //changed where the ammo++ is
      
      // if(BreakBeams.HopperBeamOutput() == true){
      //   if(BreakBeams.MiddleBeamOutput() == false && BreakBeams.TopBeamOutput() == false){ //if there isnt a ball in the middle or at the top then
      //     SmartDashboard.putNumber("beamB", beamB++);
      //     if(BreakBeams.MiddleBeamOutput() != true){ //run conveyor until there is a ball in the middle
      //       SmartDashboard.putNumber("enterLoop", enterLoop++);
      //       Conveyor.conveyorMotor.set(conveyorMotorPower);
      //     }
      //     BreakBeams.ammo++;
      //   }
      //   else if(BreakBeams.MiddleBeamOutput() == true && BreakBeams.TopBeamOutput() == false){ //if there is a ball in the middle or at the top, then
      //     if(BreakBeams.TopBeamOutput() != true){ //run the conveyor until there is a ball at the top
      //       Conveyor.conveyorMotor.set(conveyorMotorPower);
      //     }
      //     BreakBeams.ammo++;
      //   }
      //   else if(BreakBeams.TopBeamOutput() == true){//If there's a ball at the top, but not the middle, run the conveyor
      //     if(BreakBeams.MiddleBeamOutput() != true){//backwards until it hits the middle.
      //       Conveyor.conveyorMotor.set(-conveyorMotorPower);
      //     }
      //     if(BreakBeams.MiddleBeamOutput() != false){
      //       Conveyor.conveyorMotor.set(-conveyorMotorPower);
      //     }
      //     if(BreakBeams.TopBeamOutput() != true){//then, run the conveyor until there's a ball in the top
      //       Conveyor.conveyorMotor.set(conveyorMotorPower);
      //     }
      //     BreakBeams.ammo++;
      //   }
      //   Conveyor.conveyorMotor.set(0);//duh
      //   BreakBeams.ammo++; //increments the ammo by one
      // }
      
      //ammo decrement math is in commands/RunConveyorAndShooter.java in execute();

      
        if(RobotContainer.intakeButton.get()){
          Intake.intakeMotor.set(intakeMotorPower);
        }  else{
          Intake.intakeMotor.set(0);
        }
      
    }
    
    //Runs when the command ends 
    @Override
    public void end(boolean interrupted){
    }

    //Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }
  }