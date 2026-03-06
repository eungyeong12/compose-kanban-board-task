package woowacourse.kanban.board

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.kanban.board.component.AddButton
import woowacourse.kanban.board.taskcard.GroupTheTaskCard
import woowacourse.kanban.board.taskcard.TaskCard

@Preview(showBackground = true)
@Composable
fun App() {
    var taskCardGroup by rememberSaveable { mutableStateOf(listOf<TaskCard>()) }
    var showOpenWindow by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        GroupTheTaskCard(taskCardGroup)
        AddButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            { showOpenWindow = true }
        )
        OpenInputWindow(
            modifier = Modifier.align(Alignment.Center),
            { taskCardGroup = taskCardGroup + it },
            showOpenWindow,
            { showOpenWindow = false }
        )
    }
}
