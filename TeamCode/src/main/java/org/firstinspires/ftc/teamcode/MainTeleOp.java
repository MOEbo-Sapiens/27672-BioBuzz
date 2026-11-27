package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "27672_BioBuzz_TeleOp")
public class MainTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        /* -------------------- Get DC Motors -------------------- */
        DcMotor frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        DcMotor backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");


        DcMotor intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        DcMotor flywheelMotor = hardwareMap.get(DcMotor.class, "flywheelMotor");

        /* -------------------- Get Servos -------------------- */
        CRServo leftIntakeServo = hardwareMap.get(CRServo.class, "leftIntakeServo");
        CRServo rightIntakeServo = hardwareMap.get(CRServo.class, "rightIntakeServo");
        CRServo rampIntakeServo = hardwareMap.get(CRServo.class, "rampIntakeServo");

        /* -------------------- Reverse Direction for Motors/Servos -------------------- */
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightIntakeServo.setDirection(CRServo.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            /* -------------------- Get Gamepad Input -------------------- */
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;

            boolean left_bumper = gamepad1.left_bumper; // Intake
            boolean right_bumper = gamepad1.right_bumper; // Reverse Intake

            boolean right_trigger = gamepad1.right_trigger_pressed; // Shooter
            boolean left_trigger = gamepad1.left_trigger_pressed; // Reverse Shooter

            /* -------------------- Robot Motion -------------------- */
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);

            /* -------------------- Intake Mechanism --------------------  */
            if (left_bumper) {
                leftIntakeServo.setPower(-1);
                rightIntakeServo.setPower(-1);
                intakeMotor.setPower(1);

            } else if (right_bumper) {
                leftIntakeServo.setPower(1);
                rightIntakeServo.setPower(1);
                intakeMotor.setPower(-1);

            } else {
                leftIntakeServo.setPower(0);
                rightIntakeServo.setPower(0);
                intakeMotor.setPower(0);

            }

            /* -------------------- Ramp Intake Mechanism -------------------- */
            if (right_trigger) {
                rampIntakeServo.setPower(-1);
                flywheelMotor.setPower(1);

            } else if (left_trigger) {
                rampIntakeServo.setPower(1);
                flywheelMotor.setPower(-1);
            } else {
                rampIntakeServo.setPower(0);
                flywheelMotor.setPower(0);

            }
        }
    }
}