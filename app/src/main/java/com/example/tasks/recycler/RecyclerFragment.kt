package com.example.tasks.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject

class RecyclerFragment : Fragment() {
    private val items = listOf(
        "Первая строка",
        "Вторая строка",
        "Третья строка",
        "Четвертая строка",
        "Пятая строка"
    )
    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val context = requireContext()
        val recyclerView = RecyclerView(context)
        val adapter = TaskAdapter(items)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        disposables.add(
            adapter.clickObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Toast.makeText(context, "Нажатый элемент: $it", Toast.LENGTH_SHORT).show()
                }
        )

        return recyclerView
    }

    class TaskAdapter(private val data: List<String>) :
        RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textView: TextView = itemView as TextView
        }

        private val clickSubject = PublishSubject.create<Int>()
        val clickObservable = clickSubject.hide()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val textView = TextView(parent.context)
            textView.setPadding(32, 200, 32, 24)
            return ViewHolder(textView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textView.text = data[position]
            holder.itemView.setOnClickListener {
                clickSubject.onNext(position + 1)
            }
        }

        override fun getItemCount() = data.size
    }
}
