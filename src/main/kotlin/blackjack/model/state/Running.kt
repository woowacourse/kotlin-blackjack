package blackjack.model.state

import blackjack.model.Card
import blackjack.model.GameDeck
import blackjack.model.Hand
import blackjack.model.Profit

class Running(
    hand: Hand = Hand(),
    profit: Profit = Profit(),
) : Progressing(hand, profit) {
    private var _hand: Hand = hand
    override val hand: Hand
        get() = _hand

    override fun hitOrStay(isHit: Boolean): State {
        return when (isHit) {
            true -> Hit(hand, profit)
            false -> Stay(hand, profit)
        }
    }

    override fun getCard(card: Card): State {
        _hand += card
        return updateState(hand.calculate())
    }

    fun drawInitCards(gameDeck: GameDeck): State {
        repeat(INITIAL_CARD_COUNTS) { _hand += gameDeck.drawCard() }
        return initState(hand.calculate())
    }

    private fun initState(totalPoint: Int): State {
        return if (totalPoint == Hand.BLACKJACK_NUMBER) BlackJack(hand, profit)
        else Running(hand, profit)
    }

    companion object {
        const val INITIAL_CARD_COUNTS = 2
    }
}
