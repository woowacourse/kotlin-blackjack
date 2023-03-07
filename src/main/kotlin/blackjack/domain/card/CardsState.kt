package blackjack.domain.card

sealed class CardsState {

    object BlackJack : CardsState()
    object Running : CardsState()
    object Burst : CardsState()
}
