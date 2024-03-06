package blackjack.model

fun interface CardMachine {
    fun shuffle(cards: List<Card>): List<Card>
}
