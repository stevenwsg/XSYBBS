package com.wsg.xsybbs.module.note.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wsg.xsybbs.adapter.NoteAdapterV2
import com.wsg.xsybbs.base.BaseFragment
import com.wsg.xsybbs.databinding.FragmentSubNoteBinding
import com.wsg.xsybbs.module.note.vm.SubNoteViewModel

/**
 * Create by wangshengguo on 2021/6/14.
 */
class SubNoteFragment : BaseFragment() {

    companion object {
        const val TAG = "SubNoteFragment"
    }

    private var _binding: FragmentSubNoteBinding? = null
    private val binding get() = _binding!!

    private var noteType: String? = null
    private var viewModel: SubNoteViewModel? = null
    private var noteAdapter: NoteAdapterV2? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSubNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteType = arguments?.getString(TAG)
        Log.d(TAG, "note type is: $noteType")

        binding.subNoteRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        noteAdapter = NoteAdapterV2()
        binding.subNoteRv.adapter = noteAdapter

        initVm()
        initObserver()
        viewModel?.getNotes(noteType!!)
    }

    private fun initVm() {
        viewModel = ViewModelProvider(this).get(SubNoteViewModel::class.java)
    }

    private fun initObserver() {
        viewModel?.apply {
            notes.observe(viewLifecycleOwner, Observer {
                Log.d(TAG, "size: ${it?.size}")
                noteAdapter?.items = it
                noteAdapter?.notifyDataSetChanged()
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}