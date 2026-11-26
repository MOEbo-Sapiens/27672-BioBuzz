package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Basic Auto", group = "Linear OpMode")
public class Auto extends LinearOpMode {

    private DcMotor frontLeftMotor;
    private DcMotor backLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backRightMotor;

    @Override
    public void runOpMode() {
        // 1. Initialize hardware using your exact configuration names
        frontLeftMotor  = hardwareMap.get(DcMotor.class, "frontLeftMotor");  // CH Port 0
        backLeftMotor   = hardwareMap.get(DcMotor.class, "backLeftMotor");   // CH Port 1
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor"); // CH Port 2
        backRightMotor  = hardwareMap.get(DcMotor.class, "backRightMotor");  // CH Port 3

        // 2. Reverse the left side so positive power moves all four wheels forward
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        // 3. Set all motors to BRAKE when power is 0 so the robot stops immediately
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized - Ready to Start");
        telemetry.update();

        // Wait for the driver to press PLAY
        waitForStart();

        if (opModeIsActive()) {
            // --- STEP 1: Drive Forward ---
            setDrivePower(0.25);
            sleep(500); // Run for 0.5 seconds

            // --- STOP RIGHT AFTER ---
            setDrivePower(0);

            telemetry.addData("Status", "Finished and Stopped");
            telemetry.update();
        }
    }

    // Helper method to set power to all 4 drive motors at once
    private void setDrivePower(double power) {
        frontLeftMotor.setPower(power);
        backLeftMotor.setPower(power);
        frontRightMotor.setPower(power);
        backRightMotor.setPower(power);
    }
}