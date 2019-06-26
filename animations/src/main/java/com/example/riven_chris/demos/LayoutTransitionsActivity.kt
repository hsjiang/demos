package com.example.riven_chris.demos

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.transition.*
import com.example.riven_chris.R
import kotlinx.android.synthetic.main.activity_layout_transitions.*

class LayoutTransitionsActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_transitions)

        val aScene = Scene.getSceneForLayout(scene_root, R.layout.layout_a_scene, this)
        val anotherScene = Scene.getSceneForLayout(scene_root, R.layout.another_scene, this)

        anotherScene.setEnterAction(Runnable {
            Toast.makeText(this, "enter", Toast.LENGTH_SHORT).show()
        })
        aScene.setExitAction(Runnable {
            Toast.makeText(this, "exit", Toast.LENGTH_SHORT).show()
        })

        TransitionManager.go(aScene)

//        val transition = AutoTransition()
//        transition.mode = Fade.IN
        val transition = TransitionInflater.from(this).inflateTransition(R.transition.a_transition_set)
        transition.duration = 5000
//        transition.removeTarget(R.id.text_view1)
//        transition.removeTarget(R.id.text_view2)


        btn.setOnClickListener {
            TransitionManager.go(anotherScene, transition)
        }

        btn_delayed_transition.setOnClickListener {
            val labelText = TextView(this).apply {
                text = "Label"
                id = R.id.text
            }
            val rootView: ViewGroup = findViewById(R.id.fl_center_container)
            val fade = Fade(Fade.IN)
            fade.duration = 3000
            TransitionManager.beginDelayedTransition(rootView, fade)
            rootView.addView(labelText)
        }

    }
}
