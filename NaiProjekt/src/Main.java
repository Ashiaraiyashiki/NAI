public class Main {

    public static void main(String[] args) {
        String trainSetPath = "trainSet.csv";
        String testSetPath = "testSet.csv";
        int k = 30;

        IrisKKN.startKKNForDataSet(LoadData.load(trainSetPath, testSetPath), k);
        IrisKKN.startKKNInterface(LoadData.load(trainSetPath), k);
    }
}