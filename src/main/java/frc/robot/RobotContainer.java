// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.OctavianSwerveDrivetrain;
import frc.robot.utilities.BeakXBoxController;
import frc.robot.utilities.Util;
import frc.robot.utilities.units.Distance;

/** Add your docs here. */
public class RobotContainer {
    private BeakXBoxController m_driverController = new BeakXBoxController(OIConstants.DRIVER);

    // private NEODrivetrain m_drive = NEODrivetrain.getInstance();
    // private SixNEODrivetrain m_drive = SixNEODrivetrain.getInstance();
    // private CIMDrivetrain m_drive = CIMDrivetrain.getInstance();
    // private FalconDrivetrain m_drive = FalconDrivetrain.getInstance();
    // private OctavianSwerveDrivetrain m_drive = OctavianSwerveDrivetrain.getInstance();
    private OctavianSwerveDrivetrain m_drive = OctavianSwerveDrivetrain.getInstance();
    // private PracticeSwerveDrivetrain m_drive = PracticeSwerveDrivetrain.getInstance();
    

    private SlewRateLimiter m_xLimiter = new SlewRateLimiter(4.0);
    private SlewRateLimiter m_yLimiter = new SlewRateLimiter(4.0);
    private SlewRateLimiter m_rotLimiter = new SlewRateLimiter(4.0);
    
    private static RobotContainer _instance = new RobotContainer();

    public RobotContainer() {
        configureButtonBindings();
    }

    public void logAllConfigs() {
        // m_drive.logConfigData();
    }

    public void configureButtonBindings() {
        m_drive.setDefaultCommand(
                new RunCommand(() -> m_drive.drive(
                        -speedScaledDriverLeftY(),
                        speedScaledDriverLeftX(),
                        speedScaledDriverRightX(),
                        true),
                        m_drive));

        m_driverController.start.onTrue(new InstantCommand(m_drive::zero));
       
    }

    public double speedScaledDriverLeftY() {
        return m_yLimiter.calculate(Util.speedScale(m_driverController.getLeftYAxis(),
                DriveConstants.SPEED_SCALE,
                m_driverController.getRightTrigger()));
    }

    public double speedScaledDriverRightX() {
        return m_rotLimiter.calculate(-Util.speedScale(m_driverController.getRightXAxis(),
                DriveConstants.SPEED_SCALE,
                m_driverController.getRightTrigger()));
    }

    public double speedScaledDriverLeftX() {
        return m_xLimiter.calculate(-Util.speedScale(m_driverController.getLeftXAxis(),
                DriveConstants.SPEED_SCALE,
                m_driverController.getRightTrigger()));
    }

    

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */

    public static RobotContainer getInstance() {
        return _instance;
    }
}
