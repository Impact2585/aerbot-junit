package com.bellaire.aerbot.testing;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.bellaire.aerbot.input.InputMethod;
import com.bellaire.aerbot.systems.AccelerometerSystem;
import com.bellaire.aerbot.systems.GyroSystem;
import com.bellaire.aerbot.systems.WheelSystem;

import edu.wpi.first.wpilibj.Timer;

public class WheelSystemTest {

	private TestWheelSystem wheelSystem;
	private double driveXvalue, driveYvalue;
	
	/**
	 * Set up unit test
	 */
	@Before
	public void setUp() {
		wheelSystem = new TestWheelSystem();
	}

	/**
	 * Test the move method in WheelSystem
	 */
	@Test
	public void test() {
		InputMethod input1 = new InputMethod() { // test move value

			@Override
			public double getLeftX() {
				return 0;
			}

			@Override
			public double getRightX() {
				return 0;
			}

			@Override
			public double getLeftY() {
				return 1;
			}

			@Override
			public double getRightY() {
				return 0;
			}

			@Override
			public boolean shift() {
				return false;
			}

			@Override
			public boolean catchBall() {
				return false;
			}

			@Override
			public boolean shoot() {
				return false;
			}

			@Override
			public boolean shooterLift() {
				return false;
			}

			@Override
			public int intake() {
				return 0;
			}

			@Override
			public boolean intakeToggle() {
				return false;
			}

			@Override
			public boolean intakeLift() {
				return false;
			}

			@Override
			public boolean directionToggle() {
				return false;
			}

			@Override
			public boolean straightDrive() {
				return true;
			}

		};

		Assert.assertTrue(wheelSystem.getCurrentLeftY() == 0);
		wheelSystem.move(input1);
		Assert.assertTrue(wheelSystem.getCurrentLeftY() == -1);
		Assert.assertTrue(wheelSystem.getCurrentRampY() == -0.5);

		Assert.assertTrue(driveYvalue == -0.5);
		Assert.assertTrue(driveXvalue == 0);

		wheelSystem.move(input1);
		Assert.assertTrue(wheelSystem.getCurrentLeftY() == -1);
		Assert.assertTrue(wheelSystem.getCurrentRampY() == -0.75);

		Assert.assertTrue(driveYvalue == -0.75);
		Assert.assertTrue(driveXvalue == 0);
		
		InputMethod input2 = new InputMethod() { // test rotate value

			@Override
			public double getLeftX() {
				return 0;
			}

			@Override
			public double getRightX() {
				return 1;
			}

			@Override
			public double getLeftY() {
				return 0;
			}

			@Override
			public double getRightY() {
				return 0;
			}

			@Override
			public boolean shift() {
				return false;
			}

			@Override
			public boolean catchBall() {
				return false;
			}

			@Override
			public boolean shoot() {
				return false;
			}

			@Override
			public boolean shooterLift() {
				return false;
			}

			@Override
			public int intake() {
				return 0;
			}

			@Override
			public boolean intakeToggle() {
				return false;
			}

			@Override
			public boolean intakeLift() {
				return false;
			}

			@Override
			public boolean directionToggle() {
				return false;
			}

			@Override
			public boolean straightDrive() {
				return true;
			}

		};

		Assert.assertTrue(driveXvalue == 0);
		wheelSystem.move(input2);
		Assert.assertTrue(driveYvalue == -0.375);
		Assert.assertTrue(driveXvalue == 1);
		
		InputMethod input3 = new InputMethod() { // test shift value

			@Override
			public double getLeftX() {
				return 0;
			}

			@Override
			public double getRightX() {
				return 0;
			}

			@Override
			public double getLeftY() {
				return 0;
			}

			@Override
			public double getRightY() {
				return 0;
			}

			@Override
			public boolean shift() {
				return true;
			}

			@Override
			public boolean catchBall() {
				return false;
			}

			@Override
			public boolean shoot() {
				return false;
			}

			@Override
			public boolean shooterLift() {
				return false;
			}

			@Override
			public int intake() {
				return 0;
			}

			@Override
			public boolean intakeToggle() {
				return false;
			}

			@Override
			public boolean intakeLift() {
				return false;
			}

			@Override
			public boolean directionToggle() {
				return false;
			}

			@Override
			public boolean straightDrive() {
				return true;
			}

		};

		Assert.assertTrue(wheelSystem.getGear() == 0);
		wheelSystem.move(input3);
		Assert.assertTrue(wheelSystem.getGear() == 1);
		wheelSystem.move(input3); // if hold down the gear should stay 1
		Assert.assertTrue(wheelSystem.getGear() == 1);
		wheelSystem.move(input2); // the gear should be 1 if you are not sill holding it
		Assert.assertTrue(wheelSystem.getGear() == 1);
		wheelSystem.move(input3); // if hold the gear back it should change to 0
		Assert.assertTrue(wheelSystem.getGear() == 0);

	}

	private class TestWheelSystem extends WheelSystem {

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.bellaire.aerbot.systems.WheelSystem#getCurrentRampY()
		 */
		@Override
		protected double getCurrentRampY() {
			return super.getCurrentRampY();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.bellaire.aerbot.systems.WheelSystem#updateSmartDashboard(com.
		 * bellaire.aerbot.input.InputMethod)
		 */
		@Override
		public void updateSmartDashboard(InputMethod input) {
		}

		@Override
		protected GyroSystem getGyro() {
			return super.getGyro();
		}

		@Override
		protected void setGyro(GyroSystem gyro) {
			super.setGyro(gyro);
		}

		@Override
		protected AccelerometerSystem getAccelerometer() {
			return super.getAccelerometer();
		}

		@Override
		protected void setAccelerometer(AccelerometerSystem accelerometer) {
			super.setAccelerometer(accelerometer);
		}

		@Override
		protected double getCorrectRotate() {
			return super.getCorrectRotate();
		}

		@Override
		protected void setCorrectRotate(double correctRotate) {
			super.setCorrectRotate(correctRotate);
		}

		@Override
		protected boolean isStraightDriving() {
			return super.isStraightDriving();
		}

		@Override
		public void straightDrive(double moveValue) throws NullPointerException {
			driveYvalue = moveValue;
		}

		@Override
		public void automaticGearShift() {
		}

		@Override
		protected void setStraightDriving(boolean straightDriving) {
			super.setStraightDriving(straightDriving);
		}

		@Override
		protected void setGear(int gear) {
			super.setGear(gear);
		}

		@Override
		protected double getCurrentLeftY() {
			return super.getCurrentLeftY();
		}

		@Override
		protected void setCurrentLeftY(double currentLeftY) {
			super.setCurrentLeftY(currentLeftY);
		}

		@Override
		protected Timer getTimer() {
			return super.getTimer();
		}

		@Override
		protected void setTimer(Timer timer) {
			super.setTimer(timer);
		}

		@Override
		public void gearsOff() {
			setGear(0);
		}

		@Override
		public void gearsReverse() {
			setGear(1);
		}

		@Override
		public void arcadeDrive(double moveValue, double rotateValue) {
			driveYvalue = moveValue;
			driveXvalue = rotateValue;
		}

	}

}
