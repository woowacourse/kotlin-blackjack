package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PlayerTest {
    lateinit var player: Player

    @BeforeEach
    fun setUp() {
        player = Player("pobi")
    }

    @Test
    fun `플레이어는 이름을 갖는다`() {
        assertThat(player.name).isEqualTo("pobi")
    }

    @Test
    fun `플레이어는 카드 목록에 카드를 추가한다`() {
        player.addCard(Card.of(2))

        assertThat(player.hand.cards).containsExactly(Card.of(2))
    }

    @Test
    fun `카드의 합이 21을 초과하면 버스트다`() {
        player.addCard(Card.of(4))
        player.addCard(Card.of(8))
        player.addCard(Card.of(13))

        assertThat(player.isBust()).isTrue
    }

    @Test
    fun `카드의 합이 21을 초과하지 않으면 버스트가 아니다`() {
        player.addCard(Card.of(1))
        player.addCard(Card.of(13))

        assertThat(player.isBust()).isFalse
    }

    @ParameterizedTest
    @CsvSource("1, 14, 12", "1, 11, 21", "10, 20, 17")
    fun `자신의 점수를 반환한다`(firstCardNumber: Int, secondCardNumber: Int, expected: Int) {
        player.addCard(Card.of(firstCardNumber))
        player.addCard(Card.of(secondCardNumber))

        assertThat(player.getTotalScore()).isEqualTo(expected)
    }
}
