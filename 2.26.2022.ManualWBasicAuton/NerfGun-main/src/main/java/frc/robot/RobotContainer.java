/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.*;
import frc.robot.commands.Auton2022.BackUpAuton;
import frc.robot.commands.Auton2022.SimpleLinearAuton;
import frc.robot.commands.DemoCommands.RunDoubleSolenoids;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
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
  public static SimpleLinearAuton m_simpleLinearAuton = new SimpleLinearAuton(m_drivetrain, m_intake, m_shooter, m_conveyor);
  public static BackUpAuton m_backUpAuton = new BackUpAuton(m_drivetrain);
  //public static TurnTurret m_turnTurret;

  public final static Joystick leftJoystick = new Joystick(0);
  public final static JoystickButton autoAimButton = new JoystickButton(leftJoystick, 1);//this is the trigger

  public final static Joystick rightJoystick = new Joystick(1); 

  public final static XboxController operatorJoystick = new XboxController(2);
  public final static POVButton conveyorUpButton = new POVButton(operatorJoystick, 0);//up on dpad
  public final static POVButton conveyorDownButton = new POVButton(operatorJoystick, 180);//down on dpad
  public final static Trigger solenoidInOutButton = new JoystickButton(operatorJoystick, 3);//left on dpad
  public final static JoystickButton intakeButton = new JoystickButton(operatorJoystick, 2);//b button on xbox controller
  public final static JoystickButton climbUpButton = new JoystickButton(operatorJoystick, 4);//y button
  public final static JoystickButton climbDownButton = new JoystickButton(operatorJoystick, 1);//a button
  public final static JoystickButton shootNormalTrigger = new JoystickButton(operatorJoystick, 6);//right bumper
  
  public final static XboxController driverController = new XboxController(3);
  //public final static Joystick driverLeftJoystick = driverController.getRawAxis();

  public static boolean shootTrigger(){
    if(operatorJoystick.getRawAxis(3)> 0.1){
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
    return m_backUpAuton;
    
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

