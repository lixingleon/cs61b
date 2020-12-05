public class TestPlanet {
   public static void main(String [] args) {
       Planet p1 = new Planet(1, 2, 3, 4, 5, "a");
       Planet p2 = new Planet(2, 3, 4, 5, 6, "b");
       System.out.print("hey, force between these two planets is "+p1.calcForceExertedBy(p2));
   }
}
