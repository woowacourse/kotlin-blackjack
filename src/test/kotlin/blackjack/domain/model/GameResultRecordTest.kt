package blackjack.domain.model

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardNumber
import blackjack.domain.model.card.Hand
import blackjack.domain.model.card.Suit
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultRecordTest {
    @Test
    fun `딜러와 플레이어를 비교해 딜러의 게임 결과를 가져올 수 있다`() {
        val dealer =
            Dealer(
                "딜러",
                Hand.of(
                    Card.of(CardNumber.KING, Suit.HEART),
                    Card.of(CardNumber.JACK, Suit.CLUB),
                ),
            )
        val firstPlayer =
            Player(
                "블랙잭",
                Hand.of(
                    Card.of(CardNumber.ACE, Suit.CLUB),
                    Card.of(CardNumber.QUEEN, Suit.DIAMOND),
                ),
            )
        val secondPlayer =
            Player(
                "이십점",
                Hand.of(
                    Card.of(CardNumber.TEN, Suit.SPADE),
                    Card.of(CardNumber.QUEEN, Suit.HEART),
                ),
            )
        val actualResult = GameResultRecord(dealer, listOf(firstPlayer, secondPlayer)).getDealerResult()

        val expectedResult =
            mapOf(
                GameResult.WIN to 0,
                GameResult.DRAW to 1,
                GameResult.LOSE to 1,
            )

        assertThat(actualResult).isEqualTo(expectedResult)
    }
}
