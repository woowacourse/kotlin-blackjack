package blackjack.model.user

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.game.GameState
import blackjack.model.user.ParticipantInformation.DealerInformation
import blackjack.model.user.ParticipantInformation.PlayerInformation

sealed class Participant(val participantInformation: ParticipantInformation, val hand: Hand) {
    private var _state: GameState = GameState.Running.HIT
    val state: GameState
        get() = _state

    fun draw(card: Card) {
        if (isRunning()) {
            hand.drawCard(card)
            _state = state.judgeState(hand)
        }
    }

    fun changeStateToStay() {
        _state = GameState.Finished.STAY
    }

    fun isRunning(): Boolean {
        return state is GameState.Running
    }

    fun isBust(): Boolean {
        return state == GameState.Finished.BUST
    }

    class Player(
        val playerInformation: PlayerInformation,
        hand: Hand = Hand(),
    ) :
        Participant(playerInformation, hand) {
        fun judgeDrawOrNot(
            cardDeck: CardDeck,
            readDecision: () -> Boolean,
            output: () -> Unit,
        ) {
            while (isRunning()) {
                if (readDecision()) {
                    draw(cardDeck.pickCard())
                    output()
                } else {
                    changeStateToStay()
                }
            }
        }
    }

    class Dealer(
        dealerInformation: ParticipantInformation = DealerInformation(),
        hand: Hand = Hand(),
    ) : Participant(dealerInformation, hand) {
        fun initialCardDealing(
            players: List<Player>,
            cardDeck: CardDeck,
        ) {
            repeat(INITIAL_DEALING_COUNT) {
                draw(cardDeck.pickCard())
                players.forEach { participant ->
                    participant.draw(cardDeck.pickCard())
                }
            }
        }

        fun judgeDrawOrNot(
            cardDeck: CardDeck,
            output: () -> Unit,
        ) {
            while (hand.score.point <= ADDITIONAL_DRAW_CRITERIA) {
                draw(cardDeck.pickCard())
                output()
            }
            if (hand.score.point < BLACKJACK_SCORE) {
                changeStateToStay()
            }
        }

        companion object {
            private const val INITIAL_DEALING_COUNT = 2
            private const val ADDITIONAL_DRAW_CRITERIA = 16
            private const val BLACKJACK_SCORE = 21
        }
    }
}
