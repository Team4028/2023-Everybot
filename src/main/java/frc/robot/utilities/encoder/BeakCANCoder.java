// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utilities.encoder;

import com.ctre.phoenix.sensors.CANCoderStatusFrame;
import com.ctre.phoenix.sensors.WPI_CANCoder;

/** Add your docs here. */
public class BeakCANCoder extends WPI_CANCoder implements BeakAbsoluteEncoder {
    public BeakCANCoder(int deviceNumber) {
        this(deviceNumber, "");
    }
    
    public BeakCANCoder(int deviceNumber, String CANBus) {
        super(deviceNumber, CANBus);
    }

    @Override
    public void setEncoderPosition(double position) {
        super.setPosition(position);
    }

    @Override
    public void setAbsoluteOffset(double degrees) {
        super.configMagnetOffset(degrees);
    }

    @Override
    public void setDataFramePeriod(int period) {
        super.setStatusFramePeriod(CANCoderStatusFrame.SensorData, period);
    }

    @Override
    public void setSensorDirection(boolean direction) {
        super.configSensorDirection(direction);
    }

    @Override
    public void restoreFactoryDefault() {
        super.configFactoryDefault();
    }

}
