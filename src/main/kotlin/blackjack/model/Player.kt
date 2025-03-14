package blackjack.model

class Player(
    val name: String,
    override var items: Items,
) : Participant {
    override fun compareHand(other: Participant): WinningResult = WinningResult.getResult(this, other)
}
