package woowacourse.kanban.board

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    val taskCardGroup = remember { mutableStateListOf<TaskCard>() }
    var showOpenWindow by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        GroupTheTaskCard(taskCardGroup)
        AddButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            showOpenWindow,
            { showOpenWindow = true }
        )
        OpenInputWindow(
            taskCardGroup,
            showOpenWindow,
            { showOpenWindow = false }
        )
    }
}
