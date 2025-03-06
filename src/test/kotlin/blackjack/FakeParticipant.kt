package blackjack

class FakeParticipant : Participant() {
    override val hitThreshold: Int
        get() = 21
}
