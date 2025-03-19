package blackjack.domain.model.playing

import blackjack.domain.model.Card
import blackjack.domain.model.MatchResult
import blackjack.domain.model.hand.state.BlackJack
import blackjack.domain.model.hand.state.Bust
import blackjack.domain.model.hand.state.Finished
import blackjack.domain.model.hand.state.State
import blackjack.domain.model.hand.state.Stay

abstract class PlayingParticipant {
    abstract val name: String
    abstract var handsState: State

    abstract fun showStartCards(): List<Card>

    fun showCards(): List<Card> = handsState.cards()

    fun acceptCard(card: Card) {
        handsState = handsState.nextState(card)
    }

    fun stay(): Finished = handsState.stay()

    fun isStarted(): Boolean = handsState.isStarted()

    fun isFinished(): Boolean = handsState.isFinished()

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
            otherState is BlackJack -> MatchResult.LOSE
            otherState is Bust -> MatchResult.WIN
            handsState.score() > otherState.score() -> MatchResult.WIN
            handsState.score() < otherState.score() -> MatchResult.LOSE
            else -> MatchResult.DRAW
        }
    }
}
