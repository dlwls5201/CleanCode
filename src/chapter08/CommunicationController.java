package chapter08;

class CommunicationController {

    private Transmitter transmitter;

    CommunicationController() {
        if(true) {
            transmitter = new FakeTransmitter();
        } else {
            transmitter = new TransmitterAdapter(new TrasnmitterAPI());
        }
    }

    String getAnalogSignal(int frequency, int stream) {
        return transmitter.transmit(frequency, stream);
    }
}
