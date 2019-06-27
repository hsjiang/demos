package com.example.riven_chris

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.riven_chris.customs.AnimActivity
import com.example.riven_chris.demos.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {
    val items = arrayListOf<Pair<String, Class<*>>>().apply {
        add(Pair("FlipCard", AnimActivity::class.java))
        add(Pair("ClippingCircle", ClippingCircleActivity::class.java))
        add(Pair("DefaultLayoutTransitions", DefaultLayoutTransitionsActivity::class.java))
        add(Pair("StateListAnimator", StateListAnimatorActivity::class.java))
        add(Pair("AnimatedVectorDrawable", AnimatedVectorDrawableActivity::class.java))
        add(Pair("CurvePathAnimator", CurvePathAnimatorActivity::class.java))
        add(Pair("TransitionsActivity", TransitionsActivity::class.java))
        add(Pair("DynamicAnimationActivity", DynamicAnimationActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ItemsAdapter(this)
    }


    inner class ItemsAdapter(val context: Context) : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.layout_items, parent, false)
            return ItemViewHolder(view)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val item = items[position]
            holder.tvName.text = item.first
            holder.itemView.setOnClickListener {
                context.startActivity(Intent(context, item.second))
            }
        }

        inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvName by lazy { view.findViewById<TextView>(R.id.tv_name) }
        }
    }
}
