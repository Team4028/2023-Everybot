// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utilities;

/** Add your docs here. */
public class RobotPhysics {
    public double maxVelocity;
    public double maxAngularVelocity;

    public double trackWidth;
    public double wheelBase;

    public double wheelDiameter;

    public double driveGearRatio;

    /**
     * Construct a new physics object to pass to a drivetrain.
     * 
     * @param maxVelocity Maximum travel velocity of the robot, in meters per second.
     * @param maxAngularVelocity Maximum angular velocity of the robot, in radians per second. Set to 0 to calculate a theoretical.
     * @param trackWidth Distance from the left wheels to the right wheels, in inches.
     * @param wheelBase Distance from the front wheels to the back wheels, in inches.
     * @param wheelDiameter Diameter of the wheels, in inches.
     * @param driveGearRatio Gear ratio of the drive motors.
     */
    public RobotPhysics(double maxVelocity, double maxAngularVelocity, double trackWidth, double wheelBase,
            double wheelDiameter, double driveGearRatio) {
        this.maxVelocity = maxVelocity;
        this.maxAngularVelocity = (maxAngularVelocity == 0. ? calcTheoreticalAngularVelocity() : maxAngularVelocity);
        this.trackWidth = trackWidth;
        this.wheelBase = wheelBase;
        this.wheelDiameter = wheelDiameter;
        this.driveGearRatio = driveGearRatio;
    }

    protected double calcTheoreticalAngularVelocity() {
        return maxVelocity / Math.hypot(trackWidth / 2, wheelBase / 2);
    }
}