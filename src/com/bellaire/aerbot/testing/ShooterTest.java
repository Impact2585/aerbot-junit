package com.bellaire.aerbot.testing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bellaire.aerbot.input.InputMethod;
import com.bellaire.aerbot.systems.ShooterSystem;

import edu.wpi.first.wpilibj.SpeedController;

public class ShooterTest {
	
	private TestShooterSystem shooter;
	private FakeSpeedController motor;
	private boolean shooterOpen, autoshooting, manualShooting;

	@Before
	public void setUp() {
		// initialize motor and shooter objects
		shooter = new TestShooterSystem();
		motor = new FakeSpeedController();
		shooter.setSpeedController(motor);
	}

	@Test
	public void test() {
		autoshooting = true;
		InputMethod shootInput = new InputMethod(){

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
				return false;
			}

			@Override
			public boolean catchBall() {
				return false;
			}

			@Override
			public boolean shoot() {
				return autoshooting;
			}

			@Override
			public boolean shooterLift() {
				return manualShooting;
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
				return false;
			}
			
		};
		// start shooting
		shooter.shoot(shootInput);
		Assert.assertEquals(1, motor.get(), 0);
		Assert.assertFalse(shooterOpen);
		
		// cancel shot
		shooter.setLastPress(shooter.getLastPress() - 1000);
		shooter.setShootStart(shooter.getShootStart() - 1500);
		shooter.shoot(shootInput);
		Assert.assertEquals(0, motor.get(), 0);
		Assert.assertFalse(shooterOpen);
		
		// actually shoot this time
		shooter.setLastPress(shooter.getLastPress() - 1000);
		shooter.shoot(shootInput);
		Assert.assertEquals(1, motor.get(), 0);
		Assert.assertFalse(shooterOpen);
		
		// shot delay elapses and it should shoot
		autoshooting = false;
		shooter.setShootStart(shooter.getShootStart() - 4250);
		shooter.shoot(shootInput);
		Assert.assertEquals(1, motor.get(), 0);
		Assert.assertTrue(shooterOpen);
		
		// the shooter should close once again
		shooter.setShootStart(shooter.getShootStart() - 4500);
		shooter.shoot(shootInput);
		Assert.assertEquals(0, motor.get(), 0);
		Assert.assertFalse(shooterOpen);
		
		// test manual shooting
		manualShooting = true;
		for(int i = 0; i < 2; i++){
			shooter.shoot(shootInput);
			Assert.assertEquals(1, motor.get(), 0);
			Assert.assertTrue(shooterOpen);

			manualShooting = !manualShooting;
		}
		shooter.shoot(shootInput);
		Assert.assertEquals(0, motor.get(), 0);
		Assert.assertFalse(shooterOpen);
	}
	
	private class TestShooterSystem extends ShooterSystem{

		/* (non-Javadoc)
		 * @see com.bellaire.aerbot.systems.ShooterSystem#open()
		 */
		@Override
		public void open() {
			shooterOpen = true;
		}

		/* (non-Javadoc)
		 * @see com.bellaire.aerbot.systems.ShooterSystem#close()
		 */
		@Override
		public void close() {
			shooterOpen = false;
		}

		/* (non-Javadoc)
		 * @see com.bellaire.aerbot.systems.ShooterSystem#getShootStart()
		 */
		@Override
		protected long getShootStart() {
			return super.getShootStart();
		}

		/* (non-Javadoc)
		 * @see com.bellaire.aerbot.systems.ShooterSystem#setShootStart(long)
		 */
		@Override
		protected void setShootStart(long shootStart) {
			super.setShootStart(shootStart);
		}

		/* (non-Javadoc)
		 * @see com.bellaire.aerbot.systems.ShooterSystem#getLastPress()
		 */
		@Override
		protected long getLastPress() {
			return super.getLastPress();
		}

		/* (non-Javadoc)
		 * @see com.bellaire.aerbot.systems.ShooterSystem#setLastPress(long)
		 */
		@Override
		protected void setLastPress(long lastPress) {
			super.setLastPress(lastPress);
		}

		/* (non-Javadoc)
		 * @see com.bellaire.aerbot.systems.ShooterSystem#getCurrent()
		 */
		@Override
		protected long getCurrent() {
			return super.getCurrent();
		}

		/* (non-Javadoc)
		 * @see com.bellaire.aerbot.systems.ShooterSystem#setCurrent(long)
		 */
		@Override
		protected void setCurrent(long current) {
			super.setCurrent(current);
		}

		/* (non-Javadoc)
		 * @see com.bellaire.aerbot.systems.ShooterSystem#getShooter()
		 */
		@Override
		protected SpeedController getSpeedController() {
			return super.getSpeedController();
		}

		/* (non-Javadoc)
		 * @see com.bellaire.aerbot.systems.ShooterSystem#setShooter(edu.wpi.first.wpilibj.SpeedController)
		 */
		@Override
		protected void setSpeedController(SpeedController shooter) {
			super.setSpeedController(shooter);
		}
		
	}

}
