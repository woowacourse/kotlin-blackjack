package blackjack.domain

class Player(
    val name: PlayerName,
    val cardHand: CardHand,
    val betAmount: BetAmount
) {

    private fun isPossibleToDrawAdditionalCard(): DrawState {
        if (cardHand.getMinimumCardsScore() >= BlackJackReferee.BLACK_JACK_SCORE) {
            return DrawState.IMPOSSIBLE
        }

        return DrawState.POSSIBLE
    }

    fun drawCard(card: Card): DrawState {
        cardHand.draw(card)

        return isPossibleToDrawAdditionalCard()
    }
}
