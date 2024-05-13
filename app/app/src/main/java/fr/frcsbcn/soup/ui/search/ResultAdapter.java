package fr.frcsbcn.soup.ui.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Objects;

import fr.frcsbcn.soup.R;
import fr.frcsbcn.soup.generated.SongData;

public class ResultAdapter extends ArrayAdapter<SongData> {
    private final List<SongData> songs;
    private final ResultFragment.OnListItemClickListener onListItemClickListener;

    public ResultAdapter(Context context, List<SongData> songs, ResultFragment.OnListItemClickListener onListItemClickListener) {
        super(context, 0, Objects.requireNonNull(songs));
        this.onListItemClickListener = onListItemClickListener;
        this.songs = songs;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.result_list_view_element, parent, false);
        }
        TextView title = convertView.findViewById(R.id.title);
        title.setText(songs.get(position).title);
        TextView artists = convertView.findViewById(R.id.artists);
        artists.setText(String.join(", ", songs.get(position).artists));
        ImageButton playButton = convertView.findViewById(R.id.playButton);
        playButton.setOnClickListener(v -> {
            this.onListItemClickListener.onItemClick(songs.get(position).id);
        });

        return convertView;
    }
}
