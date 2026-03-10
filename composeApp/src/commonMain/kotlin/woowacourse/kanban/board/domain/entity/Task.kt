package woowacourse.kanban.board.domain.entity

data class Task(
    val title: String,
    val content: String = "",
    val tags: List<Tag> = listOf(),
    val author: String
) {
    companion object {
        fun of(title: String, content: String, tagsInput: List<String>, author: String): Result<Task> {
            if (title.isBlank()) return Result.failure(Exception("제목을 입력해주세요"))
            if (author.isBlank()) return Result.failure(Exception("작성자를 입력해주세요"))
            if (tagsInput.size > 5) return Result.failure(Exception("태그는 최대 5개까지 입력할 수 있습니다"))
            val tags = tagsInput.map { name ->
                val result = Tag.from(name)
                result.onFailure { return Result.failure(it) }
                result.getOrThrow()
            }
            return Result.success(Task(title, content, tags, author))
        }
    }
}