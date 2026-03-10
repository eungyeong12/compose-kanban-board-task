package woowacourse.kanban.board.domain.entity

data class Tag(val name: String) {
    companion object {
        fun from(name: String): Result<Tag> {
            if (name.isBlank()) return Result.failure(Exception("태그 내용이 비어 있습니다"))
            if (name.length > 5) return Result.failure(Exception("태그의 길이는 5자 이하여야 합니다"))
            return Result.success(Tag(name))
        }
    }
}