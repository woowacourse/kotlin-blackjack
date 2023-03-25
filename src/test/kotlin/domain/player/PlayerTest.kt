package domain.player

import model.domain.player.Player
import model.domain.player.User
import model.domain.state.gameover.Stay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `유저가 Hit을 하지않고 Stay한다`() {
        // given
        val name = "산군"
        val user: Player = User.from(name)

        // when
        user.stay()
        val actual = user.state

        // then
        assertThat(actual).isInstanceOf(Stay::class.java)
    }
}
