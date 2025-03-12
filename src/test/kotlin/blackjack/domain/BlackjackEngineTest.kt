package blackjack.domain

import blackjack.model.BlackjackEngine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BlackjackEngineTest {
    @Test
    fun `플레이어를 생성할 때 카드를 2장씩 가지고 생성한다`() {
        //given
        val blackjackEngine = BlackjackEngine()
        val expectedCardCount = 2
        //when
        val players = blackjackEngine.preparePlayers(listOf("시아", "공백"))
        //then
        assertAll(
            { assertEquals(players.value[0].hand.cards.size, expectedCardCount) },
            { assertEquals(players.value[1].hand.cards.size, expectedCardCount) }
        )

    }
}
