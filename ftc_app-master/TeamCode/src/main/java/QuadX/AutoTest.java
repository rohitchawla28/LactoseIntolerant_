package QuadX;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Libraries.Drivetrain;


@Autonomous(name = "AutoTest", group = "Auto")

public class AutoTest extends LinearOpMode{

    Drivetrain dt;

    @Override
    public void runOpMode() throws InterruptedException {
        dt = new Drivetrain(this);

        telemetry.addLine("Initialized");
        telemetry.update();

        AutoTransitioner.transitionOnStop(this, "TeleOp");

        waitForStart();

        dt.moveEncoder(0.5,100,1000);




    }

}