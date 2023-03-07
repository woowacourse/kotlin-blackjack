package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.player.DrawState

open class BlackJackGameParticipant(
    val cards: Cards = Cards(listOf(Card.draw(), Card.draw()))
){

    protected fun isPossibleToDrawAdditionalCard(standardScore: Int): DrawState {
        if(cards.getMinimumCardsScore() >= standardScore){
            return DrawState.IMPOSSIBLE
        }

        return DrawState.POSSIBLE
    }
}