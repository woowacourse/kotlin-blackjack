package entity

import entity.result.DealerGameResult
import entity.result.GameResultType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DealerGameResultTest {
    @Test
    fun `딜러의 게임 결과 값에 0이 포함되면 오류가 발생한다`() {
        val message = assertThrows<IllegalStateException> {
            DealerGameResult(mapOf(GameResultType.WIN to 0))
        }.message

        assertThat(message).isEqualTo("게임 결과가 잘못되었습니다.")
    }
}
