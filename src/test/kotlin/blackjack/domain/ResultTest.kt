package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResultTest {

    @Test
    fun `Result는 승무패 결과를 가진다`() {

        // when
        val resultWord: List<String> = Result.values().map { it.word }

        // then
        assertThat(resultWord).containsExactly("승", "무", "패")
    }
}
