import java.util.List;

public interface Constraint {
    /**
     * @return list of values - xn
     */
    List<Value> getValues();

    /**
     * @return function value - f(xn)
     */
    double getValue();

    /**
     * @return partial derivative value - ∂f(xn)/∂xn
     */
    double getDerivative(Value value);
}
