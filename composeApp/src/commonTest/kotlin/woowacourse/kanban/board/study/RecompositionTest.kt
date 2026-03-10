package woowacourse.kanban.board.study

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class RecompositionTest {
    var username by mutableStateOf("")
    var label by mutableStateOf("라벨")
    var validationCount = 0

    @Before
    fun setup() {
        username = ""
        label = "라벨"
        validationCount = 0
    }

    @Composable
    fun Username(username: String, label: String) {
        val isError = (username.length !in 2..5).also { validationCount++ }
        TextField(
            value = username,
            label = { Text(label) },
            isError = isError,
            onValueChange = { this.username = it }
        )
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `리컴포지션될 때 매번 유효성 검사`() = runComposeUiTest {
        setContent {
            Username(username = username, label = label)
        }
        assertThat(validationCount).isEqualTo(1)
        username = "사무엘"
        waitForIdle()
        assertThat(validationCount).isEqualTo(2)
        username = "조디악"
        waitForIdle()
        assertThat(validationCount).isEqualTo(3)
        label = "라벨2"
        waitForIdle()
        assertThat(validationCount).isEqualTo(4)
    }

    @Composable
    fun UsernameWithRemember(username: String, label: String) {
        val isError = remember {
            (username.length !in 2..5).also { validationCount++ }
        }
        TextField(
            value = username,
            label = {
                if (isError) Text("에러") else Text(label)
            },
            isError = isError,
            onValueChange = { this.username = it }
        )
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `최초 리컴포지션만 유효성 검사`() = runComposeUiTest {
        setContent {
            UsernameWithRemember(username = username, label = label)
        }
        assertThat(validationCount).isEqualTo(1)
        username = "사무엘"
        waitForIdle()
        assertThat(validationCount).isEqualTo(1)
        onNodeWithText("에러").assertExists()
    }
}