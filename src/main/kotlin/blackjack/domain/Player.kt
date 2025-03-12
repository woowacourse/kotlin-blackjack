package blackjack.domain

class Player(
    val name: String,
) : Participant() {
    override fun canDraw(): Boolean {
        return Rule.calculateScore(hand) <= Rule.BLACKJACK_SCORE
    }
}
