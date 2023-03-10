// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.utilities.drive.BeakDrivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class RotateDrivetrainToAngle extends ProfiledPIDCommand {
    private boolean relative;
    private Rotation2d initialRotation;
    private Rotation2d goal;

    private BeakDrivetrain drivetrain;

    /** Creates a new RotateDrivetrainByAngle. */
    public RotateDrivetrainToAngle(Rotation2d goal, BeakDrivetrain drivetrain, boolean relative) {
        super(
                // The ProfiledPIDController used by the command
                drivetrain.getThetaController(),
                // This should return the measurement
                () -> drivetrain.getRotation2d().getRadians(),
                // This should return the goal (can also be a constant)
                () -> drivetrain.getThetaController().getGoal(),
                // This uses the output
                (output, setpoint) -> {
                    // Use the output (and setpoint, if desired) here
                    drivetrain.drive(
                            0.,
                            0.,
                            (output + setpoint.velocity) / drivetrain.getPhysics().maxAngularVelocity.getAsRadiansPerSecond());
                });
        this.drivetrain = drivetrain;
        this.goal = goal;
        this.relative = relative;
        // Use addRequirements() here to declare subsystem dependencies.
        // Configure additional PID options by calling `getController` here.
        addRequirements(drivetrain);
        getController().enableContinuousInput(-Math.PI, Math.PI);
        getController().setTolerance(Units.degreesToRadians(1.0));
    }

    public double getGoal() {
        return goal.getRadians() + (relative ? initialRotation.getRadians() : 0.);
    }

    @Override
    public void initialize() {
        initialRotation = drivetrain.getRotation2d();
        getController().setGoal(getGoal());
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return getController().atGoal();
    }
}
