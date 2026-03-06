package woowacourse.kanban.board.taskcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun GroupTheTaskCard(taskCardGroup: List<TaskCard>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(52.dp)
    ) {
        items(taskCardGroup.size) { item ->
            CreateTaskCard(taskCard = taskCardGroup[item])
        }
    }
}