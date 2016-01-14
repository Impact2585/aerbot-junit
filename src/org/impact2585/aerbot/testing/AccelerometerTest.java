package org.impact2585.aerbot.testing;

import org.impact2585.aerbot.systems.AccelerometerSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.wpi.first.wpilibj.Timer;

public class AccelerometerTest {
	
	private TestAccelerometerSystem accelerometer;
	double time, accelerationX;

	@Before
	public void setUp() {
		accelerometer = new TestAccelerometerSystem();
		// give accelerometer a fake timer
		accelerometer.setTimer(new Timer(){

			/* (non-Javadoc)
			 * @see edu.wpi.first.wpilibj.Timer#get()
			 */
			@Override
			public synchronized double get() {
				return time;
			}

			/* (non-Javadoc)
			 * @see edu.wpi.first.wpilibj.Timer#reset()
			 */
			@Override
			public synchronized void reset() {
				
			}
			
		});
		time = 20 / 1000d;// 20 ms
	}

	@Test
	public void testGetSpeed() {
		double expectedSpeed = 0;
		accelerationX = 0.1;
		Assert.assertEquals(expectedSpeed, accelerometer.getSpeed(), 0);
		for(int i = 0; i < 50; i++){
			accelerationX = Math.random();
			double speed = accelerometer.getSpeed();
			// accelerometer should reset if there is little speed and acceleration otherwise it should get the integral of acceleration
			expectedSpeed = Math.abs(accelerometer.getAccelerationX()) < 0.15 && Math.abs(speed) < 0.5 ? 0 : expectedSpeed + accelerometer.getAccelerationX() * AccelerometerSystem.ACCELERATION_DUE_TO_GRAVITY * time;
			Assert.assertEquals(expectedSpeed, speed, 0);
		}
	}
	
	/**
	 * subclass uses fake accelerations and overrides an accessor and mutator
	 */
	private class TestAccelerometerSystem extends AccelerometerSystem{
		
		/* (non-Javadoc)
		 * @see org.impact2585.aerbot.systems.AccelerometerSystem#getAccelerationX()
		 */
		@Override
		public double getAccelerationX() {
			return accelerationX;
		}

		/* (non-Javadoc)
		 * @see org.impact2585.aerbot.systems.AccelerometerSystem#getTimer()
		 */
		@Override
		protected Timer getTimer() {
			return super.getTimer();
		}

		/* (non-Javadoc)
		 * @see org.impact2585.aerbot.systems.AccelerometerSystem#setTimer(edu.wpi.first.wpilibj.Timer)
		 */
		@Override
		protected void setTimer(Timer timer) {
			super.setTimer(timer);
		}
	}

}
