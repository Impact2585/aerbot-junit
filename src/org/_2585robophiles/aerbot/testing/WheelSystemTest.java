package org._2585robophiles.aerbot.testing;

import org._2585robophiles.aerbot.input.InputMethod;
import org._2585robophiles.aerbot.systems.AccelerometerSystem;
import org._2585robophiles.aerbot.systems.GyroSystem;
import org._2585robophiles.aerbot.systems.WheelSystem;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import edu.wpi.first.wpilibj.Joystick;
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

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#getLeftX()
			 */
			@Override
			public double getLeftX() {
				return 0;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#getRightX()
			 */
			@Override
			public double getRightX() {
				return 0;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#getLeftY()
			 */
			@Override
			public double getLeftY() {
				return 1;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#getRightY()
			 */
			@Override
			public double getRightY() {
				return 0;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#shift()
			 */
			@Override
			public boolean shift() {
				return false;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#catchBall()
			 */
			@Override
			public boolean catchBall() {
				return false;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#shoot()
			 */
			@Override
			public boolean shoot() {
				return false;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#shooterLift()
			 */
			@Override
			public boolean shooterLift() {
				return false;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#intake()
			 */
			@Override
			public int intake() {
				return 0;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#intakeToggle()
			 */
			@Override
			public boolean intakeToggle() {
				return false;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#intakeLift()
			 */
			@Override
			public boolean intakeLift() {
				return false;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#directionToggle()
			 */
			@Override
			public boolean directionToggle() {
				return false;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#straightDrive()
			 */
			@Override
			public boolean straightDrive() {
				return true;
			}
			
			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#joysticks()
			 */
			@Override
			public Joystick[] joysticks() {
				return null;
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

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#getLeftX()
			 */
			@Override
			public double getLeftX() {
				return 0;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#getRightX()
			 */
			@Override
			public double getRightX() {
				return 1;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#getLeftY()
			 */
			@Override
			public double getLeftY() {
				return 0;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#getRightY()
			 */
			@Override
			public double getRightY() {
				return 0;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#shift()
			 */
			@Override
			public boolean shift() {
				return false;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#catchBall()
			 */
			@Override
			public boolean catchBall() {
				return false;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#shoot()
			 */
			@Override
			public boolean shoot() {
				return false;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#shooterLift()
			 */
			@Override
			public boolean shooterLift() {
				return false;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#intake()
			 */
			@Override
			public int intake() {
				return 0;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#intakeToggle()
			 */
			@Override
			public boolean intakeToggle() {
				return false;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#intakeLift()
			 */
			@Override
			public boolean intakeLift() {
				return false;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#directionToggle()
			 */
			@Override
			public boolean directionToggle() {
				return false;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#straightDrive()
			 */
			@Override
			public boolean straightDrive() {
				return true;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#joysticks()
			 */
			@Override
			public Joystick[] joysticks() {
				return null;
			}

		};

		Assert.assertTrue(driveXvalue == 0);
		wheelSystem.move(input2);
		Assert.assertTrue(driveYvalue == -0.375);
		Assert.assertTrue(driveXvalue == 1);
		
		InputMethod input3 = new InputMethod() { // test shift value

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#getLeftX()
			 */
			@Override
			public double getLeftX() {
				return 0;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#getRightX()
			 */
			@Override
			public double getRightX() {
				return 0;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#getLeftY()
			 */
			@Override
			public double getLeftY() {
				return 0;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#getRightY()
			 */
			@Override
			public double getRightY() {
				return 0;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#shift()
			 */
			@Override
			public boolean shift() {
				return true;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#catchBall()
			 */
			@Override
			public boolean catchBall() {
				return false;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#shoot()
			 */
			@Override
			public boolean shoot() {
				return false;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#shooterLift()
			 */
			@Override
			public boolean shooterLift() {
				return false;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#intake()
			 */
			@Override
			public int intake() {
				return 0;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#intakeToggle()
			 */
			@Override
			public boolean intakeToggle() {
				return false;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#intakeLift()
			 */
			@Override
			public boolean intakeLift() {
				return false;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#directionToggle()
			 */
			@Override
			public boolean directionToggle() {
				return false;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#straightDrive()
			 */
			@Override
			public boolean straightDrive() {
				return true;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#joysticks()
			 */
			@Override
			public Joystick[] joysticks() {
				return null;
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
		
		// test direction toggle
		wheelSystem.move(new InputMethod() {
			
			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#straightDrive()
			 */
			@Override
			public boolean straightDrive() {
				return false;
			}
			
			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#shooterLift()
			 */
			@Override
			public boolean shooterLift() {
				return false;
			}
			
			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#shoot()
			 */
			@Override
			public boolean shoot() {
				return false;
			}
			
			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#shift()
			 */
			@Override
			public boolean shift() {
				return false;
			}
			
			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#intakeToggle()
			 */
			@Override
			public boolean intakeToggle() {
				return false;
			}
			
			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#intakeLift()
			 */
			@Override
			public boolean intakeLift() {
				return false;
			}
			
			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#intake()
			 */
			@Override
			public int intake() {
				return 0;
			}
			
			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#getRightY()
			 */
			@Override
			public double getRightY() {
				return 0;
			}
			
			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#getRightX()
			 */
			@Override
			public double getRightX() {
				return 0;
			}
			
			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#getLeftY()
			 */
			@Override
			public double getLeftY() {
				return 0;
			}
			
			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#getLeftX()
			 */
			@Override
			public double getLeftX() {
				return 0;
			}
			
			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#directionToggle()
			 */
			@Override
			public boolean directionToggle() {
				return true;
			}
			
			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#catchBall()
			 */
			@Override
			public boolean catchBall() {
				return false;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.input.InputMethod#joysticks()
			 */
			@Override
			public Joystick[] joysticks() {
				return null;
			}
		});
		Assert.assertTrue(driveYvalue < 0);
		wheelSystem.move(input1);
		Assert.assertTrue(driveYvalue > 0);
	}

	private class TestWheelSystem extends WheelSystem {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org._2585robophiles.aerbot.systems.WheelSystem#getCurrentRampY()
		 */
		@Override
		protected double getCurrentRampY() {
			return super.getCurrentRampY();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org._2585robophiles.aerbot.systems.WheelSystem#updateSmartDashboard(com.
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
