package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;


@TeleOp(name = "ZeroServo0")
public class ZeroServo extends LinearOpMode {

    public Servo zero;

    @Override
    public void runOpMode() {

        zero = hardwareMap.servo.get("zero");


        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.dpad_down) {
                zero.setPosition(0);
            } else if (gamepad1.dpad_up) {
                zero.setPosition(1);
            }




        }
    }
}
