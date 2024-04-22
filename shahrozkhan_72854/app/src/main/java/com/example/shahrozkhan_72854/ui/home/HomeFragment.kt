package com.example.shahrozkhan_72854.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shahrozkhan_72854.R
import com.example.shahrozkhan_72854.adapters.CategoryAdapter
import com.example.shahrozkhan_72854.adapters.PopularAdapter
import com.example.shahrozkhan_72854.models.CategoryModel
import com.example.shahrozkhan_72854.models.PopularModel
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {
    var scrollView: ScrollView? = null
    var progressBar: ProgressBar? = null
    var popularRec: RecyclerView? = null
    var categoryRec: RecyclerView? = null
    var db: FirebaseFirestore? = null
    var popularModelList: MutableList<PopularModel>? = null
    var popularAdapter: PopularAdapter? = null
    var categoryModelList: MutableList<CategoryModel>? = null
    var categoryAdapter: CategoryAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        db = FirebaseFirestore.getInstance()
        popularRec = root.findViewById(R.id.pop_recycleView)
        categoryRec = root.findViewById(R.id.cat_recycleView)
        progressBar = root.findViewById(R.id.progressBar)
        scrollView = root.findViewById(R.id.scroll_view)
        progressBar?.setVisibility(View.VISIBLE)
        scrollView?.setVisibility(View.GONE)
        popularRec?.setLayoutManager(LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false))
        popularModelList = ArrayList()
        popularAdapter = PopularAdapter(requireActivity(),
            popularModelList as ArrayList<PopularModel>
        )
        popularRec?.setAdapter(popularAdapter)
        db!!.collection("PopularProducts")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        val popularModel = document.toObject(
                            PopularModel::class.java
                        )
                        popularModelList?.add(popularModel)
                        popularAdapter!!.notifyDataSetChanged()
                        progressBar?.setVisibility(View.GONE)
                        scrollView?.setVisibility(View.VISIBLE)
                    }
                } else {
                    Toast.makeText(activity, "Error: " + task.exception, Toast.LENGTH_SHORT).show()
                }
            }
        categoryRec?.setLayoutManager(LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false))
        categoryModelList = ArrayList()
        categoryAdapter = CategoryAdapter(requireActivity(),
            categoryModelList as ArrayList<CategoryModel>
        )
        categoryRec?.setAdapter(categoryAdapter)
        db!!.collection("Categories")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        val categoryModel = document.toObject(
                            CategoryModel::class.java
                        )
                        categoryModelList?.add(categoryModel)
                        categoryAdapter!!.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(activity, "Error: " + task.exception, Toast.LENGTH_SHORT).show()
                }
            }
        return root
    }
}