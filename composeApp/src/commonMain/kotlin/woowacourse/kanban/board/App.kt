package woowacourse.kanban.board

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile_image
import kanbanboard.composeapp.generated.resources.task_planet
import org.jetbrains.compose.resources.painterResource

@Composable
@Preview(showBackground = true)
fun App() {
    val inputWindow = InputWindow()
    val taskCardGroup by remember { mutableStateOf(mutableListOf<TaskCard>()) }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        val openInputWindow = AddButton(modifier = Modifier.align(Alignment.BottomEnd))
        if (openInputWindow) {
            val (taskCard, isConfirmed) = inputWindow.OpenInputWindow()
            if (isConfirmed) {
                taskCardGroup.add(taskCard)
                GroupTheTaskCard(taskCardGroup)
            }
        }
    }
}

@Composable
fun AddButton(modifier: Modifier): Boolean {
    var showInputWindow by remember { mutableStateOf(false) }
    Button(
        onClick = { showInputWindow = !showInputWindow },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF5CFFD1),
            contentColor = Color.Black,
        ),
        modifier = modifier.size(height = 150.dp, width = 250.dp),
    ) {
        Row {
            Image(
                painter = painterResource(Res.drawable.task_planet),
                contentDescription = "",
                modifier = Modifier.size(50.dp),
            )
            Text(
                text = "추가",
                fontSize = 50.sp,
            )
        }
    }
    return showInputWindow
}

@Composable
fun GroupTheTaskCard(taskCardGroup: List<TaskCard>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(taskCardGroup.size) { item ->
            CreateTaskCard(taskCard = taskCardGroup[item])
        }
    }
}

@Composable
fun CreateTaskCard(taskCard: TaskCard) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier.width(286.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                text = taskCard.title,
                modifier = Modifier
                    .size(width = 252.dp, height = 26.dp)
                    .align(Alignment.CenterHorizontally),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = taskCard.contents,
                modifier = Modifier
                    .size(width = 252.dp, height = 40.dp)
                    .align(Alignment.CenterHorizontally),
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                taskCard.tags.forEach { tag ->
                    SuggestionChip(
                        label = { Text(tag) },
                        onClick = { },
                    )
                }
            }
            Row(
                modifier = Modifier
                    .size(width = 252.dp, height = 45.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(Res.drawable.profile_image),
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterVertically),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = taskCard.author,
                    modifier = Modifier
                        .height(20.dp)
                        .align(Alignment.CenterVertically),
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}