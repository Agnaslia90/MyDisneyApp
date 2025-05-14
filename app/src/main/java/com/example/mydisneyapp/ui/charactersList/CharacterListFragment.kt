package com.example.mydisneyapp.ui.charactersList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydisneyapp.databinding.CharacterListFragmentBinding
import com.example.mydisneyapp.ui.charactersList.adapter.CharactersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment: Fragment() {

    private var _binding: CharacterListFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharacterListViewModel by viewModels()
    private lateinit var adapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CharacterListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
        setupListeners()

        viewModel.loadCharactersLocalFirst()

    }

    private fun setupRecyclerView(){
        adapter = CharactersAdapter(emptyList()) { character ->
            val action = character.imageUrl?.let {
                CharacterListFragmentDirections
                    .actionCharacterListFragmentToCharacterDetailFragment(
                        character.id,
                        character.name,
                        character.imageUrl.toString()
                    )
            }
            if (action != null) {
                findNavController().navigate(action)
            }
        }

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@CharacterListFragment.adapter
        }
    }

    private fun setupObservers(){
        viewModel.characters.observe(viewLifecycleOwner, { characters ->
            characters?.let {
                Log.d("CharacterList", "Characters loaded: ${characters.size}")
                adapter.updateList(characters)
            }
        })
    }

    private fun setupListeners(){
        binding.recoverButton.setOnClickListener {
            viewModel.recoverCharacters()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}