package com.example.circuitmessing.products

import android.view.MenuItem
import com.escaper.escaper.utils.preferences
import com.example.circuitmessing.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProgressManager {

    companion object {
        private val database = Firebase.database.reference

        fun updatePageDone(productName: String, pageName: String, item: MenuItem) {
            var finishedPage: Boolean = false
            val pageRef = database.child(productName).child(pageName)
            val valueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (ds in dataSnapshot.children) {
                        val dbUsername = ds.key
                        if (dbUsername == preferences.username) {
                            finishedPage = true
                        }
                    }
                    if (!finishedPage) {
                        // Page completed
                        database.child(productName).child(pageName).child(preferences.username)
                            .setValue(true)
                        item.setIcon(R.drawable.ic_baseline_check_24)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Here goes error message
                }
            }
            pageRef.addListenerForSingleValueEvent(valueEventListener)
        }

        fun checkDonePages(productName: String, pageName: String, item: MenuItem) {
            val pageRef = database.child(productName).child(pageName)
            val valueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (ds in dataSnapshot.children) {
                        if (ds.key == preferences.username && ds.value == true) {
                            item.setIcon(R.drawable.ic_baseline_check_24)
                        }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    // Here goes error message
                }
            }
            pageRef.addListenerForSingleValueEvent(valueEventListener)
        }
    }

}