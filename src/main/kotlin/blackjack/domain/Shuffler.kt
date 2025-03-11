package blackjack.domain

fun interface Shuffler {
    fun shuffle(cards: List<Card>): List<Card>
}

object RandomShuffler : Shuffler {
    override fun shuffle(cards: List<Card>): List<Card> = cards.shuffled()
}
