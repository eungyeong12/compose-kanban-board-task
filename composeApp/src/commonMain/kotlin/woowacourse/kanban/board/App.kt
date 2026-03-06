package woowacourse.kanban.board

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import woowacourse.kanban.board.component.AddButton
import woowacourse.kanban.board.taskcard.GroupTheTaskCard
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
