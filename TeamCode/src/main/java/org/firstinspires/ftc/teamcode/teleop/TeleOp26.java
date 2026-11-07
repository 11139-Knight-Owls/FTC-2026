package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Transfer;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;



@TeleOp(name = "TeleOp26", group = "ITD")
public class TeleOp26 extends LinearOpMode {

    Robot robot;
    Drive drive;

    Claw claw;
    Intake intake;
    Transfer transfer;


    Lift lift;

    Shooter shooter;

    @Override
    public void runOpMode() {

        // Initialize robot hardware
        robot = new Robot(hardwareMap);
        drive = new Drive(robot, telemetry);
        intake = new Intake(robot, telemetry);
        transfer = new Transfer(robot, telemetry);
        claw = new Claw(robot, telemetry);
        lift = new Lift(robot, telemetry);
        shooter = new Shooter(robot, telemetry);


        waitForStart();

        while (opModeIsActive()) {

            // 1. Driving
            drive.driveLoop(gamepad1.right_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
            telemetry.addData("Drive stick Y (right stick up/down)", gamepad1.right_stick_y);
            telemetry.addData("Wheel powers LF/RF/LB/RB", "%.2f / %.2f / %.2f / %.2f",
                    robot.leftFront.getPower(), robot.rightFront.getPower(),
                    robot.leftBack.getPower(), robot.rightBack.getPower());
            telemetry.update();

            // 2. Intake
            if(gamepad1.right_trigger > 0.1) {
                intake.run(1);
            } else if(gamepad1.left_trigger > 0.1) {
                intake.run(-1);
            } else {
                intake.stop();
            }
            // 3. Transfer
            if(gamepad1.right_bumper) {
                transfer.run(1);
            } else if(gamepad1.left_bumper) {
                transfer.run(-1);
            } else {
                transfer.stop();
            }
            // 4. Lift
            if(gamepad2.left_stick_y > 0.1){
                lift.goUp();
            } else if (gamepad2.left_stick_y < -0.1){
                lift.goDown();
            } else {
                lift.stop();
            }
            // 5. Claw
            if (gamepad2.dpad_up) {
                claw.open();
            } else {
                claw.close();
            }
            // 6. Shooter
            if (gamepad2.right_trigger > 0.1){
                shooter.shoot();
            } else{
                shooter.stop();
            }



        }
    }
}

