package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    private final SparkMax shooterRight; //= new CANSparkMax(17, MotorType.kBrushless);
    private final SparkMax shooterLeft; //= new CANSparkMax(18, MotorType.kBrushless);

    public Shooter() {
        shooterRight = new SparkMax(17, MotorType.kBrushless);
        shooterLeft = new SparkMax(18, MotorType.kBrushless);
        shooterRight.setInverted(true);
    }

    public void full() {
        set(1);
    }

    public void stop() {
        set(0);
    }

    public void set(double k) {
        shooterRight.set(k);
        shooterLeft.set(k);
    }
}
