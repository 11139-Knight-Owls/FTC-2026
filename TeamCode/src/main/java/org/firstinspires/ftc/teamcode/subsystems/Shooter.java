package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.Robot;

public class Shooter {

    protected final Robot robot;
    protected final Telemetry telemetry;
    private final VoltageSensor batteryVoltageSensor;

    // Nominal voltage baseline for compensation
    private static final double TUNED_VOLTAGE = 13.1;

    public static double kF = 1.0 / 3350;
    public static double kP = 0.0005;

    private double targetRPM = 2680.0;

    public Shooter(Robot robot, Telemetry telemetry) {
        this.robot = robot;
        this.telemetry = telemetry;

        this.batteryVoltageSensor = robot.hw.voltageSensor.iterator().next();

        robot.shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.shooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    // =======================================================
    //                PUBLIC API ACTION METHODS
    // =======================================================

    public void setTargetRpm(double rpm) {
        this.targetRPM = rpm;
    }

    /**
     * \Call this continuously in your OpMode loop while shooting!
     */

    public void shoot() {
        double currentRPM = getVelo();
        double error = targetRPM - currentRPM;

        double nominalPower = (targetRPM * kF) + (error * kP);
        double currentVoltage = batteryVoltageSensor.getVoltage();
        double compensatedPower = nominalPower * (TUNED_VOLTAGE / currentVoltage);

        compensatedPower = Range.clip(compensatedPower, 0.0, 1.0);

        robot.shooter.setPower(compensatedPower);
    }

    public void stop() {
        robot.shooter.setPower(0.0);
    }

    public boolean isAtSpeed(double toleranceRpm) {
        return Math.abs(getVelo() - targetRPM) <= toleranceRpm;
    }

    // =======================================================
    //                HELPER FUNCTIONS
    // =======================================================

    public void log() {
        telemetry.addData("Shooter Target RPM", "%.0f", targetRPM);
        telemetry.addData("Shooter Current RPM", "%.0f", getVelo());
        telemetry.addData("Shooter Power", "%.2f", robot.shooter.getPower());
        telemetry.addData("Battery Voltage", "%.2f V", batteryVoltageSensor.getVoltage());
    }

    public double getVelo() {
        return (robot.shooter.getVelocity() / 28.0) * 60.0;
    }
}
