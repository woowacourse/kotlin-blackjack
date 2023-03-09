package blackjack.domain.card

import blackjack.domain.gameResult.GameResult

sealed class CardsState {

    //TODO: 현재는 메시지 보내는 형태가 아닌것 같다..
    //TODO: 각각의 상황에 대해서 다르게 처리하고 싶어서 sealed 클래스를 사용했는데 이게 사용법이 맞을까?
    object BlackJack : CardsState() {

        fun decideGameResult(dealerCardsState: CardsState) = when (dealerCardsState) {
            is BlackJack -> GameResult.DRAW
            is Running, Burst -> GameResult.BLACKJACK_WIN
        }
    }

    object Running : CardsState() {

        private fun decideGameResultByScore(myTotalScore: Int, dealerTotalScore: Int): GameResult =
            if (myTotalScore > dealerTotalScore) {
                GameResult.WIN
            } else if (myTotalScore < dealerTotalScore) {
                GameResult.LOSE
            } else {
                GameResult.DRAW
            }

        fun decideGameResult(myTotalScore: Int, otherCards: Cards): GameResult =
            when (otherCards.state) {
                is BlackJack -> GameResult.LOSE
                is Burst -> GameResult.WIN
                is Running -> decideGameResultByScore(myTotalScore, otherCards.getTotalCardsScore())
            }
    }

    object Burst : CardsState() {

        fun decideGameResult(dealerCardsState: CardsState) = when (dealerCardsState) {
            is Burst -> GameResult.WIN
            is BlackJack, Running -> GameResult.LOSE
        }
    }
}
