package org._2585robophiles.aerbot.testing;

import org._2585robophiles.aerbot.input.InputMethod;
import org._2585robophiles.aerbot.systems.IntakeSystem;
import org._2585robophiles.aerbot.systems.ShooterSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.SpeedController;

public class IntakeTest {
	
	private SpeedController motor;
	private ShooterSystem shooter;
	private TestIntakeSystem intake;
	private boolean intakeOpen, shooterOpen;
	private double shooterSpeed;
	private int motorInput;
	private boolean autointakeInput;
	private boolean catchInput;

	@Before
	public void setUp() {
		motor = new FakeSpeedController();
		shooter = new ShooterSystem(){

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.systems.ShooterSystem#open()
			 */
			@Override
			public void open() {
				shooterOpen = true;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.systems.ShooterSystem#close()
			 */
			@Override
			public void close() {
				shooterOpen = false;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.systems.ShooterSystem#setMotor(double)
			 */
			@Override
			public void setMotor(double speed) {
				shooterSpeed = speed;
			}
			
		};
		
		intake = new TestIntakeSystem();
		intake.setSpeedController(motor);
		intake.setShooter(shooter);
	}

	@Test
	public void test() {
		
		// test manual motor control
		motorInput = -1;
		autointakeInput = false;
		catchInput = false;
		
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
				return catchInput;
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
				return motorInput;
			}

			@Override
			public boolean intakeToggle() {
				return autointakeInput;
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

			@Override
			public Joystick[] joysticks() {
				return null;
			}
			
		};
		
		intake.intake(input);
		
		Assert.assertEquals(motorInput, motor.get(), 0);
		
		motorInput = 1;
		intake.intake(input);
		
		Assert.assertEquals(motorInput, motor.get(), 0);
		
		motorInput = 0;
		intake.intake(input);
		
		Assert.assertEquals(motorInput, motor.get(), 0);
		
		// test auto intake
		Assert.assertEquals(Relay.Value.kReverse, intake.intakeLiftState());
		autointakeInput = true;
		intake.intake(input);
		
		Assert.assertEquals(Relay.Value.kForward, intake.intakeLiftState());
		Assert.assertEquals(1, motor.get(), 0);
		
		autointakeInput = false;
		intake.intake(input);
		
		Assert.assertEquals(Relay.Value.kReverse, intake.intakeLiftState());
		Assert.assertEquals(0, motor.get(), 0);
		
		// test catching
		Assert.assertEquals(shooterSpeed, 0, 0);
		Assert.assertFalse(shooterOpen);
		Assert.assertFalse(intakeOpen);
		
		catchInput = true;
		
		for(int i = 0; i < 2; i++){
			intake.intake(input);

			Assert.assertEquals(-.25, shooterSpeed, 0);
			Assert.assertTrue(intakeOpen);
			Assert.assertTrue(shooterOpen);

			catchInput = !catchInput;
			autointakeInput = !autointakeInput;// make sure you can still intake while catching
		}
		autointakeInput = false;
		intake.intake(input);
		Assert.assertEquals(0, shooterSpeed, 0);
		Assert.assertFalse(intakeOpen);
		Assert.assertFalse(shooterOpen);
		Assert.assertEquals(0, motor.get(), 0);
	}
	
	private class TestIntakeSystem extends IntakeSystem{

		/* (non-Javadoc)
		 * @see org._2585robophiles.aerbot.systems.IntakeSystem#open()
		 */
		@Override
		public void open() {
			intakeOpen = true;
		}

		/* (non-Javadoc)
		 * @see org._2585robophiles.aerbot.systems.IntakeSystem#close()
		 */
		@Override
		public void close() {
			intakeOpen = false;
		}

		/* (non-Javadoc)
		 * @see org._2585robophiles.aerbot.systems.IntakeSystem#intakeLiftState()
		 */
		@Override
		public Value intakeLiftState() {
			return intakeOpen ? Relay.Value.kForward : Relay.Value.kReverse;
		}

		/* (non-Javadoc)
		 * @see org._2585robophiles.aerbot.systems.IntakeSystem#setIntake(edu.wpi.first.wpilibj.SpeedController)
		 */
		@Override
		protected void setSpeedController(SpeedController intake) {
			super.setSpeedController(intake);
		}

		/* (non-Javadoc)
		 * @see org._2585robophiles.aerbot.systems.IntakeSystem#setShooter(org._2585robophiles.aerbot.systems.ShooterSystem)
		 */
		@Override
		protected void setShooter(ShooterSystem shooter) {
			super.setShooter(shooter);
		}
		
	}

}
