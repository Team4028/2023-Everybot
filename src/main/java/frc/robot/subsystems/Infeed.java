// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class Infeed extends SubsystemBase {
  private static Infeed m_instance;
  private WPI_TalonSRX m_infeedMotor;
  private int infeedMode=1;
  private double percent=0.6;
  /** Creates a new Infeed. */
  public Infeed(){
    m_infeedMotor=new WPI_TalonSRX(11);
  }
  public void infeedIn(){
    m_infeedMotor.set(infeedMode*percent);
  }
  public void infeedStop(){
    m_infeedMotor.set(0.0);
  }
  public void infeedOut(){
    m_infeedMotor.set(-(infeedMode*percent));
  }
  public void infeedFaster(){
    if(percent<=0.6){
      percent+=0.1;
    }
    if(m_infeedMotor.get()!=0.0){
      m_infeedMotor.set(percent);
    }
    System.out.println(percent);
  }
  public void infeedSlower(){
    if(percent>=0.2){
      percent-=0.1;
    }
    if(m_infeedMotor.get()!=0.0){
      m_infeedMotor.set(percent);
    }
    System.out.println(percent);
  } 
  public void changeModeCube(){
    infeedMode=1;
  }
  public void changeModeCone(){
    infeedMode=-1;
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
