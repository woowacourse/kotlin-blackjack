package blackjack.domain.model.playing

import blackjack.domain.model.Card
import blackjack.domain.model.MatchResult
import blackjack.domain.model.hand.BlackJack
import blackjack.domain.model.hand.Bust
import blackjack.domain.model.hand.State
import blackjack.domain.model.hand.Stay

abstract class PlayingParticipant {
    abstract val name: String
    abstract var handsState: State

    fun showCards(): List<Card> = handsState.cards()

    fun acceptCard(card: Card) {
        handsState = handsState.nextState(card)
    }

    fun match(otherState: State): MatchResult =
        when (handsState.stay()) {
            is BlackJack -> matchBlackJack(otherState)
            is Bust -> MatchResult.LOSE
            is Stay -> matchStay(otherState)
        }

    private fun matchBlackJack(otherState: State): MatchResult {
        if (otherState is BlackJack) return MatchResult.DRAW
        return MatchResult.BLACKJACK
    }

    private fun matchStay(otherState: State): MatchResult {
        return when {
            otherState is Bust -> MatchResult.WIN
            handsState.score() > otherState.score() -> MatchResult.WIN
            handsState.score() < otherState.score() -> MatchResult.LOSE
            else -> MatchResult.DRAW
        }
    }
}
