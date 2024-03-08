package model.participants

import TestDeck
import model.card.Card
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var testDeck: TestDeck
    private lateinit var hand: Hand

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
        hand = Hand(testDeck)
    }

    @Test
    fun `핸드의 합이 21 미만인 경우, hit 시에 버스트 되지 않는다`() {
        val player = Player(hand)

        player.hit()

        Assertions.assertThat(player.hit()).isTrue
    }

    @Test
    fun `핸드의 합이 21 이상인 경우, hit 시에 버스트 된다`() {
        val player = Player(hand)

        player.hit()
        player.hit()

        Assertions.assertThat(player.hit()).isFalse
    }
}
