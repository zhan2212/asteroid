package _08final.mvc.model;

import java.awt.*;
import java.util.ArrayList;

public class SmallBullet extends Sprite {
    private final double FIRE_POWER = 30.0;
    public SmallBullet(double deltaX, double deltaY, Point center, int orientation){

        super();
        setTeam(Team.FRIEND);

        //defined the points on a cartesean grid
        ArrayList<Point> pntCs = new ArrayList<Point>();

        pntCs.add(new Point(0,3)); //top point
        pntCs.add(new Point(1,0));
        pntCs.add(new Point(0,1));
        pntCs.add(new Point(-1,0));

        assignPolarPoints(pntCs);

        //a bullet expires after 20 frames
        setExpire(10);
        setRadius(20);
        setColor(Color.YELLOW);


        //everything is relative to the falcon ship that fired the bullet
        setDeltaX( deltaX +
                Math.cos( Math.toRadians( orientation ) ) * FIRE_POWER );
        setDeltaY( deltaY +
                Math.sin( Math.toRadians( orientation ) ) * FIRE_POWER );
        setCenter( center );

        //set the bullet orientation to the falcon (ship) orientation
        setOrientation(orientation);

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
