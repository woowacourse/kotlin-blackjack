package blackjack.domain

class Player(
    val name: PlayerName,
    private val cardPack: CardPack,
    val cardHand: CardHand = CardHand(listOf(cardPack.draw(), cardPack.draw()))
) {

    private fun isPossibleToDrawAdditionalCard(): DrawState {
        if (cardHand.getMinimumCardsScore() >= BLACK_JACK_SCORE) {
            return DrawState.IMPOSSIBLE
        }

        return DrawState.POSSIBLE
    }

    fun drawCard(): DrawState {
        cardHand.draw(cardPack.draw())

        return isPossibleToDrawAdditionalCard()
    }

    companion object {
        const val BLACK_JACK_SCORE = 21
    }
}
