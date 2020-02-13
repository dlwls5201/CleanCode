package chapter08;

interface Transmitter {

    //주파수와 스트림을 이용해 아날로그 신호로 전송한다.
    String transmit(int frequency, int stream);
}

class FakeTransmitter implements Transmitter {

    @Override
    public String transmit(int frequency, int stream) {
        return String.valueOf(frequency * stream);
    }
}

//어댑터 패턴 : 클래스의 인터페이스를 사용자가 기대하는 인터페이스 형태로 적응(변환) 시킵니다
class TransmitterAdapter implements Transmitter {

    private TrasnmitterAPI api;

    TransmitterAdapter(TrasnmitterAPI api) {
        this.api = api;
    }

    @Override
    public String transmit(int frequency, int stream) {
        return api.transmit(frequency, stream);
    }
}