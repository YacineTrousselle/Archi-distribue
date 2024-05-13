package fr.frcsbcn.soup.ui.player;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import fr.frcsbcn.soup.databinding.FragmentPlayerBinding;

public class PlayerFragment extends Fragment {

    String id = null;
    FragmentPlayerBinding binding;

    PlayerViewModel playerViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPlayerBinding.inflate(inflater, container, false);
        playerViewModel = new ViewModelProvider(requireActivity()).get(PlayerViewModel.class);
        View root = binding.getRoot();

        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getString("id");
        }
        Log.d("SOUP", "onCreateView: songId = " + id);
        playerViewModel.initAudioPlayerProxy(id, requireActivity());

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        playerViewModel.clear();
        binding = null;
    }
}