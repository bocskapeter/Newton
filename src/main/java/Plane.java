import java.util.ArrayList;
import java.util.List;

public class Plane implements Feature {
    public final static Plane XY = new Plane(Value.ZERO,Value.ZERO,Value.ONE,Value.ZERO);
    public final static Plane XZ = new Plane(Value.ZERO,Value.ONE,Value.ZERO,Value.ZERO);
    public final static Plane YZ = new Plane(Value.ONE,Value.ZERO,Value.ZERO,Value.ZERO);
    private Value x;
    private Value y;
    private Value z;
    private Value d;

    public Plane(Value x, Value y, Value z, Value d) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.d = d;
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

    public Value getD() {
        return d;
    }

    @Override
    public List<Value> getValues() {
        List<Value> result = new ArrayList<>();
        result.add(x);
        result.add(y);
        result.add(z);
        result.add(d);
        return result;
    }
}
