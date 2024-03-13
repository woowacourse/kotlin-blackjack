package blackjack.model.deck

fun interface CardMachine {
    fun handle(cards: List<Card>): List<Card>
}
