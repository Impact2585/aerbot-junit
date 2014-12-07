package org._2585robophiles.aerbot.testing;

import org._2585robophiles.aerbot.Environment;
import org._2585robophiles.aerbot.TeleopExecuter;
import org._2585robophiles.aerbot.systems.IntakeSystem;
import org._2585robophiles.aerbot.systems.ShooterSystem;
import org._2585robophiles.aerbot.systems.WheelSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TeleopExecuterTest {
	
	private TeleopExecuter executer;
	private Environment environment;
	private boolean shooterRan, intakeRan, wheelsRan;

	@Before
	public void setUp() {
		ShooterSystem shooter = new ShooterSystem(){

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.systems.ShooterSystem#run()
			 */
			@Override
			public void run() {
				shooterRan = true;
			}
			
		};
		
		IntakeSystem intake = new IntakeSystem(){

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.systems.IntakeSystem#run()
			 */
			@Override
			public void run() {
				intakeRan = true;
			}
			
		};
		
		WheelSystem wheels = new WheelSystem(){

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.systems.WheelSystem#run()
			 */
			@Override
			public void run() {
				wheelsRan = true;
			}
			
		};
		
		environment = new Environment(){

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.Environment#getWheelSystem()
			 */
			@Override
			public WheelSystem getWheelSystem() {
				return wheels;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.Environment#getShooterSystem()
			 */
			@Override
			public ShooterSystem getShooterSystem() {
				return shooter;
			}

			/* (non-Javadoc)
			 * @see org._2585robophiles.aerbot.Environment#getIntakeSystem()
			 */
			@Override
			public IntakeSystem getIntakeSystem() {
				return intake;
			}
			
		};
		
		executer = new TeleopExecuter(environment);
	}

	@Test
	public void test() {
		// make sure all the systems run
		executer.execute();
		Assert.assertTrue(intakeRan);
		Assert.assertTrue(shooterRan);
		Assert.assertTrue(wheelsRan);
	}

}
