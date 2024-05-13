package fr.frcsbcn.soup.ui.search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import fr.frcsbcn.soup.R;
import fr.frcsbcn.soup.databinding.FragmentSearchBinding;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private FragmentSearchBinding binding;
    private EditText editTextSearch;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        searchViewModel = new ViewModelProvider(requireActivity()).get(SearchViewModel.class);

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        editTextSearch = root.findViewById(R.id.editText_search);
        Button buttonSearch = root.findViewById(R.id.button_search);

        buttonSearch.setOnClickListener(view -> {
            searchViewModel.setSearch(editTextSearch.getText().toString());
            Navigation.findNavController(view).navigate(R.id.nav_result);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}