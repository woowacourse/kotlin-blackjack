package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class ResultTest {

    @ParameterizedTest
    @ValueSource(strings = ["승", "패", "무"])
    fun `승패무 결과를 가진다`(expected: String) {
        val resultWord: List<String> = Result.values().map { it.word }
        assertThat(resultWord).contains(expected)
    }

    @ParameterizedTest
    @CsvSource(
        value = ["25,23,DRAW", "15,23,LOSE", "23,15,WIN", "20,15,LOSE", "15,15,DRAW", "15,19,WIN"]
    )
    fun `딜러 플레이어와 참가자 플레이어의 각 카드 숫자의 합을 받아 참가자의 승패를 판단한다`(dealerSum: Int, participantSum: Int, expected: Result) {
        val actual = Result.valueOf(dealerSum, participantSum)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `결과를 받아 반대의 결과를 돌려준다`() {
        assertThat(Result.reverse(Result.LOSE)).isEqualTo(Result.WIN)
    }
}
