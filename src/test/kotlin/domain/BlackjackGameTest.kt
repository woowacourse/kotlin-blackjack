package domain

import domain.judge.Result
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackGameTest {
    @Test
    fun `플레이어 3명이 승,패,패 일 때 딜러는 2승 1패이다`() {
        val actual =
            BlackjackGame(listOf("jack", "king", "queen")).judgeDealerResult(
                mapOf(
                    "jack" to Result.WIN,
                    "king" to Result.LOSS,
                    "queen" to Result.LOSS
                )
            )
        assertThat(actual).isEqualTo(listOf(Result.LOSS, Result.WIN, Result.WIN))
    }
}
