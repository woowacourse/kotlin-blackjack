package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards
import blackjack.domain.result.GameResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
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
    fun `자신의 카드 숫자의 합이 21 초과라면 패배로 판단한다`() {
        val otherPlayer = TestPlayer("딜러")
        val player = TestPlayer(
            "수달",
            Cards(
                Pair(CardNumber.KING, CardShape.DIAMOND),
                Pair(CardNumber.QUEEN, CardShape.DIAMOND),
                Pair(CardNumber.TWO, CardShape.DIAMOND)
            )
        )

        player.matchGameResult(otherPlayer)

        assertThat(player.matchGameResult(otherPlayer)).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `상대방 카드 숫자의 합이 21 초과라면 승리로 판단한다`() {
        val otherPlayer = TestPlayer(
            "딜러",
            Cards(
                Pair(CardNumber.KING, CardShape.DIAMOND),
                Pair(CardNumber.QUEEN, CardShape.DIAMOND),
                Pair(CardNumber.TWO, CardShape.DIAMOND)
            )
        )
        val player = TestPlayer("수달")

        player.matchGameResult(otherPlayer)

        assertThat(player.matchGameResult(otherPlayer)).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `상대방 카드 숫자의 합이 자신의 카드 숫자 합보다 크다면 패배로 판단한다`() {
        val otherPlayer = TestPlayer(
            "딜러",
            Cards(
                Pair(CardNumber.KING, CardShape.DIAMOND),
                Pair(CardNumber.QUEEN, CardShape.DIAMOND),
                Pair(CardNumber.ONE, CardShape.DIAMOND)
            )
        )
        val player = TestPlayer(
            "수달",
            Cards(
                Pair(CardNumber.KING, CardShape.DIAMOND),
                Pair(CardNumber.QUEEN, CardShape.DIAMOND)
            )
        )

        player.matchGameResult(otherPlayer)

        assertThat(player.matchGameResult(otherPlayer)).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `상대방 카드 숫자의 합이 자신의 카드 숫자 합보다 작다면 승리로 판단한다`() {
        val otherPlayer = TestPlayer(
            "딜러",
            Cards(
                Pair(CardNumber.KING, CardShape.DIAMOND),
                Pair(CardNumber.QUEEN, CardShape.DIAMOND),
            )
        )
        val player = TestPlayer(
            "수달",
            Cards(
                Pair(CardNumber.KING, CardShape.DIAMOND),
                Pair(CardNumber.QUEEN, CardShape.DIAMOND),
                Pair(CardNumber.ONE, CardShape.DIAMOND)
            )
        )

        player.matchGameResult(otherPlayer)

        assertThat(player.matchGameResult(otherPlayer)).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `상대방의 카드와 비교해 상대방 카드와 자신 카드 모두 블랙잭이라면 무승부로 판단한다`() {
        val otherPlayer = TestPlayer(
            "딜러",
            Cards(
                Pair(CardNumber.ONE, CardShape.HEART),
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

        player.matchGameResult(otherPlayer)

        assertThat(player.matchGameResult(otherPlayer)).isEqualTo(GameResult.DRAW)
    }

    @Test
    fun `상대방의 카드와 비교해 자신의 카드만 블랙잭이라면 블랙잭으로 판단한다`() {
        val otherPlayer = TestPlayer(
            "딜러",
            Cards(
                Pair(CardNumber.ONE, CardShape.HEART),
                Pair(CardNumber.NINE, CardShape.HEART)
            )
        )
        val player = TestPlayer(
            "수달",
            Cards(
                Pair(CardNumber.ONE, CardShape.DIAMOND),
                Pair(CardNumber.KING, CardShape.DIAMOND)
            )
        )

        player.matchGameResult(otherPlayer)

        assertThat(player.matchGameResult(otherPlayer)).isEqualTo(GameResult.BLACKJACK)
    }

    class TestPlayer(name: String, cards: Cards = Cards()) : Player(name, cards) {
        override fun canHit(): Boolean {
            TODO("Not yet implemented")
        }
    }
}
