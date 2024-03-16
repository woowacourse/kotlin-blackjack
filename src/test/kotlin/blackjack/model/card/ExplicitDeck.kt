package blackjack.model.card

import blackjack.model.CLOVER_FOUR
import blackjack.model.CLOVER_THREE
import blackjack.model.DIAMOND_ACE
import blackjack.model.DIAMOND_EIGHT
import blackjack.model.DIAMOND_KING
import blackjack.model.HEART_FIVE
import blackjack.model.HEART_NINE
import blackjack.model.HEART_QUEEN
import blackjack.model.HEART_SEVEN
import blackjack.model.SPADE_JACK
import blackjack.model.SPADE_SIX
import blackjack.model.SPADE_TWO

class ExplicitDeck : CardDeck {
    private val explicitCardDeck =
        listOf(
            HEART_FIVE,
            CLOVER_THREE,
            SPADE_JACK,
            HEART_SEVEN,
            SPADE_SIX,
            CLOVER_FOUR,
            DIAMOND_KING,
            DIAMOND_EIGHT,
            SPADE_TWO,
            HEART_QUEEN,
            HEART_NINE,
            DIAMOND_ACE,
        )

    private var cardIndex = 0

    override fun draw(): Card {
        if (cardIndex == explicitCardDeck.size) {
            cardIndex = 0
        }
        return explicitCardDeck[cardIndex++]
    }
}
