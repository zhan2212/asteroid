package _08final.mvc.model;

import java.awt.*;
import java.util.ArrayList;

public class ClearAllWeapon extends Sprite {

    private int size = 5;


    public ClearAllWeapon(Falcon fal){

        super();
        setTeam(Team.FRIEND);

        //defined the points on a cartesean grid
        ArrayList<Point> pntCs = new ArrayList<Point>();

        pntCs.add(new Point(0,3)); //top point

        pntCs.add(new Point(3,0));
        pntCs.add(new Point(0,-3));
        pntCs.add(new Point(-3,0));

        assignPolarPoints(pntCs);

        //a bullet expires after 20 frames
        setExpire(60);
        setRadius(size);


        //everything is relative to the falcon ship that fired the bullet
        setDeltaX( fal.getDeltaX());
        setDeltaY( fal.getDeltaY() );
        setCenter( fal.getCenter() );

        //set the bullet orientation to the falcon (ship) orientation
        setOrientation(fal.getOrientation());


    }

    @Override
    public void move(){

        if (getExpire() == 0)
            CommandCenter.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
        else
            this.size += 10;
            setRadius(size); // gradually grows
            setExpire(getExpire() - 1);

    }

}