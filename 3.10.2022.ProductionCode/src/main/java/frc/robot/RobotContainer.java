/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.DrivetrainCommand;
import frc.robot.commands.Auton2022.ParallelAutonCommand;
import frc.robot.commands.Auton2022.SimpleAuton;
import frc.robot.commands.DemoCommands.RunDoubleSolenoids;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;




/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  public final static GyroAccelerometer m_gyroAccel = new GyroAccelerometer();
  public final static Conveyor m_conveyor = new Conveyor();
  public final static Intake m_intake = new Intake();
  public final static Shooter m_shooter = new Shooter();
  public final static Climbers m_climbers = new Climbers();
  public final static BreakBeams m_breakBeams = new BreakBeams();
  public final static Drivetrain m_drivetrain = new Drivetrain();
  public final static Solenoids m_solenoids = new Solenoids();

  //public static Turret m_turret = new Turret();

  //The robot's commands
  public static SimpleAuton m_SimpleAuton = new SimpleAuton(m_drivetrain, m_intake, m_solenoids, m_shooter);
  public static ParallelAutonCommand m_parallelAutonCommand = new ParallelAutonCommand(m_intake, m_drivetrain, m_conveyor, m_solenoids, m_shooter, m_breakBeams);

  public final static Joystick leftJoystick = new Joystick(0);
  public final static JoystickButton autoAimButton = new JoystickButton(leftJoystick, 1);//this is the trigger
  public final static JoystickButton calibrateButton = new JoystickButton(leftJoystick, 7);
  public final static JoystickButton climbUp2Button = new JoystickButton(leftJoystick, 8);
  public final static JoystickButton climbDown2Button = new JoystickButton(leftJoystick, 10);
  
  public final static Joystick rightJoystick = new Joystick(1); 
  public final static JoystickButton solenoidClimbButton = new JoystickButton(rightJoystick, 8);
  public final static JoystickButton stageOneForceUp = new JoystickButton(rightJoystick, 6);
  public final static JoystickButton stageOneForceDown = new JoystickButton(rightJoystick, 4);

  public final static XboxController operatorJoystick = new XboxController(2);
  public final static POVButton conveyorUpButton = new POVButton(operatorJoystick, 0);//up on dpad
  public final static POVButton conveyorDownButton = new POVButton(operatorJoystick, 180);//down on dpad
  public final static JoystickButton climbUp1Button = new JoystickButton(operatorJoystick, 4);//y button
  public final static JoystickButton climbDown1Button = new JoystickButton(operatorJoystick, 1);//a button
  public final static JoystickButton solenoidIntakeButton = new JoystickButton(operatorJoystick, 3);
  public final static JoystickButton intakeButton = new JoystickButton(operatorJoystick, 2);//b button on xbox controller
  public final static JoystickButton shootHighTrigger = new JoystickButton(operatorJoystick, 6);//right bumper
  
  //public final static XboxController driverController = new XboxController(3);
  //public final static Joystick driverLeftJoystick = driverController.getRawAxis();
  public static boolean climbDown2ButtonOperator(){
    if(operatorJoystick.getRawAxis(1)>.5){
      return true;
    } else{
      return false;
    }
  }

  public static boolean climbUp2ButtonOperator(){
    if(operatorJoystick.getRawAxis(1)<-.5){
      return true;
    } else{
      return false;
    }
  }

  public static boolean shootSlowTrigger(){
    if(operatorJoystick.getRawAxis(3)> 0.1){
      return true;
    } else{
      return false;
    }
  }

  public static boolean conveyorGoTrigger(){
    if(operatorJoystick.getRawAxis(2)>0.1){
      return true;
    } else{
      return false;
    }
  }

  
  // @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    System.out.println(GyroAccelerometer.ahrs.getPitch());
    System.out.println(GyroAccelerometer.ahrs.getYaw());
    System.out.println(GyroAccelerometer.ahrs.getRoll());
  }

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    GyroAccelerometer.ahrs.calibrate();
  }
  
  public Command getAutonomousCommand(){
    return m_SimpleAuton;
    //return null;
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }
}

