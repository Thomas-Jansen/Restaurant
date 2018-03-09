package jansen.thomas.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback{

    ListView listViewMenu;

    // Get category name from intent and send a JSONrequest to get the ietms with that category
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        new MenuRequest(getApplicationContext()).getMenu(this, category);

        listViewMenu = findViewById(R.id.listViewMenu);
        listViewMenu.setOnItemClickListener(new ListItemClickListener());
    }

    // When menuItemArrayList has returned, make new adapater and set adapter on listView
    @Override
    public void gotMenu(ArrayList<MenuItem> menuItemArrayList) {
        MenuItemAdapter adapter = new MenuItemAdapter(this, R.layout.menu_item, menuItemArrayList);
        listViewMenu.setAdapter(adapter);
    }

    // Show message when received an error message
    @Override
    public void gotMenuError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    // onClickListener for menuItems. Show item details when an item gets clicked
    public class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            MenuItem clickedItem = (MenuItem) adapterView.getItemAtPosition(i);
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("clickeditem", clickedItem);
            startActivity(intent);
        }
    }
}
