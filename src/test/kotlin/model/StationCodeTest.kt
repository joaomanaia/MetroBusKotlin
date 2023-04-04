package model

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class StationCodeTest {
    @Test
    fun `test StationCode different chars than 4, throws IllegalArgumentException`() {
        val message = assertThrows<IllegalArgumentException> {
            StationCode("123")
        }

        assertThat(message)
            .hasMessageThat()
            .isEqualTo("Station code must be 4 characters long")
    }

    @Test
    fun `test StationCode with non alphanumeric chars, throws IllegalArgumentException`() {
        val message = assertThrows<IllegalArgumentException> {
            StationCode("A2S!")
        }

        assertThat(message)
            .hasMessageThat()
            .isEqualTo("Station code must be alphanumeric")
    }
}