package blackjack.domain.gamer

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test

internal class ScoreTest {

    @Test
    fun getValue() {
        assertThat(score1.value).isEqualTo(1)
    }

    @Test
    fun plus() {
        val result = score1 + score2
        assertThat(result.value).isEqualTo(3)
    }

    @Test
    fun minus() {
        val result = score2 - score1
        assertThat(result.value).isEqualTo(1)
    }

    @Test
    fun compareTo() {
        val result = score1 < score2
        assertThat(result).isTrue
    }

    companion object {
        val score1 = Score(1)
        val score2 = Score(2)
    }
}
