package com.microsoft.sample.stableids

import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

/**
 * data <=> Views
 */
class StableIdActivity : BaseMenuActivity() {

  private var items: MutableList<Item> = ArrayList()
  private lateinit var adapter: StableIdAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    title = "StableIdActivity"

    setContentView(R.layout.activity_main)
    val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

    adapter = StableIdAdapter(items)

    adapter.setHasStableIds(true) // 💡💡💡
    recyclerView.adapter = adapter

    initRightActions()
  }

  private fun initRightActions() {
    with(findViewById<LinearLayout>(R.id.actions)) {
      addView(newActionTextView(
        """
        (0..20).forEach { items.add(Item()) }
        """.trimIndent()
      ) {
        (0..20).forEach {
          items.add(Item("Subject $it", "Summary $it"))
        }
        adapter.notifyDataSetChanged()
      })

      addView(newActionTextView(
        """
        items.removeAll { it.id % 2 == 0L }
        """.trimIndent()
      ) {
        items.removeAll { it.id % 2 == 0L }
        adapter.notifyDataSetChanged()
      })

      addView(newActionTextView(
        """
        items.add(1, Item("Added Subject 999", "Summary 999"))
        """.trimIndent()
      ) {
        items.add(1, Item("Added Subject 999", "Summary 999"))
        adapter.notifyDataSetChanged()
      })

      addView(newActionTextView(
        """
        items[1].subject = "Updated"
        """.trimIndent()
      ) {
        items[1].subject = "Updated"
        adapter.notifyDataSetChanged()
      })

      addView(newActionTextView(
        """
        items.subList(0, 5).shuffle()
        """.trimIndent()
      ) {
        items.subList(0, 5).shuffle()
        adapter.notifyDataSetChanged()
      })

      addView(newActionTextView(
        """
        items.clear()
        """.trimIndent()
      ) {
        items.clear()
        adapter.notifyDataSetChanged()
      })
    }
  }
}
