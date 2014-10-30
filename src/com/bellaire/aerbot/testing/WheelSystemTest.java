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

	TestWheelSystem wheelSystem;
	
	
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
		InputMethod input = new InputMethod(){

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
		
		wheelSystem.move(input);
		Assert.assertTrue(wheelSystem.getCurrentLeftY() == -0.5);
		
	}
	
	private class TestWheelSystem extends WheelSystem{

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
			super.gearsOff();
		}

		@Override
		public void gearsReverse() {
			super.gearsReverse();
		}

		@Override
		public void arcadeDrive(double moveValue, double rotateValue) {
			super.arcadeDrive(moveValue, rotateValue);
		}
		
		
		
	}

}
