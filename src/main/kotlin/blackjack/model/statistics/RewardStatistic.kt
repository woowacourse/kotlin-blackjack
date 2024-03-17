package blackjack.model.statistics

import blackjack.model.Money
import blackjack.model.Participant

data class RewardStatistic(val participant: Participant, val reward: Money)
