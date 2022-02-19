package frc.robot.commands.Auton2022;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.RobotContainer;

public class ParallelAuton extends ParallelCommandGroup{
    public ParallelAuton(){
        addCommands(new SimpleLinearAuton(RobotContainer.m_drivetrain, RobotContainer.m_intake, RobotContainer.m_shooter, RobotContainer.m_conveyor));
    }
    
}
