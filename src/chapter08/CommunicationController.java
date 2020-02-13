package chapter08;

class CommunicationController {

    private Transmitter transmitter;

    CommunicationController(Transmitter transmitter) {
        this.transmitter = transmitter;
    }

    String getAnalogSignal(int frequency, int stream) {
        return transmitter.transmit(frequency, stream);
    }
}
