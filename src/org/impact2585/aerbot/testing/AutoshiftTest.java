package org.impact2585.aerbot.testing;

import org.impact2585.aerbot.systems.AccelerometerSystem;
import org.impact2585.aerbot.systems.WheelSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.wpi.first.wpilibj.Timer;

public class AutoshiftTest {
	
	private final AutoshiftAccelerometerSystem accelerometer = new AutoshiftAccelerometerSystem();
	private final AutoshiftWheelSystem wheelSystem = new AutoshiftWheelSystem();

	private double accelerometerSpeed;
	private double yInput;
	
	@Before
	public void setUp() {
		wheelSystem.setAccelerometer(accelerometer);
		wheelSystem.setTimer(new AutoshiftTimer());
	}

	@Test
	public void test() {
		// test if automatic gearshifting works correctly
		yInput = 1;
		wheelSystem.setCurrentLeftY(yInput);
		for(int i = 0; i < 100; i++){
			accelerometerSpeed = Math.random() * WheelSystem.SHIFTING_SPEED * 2;
			wheelSystem.automaticGearShift();
			System.out.println("speed: " + accelerometerSpeed);
			System.out.println("gear: " + wheelSystem.getGear());
			Assert.assertEquals(wheelSystem.getGear() == 0, accelerometerSpeed <= WheelSystem.SHIFTING_SPEED);
		}
	}
	
	public class AutoshiftAccelerometerSystem extends AccelerometerSystem{

		/* (non-Javadoc)
		 * @see org.impact2585.aerbot.systems.AccelerometerSystem#getSpeed()
		 */
		@Override
		public synchronized double getSpeed() {
			return accelerometerSpeed;
		}
		
	}
	
	public class AutoshiftWheelSystem extends WheelSystem{

		/* (non-Javadoc)
		 * @see org.impact2585.aerbot.systems.WheelSystem#setAccelerometer(org.impact2585.aerbot.systems.AccelerometerSystem)
		 */
		@Override
		protected void setAccelerometer(AccelerometerSystem accelerometer) {
			super.setAccelerometer(accelerometer);
		}
		
		@Override
		public void gearsOff() {
			setGear(0);
		}

		@Override
		public void gearsReverse() {
			setGear(1);
		}
		
		/* (non-Javadoc)
		 * @see org.impact2585.aerbot.systems.WheelSystem#setCurrentLeftY(double)
		 */
		@Override
		protected void setCurrentLeftY(double currentLeftY) {
			super.setCurrentLeftY(currentLeftY);
		}

		/* (non-Javadoc)
		 * @see org.impact2585.aerbot.systems.WheelSystem#setTimer(edu.wpi.first.wpilibj.Timer)
		 */
		@Override
		protected void setTimer(Timer timer) {
			super.setTimer(timer);
		}

	}
	
	public class AutoshiftTimer extends Timer{

		@Override
		public synchronized double get() {
			return WheelSystem.SHIFT_DELAY + 1;
		}

		@Override
		public synchronized void reset() {
			
		}
		
	}

}
