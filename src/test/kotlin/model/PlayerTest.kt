package model

import TestDeck
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var testDeck: TestDeck

    @BeforeEach
    fun setUp() {
        testDeck =
            TestDeck(
                mutableListOf(
                    Card.from(1),
                    Card.from(12),
                    Card.from(11),
                    Card.from(0),
                ),
            )
    }

    @Test
    fun `핸드의 합이 21 미만인 경우, 카드를 더 받을 수 있다`() {
        val hand = Hand(testDeck)
        val player = Player(hand)

        player.hit()
        player.hit()

        Assertions.assertThat(player.hit()).isTrue
    }

    @Test
    fun `핸드의 합이 21 이상인 경우, 카드를 더 받을 수 없다`() {
        val hand = Hand(testDeck)
        val player = Player(hand)

        player.hit()
        player.hit()
        player.hit()

        Assertions.assertThat(player.hit()).isFalse
    }
}
