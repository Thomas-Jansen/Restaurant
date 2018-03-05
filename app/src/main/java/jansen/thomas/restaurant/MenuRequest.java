package jansen.thomas.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menuItemArrayList);
        void gotMenuError(String message);
    }

    private Context context2;
    private Callback activity2;
    private String categorysearch;

    public MenuRequest(Context context) {
        context2 = context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String errorMessage = error.getMessage();
        activity2.gotMenuError(errorMessage);
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray menuArray = null;
        try {
            menuArray = response.getJSONArray("items");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<MenuItem> menuItemArrayList = new ArrayList<MenuItem>();
        for (int i = 0; i < menuArray.length(); i++) {
            JSONObject menuItemObject = null;
            try {
                menuItemObject = menuArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                if (menuItemObject.getString("category").equals(categorysearch)) {
                    String name = menuItemObject.getString("name");
                    String description = menuItemObject.getString("description");
                    String imageUrl = menuItemObject.getString("image_url");
                    double price = menuItemObject.getDouble("price");
                    String categorie = menuItemObject.getString("category");
                    MenuItem item = new MenuItem(name, description, imageUrl, price, categorie);
                    menuItemArrayList.add(item);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        activity2.gotMenu(menuItemArrayList);
    }

    public void getMenu(Callback activity, String category) {

        activity2 = activity;
        categorysearch = category;
        String url = "https://resto.mprog.nl/menu";

        RequestQueue queue = Volley.newRequestQueue(context2);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
    }
}
