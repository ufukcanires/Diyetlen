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

class RecyclerViewAdapterNutrite (private var nutritionList : ArrayList<NutrientModel>, private val listener : Listener) :
    RecyclerView.Adapter<RecyclerViewAdapterNutrite.RowHolder>(),Filterable{

    var nutritionListFiltered = nutritionList

    interface Listener{
        fun onItemClick(nutrition : NutrientModel)
           //    fun handleResponse(nutritionModel : NutritionModelArray)
    }

    class RowHolder (view: View) : RecyclerView.ViewHolder(view){

        fun bind(nutrition: NutrientModel, position: Int, listener : Listener){
            itemView.setOnClickListener{listener.onItemClick(nutrition)}
            itemView.food_name.text = nutrition.name
            itemView.food_cal.text = nutrition.calories.toString() + " " + "kcal"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(nutritionListFiltered[position],position,listener)
        val imgUrl = "${nutritionListFiltered[position].img_url}"
        Picasso.get().load(imgUrl).into(holder.itemView.imageFood)
    }

    override fun getItemCount(): Int {
        return nutritionListFiltered.count()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence):FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    nutritionListFiltered = nutritionList
                } else {
                    val filteredList = ArrayList<NutrientModel>()

                    try{
                        for (row in nutritionList) {
                            if (row.name.toUpperCase().contains(charString.toUpperCase())) {
                                filteredList.add(row)
                            }

                        }
                    } catch (ex:Exception) {

                    }

                    nutritionListFiltered = filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = nutritionListFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {

                if(filterResults.values == null){
                    nutritionListFiltered = ArrayList()
                }else {
                    nutritionListFiltered =  filterResults.values as ArrayList<NutrientModel>
                }
                notifyDataSetChanged()
            }
        }
    }
}