package blackjack.model.config

import blackjack.model.result.Score

object GameRule {
    const val INITIAL_CARD_COUNT = 2

    private const val BLACK_JACK_SCORE_POINT = 21
    val BLACK_JACK_SCORE = Score(BLACK_JACK_SCORE_POINT)

    const val DEALER_NAME = "딜러"
    private const val DEALER_MAX_SCORE_POINT_FOR_HIT = 16
    val DEALER_MAX_SCORE_FOR_HIT = Score(DEALER_MAX_SCORE_POINT_FOR_HIT)
}
