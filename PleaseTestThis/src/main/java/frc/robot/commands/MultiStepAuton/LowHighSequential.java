package frc.robot.commands.MultiStepAuton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.BreakBeams;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelights;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Solenoids;

public class LowHighSequential extends SequentialCommandGroup{
    public LowHighSequential(Intake intake, Drivetrain drivetrain, Conveyor conveyor, Solenoids solenoids, Shooter shooter, BreakBeams breakBeams, Limelights limelights){
        addCommands(new Step1(shooter, conveyor, solenoids), new Step2(drivetrain, intake, conveyor, breakBeams, limelights), new Step3(shooter, conveyor, drivetrain, limelights, intake), new Step4(shooter, conveyor, drivetrain));
    } 
}
