package blackjack.model

import model.Name
import model.Player
import model.Profit
import model.Result
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResultTest {
    @Test
    fun `참가자 정보와 참가자의 수익 정보를 갖는다`() {
        val result = Result(Player("jason"), Profit(0L))
        assertThat(result.participant.name.value).isEqualTo("jason")
        assertThat(result.profit.value).isEqualTo(0L)
    }

    companion object {
        fun Player(name: String): Player = Player.of(Name(name), 1_000L)
    }
}
