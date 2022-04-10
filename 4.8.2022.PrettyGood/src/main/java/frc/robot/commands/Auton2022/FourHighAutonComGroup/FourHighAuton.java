package frc.robot.commands.Auton2022.FourHighAutonComGroup;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GyroAccelerometer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelights;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Solenoids;

public class FourHighAuton extends SequentialCommandGroup{
  public FourHighAuton(Conveyor conveyor, Drivetrain drivetrain, Intake intake, Limelights limelights, Shooter shooter, Solenoids solenoids, GyroAccelerometer gyroAccel){
    addCommands(new FourStep1(intake, drivetrain, solenoids, limelights), new FourStep2(drivetrain, conveyor, limelights, shooter), new FourStep3(drivetrain, gyroAccel), new FourStep4(drivetrain), new FourStep5(drivetrain, limelights, intake, conveyor));
  }
}