package _08final.mvc.model;

import java.awt.*;
import java.util.ArrayList;

public class newWeapon extends Sprite {

    private final double FIRE_POWER = 20.0;
    private final int MAX_EXPIRE = 10;

    //for drawing alternative shapes
    //you could have more than one of these sets so that your sprite morphs into various shapes
    //throughout its life
    public double[] dLengthsAlts;
    public double[] dDegreesAlts;

    public newWeapon(Falcon fal) {

        super();
        setTeam(Team.FRIEND);
        //defined the points on a cartesean grid
        ArrayList<Point> pntCs = new ArrayList<Point>();


        pntCs.add(new Point(0, 8));
        pntCs.add(new Point(-2, 4));
        pntCs.add(new Point(-1, 5));
        pntCs.add(new Point(-2, 0));
        pntCs.add(new Point(0, 2));
        pntCs.add(new Point(2, 0));
        pntCs.add(new Point(1, 5));
        pntCs.add(new Point(2, 4));
        assignPolarPoints(pntCs);

        //a bullet expires after 20 frames
        setExpire(MAX_EXPIRE);
        setRadius(20);

        //these are alt points
        ArrayList<Point> pntAs = new ArrayList<Point>();
        pntAs.add(new Point(0, 5));
        pntAs.add(new Point(1, 3));
        pntAs.add(new Point(1, -2));
        pntAs.add(new Point(-1, -2));
        pntAs.add(new Point(-1, 3));
        assignPolorPointsAlts(pntAs);


        //everything is relative to the falcon ship that fired the bullet
        setDeltaX( fal.getDeltaX() +
                Math.cos( Math.toRadians( fal.getOrientation() ) ) * FIRE_POWER );
        setDeltaY( fal.getDeltaY() +
                Math.sin( Math.toRadians( fal.getOrientation() ) ) * FIRE_POWER );
        setCenter( fal.getCenter() );

        //set the bullet orientation to the falcon (ship) orientation
        setOrientation(fal.getOrientation());
        setColor(Color.YELLOW);

    }

    @Override
    public void move(){
        super.move();
        if (getExpire() == 0){
            CommandCenter.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
            //System.out.println(getOrientation());
            for (int deltaOrientation = -90; deltaOrientation <= 90; deltaOrientation += 10){
                CommandCenter.getInstance().getOpsList().enqueue(new SmallBullet(getDeltaX(),getDeltaY(),getCenter(),
                        getOrientation()+deltaOrientation), CollisionOp.Operation.ADD);
            }
        }
        else {
            setExpire(getExpire() - 1);
        }

    }


    @Override
    public void draw(Graphics g){

        if (getExpire() < MAX_EXPIRE -5)
            super.draw(g);
        else{
            drawAlt(g);
        }

    }
    //assign for alt imag
    protected void assignPolorPointsAlts(ArrayList<Point> pntCs) {
        dDegreesAlts = convertToPolarDegs(pntCs);
        dLengthsAlts = convertToPolarLens(pntCs);

    }
    public void drawAlt(Graphics g) {
        setXcoords( new int[dDegreesAlts.length]);
        setYcoords( new int[dDegreesAlts.length]);
        setObjectPoints( new Point[dDegrees.length]);

        for (int nC = 0; nC < dDegreesAlts.length; nC++) {

            setXcoord((int) (getCenter().x + getRadius()
                    * dLengthsAlts[nC]
                    * Math.sin(Math.toRadians(getOrientation()) + dDegreesAlts[nC])), nC);


            setYcoord((int) (getCenter().y - getRadius()
                    * dLengthsAlts[nC]
                    * Math.cos(Math.toRadians(getOrientation()) + dDegreesAlts[nC])), nC);
            //need this line of code to create the points which we will need for debris
            setObjectPoint( new Point(getXcoord(nC), getYcoord(nC)), nC);
        }


        g.setColor(Color.DARK_GRAY);
        g.drawPolygon(getXcoords(), getYcoords(), dDegreesAlts.length);
    }

}

