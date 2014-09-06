package com.bellaire.aerbot.testing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bellaire.aerbot.systems.GyroSystem;
import com.bellaire.aerbot.systems.WheelSystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StraightDriveTest {
	
	private final StraightDriveGyroSystem gyroSystem = new StraightDriveGyroSystem();
	private final StraightDriveWheelSystem wheelSystem = new StraightDriveWheelSystem();

	private double gyroAngle;
	private double currentMovement;
	private double currentRotation;
	private double pidSetpoint;
	private boolean pidEnabled;

	@Before
	public void setUp() {
		wheelSystem.setGyro(gyroSystem);
	}

	@Test
	public void test() {
		// tests if straight drive stays within 2.2 degrees
		for(int i = 0; i < 75; i++){
			wheelSystem.straightDrive(1);
			updateAngle();
			updatePID();
			System.out.println("angle: " + gyroAngle);
			System.out.println("correctRotate:" + wheelSystem.getCorrectRotate());
			Assert.assertTrue(Math.abs(gyroAngle) < 2.2);
		}
		
		// start going the other way
		for(int i = 0; i < 50; i++){
			wheelSystem.straightDrive(-1);
			updateAngle();
			updatePID();
			System.out.println("angle: " + gyroAngle);
			System.out.println("correctRotate:" + wheelSystem.getCorrectRotate());
			Assert.assertTrue(Math.abs(gyroAngle) < 2.2);
		}
	}
	
	/**
	 * updates gyroAngle
	 */
	public void updateAngle(){
		if(currentMovement != 0){
			gyroAngle -= currentRotation + 0.1;
		}
	}
	
	/**
	 * Uses proportional to update correct rotate in wheelSystem
	 */
	public void updatePID(){
		if(pidEnabled)
			wheelSystem.setCorrectRotate(WheelSystem.Kp * (pidSetpoint - gyroAngle));
	}
	
	private class StraightDriveGyroSystem extends GyroSystem{

		/* (non-Javadoc)
		 * @see com.bellaire.aerbot.systems.GyroSystem#getAngle()
		 */
		public synchronized double getAngle() {
			return gyroAngle;
		}
		
	}
	
	private class StraightDriveWheelSystem extends WheelSystem{

		protected void setGyro(GyroSystem gyro) {
			super.setGyro(gyro);
		}

		/* (non-Javadoc)
		 * @see com.bellaire.aerbot.systems.WheelSystem#arcadeDrive(double, double)
		 */
		public void arcadeDrive(double moveValue, double rotateValue) {
			currentMovement = moveValue;
			currentRotation = rotateValue;
		}

		/* (non-Javadoc)
		 * @see com.bellaire.aerbot.systems.WheelSystem#resetStraightDrivePID()
		 */
		public void resetStraightDrivePID() {

		}

		/* (non-Javadoc)
		 * @see com.bellaire.aerbot.systems.WheelSystem#straightDriveControllerEnabled()
		 */
		public boolean straightDriveControllerEnabled() {
			return pidEnabled;
		}

		/* (non-Javadoc)
		 * @see com.bellaire.aerbot.systems.WheelSystem#setStraightDrivePIDSetpoint(double)
		 */
		public void setStraightDrivePIDSetpoint(double setpoint) {
			pidSetpoint = setpoint;
		}

		/* (non-Javadoc)
		 * @see com.bellaire.aerbot.systems.WheelSystem#enableStraightDrivePID()
		 */
		public void enableStraightDrivePID() {
			pidEnabled = true;
		}

		/* (non-Javadoc)
		 * @see com.bellaire.aerbot.systems.WheelSystem#disableStraightDrivePID()
		 */
		public void disableStraightDrivePID() {
			pidEnabled = false;
		}

		/* (non-Javadoc)
		 * @see com.bellaire.aerbot.systems.WheelSystem#getCorrectRotate()
		 */
		protected double getCorrectRotate() {
			return super.getCorrectRotate();
		}

		/* (non-Javadoc)
		 * @see com.bellaire.aerbot.systems.WheelSystem#setCorrectRotate(double)
		 */
		protected void setCorrectRotate(double correctRotate) {
			super.setCorrectRotate(correctRotate);
		}
		
		
	}

}
