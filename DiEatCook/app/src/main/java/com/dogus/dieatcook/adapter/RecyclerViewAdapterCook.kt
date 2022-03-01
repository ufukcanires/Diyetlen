package com.dogus.dieatcook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.dogus.dieatcook.R
import com.dogus.dieatcook.model.CookModel
import com.dogus.dieatcook.model.NutrientModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_layout.view.*
import java.util.*
import kotlin.collections.ArrayList

class RecyclerViewAdapterCook (private var cookList : ArrayList<CookModel>,private val listener : Listener) :
RecyclerView.Adapter<RecyclerViewAdapterCook.RowHolder>(),Filterable{

    val filteredList = ArrayList<CookModel>()
    var cookListFiltered = cookList

    interface Listener{
        fun onItemClick(cook : CookModel)
        //fun handleResponse(nutritionModel:CookModelArray)
    }

    class RowHolder(view : View): RecyclerView.ViewHolder(view) {
        fun bind(cook: CookModel,position: Int,listener:Listener){
            itemView.setOnClickListener{
                listener.onItemClick(cook)
            }
            itemView.food_name.text = cook.food_name
            itemView.food_cal.text = cook.food_calori

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(cookListFiltered[position],position,listener)
        val imgUrl = "${cookListFiltered[position].img_url}"
        Picasso.get().load(imgUrl).into(holder.itemView.imageFood)
    }

    override fun getItemCount(): Int = cookListFiltered.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence):FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    cookListFiltered = cookList
                } else {
                    val filteredList = ArrayList<CookModel>()

                    try{
                        for (row in cookList) {
                            if (row.food_name.toUpperCase().contains(charString.toUpperCase())) {
                                filteredList.add(row)
                            }

                        }
                    } catch (ex:Exception) {

                    }

                    cookListFiltered = filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = cookListFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {

                if(filterResults.values == null){
                    cookListFiltered = ArrayList()
                }else {
                    cookListFiltered =  filterResults.values as ArrayList<CookModel>
                }
                notifyDataSetChanged()
            }
        }
    }
}