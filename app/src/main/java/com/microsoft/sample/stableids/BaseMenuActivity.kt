package com.microsoft.sample.stableids

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.*
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView

open class BaseMenuActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    fullscreen()
  }

  override fun onResume() {
    super.onResume()
    fullscreen()
  }

  private fun fullscreen() {
    window.decorView.systemUiVisibility = (
        window.decorView.systemUiVisibility
            or SYSTEM_UI_FLAG_FULLSCREEN
            or SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        )
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.stable -> startActivity(Intent(this, StableIdActivity::class.java))
      R.id.differ -> startActivity(Intent(this, ListDifferActivity::class.java))
    }
    finish()
    return true
  }
}

fun Context.newActionTextView(text: String, onClick: () -> Unit): TextView {
  return AppCompatTextView(this)
    .apply {
      layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
      setRippleBackground()
      setPadding(6.dp, 6.dp, 6.dp, 6.dp)
      setOnClickListener {
        try {
          onClick()
        } catch (e: Exception) {
          Toast.makeText(context, "${e.message}", Toast.LENGTH_SHORT).show()
        }
      }
      setHorizontallyScrolling(true)
      setText(text)
    }
}

fun View.setRippleBackground() = with(TypedValue()) {
  context.theme.resolveAttribute(android.R.attr.selectableItemBackground, this, true)
  setBackgroundResource(resourceId)
}

val Number.dp get() = this.toInt() * 4
