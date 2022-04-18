package com.example.forkieplayer.play

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.forkieplayer.R

class PlayAdapter(val context: Context, val playData: List<PlayData>): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, null)

        val thumbnail = view.findViewById<ImageView>(R.id.iv_thumbnail)
        val title = view.findViewById<TextView>(R.id.tv_title)
        val channelTitle = view.findViewById<TextView>(R.id.tv_channel_name)

        val data = playData[position]
        thumbnail.setImageResource(data.thumbnail)
        title.text = data.title
        channelTitle.text = data.channelTitle

        return view
    }

    override fun getCount(): Int {
        return playData.size
    }

    override fun getItem(p0: Int): Any {
        return playData[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }
}