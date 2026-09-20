package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.Robot;

/**
 * Generic subsystem template that only uses hardware inside Robot.java.
 * Copy → rename → customize.
 */
public class Claw {

    protected final Robot robot;
    protected final Telemetry telemetry;

    public Claw(Robot robot, Telemetry telemetry) {
        this.robot = robot;
        this.telemetry = telemetry;

        // You may configure robot hardware for your subsystem here:
        // robot.launcher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // robot.intakeL.setDirection(CRServo.Direction.FORWARD);
    }

    // =======================================================
    //                PUBLIC API ACTION METHODS
    // =======================================================

    /** Example—replace with real behavior */
    public void open() {
        robot.clawL.setPosition(1);
        robot.clawR.setPosition(0);
    }

    /** Stop everything related to this subsystem */
    public void close() {
        robot.clawR.setPosition(1);
        robot.clawL.setPosition(0);
    }

    // =======================================================
    //                HELPER FUNCTIONS
    // =======================================================

    /** Example mode switcher */
    public void runWithoutEncoder() {
        // robot.launcher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /** Optional telemetry */
    public void log() {
        // telemetry.addData("LauncherRPM", currentRPM);
        // telemetry.addData("IntakePower", robot.intakeL.getPower());
    }
}