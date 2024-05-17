package com.app.isanpablo.ui.home

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.app.isanpablo.CombinedAdapter
import com.app.isanpablo.R
import com.app.isanpablo.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.math.abs


class HomeFragment : Fragment() {
    private lateinit var tourismViewPager2: ViewPager2
    private lateinit var eventsViewPager2: ViewPager2
    var url = "https://worldtimeapi.org/api/timezone/Asia/Manila"
    private var _binding: FragmentHomeBinding? = null
    private val updateTimeRunnable = object : Runnable {
        override fun run() {
            updateClock()
            updateTimeHandler.postDelayed(this, 1000) // Update every second
        }
    }
    private val updateTimeHandler = Handler(Looper.getMainLooper())

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        updateTimeHandler.postDelayed(updateTimeRunnable, 0)

        setUpViews()
        setUpTransformer()
        binding.btnJobs.setOnClickListener {
            findNavController().navigate(R.id.nav_home_jobs)
        }
        binding.btnServices.setOnClickListener {
            findNavController().navigate(R.id.nav_home_services)
        }
        binding.btnBusiness2.setOnClickListener {
            findNavController().navigate(R.id.nav_home_business)
        }

        binding.btnViewSanPablo.setOnClickListener {
            findNavController().navigate(R.id.nav_tourism)
        }
        binding.btnViewAllAnnounce.setOnClickListener {
            findNavController().navigate(R.id.nav_home_announcement)
        }
        binding.videoView.setOnClickListener {
            showVideo()
        }

        return binding.root
    }
    private fun showVideo() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.videohymn)
        val mediaController = MediaController(requireContext())
        val dialogVideoView = dialog.findViewById<VideoView>(R.id.videoView2)
        mediaController.setAnchorView(dialogVideoView)
        val videoPath = "android.resource://" + requireContext().packageName + "/" + R.raw.videohymn
        val uri = Uri.parse(videoPath)
        dialogVideoView.setMediaController(mediaController) // Set MediaController before setting the video URI
        dialogVideoView.setVideoURI(uri)
        dialogVideoView.start()
        dialog.show()
    }
    private fun setUpViews() {
        tourismViewPager2 = binding.tourismViewPager2

        val combinedList = listOf(
            R.drawable.pic_sampaloc_lake to "Sampaloc Lake",
            R.drawable.pic_sm_sanpablo to "SM San Pablo",
            R.drawable.pic_sanpablo_church to "San Pablo Church",
            R.drawable.pic_city_hall to "San Pablo City Hall"
        )

        val combinedAdapterTourism = CombinedAdapter(combinedList, tourismViewPager2)
        tourismViewPager2.adapter = combinedAdapterTourism
        tourismViewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateTimeHandler.removeCallbacks(updateTimeRunnable)
                updateTimeHandler.postDelayed(updateTimeRunnable, 2000)
            }
        })

        eventsViewPager2 = binding.eventsViewPager2

        val combinedList2 = listOf(
            R.drawable.sample_news1 to "Blessing in City Transport Terminal",
            R.drawable.sample_news2 to "854 Bikers lumahok sa 10km Fun Bike",
            R.drawable.sample_news3 to "Prime-HRM Members Pagkilala sa Pagtataas ng Watawat",
            R.drawable.sample_news4 to "Nagpulong ang miyembro ng City DRRMC"
        )

        val combinedAdapterEvents = CombinedAdapter(combinedList2, eventsViewPager2)
        eventsViewPager2.adapter = combinedAdapterEvents
        eventsViewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateTimeHandler.removeCallbacks(updateTimeRunnable)
                updateTimeHandler.postDelayed(updateTimeRunnable, 2000)
            }
        })
    }

    private fun setUpTransformer() {
        val transformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(50))
            addTransformer { page, position ->
                val r = 1 - abs(position)
                page.scaleX = 0.95f + r * 0.05f
            }
        }
        tourismViewPager2.setPageTransformer(transformer)
        eventsViewPager2.setPageTransformer(transformer)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        updateTimeHandler.removeCallbacks(updateTimeRunnable)
        _binding = null
    }
    private fun updateClock() {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("LLLL dd yyyy", Locale.getDefault())
        val timeFormat = SimpleDateFormat("hh:mm:ss a", Locale.getDefault())
        val dayFormat = SimpleDateFormat("EEEE", Locale.getDefault())

        val date = dateFormat.format(calendar.time)
        val time = timeFormat.format(calendar.time)
        val day = dayFormat.format(calendar.time)

        binding.textdate.text = date
        binding.texttime.text = time
        binding.textday.text = day
    }
}