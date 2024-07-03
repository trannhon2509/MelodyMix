import android.widget.TextView;

public class SongAdapter extends RecylerView.Adapter<SongAdapter.ViewHolder>    {
    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView mTextName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextName = itemView.findViewById(R.id.text_name);
        }
    }}