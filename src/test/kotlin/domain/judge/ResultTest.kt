package domain.judge

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResultTest {
    @Test
    fun `승이 나왔을 때 패로 뒤집는다`(){
        val actual = Result.WIN.reverseResult()

        assertThat(actual).isEqualTo(Result.LOSS)
    }

    @Test
    fun `패가 나왔을 때 승으로 뒤집는다`(){
        val actual = Result.LOSS.reverseResult()

        assertThat(actual).isEqualTo(Result.WIN)
    }

    @Test
    fun `무승부가 나왔을 때 뒤집지 않는다`(){
        val actual = Result.DRAW.reverseResult()

        assertThat(actual).isEqualTo(Result.DRAW)
    }
}
