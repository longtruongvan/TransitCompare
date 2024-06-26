package com.example.transitcompare.presentation.home.view

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.transitcompare.R
import com.example.transitcompare.presentation.home.viewmodel.HomeViewModel
import com.example.transitcompare.presentation.home.viewmodel.HomeViewModelImpl
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity(), TicketAdapter.ItemClickListener,
    OptionFilterAdapter.ItemClickListener {
    private val homeViewModel: HomeViewModelImpl by viewModel<HomeViewModelImpl>()
    private var recyclerViewTicket: RecyclerView? = null
    private var recyclerViewOptionFilter: RecyclerView? = null
    private var ticketAdapter: TicketAdapter? = null
    private var optionFilterAdapter: OptionFilterAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(homeViewModel)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initView()
        initListeners()
        getData()
    }

    private fun getData() {
        homeViewModel.getTicket()
        homeViewModel.getFilters()
    }

    private fun initListeners() {
        homeViewModel.uiState.observe(this) {
            when (it) {
                is HomeViewModel.HomeUIState.ShowLoading -> {


                }

                is HomeViewModel.HomeUIState.ShowListOptionFilter -> {
                    optionFilterAdapter?.setData(it.filters)
                }

                is HomeViewModel.HomeUIState.ShowListTicket -> {
                    ticketAdapter?.setData(it.tickets, false)
                }
            }
        }
    }

    private fun initView() {
        changeStatusBarColor()
        setupOptionFilters()
        setupTickets()
    }

    private fun setupOptionFilters() {
        recyclerViewOptionFilter = findViewById(R.id.option_filters)
        optionFilterAdapter = OptionFilterAdapter(this)
        recyclerViewOptionFilter?.layoutManager =
            LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewOptionFilter?.setHasFixedSize(true)
        recyclerViewOptionFilter?.adapter = optionFilterAdapter
    }

    private fun setupTickets() {
        recyclerViewTicket = findViewById(R.id.recycler_view_ticket)
        ticketAdapter = TicketAdapter(this)
        recyclerViewTicket?.layoutManager =
            LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
        recyclerViewTicket?.setHasFixedSize(true)
        recyclerViewTicket?.adapter = ticketAdapter
    }

    private fun changeStatusBarColor() {
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        // finally change the color
        window.statusBarColor = ContextCompat.getColor(this@HomeActivity, android.R.color.white)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(homeViewModel)
    }

    override fun viewDetail(position: Int) {
        homeViewModel.tickets[position].viewDetail = !homeViewModel.tickets[position].viewDetail
        ticketAdapter?.setData(homeViewModel.tickets, true)
    }

    override fun loadMoreTickets() {
        homeViewModel.loadMoreTickets()
    }

    override fun onClick(position: Int) {
        homeViewModel.optionFilters.forEach {
            it.isSelected = false
        }
        homeViewModel.optionFilters[position].isSelected = true
        optionFilterAdapter?.setData(homeViewModel.optionFilters)
    }
}