package com.microsoft.sample.stableids

import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

/**
 * data <=> data
 */
class ListDifferActivity : BaseMenuActivity() {

  private lateinit var adapter: ListDifferAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    title = "ListDifferActivity"

    setContentView(R.layout.activity_main)
    val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

    adapter = ListDifferAdapter()
    recyclerView.adapter = adapter

    initRightActions()
  }

  private fun initRightActions() {
    with(findViewById<LinearLayout>(R.id.actions)) {
      addView(newActionTextView(
        """
        MutableList(20) { Item() }.toList()
        """.trimIndent()
      ) {
        adapter.items = MutableList(20) { Item("Subject $it", "Summary $it") }.toList()
      })

      addView(newActionTextView(
        """
        adapter.items
          .toMutableList()
          .apply { removeAll { it.id % 2 == 0L } }
          .toList()
        """.trimIndent()
      ) {
        adapter.items = adapter.items
          .toMutableList()
          .apply { removeAll { it.id % 2 == 0L } }
          .toList()
      })

      addView(newActionTextView(
        """
        adapter.items
          .toMutableList()
          .apply { add(1, Item("Added Subject 999", "Summary 999")) }
          .toList()
        """.trimIndent()
      ) {
        adapter.items = adapter.items
          .toMutableList()
          .apply { add(1, Item("Added Subject 999", "Summary 999")) }
          .toList()
      })

      addView(newActionTextView(
        """
        adapter.items
          .toMutableList()
          .apply { set(1, get(1).copy(subject = "Updated")) }
          .toList()
        """.trimIndent()
      ) {
        adapter.items = adapter.items
          .toMutableList()
          .apply { set(1, get(1).copy(subject = "Updated")) }
          .toList()
      })

      addView(newActionTextView(
        """
        adapter.items
          .toMutableList()
          .apply { subList(0, 5).shuffle() }
          .toList()
        """.trimIndent()
      ) {
        adapter.items = adapter.items
          .toMutableList()
          .apply { subList(0, 5).shuffle() }
          .toList()
      })

      addView(newActionTextView(
        """
        // clear
        adapter.items = emptyList()
        """.trimIndent()
      ) {
        adapter.items = emptyList()
      })
    }
  }
}
