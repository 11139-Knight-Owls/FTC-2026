package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

@TeleOp(name = "TeleOp26", group = "ITD")
public class TeleOp26 extends LinearOpMode {

    Robot robot;
    Drive drive;

    Claw claw;
    Intake intake;

    Lift lift;

    Shooter shooter;

    @Override
    public void runOpMode() {

        // Initialize robot hardware
        robot = new Robot(hardwareMap);
        drive = new Drive(robot, telemetry);
        intake = new Intake(robot, telemetry);
        claw = new Claw(robot, telemetry);
        lift = new Lift(robot, telemetry);
        shooter = new Shooter(robot, telemetry);


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
            // 3. Claw
            if(gamepad2.left_trigger > 0.1){
                claw.close();
            }
            if(gamepad2.left_trigger < 0.1){
                claw.open();
            }
            // 4. Lift
            if(gamepad2.left_stick_y > 0.1){
                lift.goUp(gamepad2.left_stick_y);
            }
            if (gamepad2.left_stick_y < -0.1){
                lift.goDown(gamepad1.left_stick_y);
            }
            // 5. Shooter
            if (gamepad1.right_trigger > 0.1){
                shooter.shoot();
            }else{
                shooter.stop();
            }



        }
    }
}
