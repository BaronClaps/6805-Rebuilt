package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

    private final SparkMax pivot;
    private final SparkFlex spin;
    private double speed;
    // private final SparkClosedLoopController pivotController;

    // private static final int GEAR_RATIO = 5 * 4;

    // private static final double START_ANGLE = 0.0;
    // private static final double INTAKE_ANGLE = 10.0;

    // private static final double kP = 0.1;
    // private static final double kI = 0.0;
    // private static final double kD = 0.0;

    public Intake() {
        pivot = new SparkMax(19, MotorType.kBrushless);
        spin = new SparkFlex(20, MotorType.kBrushless);

        // pivotController = pivot.getClosedLoopController();

         SparkMaxConfig config = new SparkMaxConfig();
         config.inverted(true);
        // config.closedLoop.p(kP).i(kI).d(kD);
        // config.idleMode(IdleMode.kBrake);

        pivot.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

//     private double degreesToRotations(double degrees) {
//         return (degrees / 360.0) * GEAR_RATIO;
//     }

//     public double getAngle() {
//         return (pivot.getEncoder().getPosition() / GEAR_RATIO) * 360.0;
//     }

//     public void goToAngle(double angleDeg) {
//     pivotController.setSetpoint(
//         degreesToRotations(angleDeg),
//         ControlType.kPosition,
//         ClosedLoopSlot.kSlot0
//     );
// }

//     public void goToStart() {
//         goToAngle(START_ANGLE);
//     }

//     public void goToIntake() {
//         goToAngle(INTAKE_ANGLE);
//     }

    public void full() {
        spin.set(1);
    }

    public void reverse() {
        spin.set(-1);
    }

    public void stop() {
        spin.set(0);
    }

    public void set(double k) {
        spin.set(k);
        speed = k;
    }

    public void pivotSet(double k) {
        pivot.set(k);
    }

    public double speed() {
        return speed;
    }
}