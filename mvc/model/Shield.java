package _08final.mvc.model;

import java.awt.*;
import java.util.ArrayList;

public class Shield extends Sprite {

    public Shield(Falcon fal, int val){

        super();
        setTeam(Team.FRIEND);

        //defined the points on a cartesean grid
        ArrayList<Point> pntCs = new ArrayList<Point>();

        pntCs.add(new Point(0,10)); //top point

        pntCs.add(new Point(8,-5));
        pntCs.add(new Point(-8,-5));

        assignPolarPoints(pntCs);

        //a bullet expires after 60 frames
        setExpire(100);
        setRadius(60);


        //everything is relative to the falcon ship
        setDeltaX( fal.getDeltaX() );
        setDeltaY( fal.getDeltaY() );
        setCenter( fal.getCenter() );

        //set the shield orientation to the falcon (ship) orientation
        setOrientation(fal.getOrientation()+val);
        setColor(Color.MAGENTA);


    }

    @Override
    public void move(){
        super.move();
        if (getExpire() == 0)
            CommandCenter.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
        else
            setExpire(getExpire() - 1);

    }

}
