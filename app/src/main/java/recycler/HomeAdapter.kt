package recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.social_goal_sharing.R
import com.google.android.material.imageview.ShapeableImageView
import org.w3c.dom.Text

class HomeAdapter(private val accList : ArrayList<Acc>): RecyclerView.Adapter<recycler.HomeAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.home_items,parent,false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem =accList[position]
        holder.profileicon!!.setImageResource(currentItem.profileIcon)
        holder.nametv!!.text = currentItem.nameTv
        holder.timetv!!.text = currentItem.timeTv
        holder.liked!!.text = currentItem.liked
        holder.imgpub!!.setImageResource(currentItem.imgPub)
        holder.likebtn!!.setImageResource(currentItem.likeBtn)
        holder.cmntbtn!!.setImageResource(currentItem.cmntBtn)
        holder.sharebtn!!.setImageResource(currentItem.shareBtn)

    }

    override fun getItemCount(): Int = accList.size




    class MyViewHolder (itemView : View):RecyclerView.ViewHolder(itemView){

var profileicon : ImageView?=null
var nametv : TextView?=null
var timetv : TextView?=null
var liked : TextView?=null
var imgpub : ImageView?=null
var likebtn : ImageButton?=null
var cmntbtn : ImageButton?=null
var sharebtn : ImageButton?=null
        init {
            profileicon = itemView.findViewById(R.id.profilIcon)
            nametv = itemView.findViewById(R.id.nameTv)
            timetv = itemView.findViewById(R.id.timeTv)
            liked = itemView.findViewById(R.id.liked)
            imgpub = itemView.findViewById(R.id.imgPub)
            likebtn = itemView.findViewById(R.id.likeBtn)
            cmntbtn = itemView.findViewById(R.id.cmntBtn)
            sharebtn = itemView.findViewById(R.id.shareBtn)

        }






    }


}