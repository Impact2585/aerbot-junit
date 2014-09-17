package com.bellaire.aerbot.testing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bellaire.aerbot.Environment;
import com.bellaire.aerbot.TeleopExecuter;
import com.bellaire.aerbot.systems.IntakeSystem;
import com.bellaire.aerbot.systems.ShooterSystem;
import com.bellaire.aerbot.systems.WheelSystem;

public class TeleopExecuterTest {
	
	private TeleopExecuter executer;
	private Environment environment;
	private boolean shooterRan, intakeRan, wheelsRan;

	@Before
	public void setUp() {
		ShooterSystem shooter = new ShooterSystem(){

			/* (non-Javadoc)
			 * @see com.bellaire.aerbot.systems.ShooterSystem#run()
			 */
			@Override
			public void run() {
				shooterRan = true;
			}
			
		};
		
		IntakeSystem intake = new IntakeSystem(){

			/* (non-Javadoc)
			 * @see com.bellaire.aerbot.systems.IntakeSystem#run()
			 */
			@Override
			public void run() {
				intakeRan = true;
			}
			
		};
		
		WheelSystem wheels = new WheelSystem(){

			/* (non-Javadoc)
			 * @see com.bellaire.aerbot.systems.WheelSystem#run()
			 */
			@Override
			public void run() {
				wheelsRan = true;
			}
			
		};
		
		environment = new Environment(){

			/* (non-Javadoc)
			 * @see com.bellaire.aerbot.Environment#getWheelSystem()
			 */
			@Override
			public WheelSystem getWheelSystem() {
				return wheels;
			}

			/* (non-Javadoc)
			 * @see com.bellaire.aerbot.Environment#getShooterSystem()
			 */
			@Override
			public ShooterSystem getShooterSystem() {
				return shooter;
			}

			/* (non-Javadoc)
			 * @see com.bellaire.aerbot.Environment#getIntakeSystem()
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
