package com.bellaire.aerbot.testing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bellaire.aerbot.custom.MultiMotor;

import edu.wpi.first.wpilibj.SpeedController;

public class MultiMotorTest {
	
	private MultiMotor multiMotor;
	private FakeSpeedController speedController1;
	private FakeSpeedController speedController2;

	@Before
	public void setUp() {
		// initialize instance variables
		speedController1 = new FakeSpeedController();
		speedController2 = new FakeSpeedController();
		multiMotor = new MultiMotor(new SpeedController[]{speedController1,speedController2});
	}

	@Test
	public void test() {
		double speed = Math.random();
		
		// test set methods and get method
		multiMotor.set(speed, Byte.MAX_VALUE);
		Assert.assertEquals(speed, speedController1.get(), 0);
		Assert.assertEquals(speed, speedController2.get(), 0);
		Assert.assertEquals(speed, multiMotor.get(), 0);
		Assert.assertEquals(Byte.MAX_VALUE, speedController1.getSync());
		Assert.assertEquals(Byte.MAX_VALUE, speedController2.getSync());
		
		// test pidWrite
		multiMotor.pidWrite(speed);
		Assert.assertEquals(speed, speedController1.getPidOutput(), 0);
		Assert.assertEquals(speed, speedController2.getPidOutput(), 0);
		
		// test disable
		multiMotor.disable();
		Assert.assertEquals(0, multiMotor.get(), 0);
	}

}
