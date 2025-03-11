package blackjack.domain.participant

class Player(
    val name: String,
) : Participant() {
    fun cardSize(): Int = cards.items.size
}
