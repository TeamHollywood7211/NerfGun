package frc.robot.commands.Auton2022.FourHighAutonComGroup;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GyroAccelerometer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelights;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Solenoids;

public class FourHighParallelGroup extends ParallelCommandGroup{
    public FourHighParallelGroup(Conveyor conveyor, Drivetrain drivetrain, Intake intake, Limelights limelights, Shooter shooter, Solenoids solenoids, GyroAccelerometer gyroAccel){
        addCommands(new RunShooterPID(shooter), new FourHighAuton(conveyor, drivetrain, intake, limelights, shooter, solenoids, gyroAccel));
    }
}