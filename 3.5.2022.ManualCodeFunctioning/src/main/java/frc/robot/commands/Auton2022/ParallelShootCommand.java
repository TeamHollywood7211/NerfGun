package frc.robot.commands.Auton2022;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.DrivetrainCommand;
import frc.robot.commands.RunConveyorAutomated;

public class ParallelShootCommand extends ParallelCommandGroup{
    public ParallelShootCommand(){
        addCommands(new RunConveyorAutomated(RobotContainer.m_conveyor), new DrivetrainCommand(RobotContainer.m_drivetrain, RobotContainer.m_gyroAccel));
    }
    
}
