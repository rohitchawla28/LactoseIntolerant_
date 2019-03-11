package LactoseIntolerant;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public abstract class TeleLibs extends OpMode {

    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;

    private DcMotor intakeL;
    private DcMotor intakeR;

    private DcMotor output;

    private CRServo collectL;
    private CRServo collectR;

    private DcMotor actuator;

    private Servo pivotL;
    private Servo pivotR;

    private Servo deposit;

    @Override
    public void init() {
        //MOTOR INITIALZATION
        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");

        actuator = hardwareMap.dcMotor.get("actuator");

        intakeL = hardwareMap.dcMotor.get("intakeL");
        intakeR = hardwareMap.dcMotor.get("intakeR");

        output = hardwareMap.dcMotor.get("output");

        fl.setDirection(DcMotorSimple.Direction.FORWARD);
        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.FORWARD);
        br.setDirection(DcMotorSimple.Direction.REVERSE);

        intakeL.setDirection(DcMotorSimple.Direction.FORWARD);
        intakeR.setDirection(DcMotorSimple.Direction.REVERSE);


        //SERVO INITIALIZATION
        pivotL = hardwareMap.servo.get("pivotL");
        pivotR = hardwareMap.servo.get("pivotR");
        deposit = hardwareMap.servo.get("outputServo");

        //left manip VEX motors out for now, cause not fully wired, add back once wired
        //collectL = hardwareMap.crservo.get("collectL");
        //collectR = hardwareMap.crservo.get("collectR");


        telemetry.addLine("Initialized");
        telemetry.update();

    }

    public void actuator() {
        boolean dpad_Up = gamepad2.dpad_up;
        boolean dpad_down = gamepad2.dpad_down;

        if(dpad_Up)
            actuator.setPower(1);
        else if (dpad_down)
            actuator.setPower(-1);
        else
            actuator.setPower(0);
    }

    public void intakeSlides()
    {
        double left_trigger = gamepad1.left_trigger;
        double right_trigger = gamepad1.right_trigger;

        if (left_trigger > 0.05) {
            //the right intake slide is set to reverse, may have to change that
            intakeL.setPower(-left_trigger);
            intakeR.setPower(-left_trigger);
        } else if (right_trigger > 0.05) {
            intakeL.setPower(right_trigger);
            intakeR.setPower(right_trigger);
        } else {
            intakeL.setPower(0);
            intakeR.setPower(0);
        }

    }

    public void arcadeDrive() {
        //checking for valid range to apply power (has to give greater power than .1)
        if (((Math.abs(Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y))) > .1) ||
                Math.abs(Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4) > .1) {

            double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double theta = Math.atan2(gamepad1.left_stick_y, -gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = -gamepad1.right_stick_x;

            //as per unit circle cos gives x, sin gives you y
            double FL = r * Math.cos(theta) + rightX;
            double FR = r * Math.sin(theta) - rightX;
            double BL = r * Math.sin(theta) + rightX;
            double BR = r * Math.cos(theta) - rightX;

            //make sure you don't try and give power bigger than 1
            if (((Math.abs(FL) > 1) || (Math.abs(BL) > 1)) || ((Math.abs(FR) > 1) || (Math.abs(BR) > 1))) {
                FL /= Math.max(Math.max(Math.abs(FL), Math.abs(FR)), Math.max(Math.abs(BL), Math.abs(BR)));
                BL /= Math.max(Math.max(Math.abs(FL), Math.abs(FR)), Math.max(Math.abs(BL), Math.abs(BR)));
                FR /= Math.max(Math.max(Math.abs(FL), Math.abs(FR)), Math.max(Math.abs(BL), Math.abs(BR)));
                BR /= Math.max(Math.max(Math.abs(FL), Math.abs(FR)), Math.max(Math.abs(BL), Math.abs(BR)));

            }
            fl.setPower(FL);
            fr.setPower(FR);
            bl.setPower(BL);
            br.setPower(BR);

        }
        else {
            fl.setPower(0);
            fr.setPower(0);
            bl.setPower(0);
            br.setPower(0);

        }

    }
}
