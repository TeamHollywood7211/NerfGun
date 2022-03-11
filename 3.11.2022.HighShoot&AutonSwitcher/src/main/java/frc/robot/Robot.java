// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.DemoCommands.RunClimbers;
import frc.robot.commands.RunConveyorAutomated;
import frc.robot.commands.RunIntake;
import frc.robot.commands.RunShooter;
import frc.robot.commands.Auton2022.SimpleAuton;
import frc.robot.commands.DemoCommands.RunConveyor;
import frc.robot.commands.DemoCommands.RunDoubleSolenoids;
import frc.robot.subsystems.BreakBeams;
import frc.robot.subsystems.GyroAccelerometer;
import frc.robot.subsystems.Solenoids;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private Command m_runIntake = new RunIntake(RobotContainer.m_intake);
  private Command m_runShooter = new RunShooter(RobotContainer.m_shooter);
  private Command m_runConveyor = new RunConveyor(RobotContainer.m_conveyor);
  private Command m_runClimbers = new RunClimbers(RobotContainer.m_climbers);
  private Command m_runConveyorAutomated = new RunConveyorAutomated(RobotContainer.m_conveyor);
  private Command m_runDoubleSolenoids = new RunDoubleSolenoids(RobotContainer.m_solenoids);
  private RobotContainer m_robotContainer;
  private String[] autonArray = {"TwoBallLow", "TwoBallHigh"};
  private Command chosenAuton;
  

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    UsbCamera frontCamera = CameraServer.startAutomaticCapture("frontCamera", 0);
    frontCamera.setResolution(320, 240);
    GyroAccelerometer.ahrs.calibrate();
    m_robotContainer = new RobotContainer();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putStringArray("Auto List", autonArray);
    String autoName = SmartDashboard.getString("Auto Selector", "TwoBallLow");
    switch(autoName) {
      case "TwoBallLow":
      chosenAuton = RobotContainer.m_SimpleAuton;
      case "TwoBallHigh":
      chosenAuton = RobotContainer.m_SimpleAuton;
    }
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    
  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    //m_robotContainer.getAutonomousCommand(); <put this after m_autonomousCommand = if you want it to go back to og
    m_autonomousCommand = chosenAuton;
    System.out.print("autonomous is initialized");
    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
      System.out.print("autonomous command is scheduled.");
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
      System.out.print("Autonomous.cancel has run");
    }    

    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    m_runIntake.schedule();
    //m_runConveyorAutomated.schedule();
    m_runShooter.schedule();
    m_runDoubleSolenoids.schedule();
    m_runClimbers.schedule();
    m_runConveyor.schedule();
    System.out.print("all commands are scheduled.");
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
