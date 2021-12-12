import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularValueDecomposition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello...");

        Value c1X = new Value(1.25);
        Value c1Y = new Value(1.78);
        Value c1Z = new Value(0.0625);
        Point clickedPoint1 = new Point(c1X, c1Y, c1Z);

        Value c2X = new Value(5.5);
        Value c2Y = new Value(1.78);
        Value c2Z = new Value(0.0625);
        Point clickedPoint2 = new Point(c2X, c2Y, c2Z);

        PointToPlaneDistance pointToPlaneDistance1 = new PointToPlaneDistance(Plane.XY, clickedPoint1, Value.ZERO);
        PointToPlaneDistance pointToPlaneDistance2 = new PointToPlaneDistance(Plane.YZ, clickedPoint1, c1X);
        PointToPlaneDistance pointToPlaneDistance3 = new PointToPlaneDistance(Plane.XZ, clickedPoint1, c1Y);

        PointToPlaneDistance pointToPlaneDistance4 = new PointToPlaneDistance(Plane.XY, clickedPoint2, Value.ZERO);
        PointToPlaneDistance pointToPlaneDistance6 = new PointToPlaneDistance(Plane.XZ, clickedPoint2, c2Y);

        Value distance = new Value(Value.FIX,5.00);

        PointToPointDistance pointToPointDistance = new PointToPointDistance(clickedPoint1,clickedPoint2,distance);

        List<Constraint> constraints = new ArrayList<>();
        constraints.add(pointToPlaneDistance1);
        constraints.add(pointToPlaneDistance2);
        constraints.add(pointToPlaneDistance3);
        constraints.add(pointToPlaneDistance4);
        constraints.add(pointToPlaneDistance6);
        constraints.add(pointToPointDistance);

        List<Value> valueList = new ArrayList<>();
        for (Constraint constraint : constraints) {
            valueList.addAll(constraint.getValues());
        }

        List<Value> variables = new ArrayList<>();
        for (Value value : valueList) {
            if (!variables.contains(value) && value.getStatus() == Value.VARIABLE) {
                variables.add(value);
            }
        }
        System.out.println("Values: " + valueList);
        System.out.println("Constraints: " + constraints.size());
        System.out.println("Variables: " + variables.size());

        double[] fx = new double[constraints.size()];
        double[][] dfx = new double[constraints.size()][constraints.size()];



        RealMatrix coefficients;
        DecompositionSolver solver;
        RealVector constants;
        RealVector solution;

        for (int k = 0; k <10; k++){

            System.out.println("Iteration: " + k);


            for (int i = 0; i < constraints.size(); i++) {
                fx[i] = -1.0 * constraints.get(i).getValue();
                for (int j = 0; j < constraints.size(); j++) {
                    dfx[i][j] = constraints.get(i).getDerivative(variables.get(j));
                }
            }

            System.out.println("Clicked Points: " + clickedPoint1 + "\n" + clickedPoint2);
            System.out.println("Fx: " + Arrays.toString(fx));
            for (double[] line : dfx) {
                System.out.println("dFx: " + Arrays.toString(line));
            }

            coefficients = new Array2DRowRealMatrix(dfx, false);
            solver = new SingularValueDecomposition(coefficients).getSolver();
            constants = new ArrayRealVector(fx, false);
            solution = solver.solve(constants);

            System.out.println("S: " + solution.toString());

            for (int i = 0; i < variables.size(); i++){
                variables.get(i).set(variables.get(i).get()+solution.getEntry(i));
            }
            System.out.println("x: " + variables);
        }
    }
}
