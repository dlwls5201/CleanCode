package chapter08;

public class Sample {

    public static void main(String[] args) {

        CommunicationController cc = new CommunicationController();
        String analogSignal = cc.getAnalogSignal(100, 100);
    }
}
