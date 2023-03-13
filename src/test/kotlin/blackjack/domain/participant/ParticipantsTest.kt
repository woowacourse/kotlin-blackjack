package blackjack.domain.participant

import blackjack.domain.DIAMOND_JACK
import blackjack.domain.DIAMOND_KING
import blackjack.domain.DIAMOND_THREE
import blackjack.domain.DIAMOND_TWO
import blackjack.domain.SPADE_ACE
import blackjack.domain.SPADE_EIGHT
import blackjack.domain.SPADE_FIVE
import blackjack.domain.SPADE_FOUR
import blackjack.domain.SPADE_JACK
import blackjack.domain.SPADE_KING
import blackjack.domain.SPADE_TEN
import blackjack.domain.SPADE_THREE
import blackjack.domain.SPADE_TWO
import blackjack.domain.card.CardDeck
import blackjack.domain.money.BetMoney
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsTest {
    @Test
    fun `참여자들은 최소 2명이어야 한다`() {
        val players = emptyList<Participant>()

        Assertions.assertThatIllegalArgumentException().isThrownBy { Participants(players) }
            .withMessageContaining("블랙잭은 딜러를 포함하여 최소 2명에서 최대 8명의 플레이어가 참여 가능합니다. (현재 플레이어수 : 0명)")
    }

    @Test
    fun `참여자들은 최대 8명까지 가능하다`() {
        val players = listOf(
            Player("a", money = BetMoney(1000)),
            Player("b", money = BetMoney(1000)),
            Player("c", money = BetMoney(1000)),
            Player("d", money = BetMoney(1000)),
            Player("e", money = BetMoney(1000)),
            Player("f", money = BetMoney(1000)),
            Player("g", money = BetMoney(1000)),
            Player("h", money = BetMoney(1000)),
        )

        Assertions.assertThatIllegalArgumentException().isThrownBy { Participants(players + Dealer()) }
            .withMessageContaining("블랙잭은 딜러를 포함하여 최소 2명에서 최대 8명의 플레이어가 참여 가능합니다. (현재 플레이어수 : 9명)")
    }

    @Test
    fun `참여자들에 딜러가 포함되어 있어야 한다`() {
        val players = listOf(
            Player("a", money = BetMoney(1000)),
            Player("b", money = BetMoney(1000)),
            Player("c", money = BetMoney(1000))
        )

        Assertions.assertThatIllegalArgumentException().isThrownBy { Participants(players) }
            .withMessageContaining("참여자에 딜러가 포함되어 있지 않습니다.")
    }

    @Test
    fun `참여자들 중에서 플레이어들을 반환한다`() {
        val players = listOf(
            Player("부나", money = BetMoney(1000)),
            Player("반달", money = BetMoney(1000)),
            Player("글로", money = BetMoney(1000))
        )
        val dealer = Dealer()
        val expected = Participants(players + dealer).getPlayers()

        assertThat(expected).isEqualTo(players)
    }

    @Test
    fun `참여자들 중에서 딜러를 반환한다`() {
        val players = listOf(
            Player("부나", money = BetMoney(1000)),
            Player("반달", money = BetMoney(1000)),
            Player("글로", money = BetMoney(1000))
        )
        val dealer = Dealer()
        val expected = Participants(players + dealer).getDealer()

        assertThat(expected).isEqualTo(dealer)
    }

    @Test
    fun `참여자들은 처음에 카드를 2장씩 뽑는다`() {
        val cardDeck = CardDeck(
            SPADE_TWO, SPADE_FOUR,
            SPADE_ACE, SPADE_THREE,
            SPADE_FIVE, DIAMOND_JACK,
            DIAMOND_THREE, DIAMOND_TWO,
        )
        val players = listOf(
            Player("부나", money = BetMoney(1000)),
            Player("반달", money = BetMoney(1000)),
            Player("글로", money = BetMoney(1000))
        )
        Participants(players + Dealer()).drawFirst(cardDeck) {
            assertThat(it.getCards().size).isEqualTo(2)
        }
    }

    @Test
    fun `참여자들이 카드를 뽑을 수 있을 때까지 뽑는다`() {
        val participants = Participants(Dealer(), Player("부나", money = BetMoney(1000)))
        val cardDeck = CardDeck(
            SPADE_TEN, SPADE_EIGHT, DIAMOND_KING,
            SPADE_KING, SPADE_JACK, DIAMOND_JACK
        )
        val expectedCardSize = listOf(1, 2, 1, 2, 3)
        var sequence = 0

        participants.takeTurns(cardDeck) { player ->
            val actual = player.getCards().size
            val expected = expectedCardSize[sequence++]
            assertThat(actual).isEqualTo(expected)
        }
    }

    private fun Participants(vararg participants: Participant): Participants =
        Participants(participants.toList())
}
