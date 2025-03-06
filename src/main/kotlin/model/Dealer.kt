package model

class Dealer(val dealerCards: Cards) : Participant(dealerCards) {
    init {
        require(dealerCards.getCardsCount() == 2) { "[ERROR] 딜러는 2장의 카드를 가져야합니다." }
    }

    override fun turn(allCards: Cards): Boolean {
        if (getScore() <= 16) {
            allCards.drawCards(1)
        }
        return ScoreCalculator(dealerCards).totalCardScore() >= 17
    }
}
