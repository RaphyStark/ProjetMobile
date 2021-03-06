package com.example.projetmobile.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projetmobile.Constants;
import com.example.projetmobile.R;
import com.example.projetmobile.presentation.model.Mark;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
{
    private final List<Mark> values;
    private final OnItemClickListener listener;


    public interface OnItemClickListener
    {
            void onItemClick (Mark item);
    }


    ListAdapter(List<Mark> markList, OnItemClickListener listener)
    {
        this.values = markList;
        this.listener = listener;
    }


    static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView photo;
        TextView txtHeader;
        TextView txtFooter;
        View layout;

        ViewHolder(View v)
        {
            super(v);
            photo = v.findViewById(R.id.icon);
            txtHeader = v.findViewById(R.id.firstLine);
            txtFooter = v.findViewById(R.id.secondLine);
            layout = v;
        }
    }


    @NotNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater;
        inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        final Mark currentMark = values.get(position);
        holder.txtHeader.setText(currentMark.getName());
        holder.txtFooter.setText(currentMark.getFilm());
        String pathName = Constants.BASE_URL2;
        String picName = currentMark.getUrl();
        String allPath = pathName+picName+".jpg";
        Picasso.get()
                    .load(allPath)
                    .into(holder.photo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(currentMark);
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return values.size();
    }

}