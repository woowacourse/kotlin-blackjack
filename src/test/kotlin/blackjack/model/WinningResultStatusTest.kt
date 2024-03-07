package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class WinningResultStatusTest {
    @ParameterizedTest
    @CsvSource(
        "DEFEAT, VICTORY",
        "VICTORY, DEFEAT",
        "DRAW, DRAW",
    )
    fun `플레이어의 승리는 딜러의 패배로 플레이어의 패배는 딜러의 승리로 기록된다`(
        playerWinningResult: String,
        dealerWinningResult: String,
    ) {
        val actual = WinningResultStatus.valueOf(playerWinningResult).reverse()
        assertThat(actual).isEqualTo(WinningResultStatus.valueOf(dealerWinningResult))
    }
}
