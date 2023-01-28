// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class Infeed extends SubsystemBase {
  private static Infeed m_instance;
  private WPI_TalonSRX m_infeedMotor;
  private double percent=0.1;
  /** Creates a new Infeed. */
  public Infeed(){
    m_infeedMotor=new WPI_TalonSRX(11);
  }
  public void infeedIn(){
    m_infeedMotor.set(percent);
    System.out.println("MOVING INFEEED");
  }
  public void infeedStop(){
    m_infeedMotor.set(0.0);
  }
  public void infeedOut(){
    m_infeedMotor.set(-percent);
  }
  public void infeedFaster(){
    if(Math.signum(percent)<=1.0){
      percent+=(0.1*Math.signum(percent));
    }
    if(m_infeedMotor.get()!=0.0){
      m_infeedMotor.set(percent);
    }
  }
  public void infeedSlower(){
    if(Math.signum(percent)>=0.0){
      percent-=(0.1*Math.signum(percent));
    }
    if(m_infeedMotor.get()!=0.0){
      m_infeedMotor.set(percent);
    }
  } 
  public static Infeed getInstance(){
    if(m_instance==null){
      m_instance=new Infeed();
    }
    return m_instance;
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
