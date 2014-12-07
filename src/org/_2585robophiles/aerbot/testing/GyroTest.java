package org._2585robophiles.aerbot.testing;

import org._2585robophiles.aerbot.systems.GyroSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class GyroTest {
	
	private GyroSystem gyroSystem;
	private double angle;

	@Before
	public void setUp() {
		// create subclass of GyroSystem
		gyroSystem = new GyroSystem(){

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.systems.GyroSystem#getAngle()
			 */
			@Override
			public synchronized double getAngle() {
				return angle;
			}
			
		};
	}

	@Test
	public void testGetHeading() {
		// test if getHeading() always returns numbers from 0 - 359
		for(int i = 0; i < 100; i++){
			// generate a random number from 0 - 999 with a 50% chance of being negative
			angle = Math.random() * 1000;
			if(Math.random() < 0.5)
				angle *= -1;
			Assert.assertTrue(gyroSystem.getHeading()  > -1);
			Assert.assertTrue(gyroSystem.getHeading() < 360);
		}
	}

}
