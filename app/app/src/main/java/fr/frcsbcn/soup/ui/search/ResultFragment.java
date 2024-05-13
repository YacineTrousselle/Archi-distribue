package fr.frcsbcn.soup.ui.search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.Arrays;

import fr.frcsbcn.soup.R;
import fr.frcsbcn.soup.databinding.FragmentResultBinding;

public class ResultFragment extends Fragment {
    private SearchViewModel searchViewModel;
    private ListView listView;
    private ProgressBar progressBar;
    private FragmentResultBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        searchViewModel = new ViewModelProvider(requireActivity()).get(SearchViewModel.class);

        binding = FragmentResultBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listView = root.findViewById(R.id.result_list_view);
        progressBar = root.findViewById(R.id.progressBar);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchViewModel = new ViewModelProvider(requireActivity()).get(SearchViewModel.class);

        searchViewModel.getSongs().observe(getViewLifecycleOwner(), songDataList -> {
            Log.d("SOUP", "onViewCreated: " + Arrays.toString(songDataList.stream().map(songData -> songData.title + " " + Arrays.toString(songData.artists)).toArray()));
            listView.setAdapter(new ResultAdapter(getContext(), songDataList, id -> {
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                Navigation
                        .findNavController(view)
                        .navigate(R.id.nav_player, bundle);
            }));
            progressBar.setVisibility(View.GONE);
        });

        searchViewModel.fetchData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    public interface OnListItemClickListener {
        void onItemClick(String id);
    }
}