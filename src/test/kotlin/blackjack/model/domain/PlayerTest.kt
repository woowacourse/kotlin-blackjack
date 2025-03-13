package blackjack.model.domain

import blackjack.model.domain.card.Card
import blackjack.model.domain.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private val player1 = Player("제리")

    @BeforeEach
    fun setup() {
        // given
        player1.receiveCard(listOf(Card.from("AceHeart"), Card.from("SixSpade")))
    }

    @Test
    fun `받은 카드의 목록을 반환한다`() {
        // when
        val actual = player1.cardDeck
        val expected = listOf(Card.from("AceHeart"), Card.from("SixSpade"))
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `카드 숫자의 합을 토대로 bust를 판단한다`() {
        // given
        player1.receiveCard(listOf(Card.from("QueenHeart"), Card.from("QueenSpade")))
        // when
        val actual = player1.hand.isBust()
        val expected = true
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `플레이어의 숫자의 합과 받은 숫자의 합을 비교하여 승패를 결정한다`() {
        // when
        val actual = player1.compareScores(false, 8)
        val expected = GameResult.Win
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
