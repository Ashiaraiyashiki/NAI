import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadData {

    private String trainSetPath;
    private String testSetPath;
    private List<Iris> trainIrisList;
    private List<Iris> testIrisList;

    public List<Iris> getTrainData() {
        return this.trainIrisList;
    }

    public List<Iris> getTestData() {
        return this.testIrisList;
    }

    public static LoadData load(String trainSetPath) {
        return new LoadData(trainSetPath);
    }

    public static LoadData load(String trainSetPath, String testSetPath) {
        return new LoadData(trainSetPath, testSetPath);
    }

    private LoadData(String trainSetPath) {

        this.trainSetPath = trainSetPath;
        try {
            trainIrisList = loadTrainData();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private LoadData(String trainSetPath, String testSetPath) {

        this.trainSetPath = trainSetPath;
        this.testSetPath = testSetPath;
        try {
            trainIrisList = loadTrainData();
            testIrisList = loadTestSet();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private List<Iris> loadTrainData() throws IOException {

        List<Iris> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(new File(trainSetPath)));

        String line;
        while ((line = br.readLine()) != null) {
            String[] splitLine = line.split(";");
            switch (splitLine[4]) {
                case "Iris-setosa":
                    list.add(new IrisSetosa(
                            Double.parseDouble(splitLine[0]),
                            Double.parseDouble(splitLine[1]),
                            Double.parseDouble(splitLine[2]),
                            Double.parseDouble(splitLine[3])));
                    break;
                case "Iris-versicolor":
                    list.add(new IrisVersicolour(
                            Double.parseDouble(splitLine[0]),
                            Double.parseDouble(splitLine[1]),
                            Double.parseDouble(splitLine[2]),
                            Double.parseDouble(splitLine[3])));
                    break;
                case "Iris-virginica":
                    list.add(new IrisVirginica(
                            Double.parseDouble(splitLine[0]),
                            Double.parseDouble(splitLine[1]),
                            Double.parseDouble(splitLine[2]),
                            Double.parseDouble(splitLine[3])));
                    break;
            }

        }

        return list;
    }

    private List<Iris> loadTestSet() throws IOException {

        List<Iris> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(new File(testSetPath)));

        String line;
        while ((line = br.readLine()) != null) {
            String[] splitLine = line.split(";");
            list.add(new IrisUnrecognized(
                    Double.parseDouble(splitLine[0]),
                    Double.parseDouble(splitLine[1]),
                    Double.parseDouble(splitLine[2]),
                    Double.parseDouble(splitLine[3])
            ));
        }

        return list;
    }
}