package frc.robot.subsystems;

import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    private final SparkMax shooterRight; //= new CANSparkMax(17, MotorType.kBrushless);
    private final SparkMax shooterLeft; //= new CANSparkMax(18, MotorType.kBrushless);
    

    SparkMaxConfig leftConfig = new SparkMaxConfig();
    SparkMaxConfig rightConfig = new SparkMaxConfig();

    public static double kP =.1, kI = 0, kD = 0.00001;


    public Shooter() {
    shooterRight = new SparkMax(17, MotorType.kBrushless);
    shooterLeft = new SparkMax(18, MotorType.kBrushless);

    leftConfig.closedLoop
        .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
        .p(kP)
        .i(kI)
        .d(kD);

    rightConfig
        .inverted(true)
        .follow(shooterLeft, true);

    shooterRight.configure(
        rightConfig,
        SparkMax.ResetMode.kResetSafeParameters,
        SparkMax.PersistMode.kPersistParameters
    );
}

public void setVelocity(double rpm) {
    var pid = shooterLeft.getClosedLoopController();
    pid.setSetpoint(rpm, SparkMax.ControlType.kVelocity);
}

    public void fullPower() {
        set(.5); // .8
    }

    public void stopPower() {
        set(0);
    }

    public void set(double k) {
        shooterLeft.set(k);
    }

    public double getVelocity() {
        return shooterLeft.getEncoder().getVelocity();
    }
}
