package recycler

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.social_goal_sharing.R
import com.google.android.material.imageview.ShapeableImageView
import org.w3c.dom.Text

class HomeAdapter(private val accList : ArrayList<Acc>): RecyclerView.Adapter<recycler.HomeAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem =accList[position]
        holder.profileIcon.setImageResource(currentItem.profileIcon)
        holder.imgpub.setImageResource(currentItem.imgPub)
        //holder.linear.layout(currentItem.linear)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class MyViewHolder (itemView : View):RecyclerView.ViewHolder(itemView){

val profileIcon :ShapeableImageView = itemView.findViewById(R.id.profilIcon)
val imgpub :ShapeableImageView = itemView.findViewById(R.id.imgpub)

        val linear : View = itemView.findViewById(R.id.linear1)
        val linearPub : View = itemView.findViewById(R.id.linearPub)
        val liked : TextView = itemView.findViewById(R.id.liked)




    }


}