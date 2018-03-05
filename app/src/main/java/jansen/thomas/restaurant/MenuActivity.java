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

    @Override
    public void gotMenu(ArrayList<MenuItem> menuItemArrayList) {
        MenuItemAdapter adapter = new MenuItemAdapter(this, R.layout.menu_item, menuItemArrayList);
        listViewMenu.setAdapter(adapter);
    }

    @Override
    public void gotMenuError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

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
