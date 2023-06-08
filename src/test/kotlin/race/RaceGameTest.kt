package race

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test

class RaceGameTest {
    @Test
    fun `입력된 자동차 수와 RaceResult의 자동차 수가 동일해야 한다`() {
        val raceStartInformation = RaceStartInformation(numberOfCars = 3, numberOfAttempts = 5)

        CarRaceGame().runCarRace(raceStartInformation)
            .turns
            .map {
                it.carPositions
                    .size
            }.forEach {
                assertThat(it).isEqualTo(raceStartInformation.numberOfCars)
            }
    }

    @Test
    fun `RaceResult 내 positions 길이는 입력된 시도 횟수 + 1 이어야 한다`() {
        val raceStartInformation = RaceStartInformation(numberOfCars = 3, numberOfAttempts = 5)

        CarRaceGame().runCarRace(raceStartInformation)
            .turns
            .size
            .let {
                assertThat(it).isEqualTo(raceStartInformation.numberOfAttempts + 1)
            }
    }

    @RepeatedTest(10)
    fun `차량의 위치는 이전 위치와 같거나 +1 이어야 한다`() {
        val raceStartInformation = RaceStartInformation(numberOfCars = 3, numberOfAttempts = 5)

        CarRaceGame().runCarRace(raceStartInformation)
            .turns
            .reduce { prevPosition, currentPosition ->
                prevPosition.carPositions
                    .zip(currentPosition.carPositions)
                    .forEach {
                        val distance = it.second.position - it.first.position
                        assertThat(distance == 0 || distance == 1).isTrue()
                    }
                currentPosition
            }
    }
}
