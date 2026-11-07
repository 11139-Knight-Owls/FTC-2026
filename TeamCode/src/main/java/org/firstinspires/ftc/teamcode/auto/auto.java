package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

// Drive Forward and park Auto
@Autonomous(name = "park auto", group = "Auto")
public class auto extends LinearOpMode {

    // Starting-point tuning values. These are placeholders; tune with the robot on the field.
    private static final double APPROACH_SECONDS = 10;
    private static final double SHOOT_SECONDS = 5.0;
    private static final double RETREAT_SECONDS = 0.8;
    private static final double DRIVE_POWER = 0.35;
    private static final double FEEDER_POWER = 1.0;

    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap);
        Drive drive = new Drive(robot, telemetry);
        Shooter shooter = new Shooter(robot, telemetry);

        telemetry.addLine("Hive auto ready");
        telemetry.addLine("Check robot starts facing the Hive and balls are loaded.");
        telemetry.update();

        waitForStart();
        if (isStopRequested()) {
            return;
        }

        try {
            // Approach the Hive. Positive drive is forward in Drive.setMecanum().
            driveFor(drive, DRIVE_POWER, APPROACH_SECONDS);

            // Spin up, then run the shooter and both ball-feeding motors long enough
            // to empty the preload. Increase SHOOT_SECONDS if balls remain.
            shooter.shoot();
            sleep(3000);
            if (opModeIsActive()) {
                robot.intake.setPower(FEEDER_POWER);
                robot.transfer.setPower(FEEDER_POWER);
                sleep((long) (SHOOT_SECONDS * 1000));
            }
            shooter.stop();
            robot.intake.setPower(0);
            robot.transfer.setPower(0);

            // Back away from the Hive.
            driveFor(drive, -DRIVE_POWER, RETREAT_SECONDS);
        } finally {
            drive.stop();
            shooter.stop();
            robot.intake.setPower(0);
            robot.transfer.setPower(0);
        }
    }

    private void driveFor(Drive drive, double power, double seconds) {
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        while (opModeIsActive() && timer.seconds() < seconds) {
            drive.setMecanum(power, 0, 0, 1.0);
            idle();
        }
        drive.stop();
    }
}
