public class IrisUnrecognized implements Iris {

    private double sepalLength;
    private double sepalWidth;
    private double petalLength;
    private double petalWidth;

    public IrisUnrecognized(double sepalLength, double sepalWidth, double petalLength, double petalWidth) {
        this.sepalLength = sepalLength;
        this.sepalWidth = sepalWidth;
        this.petalLength = petalLength;
        this.petalWidth = petalWidth;
    }

    @Override
    public double getSepalLength() {
        return sepalLength;
    }

    @Override
    public double getSepalWidth() {
        return sepalWidth;
    }

    @Override
    public double getPetalLength() {
        return petalLength;
    }

    @Override
    public double getPetalWidth() {
        return petalWidth;
    }

    @Override
    public String toString() {
        return "{" +
                "sepalLength=" + sepalLength +
                ", sepalWidth=" + sepalWidth +
                ", petalLength=" + petalLength +
                ", petalWidth=" + petalWidth +
                '}';
    }
}
