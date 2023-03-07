package blackjack.model

import model.Bet
import model.BetInfos
import model.Hand
import model.Name
import model.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetInfosTest {
    @Test
    fun `플레이어와 배팅금액을 연결지을 수 있다`() {
        val player = Player(Hand(emptyList()), Name("hyem"))
        val bet = Bet(10000)
        val infos = BetInfos(mapOf(player to bet))
        assertThat(infos[player]).isEqualTo(bet)
    }
}
