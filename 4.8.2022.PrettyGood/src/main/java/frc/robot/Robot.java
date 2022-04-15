// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.DrivetrainCommand;
import frc.robot.commands.RunClimbers;
import frc.robot.commands.RunConveyor;
import frc.robot.commands.RunDoubleSolenoids;
import frc.robot.commands.RunIntake;
import frc.robot.commands.RunShooter;
import frc.robot.commands.Auton2022.FourHighAutonComGroup.FourHighAuton;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private RobotContainer m_robotContainer;
  private Command m_autonomousCommand;
  private Command m_drivetrainCommand;
  private Command m_runIntake;
  private Command m_runShooter;
  private Command m_runConveyor;
  private Command m_runClimbers;
  private Command m_runDoubleSolenoids;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    UsbCamera frontCamera = CameraServer.startAutomaticCapture("frontCamera", 0);
    frontCamera.setResolution(320, 240);
    m_robotContainer = new RobotContainer();
    m_drivetrainCommand = new DrivetrainCommand(m_robotContainer.m_drivetrain, m_robotContainer.m_gyroAccel, m_robotContainer.m_limelights);
    m_runIntake = new RunIntake(m_robotContainer.m_intake, m_robotContainer.m_breakBeams);
    m_runShooter = new RunShooter(m_robotContainer.m_shooter);
    m_runConveyor = new RunConveyor(m_robotContainer.m_conveyor);
    m_runClimbers = new RunClimbers(m_robotContainer.m_climbers);
    m_runDoubleSolenoids = new RunDoubleSolenoids(m_robotContainer.m_solenoids);
    m_robotContainer.m_drivetrain.resetDriveMotorControllers();
    m_robotContainer.clearAllStickyFaults();
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
    m_robotContainer.m_drivetrain.resetDrivetrainEncoders();
    // if(DriverStation.getAlliance().toString() == "Blue"){
    //   m_robotContainer.m_limelights.frontTable.getEntry("pipeline").setNumber(1);
    // } else{
    //   m_robotContainer.m_limelights.frontTable.getEntry("pipeline").setNumber(0);
    // }
    // m_robotContainer.m_limelights.frontTable.getEntry("camMode").setNumber(0);
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
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
    m_robotContainer.m_limelights.frontTable.getEntry("camMode").setNumber(1);
    m_robotContainer.m_limelights.frontTable.getEntry("pipeline").setNumber(2);
    m_drivetrainCommand.schedule();
    m_runIntake.schedule();
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
