package model.participants

import model.HEART_JACK
import model.HEART_QUEEN
import model.HEART_TWO
import model.card.Deck
import model.createBustedDealer
import model.createBustedPlayer
import model.createDealer
import model.createPlayer
import model.createTestDeck
import model.result.ResultType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var testDeck: Deck

    @BeforeEach
    fun setUp() {
        testDeck =
            createTestDeck(
                HEART_TWO,
                HEART_JACK,
                HEART_QUEEN,
            )
    }

    @Test
    fun `Dealer에서 게임 결과를 판단할 수 있다`() {
        val player = createPlayer()
        val dealer = createDealer()

        dealer.hit(testDeck.pop())
        player.hit(testDeck.pop())

        val result = dealer.judge(player)
        assertThat(result).isEqualTo(ResultType.LOSE)
    }

    @Test
    fun `Player와 Dealer 모두 bust라면 Dealer의 승리`() {
        val bustedPlayer = createBustedPlayer()
        val bustedDealer = createBustedDealer()

        val result = bustedDealer.judge(bustedPlayer)
        assertThat(result).isEqualTo(ResultType.WIN)
    }
}
