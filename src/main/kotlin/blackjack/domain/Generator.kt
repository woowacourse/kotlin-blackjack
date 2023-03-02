package blackjack.domain

import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape

interface Generator {

    fun generateCardNumber(): CardNumber
    fun generateCardShape(): CardShape
}
