package woowacourse.kanban.board.domain.entity

@JvmInline
value class Tag(val name: String) {
    init {
        require(name.isNotBlank()) { "태그 내용이 비어 있습니다" }
        require(name.length <= 5) { "태그의 길이는 5자 이하여야 합니다" }
    }
}