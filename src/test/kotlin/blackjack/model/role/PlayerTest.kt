package blackjack.model.role

import blackjack.model.PlayerName
import blackjack.model.card.state.Done
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.RepeatedTest

class PlayerTest {
    @RepeatedTest(1000)
    fun `플레이어의 턴을 진행하여 완료(블랙잭 혹은, 버스트 혹은, 스테이) 상태가 된다`() {
        val player = Player(PlayerName("심지"))
        val endState = player.runPhase(canDraw = { true })
        assertThat(endState is Done).isTrue
    }
}
