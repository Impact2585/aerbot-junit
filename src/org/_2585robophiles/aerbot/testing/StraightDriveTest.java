package org._2585robophiles.aerbot.testing;

import org._2585robophiles.aerbot.systems.GyroSystem;
import org._2585robophiles.aerbot.systems.WheelSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
		for(int outer = 0; outer < 5; outer++){
			// gyroAngle set to random positive or negative multiple of 360 after second outer iteration
			if(outer > 2){
				gyroAngle = (int)(Math.random() * 4 + 1) * 360;
				if(Math.random() < 0.5)
					gyroAngle *= -1;
				// reset wheel system straight driving
				wheelSystem.disableStraightDrivePID();
				wheelSystem.setCorrectRotate(0);
				wheelSystem.setStraightDriving(false);
				System.out.println("changing");
				printInfo();
			}
			for(int i = 0; i < 75; i++){
				wheelSystem.straightDrive(outer % 2 == 1 ? -1 : 1);// -1 on second outer iteration
				updateAngle();
				updatePID();
				printInfo();
				Assert.assertTrue(Math.abs(360 - gyroSystem.getHeading()) < 2.2);
			}
		}
	}
	
	/**
	 * print current angle, heading and correctRotate of the wheel system
	 */
	public void printInfo(){
		System.out.println("angle: " + gyroAngle);
		System.out.println("heading: " + gyroSystem.getHeading());
		System.out.println("correctRotate:" + wheelSystem.getCorrectRotate());
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
		 * @see org._2585robophiles.aerbot.systems.GyroSystem#getAngle()
		 */
		public synchronized double getAngle() {
			return gyroAngle;
		}
		
	}
	
	private class StraightDriveWheelSystem extends WheelSystem{

		/* (non-Javadoc)
		 * @see org._2585robophiles.aerbot.systems.WheelSystem#setGyro(org._2585robophiles.aerbot.systems.GyroSystem)
		 */
		@Override
		protected void setGyro(GyroSystem gyro) {
			super.setGyro(gyro);
		}

		/* (non-Javadoc)
		 * @see org._2585robophiles.aerbot.systems.WheelSystem#arcadeDrive(double, double)
		 */
		@Override
		public void arcadeDrive(double moveValue, double rotateValue) {
			currentMovement = moveValue;
			currentRotation = rotateValue;
		}

		/* (non-Javadoc)
		 * @see org._2585robophiles.aerbot.systems.WheelSystem#resetStraightDrivePID()
		 */
		@Override
		public void resetStraightDrivePID() {

		}

		/* (non-Javadoc)
		 * @see org._2585robophiles.aerbot.systems.WheelSystem#straightDriveControllerEnabled()
		 */
		@Override
		public boolean straightDriveControllerEnabled() {
			return pidEnabled;
		}

		/* (non-Javadoc)
		 * @see org._2585robophiles.aerbot.systems.WheelSystem#setStraightDrivePIDSetpoint(double)
		 */
		@Override
		public void setStraightDrivePIDSetpoint(double setpoint) {
			pidSetpoint = setpoint;
		}

		/* (non-Javadoc)
		 * @see org._2585robophiles.aerbot.systems.WheelSystem#enableStraightDrivePID()
		 */
		@Override
		public void enableStraightDrivePID() {
			pidEnabled = true;
		}

		/* (non-Javadoc)
		 * @see org._2585robophiles.aerbot.systems.WheelSystem#disableStraightDrivePID()
		 */
		@Override
		public void disableStraightDrivePID() {
			pidEnabled = false;
		}

		/* (non-Javadoc)
		 * @see org._2585robophiles.aerbot.systems.WheelSystem#getCorrectRotate()
		 */
		@Override
		protected double getCorrectRotate() {
			return super.getCorrectRotate();
		}

		/* (non-Javadoc)
		 * @see org._2585robophiles.aerbot.systems.WheelSystem#setCorrectRotate(double)
		 */
		@Override
		protected void setCorrectRotate(double correctRotate) {
			super.setCorrectRotate(correctRotate);
		}

		/* (non-Javadoc)
		 * @see org._2585robophiles.aerbot.systems.WheelSystem#isStraightDriving()
		 */
		@Override
		protected boolean isStraightDriving() {
			return super.isStraightDriving();
		}

		/* (non-Javadoc)
		 * @see org._2585robophiles.aerbot.systems.WheelSystem#setStraightDriving(boolean)
		 */
		@Override
		protected void setStraightDriving(boolean straightDriving) {
			super.setStraightDriving(straightDriving);
		}
		
		
	}

}
