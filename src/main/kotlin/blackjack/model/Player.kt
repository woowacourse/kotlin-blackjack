package blackjack.model

open class Player(
    open val name: String,
    open val cards: Cards = Cards(emptyList()),
) {
    lateinit var result: GameResult

    open fun appendCard(card: Card) {
        cards.add(card)
    }

    open fun isBlackjack(firstTurn: Boolean): Boolean = cards.isBlackjack(firstTurn)

    fun updateResult(dealerGameResult: GameResult) {
        result = GameResult.reversed(dealerGameResult)
    }
}
