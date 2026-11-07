package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.VoltageSensor;

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

        double speedScale = 1;
        boolean shooting = false;

        VoltageSensor batteryVoltageSensor = hardwareMap.voltageSensor.iterator().next();



        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.dpad_up){
                speedScale += 0.1;
            }
            if (gamepad1.dpad_down){
                speedScale -= 0.1;
            }
            if (gamepad1.dpad_left){
                speedScale = 0.4;
            }
            if (gamepad1.dpad_right){
                speedScale = 1;
            }
            // 1. Driving
            drive.driveLoop(gamepad1.right_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x, speedScale);
            telemetry.addData("Drive stick Y (right stick up/down)", gamepad1.right_stick_y);
            telemetry.addData("Wheel powers LF/RF/LB/RB", "%.2f / %.2f / %.2f / %.2f",
                    robot.leftFront.getPower(), robot.rightFront.getPower(),
                    robot.leftBack.getPower(), robot.rightBack.getPower());
            telemetry.addData("shooting??",shooting);
            telemetry.addData("shooter vero", shooter.getVelo());
            telemetry.update();

            // 2. Intake
            if(gamepad1.right_trigger > 0.1) {
                intake.run(0.9);
            } else if(gamepad1.right_trigger < 0.1 && !gamepad1.y){
                intake.stop();
            }
            // 3. Transfer
            if(gamepad1.right_trigger >  0.1) {
                transfer.run(1);
            } else if(gamepad1.right_trigger < 0.1) {
                transfer.stop();
            }
            //purge
            if (gamepad1.y){
                intake.run(-1);
                transfer.run(-1);
            }else if (gamepad1.yWasReleased()){
                intake.stop();
                transfer.stop();
            }

            // 4. Lift
            if(gamepad2.left_stick_y > 0.1){
                lift.goUp(gamepad2.left_stick_y);
            } else if (gamepad2.left_stick_y < -0.1){
                lift.goDown(gamepad2.left_stick_y);
            } else {
                lift.stop();
            }
            // 5. Claw
            if (gamepad2.dpad_up) {
                claw.open();
            } else if (gamepad2.dpad_down) {
                claw.close();
            }
            // 6. Shooter

            double currentVoltage = batteryVoltageSensor.getVoltage();

            if (gamepad1.a && !shooting){
                shooting = true;
            } else if (gamepad1.a && shooting){
                shooting = false;
            }

            if (shooting) {
                shooter.shoot();
            } else {
                shooter.stop();
            }



        }
    }
}

