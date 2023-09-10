package com.example.studooov2.UserSignUpLogin.InternetOperations.Data.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.studooov2.R
import com.example.studooov2.UserSignUpLogin.Models.regularUser
import com.example.studooov2.UserSignUpLogin.userSearchAdapter
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import javax.inject.Inject

class UserAdapter @Inject constructor(val context: Context) : PagingDataAdapter<regularUser, UserAdapter.ViewHolder>(COMPARATOR){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userSearchUsername.text = getItem(position)?.username
        holder.userSearchUni.text = getItem(position)?.uni
        downloadPhoto(getItem(position)?.profile_photo, holder.userSearchProfilePhoto)
    }

    private fun downloadPhoto(profilePhoto: String?, userSearchProfilePhoto: ImageView?) {
        val firebaseStorage: FirebaseStorage
        val storageReference: StorageReference
        firebaseStorage = FirebaseStorage.getInstance()
        storageReference = firebaseStorage.reference
        val reference: StorageReference = storageReference.child("profile_photos/$profilePhoto")
        reference.downloadUrl.addOnSuccessListener { uri ->
            if (userSearchProfilePhoto != null) {
                Glide.with(context)
                        .load(uri)
                        .into(userSearchProfilePhoto)
            }
        }.addOnFailureListener { e ->

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_search_row_design, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val userSearchUsername = itemView.findViewById<TextView>(R.id.userSearchUsername)
        val userSearchUni = itemView.findViewById<TextView>(R.id.userSearchUni)
        val userSearchProfilePhoto = itemView.findViewById<ImageView>(R.id.userSearchProfilePhoto)
        val userSearchUniLogo = itemView.findViewById<ImageView>(R.id.userSearchUniLogo)
        val userSearchRowLinearLayout = itemView.findViewById<LinearLayout>(R.id.userSearchRowLinearLayout)

    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<regularUser>() {
            override fun areItemsTheSame(oldItem: regularUser, newItem: regularUser): Boolean {
                return oldItem.user_id == newItem.user_id
            }

            override fun areContentsTheSame(oldItem: regularUser, newItem: regularUser): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }



}