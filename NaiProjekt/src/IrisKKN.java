import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class IrisKKN {

    private LoadData loadData;
    private int k;

    public static void startKKNForDataSet(LoadData ld, int k) {

        IrisKKN irisKkn = new IrisKKN(ld, k);
        for (Iris i : irisKkn.loadData.getTestData()) {
            System.out.println(i + " = " + irisKkn.KKN(i));
        }
    }

    public static void startKKNInterface(LoadData ld, int k) {

        IrisKKN irisKnn = new IrisKKN(ld, k);
        irisKnn.createGUI(irisKnn);
    }

    private IrisKKN(LoadData ld, int k) {

        loadData = ld;
        this.k = k;
    }

    private String KKN(Iris i) {

        List<Iris> closest = new ArrayList<>();
        List<Double> distances = new ArrayList<>();
        for (Iris j : loadData.getTrainData()) {

            double tmp = distance(i, j);

            if (closest.size() < k) {
                closest.add(j);
                distances.add(tmp);
            } else {
                int index = 0;
                for (int l = 1; l < k; l++) {
                    if (distances.get(index) < distances.get(l)) {
                        index = l;
                    }
                }
                if (distances.get(index) > tmp) {
                    closest.set(index, j);
                    distances.set(index, tmp);
                }
            }
        }

        Map<String, Integer> count = new HashMap<>();

        for (Iris k : closest) {
            if (count.containsKey(k.getClass().toString())) {
                count.replace(k.getClass().toString(), count.get(k.getClass().toString()) + 1);
            } else {
                count.put(k.getClass().toString(), 1);
            }
        }

        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }

        String result = "-";
        double accuracy = 0.0;
        if (maxEntry != null) {
            accuracy = maxEntry.getValue().doubleValue() / k;
            switch (maxEntry.getKey()) {
                case "class IrisSetosa":
                    result = "IrisSetosa";
                    break;
                case "class IrisVersicolour":
                    result = "IrisVersicolour";
                    break;
                case "class IrisVirginica":
                    result = "IrisVirginica";
                    break;
            }
        }

        return result + (", Accuracy: " + accuracy);
    }

    private double distance(Iris x, Iris y) {

        return Math.sqrt(
                Math.pow(y.getPetalLength() - x.getPetalLength(), 2.0)
                        + Math.pow(y.getPetalWidth() - x.getPetalWidth(), 2.0)
                        + Math.pow(y.getSepalLength() - x.getSepalLength(), 2.0)
                        + Math.pow(y.getSepalWidth() - x.getSepalWidth(), 2.0)
        );
    }

    private void createGUI(IrisKKN irisKnn) {

        JFrame frame = new JFrame();
        Container container = frame.getContentPane();
        JLabel result = new JLabel("Result: ");
        JButton getResult = new JButton("getResult");
        JTextField sepalLength = new JTextField();
        JTextField sepalWidth = new JTextField();
        JTextField petalLength = new JTextField();
        JTextField petalWidth = new JTextField();
        JLabel sepalLengthTip = new JLabel("sepalLength");
        JLabel sepalWidthTip = new JLabel("sepalWidth");
        JLabel petalLengthTip = new JLabel("petalLength");
        JLabel petalWidthTip = new JLabel("petalWidth");

        JPanel tips = new JPanel();
        JPanel textFields = new JPanel();
        JPanel buttons = new JPanel();
        JPanel results = new JPanel();
        tips.setLayout(new GridLayout());
        textFields.setLayout(new GridLayout());
        buttons.setLayout(new GridLayout());
        results.setLayout(new GridLayout());

        tips.add(sepalLengthTip);
        tips.add(sepalWidthTip);
        tips.add(petalLengthTip);
        tips.add(petalWidthTip);
        textFields.add(sepalLength);
        textFields.add(sepalWidth);
        textFields.add(petalLength);
        textFields.add(petalWidth);
        buttons.add(getResult);
        results.add(result);

        container.add(tips);
        container.add(textFields);
        container.add(buttons);
        container.add(results);
        container.setLayout(new GridLayout(4, 1));

        getResult.addActionListener(e -> {
            try {
                if (!petalLength.getText().isEmpty() &&
                        !petalWidth.getText().isEmpty() &&
                        !sepalLength.getText().isEmpty() &&
                        !sepalWidth.getText().isEmpty()
                ) {
                    Iris i = new IrisUnrecognized(
                            Double.parseDouble(sepalLength.getText()),
                            Double.parseDouble(sepalWidth.getText()),
                            Double.parseDouble(petalLength.getText()),
                            Double.parseDouble(petalWidth.getText())
                    );
                    result.setText("Result: " + irisKnn.KKN(i));
                } else {
                    throw new Exception("\" \"");
                }
            } catch (Exception error) {
                result.setText("Result: Wrong value: " + error.getMessage());
            }
        });

        frame.setSize(400, 150);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}