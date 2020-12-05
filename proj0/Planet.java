public class Planet {
public double xxPos;
public double yyPos;
public double xxVel;
public double yyVel;
public double mass;
public String imgFileName;

public Planet (double xP, double yP, double xV, double yV, double m, String img){
xxPos = xP; 
yyPos = yP;
xxVel = xV;
yyVel = yV;
mass = m;
imgFileName = img ;
}
public Planet(Planet p ){
xxPos = p.xxPos;
yyPos = p.yyPos;
xxVel = p.xxVel;
yyVel = p.yyVel;
mass = p.mass;
imgFileName = p.imgFileName;

}

public double calcDistance (Planet p){
    double dx = this.xxPos - p.xxPos;
    double dy = this.yyPos - p.yyPos;
    return Math.sqrt(Math.pow(dx, 2)+ Math.pow(dy, 2)) ;
}
public double calcForceExertedBy(Planet p){
    double G = 6.67e-11;
    double distance = this.calcDistance(p);
    return G * this.mass * p.mass/(distance*distance);
}

public double calcForceExertedByX(Planet p){
    double Force = calcForceExertedBy(p);
    double r = calcDistance(p);
    double dx = p.xxPos - this.xxPos;
    double dy = p.yyPos - this.yyPos;
    double Fx = Force * (dx/r);
    return Fx;

}

public double calcForceExertedByY(Planet p){
    double Force = calcForceExertedBy(p);
    double r = calcDistance(p);
    double dx = p.xxPos - this.xxPos;
    double dy = p.yyPos - this.yyPos;
    double Fy = Force*(dy/r);
    return Fy;
}

public double calcNetForceExertedByX(Planet[] allPlanets){
    double[] ForceOnX = new double[allPlanets.length];
    for (int i = 0; i<allPlanets.length; i++){
        if(this.equals(allPlanets[i])){
            ForceOnX[i] = 0;
        }
        else{
            ForceOnX[i] = calcForceExertedByX(allPlanets[i]);
        }
    }
    double sum = 0.0;
    // for (int i = 0; i<ForceOnX.length; i++){
    //     sum = sum+ForceOnX[i];
    // }
    for (double value: ForceOnX){
        sum = sum+value;
    }

    return sum;
}

public double calcNetForceExertedByY(Planet[] allPlanets){
    double[] ForceOnY = new double[allPlanets.length];
    for (int i = 0; i<allPlanets.length; i++){
        if(this.equals(allPlanets[i])){
            ForceOnY[i] = 0;
        }
        else{
            ForceOnY[i] = calcForceExertedByY(allPlanets[i]);
        }
    }
    double sum = 0.0;
    // for (int i = 0; i<ForceOnY.length; i++){
    //     sum = sum+ForceOnY[i];
    // }
    for (double value: ForceOnY){
        sum = sum+value;
    }

    return sum;
}

public void update(double dt, double fNetX, double fNetY){
    double ax = fNetX/mass;
    double ay = fNetY/mass;
    xxVel = xxVel+ dt*ax;
    yyVel= yyVel+ dt*ay;
    xxPos = xxPos+dt*xxVel;
    yyPos = yyPos+dt*yyVel;
}

public void draw(){
    StdDraw.picture(xxPos, yyPos, "./images/"+imgFileName);
}

}