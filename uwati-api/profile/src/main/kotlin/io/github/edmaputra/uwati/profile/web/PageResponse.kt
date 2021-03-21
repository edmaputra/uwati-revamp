package io.github.edmaputra.uwati.profile.web

data class PageResponse<T>(

  val content: T,

  val pageNumber: Int,

  val totalPage: Int,

  val totalItems: Int
)
