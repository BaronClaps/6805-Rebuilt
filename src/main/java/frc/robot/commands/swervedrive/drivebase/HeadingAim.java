package frc.robot.commands.swervedrive.drivebase;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class HeadingAim extends Command {
private final SwerveSubsystem m_drive;
private final PIDController m_pid;
private final double m_targetAngle;
public double rotationDivider = 3; // Divides the output of the PID to convert from rad/s to a more reasonable value for the drive method
private static final Translation2d zeroTranslation = new Translation2d(0, 0);

public HeadingAim(SwerveSubsystem drive, double targetAngle) {
this.m_drive = drive;
this.m_targetAngle = targetAngle;
this.m_pid = new PIDController(0.008, 0, 0);

// Tells PID that -180 and 180 are the same spot
this.m_pid.enableContinuousInput(-180, 180);
this.m_pid.setTolerance(1.5); // Stop within 1.5 degrees

addRequirements(m_drive);
}

@Override
public void execute() {
double currentYaw = m_drive.getHeading().getRadians();
double rotationOutput = m_pid.calculate(currentYaw, m_targetAngle);
m_drive.drive(zeroTranslation, rotationOutput / rotationDivider, false); // Divide by rotationDivider to convert radian/s to a more reasonable value
}

@Override
public void end(boolean interrupted) {
m_drive.drive(zeroTranslation, 0, false); // Stop when done
}

@Override
public boolean isFinished() {
return m_pid.atSetpoint();
}
}
