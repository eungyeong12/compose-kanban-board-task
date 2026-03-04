package woowacourse.kanban.board

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class InputWindow {
    var title = "LazyColumn 컴포넌트 구현"

    @Composable
    fun OpenInputWindow() {
        Box(
            modifier = Modifier.size(200.dp),
        ) {
            Column() {
                // 제목 입력
                Row(modifier = Modifier.height(40.dp)) {
                    Text(
                        text = "제목: ",
                        modifier = Modifier.align(Alignment.CenterVertically),
                    )
                    title = getTextField(Modifier.weight(1f))
                }
            }
        }
    }

    @Composable
    fun getTextField(weight: Modifier): String {
        var text by rememberSaveable { mutableStateOf("") }

        TextField(
            value = text,
            onValueChange = { newText -> text = newText },
            label = { Text("입력하세요") },
            modifier = weight,
        )
        return text
    }
}
