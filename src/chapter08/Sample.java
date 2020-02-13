package chapter08;

public class Sample {

    public static void main(String[] args) {
        Transmitter fake = new FakeTransmitter();
        Transmitter api = new TransmitterAdapter(new TrasnmitterAPI());

        CommunicationController cc = new CommunicationController(api);
        String analogSignal = cc.getAnalogSignal(100, 100);
    }
}
