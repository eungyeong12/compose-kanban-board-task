package woowacourse.kanban.board.ui.taskcard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.StateFlow
import woowacourse.kanban.board.domain.Task
import woowacourse.kanban.board.ui.home.HomeUiState

@Composable
fun InputWindow(
    modifier: Modifier,
    onAddTaskCard: (String, String, List<String>, String) -> Boolean,
    showInputWindow: Boolean,
    onValueChange: () -> Unit,
    errorMessage: String
) {
    var title by rememberSaveable { mutableStateOf("") }
    var content by rememberSaveable { mutableStateOf("") }
    var tags by rememberSaveable { mutableStateOf(listOf<String>()) }
    var author by rememberSaveable { mutableStateOf("") }

    if (showInputWindow) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            border = BorderStroke(1.dp, Color(0xFF313232)),
            modifier = modifier.width(280.dp)
        ) {
            Column(
                modifier = modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                InputField(title = "제목: ", onValueChange = { title = it })
                InputField(title = "내용: ", onValueChange = { content = it })
                TagInput(title = "태그: ", onAddTag = { tags = tags + it })
                InputField(title = "작성자: ", onValueChange = { author = it })
                ErrorMessage(message = errorMessage)
                SaveButton(
                    onClick = {
                        val success = onAddTaskCard(title, content, tags, author)
                        if (success) {
                            title = ""
                            content = ""
                            tags = listOf()
                            author = ""
                            onValueChange()
                        }
                    },
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

@Composable
fun InputField(title: String, onValueChange: (String) -> Unit) {
    var text by rememberSaveable { mutableStateOf("") }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = title)
        Spacer(modifier = Modifier.width(8.dp))
        TextField(
            value = text,
            onValueChange = {
                text = it
                onValueChange(text)
            },
            label = { Text("입력하세요") },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun TagInput(title: String, onAddTag: (String) -> Unit) {
    var tag by rememberSaveable { mutableStateOf("") }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = title)
        Spacer(modifier = Modifier.width(8.dp))
        TextField(
            value = tag,
            onValueChange = { tag = it },
            label = { Text("태그 입력 후 엔터") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    onAddTag(tag)
                    tag = ""
                },
            ),
        )
    }
}

@Composable
fun ErrorMessage(message: String) {
    Text(text = message, color = Color.Red)
}

@Composable
fun SaveButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
    ) {
        Text("확인")
    }
}

@Preview
@Composable
fun InputWindowPreview() {
    InputWindow(
        modifier = Modifier,
        onAddTaskCard = { _, _, _, _ -> true },
        showInputWindow = true,
        onValueChange = {},
        errorMessage = ""
    )
}