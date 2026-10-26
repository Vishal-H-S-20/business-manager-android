package com.example.nandivishal;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class    ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> implements Filterable
{
private ArrayList<ExampleItem> mExampleList;
private OnItemClickListener mListener;
private ArrayList<ExampleItem> exampleListFull;

public interface OnItemClickListener{
    void onItemClick(int position);
    void onDeleteClick(int position);
}

public void setOnItemClickListener(OnItemClickListener listener)
{
mListener=listener;
}

    public static class ExampleViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView mImageView;
        public TextView mTextView;
        public TextView  mTextView1;
        public ImageView mDeleteImage;

        public ExampleViewHolder(@NonNull View itemView,OnItemClickListener listener)
        {
            super(itemView);
            mImageView= itemView.findViewById(R.id.image_view);
            mTextView= itemView.findViewById(R.id.line_1);
            mTextView1= itemView.findViewById(R.id.line_2);
            mDeleteImage=itemView.findViewById(R.id.image_delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                      if (listener!=null)
                     {
                         int position=getAdapterPosition();

                         if (position != RecyclerView.NO_POSITION)
                         {
                             listener.onItemClick(position);
                         }
                     }
                }
            });


            mDeleteImage.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v)
                {
                    if (listener!=null)
                    {
                        int position=getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION)
                        {
                            listener.onDeleteClick(position);
                        }
                    }
                    return false;
                }

            });

        }
    }

    public ExampleAdapter(ArrayList<ExampleItem> exampleList)
    {
 this.mExampleList=exampleList;
exampleListFull=new ArrayList<>(exampleList);
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
        ExampleViewHolder evh = new ExampleViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {

        ExampleItem currentItem= mExampleList.get(position);
        holder.mImageView.setImageResource(currentItem.getmImageResource());
        holder.mTextView.setText(currentItem.getmLine1());
        holder.mTextView1.setText(currentItem.getmLine2());

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ExampleItem> filteredList=new ArrayList<>();
            if (constraint==null|| constraint.length()==0)
            {
                filteredList.addAll(exampleListFull);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ExampleItem item : exampleListFull)
                {
                    if (item.getmLine1().toLowerCase().contains(filterPattern))
                    {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values=filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mExampleList.clear();
            mExampleList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
}
