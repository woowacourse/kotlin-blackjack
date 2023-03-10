package blackjack.domain.card

sealed class CardsState {

    object BlackJack : CardsState()

    data class Running(val score: Int) : CardsState()

    object Bust : CardsState()
}
