package blackjack.domain.card

sealed class CardsState {

    //TODO: 현재는 메시지 보내는 형태가 아닌것 같다..
    //TODO: 각각의 상황에 대해서 다르게 처리하고 싶어서 sealed 클래스를 사용했는데 이게 사용법이 맞을까?
    object BlackJack : CardsState()

    data class Running(val score: Int) : CardsState()

    object Bust : CardsState()
}
