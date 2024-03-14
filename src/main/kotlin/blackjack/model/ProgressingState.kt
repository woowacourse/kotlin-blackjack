package blackjack.model

import blackjack.base.BaseHolder

sealed class Progressing(override val hand: Hand) : State {
    override fun updateState(totalPoint: Int): State {
        return if (totalPoint > Hand.BLACKJACK_NUMBER) Bust(hand)
        else if (totalPoint == Hand.BLACKJACK_NUMBER) Stay(hand)
        else Running(hand)
    }

    override fun decideWinner(opponent: BaseHolder): GameResult = GameResult()
}

class Running(hand: Hand = Hand()) : Progressing(hand) {
    private var _hand: Hand = hand
    override val hand: Hand
        get() = _hand

    override fun hitOrStay(isHit: Boolean): State {
        return when (isHit) {
            true -> Hit(hand)
            false -> Stay(hand)
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
        return if (totalPoint == Hand.BLACKJACK_NUMBER) BlackJack(hand)
        else Running(hand)
    }

    companion object {
        const val INITIAL_CARD_COUNTS = 2
    }
}

class Hit(hand: Hand) : Progressing(hand) {
    private var _hand: Hand = hand
    override val hand: Hand
        get() = _hand

    override fun getCard(card: Card): State {
        _hand += card
        return updateState(hand.calculate())
    }

    override fun hitOrStay(isHit: Boolean): State = this
}
