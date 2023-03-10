package blackjack.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ParticipantsTest {
    @Test
    fun `참여자들은 최소 2명이어야 한다`() {
        val players = emptyList<Participant>()

        Assertions.assertThatIllegalArgumentException().isThrownBy { Participants(players) }
            .withMessageContaining("블랙잭은 딜러를 포함하여 최소 2명에서 최대 8명의 플레이어가 참여 가능합니다. (현재 플레이어수 : 0명)")
    }

    // @Test
    // fun `참여자들은 최대 8명까지 가능하다`() {
    //     val players = listOf(
    //         Player("a"), Player("b"), Player("c"),
    //         Player("d"), Player("e"), Player("f"),
    //         Player("g"), Player("h"), Player("h")
    //     )
    //
    //     Assertions.assertThatIllegalArgumentException().isThrownBy { Participants(players) }
    //         .withMessageContaining("블랙잭은 딜러를 포함하여 최소 2명에서 최대 8명의 플레이어가 참여 가능합니다. (현재 플레이어수 : 9명)")
    // }

    // @Test
    // fun `참여자들이 카드를 2장씩 뽑는다`() {
    //     val cardDeck = CardDeck(
    //         Card(CardNumber.ACE, Suit.SPADE),
    //         Card(CardNumber.THREE, Suit.SPADE),
    //         Card(CardNumber.FIVE, Suit.SPADE),
    //         Card(CardNumber.ACE, Suit.CLOVER),
    //         Card(CardNumber.THREE, Suit.CLOVER),
    //         Card(CardNumber.FIVE, Suit.CLOVER)
    //     )
    //     val players = listOf(Player("부나"), Player("반달"), Player("글로"))
    //     val participants = Participants(players)
    //
    //     participants.drawFirst(cardDeck)
    //
    //     assertAll(
    //         {
    //             assertThat(players[0].getCards()).containsExactly(
    //                 Card(CardNumber.ACE, Suit.SPADE),
    //                 Card(CardNumber.THREE, Suit.SPADE)
    //             )
    //         },
    //         {
    //             assertThat(players[1].getCards()).containsExactly(
    //                 Card(CardNumber.FIVE, Suit.SPADE),
    //                 Card(CardNumber.ACE, Suit.CLOVER)
    //             )
    //         },
    //         {
    //             assertThat(players[2].getCards()).containsExactly(
    //                 Card(CardNumber.THREE, Suit.CLOVER),
    //                 Card(CardNumber.FIVE, Suit.CLOVER)
    //             )
    //         },
    //     )
    // }

    // @ParameterizedTest(name = "{0}가(이) 카드를 뽑을 수 있을 때까지 뽑는다")
    // @ValueSource(strings = ["부나", "반달", "로피", "글로", "코비", "뽀또"])
    // fun `모든 플레이어들이 카드를 뽑을 수 있을 때까지 뽑는다`(name: String) {
    //     val participants = Participants(Dealer(), Player(name))
    //     var prevCardCount = 0
    //
    //     participants.takePlayerTurns(CardDeck()) { player ->
    //         val actual = player.getCards().size
    //         assertThat(++prevCardCount).isEqualTo(actual)
    //     }
    // }

    // @Test
    // fun `딜러가 카드를 뽑을 수 있을 때까지 뽑는다`() {
    //     val participants = Participants(Dealer(), Player("부나"), Player("반달"))
    //     var prevCardCount = 0
    //
    //     participants.takeDealerTurns(CardDeck()) { dealer ->
    //         val actual = dealer.getCards().size
    //         assertThat(++prevCardCount).isEqualTo(actual)
    //     }
    // }

    private fun Participants(vararg participants: Participant): Participants =
        Participants(participants.toList())
}
