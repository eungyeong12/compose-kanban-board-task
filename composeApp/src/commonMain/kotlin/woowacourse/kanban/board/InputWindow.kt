package woowacourse.kanban.board

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile_image
import org.jetbrains.compose.resources.painterResource

class InputWindow {
    var title = "LazyColumn 컴포넌트 구현"
    var contents = ""
    var author = "다이노"
    var tags = mutableListOf<String>()


    @Composable
    // @Preview(showBackground = true)
    fun OpenInputWindow() {
        var showTaskCard by remember { mutableStateOf(false) }

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
                        modifier = Modifier.align(Alignment.CenterVertically),
                    )
                    author = GetTextField(Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(10.dp))
                // 확인 버튼
                Button(
                    onClick = {
                        showTaskCard = true
                    },
                    modifier = Modifier.align(Alignment.End),
                ) {
                    Text("확인")
                }
            }
        }

        if (showTaskCard) TaskCard()
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
        var tempTag by remember { mutableStateOf("") }

        Column() {


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
                    },
                ),
            )
        }
    }

    @Preview()
    @Composable
    fun TaskCard() {
        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier.width(286.dp),
        ) {
            LazyColumn(
                contentPadding = PaddingValues(16.dp)
            ) {
                item {
                    Text(
                        text = title,
                        modifier = Modifier
                            .size(width = 252.dp, height = 26.dp)
                            .align(Alignment.CenterHorizontally),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = contents,
                        modifier = Modifier
                            .size(width = 252.dp, height = 40.dp)
                            .align(Alignment.CenterHorizontally),
                        fontSize = 14.sp,
                        color = Color.Gray,
                    )
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        tags.forEach { tag ->
                            InputChip(
                                selected = false,
                                label = { Text(tag) },
                                onClick = {  },
                            )
                        }
                    }
                    Row(modifier = Modifier
                        .size(width = 252.dp, height = 45.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Image(
                            painter = painterResource(Res.drawable.profile_image),
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.CenterVertically),
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = author,
                            modifier = Modifier
                                .height(20.dp)
                                .align(Alignment.CenterVertically),
                            fontSize = 14.sp,
                        )
                    }
                }
            }
        }
    }
}
