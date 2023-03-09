package blackjack.domain.card

//TODO: 왜 내가 sealed class를 사용했을까?
sealed class CardsState {

    object BlackJack : CardsState()
    object Running : CardsState()
    object Burst : CardsState()
}
