/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BreakBeams;
import frc.robot.subsystems.Intake;
import static frc.robot.Constants.*;

public class RunIntake extends CommandBase {
  //private final Harvester m_harvester;

  
  
  public RunIntake(Intake intake) {
    //Use addRequirements() here to declare subsystem dependencies
    addRequirements(intake);
  }

    //Runs when the command is initially run by the scheduler
    @Override
    public void initialize() {
    }

    //Runs consistently while the command is being scheduled
    @Override
    public void execute() {
      while(BreakBeams.ammo<2){
        if(RobotContainer.intakeButton.get()){
          Intake.intakeMotor.set(intakeMotorPower);
        }  else{
          Intake.intakeMotor.set(0);
        }
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