package domain.person

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape.HEART
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PlayerTest {

    private lateinit var player: Player

    @BeforeEach
    private fun setUp() {
        player = Player("베르")
    }

    @Test
    fun `플레이어는 이름을 가진다`() {
        assertThat(player.name).isEqualTo("베르")
    }

    @Test
    fun `플레이어는 카드를 받아서 패에 추가할 수 있다`() {
        player.receiveCard(Card(HEART, CardNumber.ACE))
        assertThat(player.cards.size).isEqualTo(1)
    }

    @Test
    fun `플레이어는 처음에 Hit 상태이다`() {
        assertThat(player.gameState).isEqualTo(GameState.HIT)
    }

    @CsvSource(value = ["ACE,TEN,21", "TWO,THREE,5"])
    @ParameterizedTest
    fun `카드 패의 총합을 계산한다`(number1: CardNumber, number2: CardNumber, sum: Int) {
        player.receiveCard(Card(HEART, number1))
        player.receiveCard(Card(HEART, number2))
        assertThat(player.calculateTotalCardNumber()).isEqualTo(sum)
    }

    @Test
    fun `카드 패의 합이 21이 넘으면 게임 상태가 BUST 가 된다`() {
        player.receiveCard(Card(HEART, CardNumber.KING))
        player.receiveCard(Card(HEART, CardNumber.QUEEN))
        player.receiveCard(Card(HEART, CardNumber.JACK))
        assertThat(player.gameState).isEqualTo(GameState.BUST)
    }

    @Test
    fun `카드를 받기를 거부하면 게임 상태가 STAND 가 된다`() {
        player.rejectReceiveCard()
        assertThat(player.gameState).isEqualTo(GameState.STAND)
    }
}
