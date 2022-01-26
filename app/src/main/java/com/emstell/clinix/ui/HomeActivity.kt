package com.emstell.clinix.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.emstell.clinix.databinding.ActivityHomeBinding
import com.emstell.clinix.network.models.home.Clinic
import com.emstell.clinix.network.models.home.Doctor
import com.emstell.clinix.ui.Adapters.ClinicAdapter
import com.emstell.clinix.ui.Adapters.DoctorAdapter
import com.emstell.clinix.ui.Interface.ItemClickInterface
import com.emstell.clinix.viewmodel.HomeActivityViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

class HomeActivity : AppCompatActivity() {
    private lateinit var homeBinding: ActivityHomeBinding
    private lateinit var homeActivityViewModel: HomeActivityViewModel
    private lateinit var clinicList:List<Clinic>
    private lateinit var doctorList:List<Doctor>
    private lateinit var clinicAdapter: ClinicAdapter
    private lateinit var doctorAdapter:DoctorAdapter
    private lateinit var doctorLinerLayoutManager:LinearLayoutManager
    private lateinit var clinicLinearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)
        supportActionBar?.hide()
        initViewModel()
        lifecycleScope.launch(Dispatchers.IO){ homeActivityViewModel.callHomeData()}
        clinicRecyclerScrolls()
        doctorRecyclerScrolls()

    }


    private fun initViewModel() {

        homeActivityViewModel= ViewModelProvider(this)[HomeActivityViewModel::class.java]
        homeActivityViewModel.getHomeResponse().observe(this,{
            clinicList = if (it.clinics.isNotEmpty()) it.clinics else ArrayList()
            doctorList = if (it.doctors.isNotEmpty()) it.doctors else ArrayList()
            if (it.status == "200") {
                 homeBinding.apply {
                     idConstraintLayoutNotConnected.visibility = View.GONE
                     idScrollView.visibility = View.VISIBLE
                 }
            }
            else {
                homeBinding.apply {
                    idConstraintLayoutNotConnected.visibility = View.VISIBLE
                    idScrollView.visibility = View.GONE
                }
            }
            clinicAdapter=ClinicAdapter(this,clinicList,object:ItemClickInterface{
                override fun mClick(view: View, pos: Int) {
                     startActivity(Intent(this@HomeActivity, ClinicActivity::class.java).putExtra("clinicId",clinicList[pos].clinic_id))
                  }
            })
            doctorAdapter= DoctorAdapter(this,doctorList)
            homeBinding.apply {
                idRecyclerViewClinics.apply {
                    clinicLinearLayoutManager=LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
                    layoutManager=clinicLinearLayoutManager
                    adapter=clinicAdapter
                }
                idRecyclerViewDoctors.apply {
                    doctorLinerLayoutManager=LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
                    layoutManager=doctorLinerLayoutManager
                    adapter=doctorAdapter
                }
                idProgressBar.visibility = View.GONE
            }
        })
    }
    private fun doctorRecyclerScrolls() {
        homeBinding.idButtonDoctorRightArrow.setOnClickListener {
            val totalCount=homeBinding.idRecyclerViewDoctors.adapter?.itemCount
            if (totalCount!! <=0)
                return@setOnClickListener
            val lastVisiblePos= doctorLinerLayoutManager.findLastVisibleItemPosition()
            if (lastVisiblePos>=totalCount)
                return@setOnClickListener
            homeBinding.idRecyclerViewDoctors.layoutManager?.smoothScrollToPosition(homeBinding.idRecyclerViewDoctors,null,lastVisiblePos+1)
        }
        homeBinding.idButtonDoctorLeftArrow.setOnClickListener {
            val firstVisiblePos=doctorLinerLayoutManager.findFirstVisibleItemPosition()
            if (firstVisiblePos>0)
                homeBinding.idRecyclerViewDoctors.layoutManager?.smoothScrollToPosition(homeBinding.idRecyclerViewDoctors,null,firstVisiblePos-1)
        }
    }

    private fun clinicRecyclerScrolls() {
        homeBinding.idButtonRightArrow.setOnClickListener {
            val totalCount=homeBinding.idRecyclerViewClinics.adapter?.itemCount
            if (totalCount!! <=0)
                return@setOnClickListener
            val lastVisiblePos= clinicLinearLayoutManager.findLastVisibleItemPosition()
            if (lastVisiblePos>=totalCount)
                return@setOnClickListener
            homeBinding.idRecyclerViewClinics.layoutManager?.smoothScrollToPosition(homeBinding.idRecyclerViewClinics,null,lastVisiblePos+1)
        }
        homeBinding.idButtonLeftArrow.setOnClickListener {
            val firstVisiblePos=clinicLinearLayoutManager.findFirstVisibleItemPosition()
            if (firstVisiblePos>0)
                homeBinding.idRecyclerViewClinics.layoutManager?.smoothScrollToPosition(homeBinding.idRecyclerViewClinics,null,firstVisiblePos-1)
        }
    }

}