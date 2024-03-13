package model.participants

import DeckExplicitGeneration
import model.ParticipantState
import model.card.Card
import model.card.Deck
import model.card.MarkType
import model.card.ValueType
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var testDeck: Deck
    private lateinit var hand: Hand

    @BeforeEach
    fun setUp() {
        testDeck =
            Deck.create(
                DeckExplicitGeneration(
                    mutableListOf(
                        Card(ValueType.TWO, MarkType.SPADE),
                        Card(ValueType.JACK, MarkType.SPADE),
                        Card(ValueType.QUEEN, MarkType.SPADE),
                        Card(ValueType.ACE, MarkType.SPADE),
                    ),
                ),
            )
        hand = Hand()
    }

//    @Test
//    fun `핸드의 합이 21 미만인 경우, hit 시에 버스트 되지 않는다`() {
//        val player = Player(ParticipantState.Playing(Hand()))
//
//        player.hit(testDeck.pop())
//
//        Assertions.assertThat(player.hit(testDeck.pop())).isTrue
//    }

//    @Test
//    fun `핸드의 합이 21 이상인 경우, hit 시에 버스트 된다`() {
//        val player = Player(hand)
//
//        player.hit(testDeck.pop())
//        player.hit(testDeck.pop())
//
//        Assertions.assertThat(player.hit(testDeck.pop())).isFalse
//    }
}
