package blackjack

class Player(private val name: String, private val cardHand: CardHand) {
    companion object {
        private const val BLACKJACK = 21
    }
}
