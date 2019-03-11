//package LactoseIntolerant;
//
//import android.text.ParcelableSpan;
//
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
//
//import LactoseIntolerantLibs.CSV;
//import LactoseIntolerantLibs.Drivetrain;
//import LactoseIntolerantLibs.Gyro;
//
//@TeleOp
//        (name = "Test", group = "TeleOp")
//public class Motion extends OpMode {
//
//    Drivetrain drive;
//    Gyro gyro;
//    OpMode mOpMode = this;
//    boolean toggle = false;
//    StringBuilder saveTo;
//    ElapsedTime time;
//
//    public void init() {
//        drive = new Drivetrain(mOpMode);
//        gyro = new Gyro(mOpMode, true);
//        saveTo = new StringBuilder();
//        time = new ElapsedTime();
//        time.startTime();
//        saveTo.append("Time,X-Accel,Y-Accel,Z-Accel\n");
//    }
//
//    public void loop() {
//        if (gamepad1.x) {
//            toggle = !toggle;
//        }
//        if (toggle) {
//            drive.setPower(1);
//            Acceleration a = gyro.getAcceleration();
//            saveTo.append(""+time.milliseconds() + "," + a.xAccel + "," + a.yAccel + "," + a.zAccel + "\n");
//        }
//        if (gamepad1.y) {
//            CSV.writeToCSV(saveTo.toString());
//        }
//    }
//}