package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards
import blackjack.domain.result.GameResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow

class PlayerTest {

    @Test
    fun `이름을 생성할 수 있다`() {
        assertDoesNotThrow { TestPlayer("수달") }
    }

    @Test
    fun `카드들을 가진다`() {
        val player = TestPlayer("aa")
        assertThat(player.cards.values).isEqualTo(Cards().values)
    }

    @Test
    fun `새로운 카드를 받아 가진 카드에 추가한다`() {
        val player = TestPlayer("aa")
        val expected = Card.from(CardNumber.FOUR, CardShape.HEART)
        player.addCard(expected)
        assertThat(player.cards.values).isEqualTo(listOf(expected))
    }

    @Test
    fun `상대방의 카드와 비교해 게임 결과를 업데이트 한다`() {
        val otherPlayer = TestPlayer(
            "딜러",
            Cards(
                Pair(CardNumber.SEVEN, CardShape.HEART),
                Pair(CardNumber.TEN, CardShape.HEART)
            )
        )
        val player = TestPlayer(
            "수달",
            Cards(
                Pair(CardNumber.ONE, CardShape.DIAMOND),
                Pair(CardNumber.KING, CardShape.DIAMOND)
            )
        )

        player.decideGameResult(otherPlayer)
        otherPlayer.decideGameResult(player)
        assertAll(
            { assertThat(player.matchResult.getResult()).isEqualTo(GameResult.BLACKJACK) },
            { assertThat(otherPlayer.matchResult.getResult()).isEqualTo(GameResult.LOSE) },
        )
    }

    class TestPlayer(name: String, cards: Cards = Cards()) : Player(name, cards) {
        override fun canHit(): Boolean {
            TODO("Not yet implemented")
        }
    }
}
