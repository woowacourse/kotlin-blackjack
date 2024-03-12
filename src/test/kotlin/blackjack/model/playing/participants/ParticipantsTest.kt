package blackjack.model.playing.participants

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.card.CardNumber
import blackjack.model.card.CardShape
import blackjack.model.card.generator.RandomCardGenerator
import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.participants.player.Player
import blackjack.model.playing.participants.player.PlayerName
import blackjack.model.playing.participants.player.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsTest {
    private val defaultParticipants =
        Participants(
            Dealer(
                CardHand(
                    Card(CardShape.CLOVER, CardNumber.ACE),
                    Card(CardShape.SPADE, CardNumber.SIX),
                ),
            ),
            Players(
                listOf(
                    Player(
                        PlayerName("심지"),
                        CardHand(
                            Card(CardShape.CLOVER, CardNumber.FIVE),
                            Card(CardShape.SPADE, CardNumber.KING),
                        ),
                    ),
                    Player(
                        PlayerName("해나"),
                        CardHand(
                            Card(CardShape.HEART, CardNumber.SIX),
                            Card(CardShape.DIAMOND, CardNumber.QUEEN),
                        ),
                    ),
                    Player(
                        PlayerName("악어"),
                        CardHand(
                            Card(CardShape.HEART, CardNumber.SEVEN),
                            Card(CardShape.CLOVER, CardNumber.THREE),
                        ),
                    ),
                    Player(
                        PlayerName("팡태"),
                        CardHand(
                            Card(CardShape.DIAMOND, CardNumber.ACE),
                            Card(CardShape.SPADE, CardNumber.SIX),
                        ),
                    ),
                ),
            ),
        )

    @Test
    fun `참가자들에게 카드를 2장씩 준다`() {
        val participants =
            Participants(
                Dealer(CardHand(emptyList())),
                Players(
                    listOf(
                        Player(PlayerName("심지"), CardHand(emptyList())),
                        Player(PlayerName("해나"), CardHand(emptyList())),
                    ),
                ),
            )

        participants.addInitialCards(RandomCardGenerator(CardDeck.cardDeck))

        val cardHandSize1 = participants.dealer.cardHand.hand.size
        val cardHandSize2 = participants.players.players[0].cardHand.hand.size
        val cardHandSize3 = participants.players.players[1].cardHand.hand.size

        assertThat(cardHandSize1).isEqualTo(2)
        assertThat(cardHandSize2).isEqualTo(2)
        assertThat(cardHandSize3).isEqualTo(2)
    }

    @Test
    fun `플레이어들의 카드 패의 합을 이름과 짝 지어서 가져온다`() {
        val actual = defaultParticipants.getPlayerResult()

        assertThat(actual).isEqualTo(
            mapOf(
                PlayerName("심지") to 15,
                PlayerName("해나") to 16,
                PlayerName("악어") to 10,
                PlayerName("팡태") to 17,
            ),
        )
    }
}
