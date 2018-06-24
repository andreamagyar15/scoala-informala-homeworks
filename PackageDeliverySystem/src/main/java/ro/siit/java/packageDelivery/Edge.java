/*
Edge between the source and destination with distance
 */
package ro.siit.java.packageDelivery;

public class Edge {
    private String a;
    private String b;
    private int dist;

    public Edge(String a, String b, int dist) {
        this.a = a;
        this.b = b;
        this.dist = dist;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public int getDist() {
        return dist;
    }

}
