package _08final.mvc.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Debris extends Sprite {

    private final double FIRE_POWER = 1;



    public Debris(double deltaX, double deltaY, Point center, int orientation){

        super();
        setTeam(Team.DEBRIS);

        //defined the points on a cartesean grid
        ArrayList<Point> pntCs = new ArrayList<Point>();

        Random rnd =  new Random();

        pntCs.add(new Point(0,1+rnd.nextInt(4))); //top point
        pntCs.add(new Point(2,2));
        pntCs.add(new Point(1+rnd.nextInt(4),0));
        pntCs.add(new Point(2,-2));
        pntCs.add(new Point(0,-1-rnd.nextInt(4)));
        pntCs.add(new Point(-2,-2));
        pntCs.add(new Point(-1-rnd.nextInt(4),0));
        pntCs.add(new Point(-2,2));

        assignPolarPoints(pntCs);

        //a bullet expires after 20 frames
        setExpire(5);
        setRadius(12);


        //everything is relative to the falcon ship that fired the bullet
        setDeltaX( deltaX +
                Math.cos( Math.toRadians( orientation ) ) * (FIRE_POWER+rnd.nextInt(15)) );
        setDeltaY( deltaY +
                Math.sin( Math.toRadians( orientation ) ) * (FIRE_POWER+rnd.nextInt(15)) );
        setCenter( center );

        //set the bullet orientation to the falcon (ship) orientation
        setOrientation(orientation);

        setColor(Color.red);

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

