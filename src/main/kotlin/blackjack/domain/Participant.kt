package blackjack.domain

interface Participant {
    val cards: List<TrumpCard>

    fun drawCard(card: TrumpCard)
}
