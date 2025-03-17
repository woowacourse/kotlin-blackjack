package blackjack.domain.model

import blackjack.domain.model.card.CLUB_ACE
import blackjack.domain.model.card.CLUB_KING
import blackjack.domain.model.card.DIAMOND_QUEEN
import blackjack.domain.model.card.HEART_KING
import blackjack.domain.model.card.HEART_QUEEN
import blackjack.domain.model.card.Hand
import blackjack.domain.model.card.SPADE_TEN
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScoreboardTest {
    @Test
    fun `딜러와 플레이어를 비교해 딜러의 게임 결과를 가져올 수 있다`() {
        val dealer =
            Dealer(
                "딜러",
                Hand.of(
                    HEART_KING,
                    CLUB_KING,
                ),
            )
        val firstPlayer =
            Player(
                "블랙잭",
                Hand.of(
                    CLUB_ACE,
                    DIAMOND_QUEEN,
                ),
            )
        val secondPlayer =
            Player(
                "이십점",
                Hand.of(
                    SPADE_TEN,
                    HEART_QUEEN,
                ),
            )
        val actualResult = Scoreboard(dealer, listOf(firstPlayer, secondPlayer)).getDealerResult()

        val expectedResult =
            mapOf(
                GameResult.BLACKJACK_WIN to 0,
                GameResult.WIN to 0,
                GameResult.DRAW to 1,
                GameResult.LOSE to 1,
            )

        assertThat(actualResult).isEqualTo(expectedResult)
    }
}
