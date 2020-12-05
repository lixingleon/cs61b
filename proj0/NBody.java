import java.util.ArrayList;

public class NBody {
    public static double readRadius(String filepath){
        In in = new In(filepath);
        int firstItem = in.readInt();
        double secondItem = in.readDouble();
        return secondItem;
    }
    public static Planet[] readPlanets(String filepath){
        In in = new In(filepath);
        int firstitem = in.readInt();
        double radius = in.readDouble();
        ArrayList<Planet> planets = new ArrayList<Planet>();
       for(int i = 0;i<5;i++){
            planets.add(new Planet(in.readDouble(),in.readDouble() , in.readDouble(), in.readDouble(), in.readDouble(), in.readString()) );
        }
        return planets.toArray(new Planet[planets.size()]);
    }

    public static void main (String[] args){
        double T = Double.parseDouble(args[0]);
        double dt =Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);
        StdDraw.setScale(-radius,radius);
        StdDraw.clear();
        StdDraw.picture(0,0,"./images/starfield.jpg");
        for (Planet p: allPlanets){
            p.draw();
        }
        StdDraw.enableDoubleBuffering();
        double time = 0.0;
        while(time<=T){
            double[] xForces = new double[5];
            double[] yForces = new double[5];
            for(int i = 0; i<5;i++){
                xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
            }
            for(int i = 0; i<5;i++){
                allPlanets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0,0,"./images/starfield.jpg");
            for (Planet p: allPlanets){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time = time+dt;
        }
        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
            allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);   
        }

    }
}
