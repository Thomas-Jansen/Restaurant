package jansen.thomas.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

// CategoriesActivity implements Callback to receive the JSONresponse on a later time
public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    ListView listViewCategories;

    // Oncreate load listView to show categories and send a request to CategoriesRequest
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        listViewCategories = findViewById(R.id.listViewCategories);
        listViewCategories.setOnItemClickListener(new ListItemClickListener());

        new CategoriesRequest(getApplicationContext()).getCategories(this);
    }

    // When request has returned with a response, fill the listView adapater and show the categories
    @Override
    public void gotCategories(ArrayList<String> categories) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);
        listViewCategories.setAdapter(adapter);
    }

    // When request has returned with an error, show the message with Toast
    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    // OnClickListener to get the category and send it to MenuActivity via intent
    public class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            String category = (String) adapterView.getItemAtPosition(i);
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);
        }
    }
}
