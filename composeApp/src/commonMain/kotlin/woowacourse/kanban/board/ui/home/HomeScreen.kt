package woowacourse.kanban.board.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.domain.Task

@Composable
fun HomeScreen(
    tasks: List<Task>
) {
    var showInputWindow by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        TaskCards(tasks)
        AddButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            { showInputWindow = true }
        )
//        InputWindow(
//            modifier = Modifier.align(Alignment.Center),
//            { tasks = tasks + it },
//            showInputWindow,
//            { showInputWindow = false }
//        )
    }
}