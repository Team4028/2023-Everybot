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
    m_armMotor=new CANSparkMax(15, MotorType.kBrushless);
  }
  public static Arm getInstance(){
    if(m_instance==null){
      m_instance=new Arm();
    }
    return m_instance;
  }
  /**parseDouble finds 550 in 'BURN NEO 550' and sets neo speed to 550% */
  public void runMotor(){
    m_armMotor.set(0.4);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
