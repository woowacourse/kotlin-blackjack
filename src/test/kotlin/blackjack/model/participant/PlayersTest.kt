package blackjack.model.participant

import blackjack.model.BattingMoney
import blackjack.model.deck.Deck
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayersTest {
    @Test
    fun `플레이어는 1명 이상 6명 이하가 아니라면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Players(tooManyPlayers, Deck()::draw)
            Players(noPlayers, Deck()::draw)
        }
    }

    companion object {
        private val tooManyPlayers =
            mapOf(
                "벼리" to BattingMoney.ofAmount(100),
                "채채" to BattingMoney.ofAmount(100),
                "서기" to BattingMoney.ofAmount(100),
                "올리브" to BattingMoney.ofAmount(100),
                "악어" to BattingMoney.ofAmount(100),
                "팡태" to BattingMoney.ofAmount(100),
                "심지" to BattingMoney.ofAmount(100),
                "해나" to BattingMoney.ofAmount(100),
            )

        private val noPlayers = mapOf<String, BattingMoney>()
    }
}
