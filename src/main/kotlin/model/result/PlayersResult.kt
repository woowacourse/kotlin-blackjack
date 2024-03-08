package model.result

import model.participants.HumanName

data class PlayersResult(val result: Map<HumanName, ResultType>)
