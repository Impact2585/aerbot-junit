package com.bellaire.aerbot.testing;

import edu.wpi.first.wpilibj.SpeedController;

public class FakeSpeedController implements SpeedController {
	
	private double speed;

	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.PIDOutput#pidWrite(double)
	 */
	@Override
	public void pidWrite(double output) {

	}

	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.SpeedController#disable()
	 */
	@Override
	public void disable() {
		speed = 0;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.SpeedController#get()
	 */
	@Override
	public double get() {
		return speed;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.SpeedController#set(double)
	 */
	@Override
	public void set(double speed) {
		this.speed = speed;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.SpeedController#set(double, byte)
	 */
	@Override
	public void set(double speed, byte syncGroup) {
		set(speed);
	}

}
