package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;

public class HollywoodTimer {
    public void hollyWait(double Time){
        Timer hollyTimer = new Timer();
        boolean done;
        hollyTimer.start();
        if(hollyTimer.get()<Time){
            done = false;
        }else{
            done = true;
        }
        while(done == false){}
    }
}
