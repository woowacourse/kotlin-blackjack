package model

import TestDeck
import fixture.SPADE_KING
import fixture.SPADE_TEN
import fixture.SPADE_TWO
import model.card.Card
import model.human.Player
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
                    Card.from(2),
                    Card.from(10),
                ),
            )
    }

    @Test
    fun `핸드의 합이 21 미만인 경우, 카드를 더 받을 수 있다`() {
        val hand = Hand()
        val player = Player(hand)

        player.hand.draw(SPADE_TEN)
        player.hand.draw(SPADE_KING)

        Assertions.assertThat(player.isPossible()).isTrue
    }

    @Test
    fun `핸드의 합이 21 이상인 경우, 카드를 더 받을 수 없다`() {
        val hand = Hand()
        val player = Player(hand)

        player.hand.draw(SPADE_TEN)
        player.hand.draw(SPADE_KING)
        player.hand.draw(SPADE_TWO)

        Assertions.assertThat(player.isPossible()).isFalse
    }
}
