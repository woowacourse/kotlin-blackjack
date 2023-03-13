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

    fun drawCard(cardPack: CardPack): DrawState {
        if (cardPack.isEmpty()) {
            cardPack.addCardDeck()
        }
        cardHand.draw(cardPack.draw())

        return isPossibleToDrawAdditionalCard()
    }
}
