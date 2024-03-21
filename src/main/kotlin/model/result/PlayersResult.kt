package model.result

import model.participants.IdCard

data class PlayersResult(val result: Map<IdCard, ResultType>)
