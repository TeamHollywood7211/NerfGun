package frc.robot.commands.Auton2022;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.DrivetrainCommand;
import frc.robot.commands.RunConveyorAutomated;
import frc.robot.commands.RunIntake;
import frc.robot.subsystems.BreakBeams;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GyroAccelerometer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Solenoids;

public class ParallelCommand extends ParallelCommandGroup{
    public ParallelCommand(Intake intake, Drivetrain drivetrain, BreakBeams breakBeams, GyroAccelerometer gyroAccel){
        addCommands(new DrivetrainCommand(drivetrain, gyroAccel), new RunIntake(intake, breakBeams));
    } 
}
