package org.impact2585.aerbot.testing;

import org.impact2585.aerbot.custom.DoubleSolenoid;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;

public class DoubleSolenoidTest {

	private DoubleSolenoid doubleSolenoide;
	private Relay.Value relay1, relay2;

	public class FakeDoubleSolenoid extends DoubleSolenoid {

		public FakeDoubleSolenoid() {
			super(null, null);
		}

		@Override
		public void setRelayValues(Value relayOne, Value relayTwo) {
			relay1 = relayOne;
			relay2 = relayTwo;
		}

	}

	@Before
	public void setUp() {
		// init instance variables
		doubleSolenoide = new FakeDoubleSolenoid();
	}

	@Test
	public void test() {

		// pretext
		Assert.assertEquals(doubleSolenoide.isDefaultState(), true);

		// test toggle
		doubleSolenoide.toggle();
		Assert.assertEquals(relay1, Relay.Value.kForward);
		Assert.assertEquals(relay2, Relay.Value.kOff);

		doubleSolenoide.toggle();
		Assert.assertEquals(relay1, Relay.Value.kOff);
		Assert.assertEquals(relay2, Relay.Value.kForward);

	}

}
