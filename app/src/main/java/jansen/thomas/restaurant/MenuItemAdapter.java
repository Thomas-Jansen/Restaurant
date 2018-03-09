package jansen.thomas.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

// An adapter to show the different menuItems
public class MenuItemAdapter extends ArrayAdapter<MenuItem> {

    ArrayList<MenuItem> menuItemArrayList;
    Context context1;

    public MenuItemAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        menuItemArrayList = objects;
        context1 = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent, false);
        }

//      Get the menuItem and fill the listView
        MenuItem item = menuItemArrayList.get(position);

        String name = item.getName();
        String price = "€ " + String.valueOf(item.getPrice());
        String fotoURL = item.getImageUrl();

        TextView nameView = convertView.findViewById(R.id.textViewName);
        TextView priceView = convertView.findViewById(R.id.textViewPrice);
        ImageView fotoView = convertView.findViewById(R.id.imageViewFoto);

        nameView.setText(name);
        priceView.setText(price);
        Picasso.with(context1).load(fotoURL).into(fotoView);

        return convertView;
    }
}
