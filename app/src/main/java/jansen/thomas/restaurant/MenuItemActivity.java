package jansen.thomas.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

// Show the menuItem. Get the item through intent and set all textViews
// Uses Picasso to load the URL into the imageView
public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        Intent intent = getIntent();
        MenuItem item = (MenuItem) intent.getSerializableExtra("clickeditem");

        String name = item.getName();
        String price = "€ " + String.valueOf(item.getPrice());
        String fotoURL = item.getImageUrl();
        String description = item.getDescription();

        TextView nameView = findViewById(R.id.textViewNameDetail);
        TextView priceView = findViewById(R.id.textViewPriceDetail);
        ImageView fotoView = findViewById(R.id.imageViewFotoDetail);
        TextView descriptionView = findViewById(R.id.textViewDescriptionDetail);

        nameView.setText(name);
        priceView.setText(price);
        Picasso.with(this).load(fotoURL).into(fotoView);
        descriptionView.setText(description);
    }
}
