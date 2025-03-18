package blackjack.model

class Player(
    val name: String,
    override val items: Items,
) : Participant {
    fun compareHand(other: Participant): WinningResult = WinningResult.getResult(this, other)

    companion object {
        fun makePlayer(
            name: String,
            hand: Hand,
        ): Player = Player(name, Items(hand, Money(0.0)))
    }
}
