package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Transfer extends SubsystemBase {

    private final SparkMax transfer;

    public Transfer() {
        transfer = new SparkMax(21, MotorType.kBrushless); // transfer

        SparkMaxConfig transferConfig = new SparkMaxConfig();
        transferConfig
            .inverted(true);

    transfer.configure(
        transferConfig,
        SparkMax.ResetMode.kResetSafeParameters,
        SparkMax.PersistMode.kPersistParameters
    );
    }
    public void full() {
        transfer.set(1);
    }

    public void reverse() {
        transfer.set(-1);
    }

    public void stop() {
        transfer.set(0);
    }

    public void set(double k) {
        transfer.set(k);
    }
}