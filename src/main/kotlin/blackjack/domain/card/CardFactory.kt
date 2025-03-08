package blackjack.domain.card

interface CardFactory {
    fun makeCard(): List<TrumpCard>
}
