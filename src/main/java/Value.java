public class Value {
    public final static short FIX = 0;
    public final static short VARIABLE = 1;

    public final static Value ZERO = new Value(FIX, 0);
    public final static Value ONE = new Value(FIX, 1.0);

    private short status;
    private double value;
    private double stored;

    public Value(double value) {
        this.value = value;
        this.stored = value;
        this.status = VARIABLE;
    }

    public Value(short status, double value) {
        this.status = status;
        this.value = value;
        this.stored = value;
    }

    public double get() {
        return value;
    }

    public void set(double newValue){
        if (status==VARIABLE) {
            this.value = newValue;
        }
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public void store() {
        stored = value;
    }

    public void restore() {
        value = stored;
    }

    @Override
    public String toString() {
        return "Value{" +
                "status=" + status +
                ", value=" + value +
                ", stored=" + stored +
                '}';
    }
}
