package blackjack.model.result

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ScoreTest {
    @Test
    fun `카드 손 패 점수의 대소를 비교한다`() {
        val score = Score(19)
        val score2 = Score(17)
        assertTrue(score > score2)
    }
}
