package blackjack.domain.model.participant.bet

import blackjack.domain.model.card.CardStatus
import blackjack.domain.model.participant.WinLoss
import blackjack.domain.model.participant.bet.ProfitRate.Companion.calculateProfitRate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ProfitRateTest {
    @ParameterizedTest
    @CsvSource(
        "WIN, BLACKJACK, BLACKJACK_WIN",
        "WIN, NORMAL, NORMAL_WIN",
        "LOSE, NORMAL, LOSE",
        "DRAW, BLACKJACK, DRAW",
    )
    fun `승패와 카드 결과로 수익률을 알려준다`(
        winLoss: WinLoss,
        cardStatus: CardStatus,
        expectedRate: ProfitRate,
    ) {
        assertThat(calculateProfitRate(winLoss, cardStatus)).isEqualTo(expectedRate)
    }
}
