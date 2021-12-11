import java.util.ArrayList;
import java.util.List;

public class Point implements Feature {
    public final static Point ORIGIN = new Point(Value.ZERO,Value.ZERO,Value.ZERO);
    public final static Point I = new Point(Value.ONE,Value.ZERO,Value.ZERO);
    public final static Point J = new Point(Value.ZERO,Value.ONE,Value.ZERO);
    public final static Point K = new Point(Value.ZERO,Value.ZERO, Value.ONE);
    private Value x;
    private Value y;
    private Value z;

    public Point(Value x, Value y, Value z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Value getX() {
        return x;
    }

    public Value getY() {
        return y;
    }

    public Value getZ() {
        return z;
    }

    @Override
    public List<Value> getValues() {
        List<Value> result = new ArrayList<>();
        result.add(x);
        result.add(y);
        result.add(z);
        return result;
    }
}
