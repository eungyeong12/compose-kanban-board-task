package woowacourse.kanban.board

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.task_planet
import org.jetbrains.compose.resources.painterResource
import woowacourse.kanban.board.taskcard.CreateTaskCard
import woowacourse.kanban.board.taskcard.TaskCard

@Composable
fun App() {
    val taskCardGroup = remember { mutableStateListOf<TaskCard>() }
    val openInputWindow = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        GroupTheTaskCard(taskCardGroup)
        AddButton(modifier = Modifier.align(Alignment.BottomEnd), openInputWindow)
        if (openInputWindow.value) {
            OpenInputWindow(taskCardGroup, openInputWindow)
        }
    }
}

@Composable
fun AddButton(modifier: Modifier, openInputWindow: MutableState<Boolean>) {
    Button(
        onClick = { openInputWindow.value = true },
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
}

@Composable
fun GroupTheTaskCard(taskCardGroup: List<TaskCard>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(52.dp)
    ) {
        items(taskCardGroup.size) { item ->
            CreateTaskCard(taskCard = taskCardGroup[item])
        }
    }
}