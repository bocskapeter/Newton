import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularValueDecomposition;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello...");

        Value clickedX = new Value(1.25);
        Value clickedY = new Value(1.78);
        Value clickedZ = new Value(0.0625);
        Point clickedPoint = new Point(clickedX,clickedY,clickedZ);
        PointToPlaneDistance pointToPlaneDistance1 = new PointToPlaneDistance(Plane.XY,clickedPoint,Value.ZERO);
        PointToPlaneDistance pointToPlaneDistance2 = new PointToPlaneDistance(Plane.YZ,clickedPoint,clickedX);
        PointToPlaneDistance pointToPlaneDistance3 = new PointToPlaneDistance(Plane.XZ,clickedPoint,clickedY);

        List<Constraint> constraints = new ArrayList<>();
        constraints.add(pointToPlaneDistance1);
        constraints.add(pointToPlaneDistance2);
        constraints.add(pointToPlaneDistance3);

        List<Value> valueList = pointToPlaneDistance1.getValues();
        valueList.addAll(pointToPlaneDistance2.getValues());
        valueList.addAll(pointToPlaneDistance3.getValues());

        List<Value> variables = new ArrayList<>();
        for (Value value : valueList){
            if (!variables.contains(value) && value.getStatus()==Value.VARIABLE){
                variables.add(value);
            }
        }

        for (Constraint constraint: constraints){

        }

        RealMatrix coefficients =
                new Array2DRowRealMatrix(new double[][]{{ 0, 0, 1.0},
                        {0, 0, 0},
                        {0, 0, 0}},
                        false);
        DecompositionSolver solver = new SingularValueDecomposition(coefficients).getSolver();

        RealVector constants = new ArrayRealVector(new double[]{1.25, 1.78, 1.3125}, false);
        RealVector solution = solver.solve(constants);

        System.out.println("S: " + solution.toString());
    }
}
