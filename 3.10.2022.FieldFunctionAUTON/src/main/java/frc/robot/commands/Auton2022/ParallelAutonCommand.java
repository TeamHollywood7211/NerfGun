package frc.robot.commands.Auton2022;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BreakBeams;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Solenoids;

public class ParallelAutonCommand extends ParallelCommandGroup{
    public ParallelAutonCommand(Intake intake, Drivetrain drivetrain, Conveyor conveyor, Solenoids solenoids, Shooter shooter, BreakBeams breakBeams){
        addCommands(new SimpleAuton(drivetrain, intake, solenoids, shooter), new SimpleAutonStep2(conveyor, breakBeams));
    } 
}
