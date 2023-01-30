// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
public class Arm extends SubsystemBase {
  private static Arm m_instance;
  private CANSparkMax m_armMotor;
  /** Creates a new Arm. */
  public Arm(){
    m_armMotor=new CANSparkMax(1, MotorType.kBrushless);
  }
  public static Arm getInstance(){
    if(m_instance==null){
      m_instance=new Arm();
    }
    return m_instance;
  }
  public void runMotor(){
    m_armMotor.set(0.3);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
