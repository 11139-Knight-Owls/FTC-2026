package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

@TeleOp(name = "TeleOp26", group = "ITD")
public class TeleOp26 extends LinearOpMode {

    Robot robot;
    Drive drive;

    Intake intake;

    @Override
    public void runOpMode() {

        // Initialize robot hardware
        robot = new Robot(hardwareMap);
        drive = new Drive(robot, telemetry);
        intake = new Intake(robot, telemetry);


        waitForStart();

        while (opModeIsActive()) {

            // 1. Driving
            drive.driveLoop(gamepad1.right_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);

            // 2. Intake
            if(gamepad2.right_trigger > 0.1) {
                intake.run(1);
            }else{
                intake.stop();
            }



        }
    }
}
