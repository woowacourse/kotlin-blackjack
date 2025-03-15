package blackjack.domain.card

fun interface CardFactory {
    fun makeCard(): List<TrumpCard>
}
