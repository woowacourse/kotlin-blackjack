package blackjack.domain.card

fun interface CardFactory : () -> List<TrumpCard>
