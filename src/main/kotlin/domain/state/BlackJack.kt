package domain.state

import domain.card.Card
import domain.card.Cards
import domain.game.GameResultType
import domain.participant.BettingMoney

class BlackJack(
    cards: Cards,
    bettingMoney: BettingMoney,
) : Finished(cards, bettingMoney) {
    override fun draw(card: Card): State {
        throw IllegalStateException("카드를 뽑을 수 없어요!!")
    }

    override fun getProfitRate(dealerState: State): Double {
        if (dealerState is BlackJack) return bettingMoney.money * GameResultType.DRAW.profitRate
        return bettingMoney.money * GameResultType.BLACKJACK.profitRate
    }
}
