package chapter08

class ComunicationController(private val transmitter: Transmitter) {

    fun transmit(frequency: Int, stream: Int): String {
        return transmitter.transmit(frequency, stream)
    }
}

interface Transmitter {

    //주파수와 스트임을 이용해 아날로그 신호로 전송한다.
    fun transmit(frequency: Int, stream: Int): String
}

class FakeTransmitter: Transmitter {
    override fun transmit(frequency: Int, stream: Int): String {
        return (frequency * stream).toString()
    }
}

class TrasnmitterApdater(private val api: TrasnmitterAPI): Transmitter {
    override fun transmit(frequency: Int, stream: Int): String {
        return api.getTransmitter(frequency, stream)
    }
}

class TrasnmitterAPI {

    private var api: Any? = null

    fun getTransmitter(frequency: Int, stream: Int): String {
        return "" //api.get(frequency, stream)
    }
}

fun main() {
    val fake = FakeTransmitter()
    val api = TrasnmitterApdater(TrasnmitterAPI())

    ComunicationController(fake)
}