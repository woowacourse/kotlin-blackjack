package blackjack.domain.model

abstract class Participant {
    abstract val name: String
    abstract val hands: Hands
}
