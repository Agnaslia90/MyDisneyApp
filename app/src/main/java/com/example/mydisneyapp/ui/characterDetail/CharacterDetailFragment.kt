package com.example.mydisneyapp.ui.characterDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.mydisneyapp.databinding.CharacterDetailFragmentBinding
import com.example.mydisneyapp.domain.model.CharacterModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class CharacterDetailFragment: Fragment() {

    private var _binding: CharacterDetailFragmentBinding? = null
    private val binding get()= _binding!!

    private val viewModel: CharacterDetailViewModel by viewModels()

    private val args by lazy { CharacterDetailFragmentArgs.fromBundle(requireArguments())}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CharacterDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindCharacterInfo()
        setupListeners()
    }

    private fun bindCharacterInfo() {
        binding.characterName.text = args.characterName

        if (args.characterImage.isNotEmpty()) {
            Glide.with(requireContext())
                .load(args.characterImage)
                .into(binding.characterImage)
        } else {
            binding.characterImage.setImageResource(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark_normal_background)
        }
    }

    private fun setupListeners() {
        binding.backArrow.setOnClickListener{
            requireActivity().onBackPressed()
        }

        binding.deleteButton.setOnClickListener {
            val characterModel = CharacterModel(
                id = args.characterId,
                name = args.characterName,
                imageUrl = args.characterImage,
            )
            deleteCharacter(characterModel)
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun deleteCharacter(characterModel: CharacterModel){
        viewModel.deleteCharacter(characterModel)
    }
}