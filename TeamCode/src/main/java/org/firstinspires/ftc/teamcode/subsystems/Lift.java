package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.Robot;

import com.qualcomm.robotcore.hardware.DcMotorEx;


/**
 * Generic subsystem template that only uses hardware inside Robot.java.
 * Copy → rename → customize.
 */
public class Lift {

    protected final Robot robot;
    protected final Telemetry telemetry;

    public Lift(Robot robot, Telemetry telemetry) {
        this.robot = robot;
        this.telemetry = telemetry;

        // You may configure robot hardware for your subsystem here:
        //robot.lift.setMode(DcMotorEx.RunMode.);
        // robot.intakeL.setDirection(CRServo.Direction.FORWARD);
    }

    // =======================================================
    //                PUBLIC API ACTION METHODS
    // =======================================================

    /** Example—replace with real behavior */
    public void move(double power) {
         robot.lift.setPower(power);
    }

    public void goUp(int pos) {
        robot.lift.setTargetPosition(pos);
        robot.lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

    }

    /** Stop everything related to this subsystem */
    public void stop() {
         robot.lift.setPower(0);
    }

    // =======================================================
    //                HELPER FUNCTIONS
    // =======================================================

    /** Example mode switcher */
    public void resetEncoder() {
        robot.lift.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

    }

    public void runWithoutEncoder() {
         robot.lift.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    }

    /** Optional telemetry */
    public void log() {
        // telemetry.addData("LauncherRPM", currentRPM);
        // telemetry.addData("IntakePower", robot.intakeL.getPower());
    }
}