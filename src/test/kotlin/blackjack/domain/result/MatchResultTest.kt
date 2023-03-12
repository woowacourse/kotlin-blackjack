package blackjack.domain.result

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MatchResultTest {

    @Test
    fun `게임 결과를 받아 각 결과의 개수를 카운트 할 수 있다`() {
        val matchResult = MatchResult()
        matchResult.count(GameResult.WIN)
        assertThat(matchResult.getResults()[GameResult.WIN]).isEqualTo(1)
    }

    @Test
    fun `카운트된 게임 결과 하나를 반환한다`() {
        val matchResult = MatchResult()
        matchResult.count(GameResult.WIN)
        assertThat(matchResult.getResult()).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `참가자들에 반대되는 게임 결과를 가진다`() {
        val matchResult = MatchResult()
        matchResult.count(GameResult.WIN)
        matchResult.reversGameResult()
        assertThat(matchResult.getResult()).isEqualTo(GameResult.LOSE)
    }
}
