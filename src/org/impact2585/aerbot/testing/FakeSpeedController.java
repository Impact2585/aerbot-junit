package org.impact2585.aerbot.testing;

import edu.wpi.first.wpilibj.SpeedController;

public class FakeSpeedController implements SpeedController {
	
	private double speed, pidOutput;
	private byte sync;

	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.PIDOutput#pidWrite(double)
	 */
	@Override
	public void pidWrite(double output) {
		pidOutput = output;
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
		sync = syncGroup;
	}

	/**
	 * @return the pidOutput
	 */
	public double getPidOutput() {
		return pidOutput;
	}

	/**
	 * @param pidOutput the pidOutput to set
	 */
	protected void setPidOutput(double pidOutput) {
		this.pidOutput = pidOutput;
	}

	/**
	 * @return the sync
	 */
	public byte getSync() {
		return sync;
	}

	/**
	 * @param sync the sync to set
	 */
	protected void setSync(byte sync) {
		this.sync = sync;
	}

}
