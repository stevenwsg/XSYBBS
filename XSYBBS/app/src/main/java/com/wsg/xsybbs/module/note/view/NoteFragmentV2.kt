package com.wsg.xsybbs.module.note.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.wsg.base.BaseFragment
import com.wsg.xsybbs.adapter.GlideImageLoader
import com.wsg.xsybbs.base.BaseActivity
import com.wsg.xsybbs.bean.Banne
import com.wsg.xsybbs.bean.Type
import com.wsg.xsybbs.databinding.FragmentNoteV2Binding
import com.wsg.xsybbs.manager.TypeManager
import com.wsg.xsybbs.module.addnote.view.AddNoteActivity
import com.wsg.xsybbs.module.note.vm.NoteViewModel
import com.wsg.xsybbs.module.searchnote.view.SearchNoteActivity

/**
 * Create by wangshengguo on 2021/6/14.
 */
class NoteFragmentV2 : BaseFragment() {

    companion object {
        const val TAG = "NoteFragmentV2"
    }

    private var _binding: FragmentNoteV2Binding? = null
    private val binding get() = _binding!!

    private var viewModel: NoteViewModel? = null
    private var noteVpAdapter: NoteVPAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteV2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initVm()
        initObserver()
        viewModel?.getBannes()
        viewModel?.getTypeList()
    }

    private fun initView() {
        binding.addNote.setOnClickListener {
            startActivity(Intent(activity, AddNoteActivity::class.java))
        }
        binding.searchNote.setOnClickListener {
            startActivity(Intent(activity, SearchNoteActivity::class.java))
        }
    }

    private fun initVm() {
        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
    }

    private fun initObserver() {
        viewModel?.apply {
            banes.observe(viewLifecycleOwner, object : Observer<List<Banne>> {
                override fun onChanged(t: List<Banne>?) {
                    t?.let {
                        binding.banner.setImageLoader(GlideImageLoader())
                        val urls = t.map { it.photo }
                        binding.banner.setImages(urls)
                        binding.banner.start()
                    } ?: let {
                        Log.e(TAG, "bannes is null")
                    }

                }
            })

            typeList.observe(viewLifecycleOwner, object : Observer<List<Type>> {
                override fun onChanged(t: List<Type>?) {
                    t?.let { it ->
                        TypeManager.typeList = it
                        noteVpAdapter =
                            NoteVPAdapter(context as BaseActivity, it.map { it.content })
                        binding.vp2Note.adapter = noteVpAdapter

                        TabLayoutMediator(binding.tabLayoutType, binding.vp2Note) { tab, position ->
                            //  为Tab设置Text
                            tab.text = it.map { it.content }[position]
                        }.attach()
                    } ?: let {
                        Log.e(TAG, "typeList is null")
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}