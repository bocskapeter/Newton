import java.util.ArrayList;
import java.util.List;

public class PointToPointDistance implements Constraint {
    private final Point point1;
    private final Point point2;
    private final Value distance;

    public PointToPointDistance(Point point1, Point point2, Value distance) {
        this.point1 = point1;
        this.point2 = point2;
        this.distance = distance;
    }

    @Override
    public List<Value> getValues() {
        List<Value> result = new ArrayList<>();
        result.addAll(point1.getValues());
        result.addAll(point2.getValues());
        result.add(distance);
        return result;
    }

    @Override
    public double getValue() {
        return distance() - distance.get();
    }

    private double distance(){
        double x = point2.getX().get() - point1.getX().get();
        double y = point2.getY().get() - point1.getY().get();
        double z = point2.getZ().get() - point1.getZ().get();
        return Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public double getDerivative(Value value) {
        if (value == point1.getX()) return -1.0 * (point2.getX().get() - point1.getX().get()) / distance();
        if (value == point2.getX()) return -1.0 * (point1.getX().get() - point2.getX().get()) / distance();
        if (value == point1.getY()) return -1.0 * (point2.getY().get() - point1.getY().get()) / distance();
        if (value == point2.getY()) return -1.0 * (point1.getY().get() - point2.getY().get()) / distance();
        if (value == point1.getZ()) return -1.0 * (point2.getZ().get() - point1.getZ().get()) / distance();
        if (value == point2.getZ()) return -1.0 * (point1.getZ().get() - point2.getZ().get()) / distance();
        if (value == distance) return -1.0;
        return 0.0;
    }
}
