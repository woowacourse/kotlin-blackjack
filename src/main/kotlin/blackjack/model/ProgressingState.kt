package blackjack.model

sealed class Progressing(hand: Hand) : State(hand)

class Running(hand: Hand = Hand()) : Progressing(hand) {
    private var _hand: Hand = hand
    override val hand: Hand
        get() = _hand

    fun drawInitCards(gameDeck: GameDeck): State {
        repeat(INITIAL_CARD_COUNTS) { _hand += gameDeck.drawCard() }
        return initState(hand.calculate())
    }

    private fun initState(totalPoint: Int): State {
        return if (totalPoint == Hand.BLACKJACK_NUMBER) BlackJack(hand)
        else Running(hand)
    }

    fun hitOrStay(isHit: Boolean): State {
        return when (isHit) {
            true -> Hit(hand)
            false -> Stay(hand)
        }
    }

    companion object {
        const val INITIAL_CARD_COUNTS = 2
    }
}

class Hit(hand: Hand) : Progressing(hand) {
    private var _hand: Hand = hand
    override val hand: Hand
        get() = _hand

    fun getCard(card: Card): State {
        _hand += card
        return updateState(hand.calculate())
    }

    private fun updateState(totalPoint: Int): State {
        return if (totalPoint > Hand.BLACKJACK_NUMBER) Bust(hand)
        else if (totalPoint == Hand.BLACKJACK_NUMBER) Stay(hand)
        else Running(hand)
    }
}
