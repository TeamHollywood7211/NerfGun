

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.DrivetrainCommand;
import frc.robot.commands.RunIntake;
import frc.robot.subsystems.BreakBeams;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GyroAccelerometer;
import frc.robot.subsystems.Intake;

public class ParallelCommand extends ParallelCommandGroup{
    public ParallelCommand(Intake intake, Drivetrain drivetrain, BreakBeams breakBeams, GyroAccelerometer gyroAccel){
        addCommands(new DrivetrainCommand(drivetrain, gyroAccel), new RunIntake(intake, breakBeams));
    } 
}
