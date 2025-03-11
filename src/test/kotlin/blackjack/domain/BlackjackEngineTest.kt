package blackjack.domain

import blackjack.model.BlackjackEngine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackEngineTest {
    @Test
    fun `플레이어를 생성할 때 카드를 2장씩 가지고 생성한다`(){
        //given
        val blackjackEngine = BlackjackEngine()
        //when
        val players = blackjackEngine.preparePlayers(listOf("시아","공백"))
        //then
        assertThat(players.value[0].hand.cards.size).isEqualTo(2)
        assertThat(players.value[1].hand.cards.size).isEqualTo(2)
    }
}
