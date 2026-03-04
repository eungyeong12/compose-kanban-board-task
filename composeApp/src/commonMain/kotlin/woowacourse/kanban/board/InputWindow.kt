package woowacourse.kanban.board

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class InputWindow {
    var title = "LazyColumn 컴포넌트 구현"
    var contents = ""
    var author = ""

    @Composable
    @Preview(showBackground = true)
    fun OpenInputWindow() {
        OutlinedCard(
            modifier = Modifier
                .size(width = 200.dp, height = 270.dp),
        ) {
            Column {
                // 제목 입력
                Row(modifier = Modifier.height(40.dp)) {
                    Text(
                        text = "제목: ",
                        modifier = Modifier.align(Alignment.CenterVertically),
                    )
                    title = GetTextField(Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(10.dp))
                // 내용 입력
                Row(modifier = Modifier.height(40.dp)) {
                    Text(
                        text = "내용: ",
                        modifier = Modifier.align(Alignment.CenterVertically),
                    )
                    contents = GetTextField(Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(10.dp))
                // 태그 입력
                Row(modifier = Modifier.height(40.dp)) {
                    Text(
                        text = "태그: ",
                        modifier = Modifier.align(Alignment.CenterVertically),
                    )
                    // val tagOrigin = getTextField(Modifier.weight(1f))
                    TagInput()
                }
                Spacer(modifier = Modifier.height(10.dp))
                // 작성자 입력
                Row(modifier = Modifier.height(40.dp)) {
                    Text(
                        text = "작성자: ",
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    author = GetTextField(Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(10.dp))
                // 확인 버튼
                Button(onClick = {},
                    modifier = Modifier.align(Alignment.End)) {
                    Text("확인")
                }
            }
        }
    }

    @Composable
    fun GetTextField(weight: Modifier): String {
        var text by rememberSaveable { mutableStateOf("") }

        TextField(
            value = text,
            onValueChange = { newText -> text = newText },
            label = { Text("입력하세요") },
            modifier = weight,
        )
        return text
    }

    @Composable
    fun TagInput() {
        val tags = remember { mutableStateListOf<String>() }
        var tempTag by remember { mutableStateOf("") }

        Column() {
//            FlowRow(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.spacedBy(8.dp),
//                verticalArrangement = Arrangement.spacedBy(4.dp)
//            ) {
//                tags.forEach { tag ->
//                    InputChip(
//                        selected = false,
//                        onClick = { },
//                        label = { Text(tag) },
////                        trailingIcon = {
////                            Icon(
////                                Icons.Default.Close,
////                                contentDescription = "삭제",
////                                modifier = Modifier.size(16.dp)
////                            )
////                        },
////                        onClick = { tags.remove(tag) }
//                    )
//
//                }
//            }

            TextField(
                value = tempTag,
                onValueChange = { tempTag = it },
                label = { Text("태그 입력 후 엔터") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (tempTag.isNotBlank() && !tags.contains(tempTag)) {
                            tags.add(tempTag.trim())
                            tempTag = ""
                        }
                    }
                )
            )
        }
    }
}
