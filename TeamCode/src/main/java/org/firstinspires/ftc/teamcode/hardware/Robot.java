package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
// TODO: add imports for new components (CRServo, Servo, IMU, etc.)

public class Robot {
    public DcMotor rightFront, leftFront, rightBack, leftBack;
    // TODO: declare new motors here
    // TODO: declare new servos here
    // TODO: declare sensors here

    public HardwareMap hw;

    /** FIXED: Robot constructor now stores the hardware map AND initializes. */
    public Robot(HardwareMap hardwareMap) {
        init(hardwareMap);
    }

    /** Proper init for all motors */
    public void init(HardwareMap hardwareMap) {
        this.hw = hardwareMap;

        // Motors (MAKE SURE names match your config!)
        rightFront = initMotor("RightFront", false);
        leftFront  = initMotor("LeftFront", true);
        rightBack  = initMotor("RightBack", false);
        leftBack   = initMotor("LeftBack", true);

        // TODO: init new motors here

        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        // TODO: init new servos here

        // TODO: init sensors here

        // Brake mode default
        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private DcMotor initMotor(String name, boolean forward) {
        DcMotor m = hw.get(DcMotor.class, name);

        m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        return m;
    }

    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior) {
        if (leftFront != null) leftFront.setZeroPowerBehavior(behavior);
        if (rightFront!= null) rightFront.setZeroPowerBehavior(behavior);
        if (leftBack  != null) leftBack.setZeroPowerBehavior(behavior);
        if (rightBack != null) rightBack.setZeroPowerBehavior(behavior);
        // TODO: add new motors here if they need zero power behavior set
    }

    public void resetEncoders() {
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}