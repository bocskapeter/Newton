import java.util.ArrayList;
import java.util.List;

public class PointToPlaneDistance implements Constraint {
    private final Plane plane;
    private final Point point;
    private final Value distance;

    public PointToPlaneDistance(Plane plane, Point point, Value distance) {
        this.plane = plane;
        this.point = point;
        this.distance = distance;
    }

    @Override
    public double getValue() {
        return plane.getX().get() * point.getX().get() +
                plane.getY().get() * point.getY().get() +
                plane.getZ().get() * point.getZ().get() +
                plane.getD().get() - distance.get();
    }

    @Override
    public double getDerivative(Value value) {
        if (value == plane.getX()) return point.getX().get();
        if (value == plane.getY()) return point.getY().get();
        if (value == plane.getZ()) return point.getZ().get();
        if (value == point.getX()) return plane.getX().get();
        if (value == point.getY()) return plane.getY().get();
        if (value == point.getZ()) return plane.getZ().get();
        if (value == distance) return 1.0;
        return 0;
    }

    @Override
    public List<Value> getValues() {
        List<Value> result = new ArrayList<>();
        result.addAll(plane.getValues());
        result.addAll(point.getValues());
        result.add(distance);
        return result;
    }
}
