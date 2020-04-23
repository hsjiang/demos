package com.riven_chris.mvvm.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.riven_chris.mvvm.R
import com.riven_chris.mvvm.sample.adapter.PagingListAdapter
import com.riven_chris.mvvm.viewModelProvider
import kotlinx.android.synthetic.main.activity_paging.*

class PagingActivity : AppCompatActivity() {

    private lateinit var mViewModel: PagingViewModel

    private val mAdapter by lazy { PagingListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)
        mViewModel = viewModelProvider()

        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = mAdapter

        mViewModel.items.observe(this, Observer {
//            mAdapter.submitList(it)
        })
    }
}
