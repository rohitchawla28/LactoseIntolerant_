package LactoseIntolerantLibs;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {

    private LinearOpMode opMode;

    private DcMotor intakeL;
    private DcMotor intakeR;

    private Servo pivotL;
    private Servo pivotR;

    private CRServo collectL;
    private CRServo collectR;

    public Intake (LinearOpMode opMode) {
        intakeL = opMode.hardwareMap.dcMotor.get("intakeL");
        intakeR = opMode.hardwareMap.dcMotor.get("intakeR");

        pivotL = opMode.hardwareMap.servo.get("pivotL");
        pivotR = opMode.hardwareMap.servo.get("pivotR");

        collectL = opMode.hardwareMap.crservo.get("collectL");
        collectR = opMode.hardwareMap.crservo.get("collectR");

    }

}
