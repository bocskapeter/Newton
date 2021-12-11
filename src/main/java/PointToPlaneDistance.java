import java.util.ArrayList;
import java.util.List;

public class PointToPlaneDistance implements Constraint {
    private Plane plane;
    private Point point;
    private Value d;

    public PointToPlaneDistance(Plane plane, Point point, Value d) {
        this.plane = plane;
        this.point = point;
        this.d = d;
    }

    public Plane getPlane() {
        return plane;
    }

    public Point getPoint() {
        return point;
    }

    public Value getD() {
        return d;
    }


    @Override
    public double getValue() {
        return plane.getX().get() * point.getX().get() +
                plane.getY().get() * point.getY().get() +
                plane.getX().get() * point.getZ().get() +
                d.get();
    }

    @Override
    public double getDerivative(Value value) {
        if (value == plane.getX()) return point.getX().get();
        if (value == plane.getY()) return point.getY().get();
        if (value == plane.getZ()) return point.getZ().get();
        if (value == point.getX()) return plane.getX().get();
        if (value == point.getY()) return plane.getY().get();
        if (value == point.getZ()) return plane.getZ().get();
        return 0;
    }

    @Override
    public List<Value> getValues() {
        List<Value> result = new ArrayList<>();
        result.addAll(plane.getValues());
        result.addAll(point.getValues());
        result.add(d);
        return result;
    }
}
