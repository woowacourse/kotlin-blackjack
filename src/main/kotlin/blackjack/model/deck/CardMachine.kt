package blackjack.model.deck

fun interface CardMachine {
    fun shuffle(cards: List<Card>): List<Card>
}
