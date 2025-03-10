package blackjack.domain

interface Shuffler {
    fun shuffle(cards: List<Card>): List<Card>
}

object RandomShuffler : Shuffler {
    override fun shuffle(cards: List<Card>): List<Card> = cards.shuffled()
}

object FakeShuffler : Shuffler {
    override fun shuffle(cards: List<Card>): List<Card> = cards
}
